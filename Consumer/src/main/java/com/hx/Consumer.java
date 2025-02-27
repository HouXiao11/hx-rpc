package com.hx;

import com.hx.common.Invocation;
import com.hx.protocol.HttpClient;

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
        // HelloService helloService = ?;
        // String result = helloService.sayHello("dilireba");
        // System.out.println(result);

        // Invocation对象通过请求的方式发送出去
        Invocation invocation = new Invocation(HelloService.class.getName(),"sayHello",
                new Class[]{String.class},new Object[]{"dilireba"});

        // 通过HttpClient发送
        HttpClient httpClient = new HttpClient();
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println(result);
    }
}
