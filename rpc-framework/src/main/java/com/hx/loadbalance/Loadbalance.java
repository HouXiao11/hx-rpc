package com.hx.loadbalance;

import com.hx.common.URL;

import java.util.List;
import java.util.Random;

/**
 * ClassName: Loadbalance
 * Package: com.hx.loadbalance
 * Description:
 *
 * @Author houxiao
 * @Create 2025/2/27 18:55
 * @Version 1.0
 */
public class Loadbalance {
    public static URL random(List<URL> urls){
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}
