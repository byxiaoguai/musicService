package org.system.vip.tools;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘政
 * @date 2020-10-28 16:24
 */
public class IPUtil {
    /**
     * 获得请求IP地址
     *
     * @return
     */
    public static String getIpAddr() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (request == null) {
            return null;
        }
        String header = request.getHeader("X-Forwarded-For");

        if (header == null || header.length() == 0 || "unknown".equalsIgnoreCase(header)) {
            header = request.getHeader("Proxy-Client-IP");

        }
        if (header == null || header.length() == 0 || "unknown".equalsIgnoreCase(header)) {
            header = request.getHeader("WL-Proxy-Client-IP");
        }

        if (header == null || header.length() == 0 || "unknown".equalsIgnoreCase(header)) {
            header = request.getRemoteAddr();
        }
        return header;
    }
}
