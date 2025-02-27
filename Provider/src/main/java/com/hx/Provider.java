package com.hx;

import com.hx.protocol.HttpServer;
import com.hx.register.LocalRegister;

/**
 * ClassName: Provider
 * Package: com.hx
 * Description:
 *
 * @Author houxiao
 * @Create 2025/2/27 16:39
 * @Version 1.0
 */
public class Provider {
    public static void main(String[] args) {

        LocalRegister.regist(HelloService.class.getName(), "1.0",HelloServiceImpl.class);
        LocalRegister.regist(HelloService.class.getName(), "2.0",HelloServiceImpl2.class);

        // 接收网络请求  Netty、Tomcat、Socket Server等
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost",8080);
    }
}
