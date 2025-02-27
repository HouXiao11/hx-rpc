package com.hx.proxy;

import com.hx.common.Invocation;
import com.hx.common.URL;
import com.hx.loadbalance.Loadbalance;
import com.hx.protocol.HttpClient;
import com.hx.register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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

                // Invocation对象通过请求的方式发送出去
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(),
                        method.getParameterTypes(), args);

                // 通过HttpClient发送
                HttpClient httpClient = new HttpClient();

                // 服务发现
                List<URL> list = RemoteMapRegister.get(interfaceClass.getName());

                // 负载均衡
                URL url = Loadbalance.random(list);

                // 服务调用
                String result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                return result;
            }
        });

        return (T) proxyInstance;
    }
}
