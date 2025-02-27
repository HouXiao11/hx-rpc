package com.hx;

import com.hx.HelloService;

/**
 * ClassName: HelloServiceImpl
 * Package: com.hx
 * Description:
 *
 * @Author houxiao
 * @Create 2025/2/27 16:28
 * @Version 1.0
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello: " + name;
    }
}
