package com.liuzhengjie.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 测试用servlet
 * @author liuzhengjie
 */
public class PrimitiveServlet implements Servlet{

    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("====primitiveServlet>>>>init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.print("====primitiveServlet>>>>service");
        PrintWriter out = servletResponse.getWriter();
        out.println("Hello. Roses are red.");
        out.print("Violets are blue.");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
