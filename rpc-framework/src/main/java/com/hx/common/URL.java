package com.hx.common;

// import lombok.AllArgsConstructor;
// import lombok.Data;

import java.io.Serializable;

// @Data
// @AllArgsConstructor
public class URL implements Serializable {
    private String hostname;
    private Integer port;

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
