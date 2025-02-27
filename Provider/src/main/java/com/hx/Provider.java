package com.hx;

import com.hx.common.URL;
import com.hx.protocol.HttpServer;
import com.hx.register.LocalRegister;
import com.hx.register.RemoteMapRegister;

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

        // 启动 接收网络请求
        // Netty、Tomcat、Jetty、SocketServer等
        // 读取配置
        // Spring --> Spring容器 --> 执行：简化main函数，启动Spring

        // 本地注册
        LocalRegister.regist(HelloService.class.getName(), "1.0", HelloServiceImpl.class);
        LocalRegister.regist(HelloService.class.getName(), "2.0", HelloServiceImpl2.class);

        // 注册中心注册 服务注册
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.regist(HelloService.class.getName(),url);

        // Tomcat
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());
    }
}
