package com.hx.proxy;

import com.hx.common.Invocation;
import com.hx.common.URL;
import com.hx.loadbalance.Loadbalance;
import com.hx.protocol.HttpClient;
import com.hx.register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ProxyFactory
 * Package: com.hx.proxy
 * Description:
 *
 * @Author houxiao
 * @Create 2025/2/27 18:20
 * @Version 1.0
 */
public class ProxyFactory {
    public static <T> T getProxy(Class interfaceClass){

        // 读取用户配置
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //服务mock
                String mock = System.getProperty("mock");
                if(mock != null && mock.startsWith("return:")){
                    String result = mock.replace("return:","");
                    return result;
                }

                // Invocation对象通过请求的方式发送出去
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(),
                        method.getParameterTypes(), args);

                // 通过HttpClient发送
                HttpClient httpClient = new HttpClient();

                // 服务发现
                List<URL> list = RemoteMapRegister.get(interfaceClass.getName());

                // 服务调用
                String result = null;
                List<URL> invokedUrls = new ArrayList<>();//记录已经调用过的url

                // 服务容错
                int max = 3;
                while(max > 0){

                    // 负载均衡
                    list.remove(invokedUrls);
                    URL url = Loadbalance.random(list);
                    invokedUrls.add(url);

                    try {
                        result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                    } catch (Exception e) {

                        if(max-- != 0) continue;

                        // error-callback=com.hx.HelloServiceErrorCallBack (implements HelloService)
                        // 报错后执行这个类中对应的方法sayHello，可以重写
                        // 服务容错
                        return "报错了";
                    }
                }

                return result;
            }
        });

        return (T) proxyInstance;
    }
}
