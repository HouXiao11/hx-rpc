package com.hx;

/**
 * ClassName: HelloServiceImpl
 * Package: com.hx
 * Description:
 *
 * @Author houxiao
 * @Create 2025/2/27 16:28
 * @Version 1.0
 */
public class HelloServiceImpl2 implements HelloService{
    @Override
    public String sayHello(String name) {
        return "hello: " + name;
    }
}
