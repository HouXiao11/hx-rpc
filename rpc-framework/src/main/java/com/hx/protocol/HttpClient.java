package com.hx.protocol;

// import com.alibaba.fastjson.JSONObject;
import com.hx.common.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;

public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) throws IOException {

        // 读取用户的配置 发送http请求
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            //配置
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream inputStream = httpURLConnection.getInputStream();//阻塞式获取，一定要等结果返回，才会继续执行
            String result = IOUtils.toString(inputStream);
            return result;
        } catch (MalformedURLException e) {

        } catch (IOException e) {
            throw e;
        }

        return null;
    }
}
