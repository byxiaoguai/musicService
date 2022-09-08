package org.system.vip.dto;

import java.io.Serializable;

/**
 * @Author lz
 * @Date 2022/3/12 11:02
 * @Version 1.0
 */

public class Data implements Serializable {

    private String ip;
    private int port;
    private String expire_time;
    private String city;
    private String isp;

    public String getIp() {
        return ip;
    }

    public Data setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public int getPort() {
        return port;
    }

    public Data setPort(int port) {
        this.port = port;
        return this;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public Data setExpire_time(String expire_time) {
        this.expire_time = expire_time;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Data setCity(String city) {
        this.city = city;
        return this;
    }

    public String getIsp() {
        return isp;
    }

    public Data setIsp(String isp) {
        this.isp = isp;
        return this;
    }
}
