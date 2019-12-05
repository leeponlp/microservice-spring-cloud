//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.suxiaocheng.framework.xss;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> parameterMap;
    private final byte[] body; //用于保存读取body中数据


//    public XssHttpServletRequestWrapper(HttpServletRequest request) {
//        super(request);
//    }

    public XssHttpServletRequestWrapper(HttpServletRequest request) throws IOException{
        super(request);
        parameterMap = request.getParameterMap();
        body = StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return XssUtils.stripXSS(value);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return XssUtils.stripXSS(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return super.getParameterValues(name);
        } else {
            int length = values.length;
            String[] escapseValues = new String[length];

            for (int i = 0; i < length; ++i) {
                escapseValues[i] = XssUtils.stripXSS(values[i]);
            }

            return escapseValues;
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isReady() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void setReadListener(ReadListener arg0) {
                // TODO Auto-generated method stub

            }
        };
    }

    public final boolean checkParameter() {
        Map<String, String[]> submitParams = new HashMap(parameterMap);
        Set<String> submitNames = submitParams.keySet();
        for (String submitName : submitNames) {
            Object submitValues = submitParams.get(submitName);
            if ((submitValues instanceof String)) {
                if (XssUtils.isContainXSS((String) submitValues)) {
                    return true;
                }
            } else if ((submitValues instanceof String[])) {
                for (String submitValue : (String[])submitValues){
                    if (XssUtils.isContainXSS(submitValue)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public  boolean checkHeader(String validateHeaderKey) {
        Enumeration<String> names = super.getHeaderNames();
        if (StringUtils.isNotBlank(validateHeaderKey)) {
            String[] split = validateHeaderKey.split(",");
            for(int i = 0;i<split.length;i++) {
                String value = super.getHeader(split[i]);
                if (XssUtils.isContainXSS(value)) {
                    return true;
                }
            }
        }else{
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                String value = super.getHeader(name);
                if (XssUtils.isContainXSS(value)) {
                    return true;
                }
            }

        }

        return false;
    }


}
