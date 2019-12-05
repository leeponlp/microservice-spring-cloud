//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.suxiaocheng.framework.xss;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class XssFilter implements Filter {

    private String validateHeaderKey ;

    public XssFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        validateHeaderKey = filterConfig.getInitParameter("validateHeaderKey");
        log.debug("(XssFilter) initialize validateHeaderKey is {}",validateHeaderKey);
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String method = "GET";
        String param = "";
        XssHttpServletRequestWrapper xssRequest = null;
        if (request instanceof HttpServletRequest) {
            method = ((HttpServletRequest) request).getMethod();
            xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if ("POST".equalsIgnoreCase(method)) {
            param = this.getBodyString(xssRequest.getReader());
            if(StringUtils.isNotBlank(param)){
                if (XssUtils.isContainXSS(param)) {
                    writeResponse(response);
                    return;
                }

            }
        }

        if (xssRequest.checkParameter()) {
            writeResponse(response);
            return;
        }
        if (xssRequest.checkHeader(validateHeaderKey)) {
            writeResponse(response);
            return;
        }



        chain.doFilter(xssRequest, response);

    }

    /***
     * 将错误代码吐出去
     * @param response
     * @throws IOException
     */
    private void writeResponse(ServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        ResultBase result = new ResultBase();
//        result.setCode("10000");
//        out.write(JSONObject.toJSONString(result));
    }

    @Override
    public void destroy() {
        log.debug("(XssFilter) destroy");
    }




    /***
     * 获取request请求body中参数
     * @param br
     * @return
     */
    public  String getBodyString(BufferedReader br) {
        String inputLine;
        StringBuilder str = new StringBuilder("");
        try {
            while ((inputLine = br.readLine()) != null) {
                str.append(inputLine);
            }
            br.close();
        } catch (IOException e) {
            log.error("getBodyString IOException",e);
        }
        return str.toString();

    }
}
