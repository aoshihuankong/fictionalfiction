package com.huankong.fictionalfiction.bean;

import org.springframework.context.annotation.Bean;

import java.util.Objects;


public class IPProxy {
    private String IPAddress;
    private String IPPort;
    private String IPType;
    private String IPSpeed;

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getIPPort() {
        return IPPort;
    }

    public void setIPPort(String IPPort) {
        this.IPPort = IPPort;
    }

    public String getIPType() {
        return IPType;
    }

    public void setIPType(String IPType) {
        this.IPType = IPType;
    }

    public String getIPSpeed() {
        return IPSpeed;
    }

    public void setIPSpeed(String IPSpeed) {
        this.IPSpeed = IPSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IPProxy ipProxy = (IPProxy) o;
        return Objects.equals(IPAddress, ipProxy.IPAddress) &&
                Objects.equals(IPPort, ipProxy.IPPort) &&
                Objects.equals(IPType, ipProxy.IPType) &&
                Objects.equals(IPSpeed, ipProxy.IPSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IPAddress, IPPort, IPType, IPSpeed);
    }
}
