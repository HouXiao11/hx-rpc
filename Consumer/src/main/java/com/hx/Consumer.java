package com.hx;

import com.hx.proxy.ProxyFactory;

/**
 * ClassName: Consumer
 * Package: com.hx
 * Description:
 *
 * @Author houxiao
 * @Create 2025/2/27 16:32
 * @Version 1.0
 */
public class Consumer {
    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("dilireba123122");
        System.out.println(result);

    }
}
