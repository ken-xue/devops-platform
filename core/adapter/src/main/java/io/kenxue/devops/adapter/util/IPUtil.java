package io.kenxue.devops.adapter.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 麦奇
 * @Date: 21-11-20 下午10:36
 */
@Slf4j
public class IPUtil {
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("X-Forwarded-For");
            if (ipAddress != null) {
                if (!ipAddress.isEmpty() && !"unKnown".equalsIgnoreCase(ipAddress)) {
                    int index = ipAddress.indexOf(",");
                    if (index != -1) {
                        return ipAddress.substring(0, index);
                    } else {
                        return ipAddress;
                    }
                }
            }
            ipAddress = request.getHeader("X-Real-IP");
            if (ipAddress != null) {
                if (!ipAddress.isEmpty() && !"unKnown".equalsIgnoreCase(ipAddress)) {
                    return ipAddress;
                }
            }
            ipAddress = request.getHeader("Proxy-Client-IP");
            if (ipAddress != null) {
                if (!ipAddress.isEmpty() && !"unKnown".equalsIgnoreCase(ipAddress)) {
                    return ipAddress;
                }
            }
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
            if (ipAddress != null) {
                if (!ipAddress.isEmpty() && !"unKnown".equalsIgnoreCase(ipAddress)) {
                    return ipAddress;
                }
            }
            ipAddress = request.getRemoteAddr();
            return ipAddress.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ipAddress;
        } catch (Exception e) {
            log.error("parse ip address error : {}",e.getMessage());
            ipAddress = "parse ip address error";
        }
        return ipAddress;
    }
}
