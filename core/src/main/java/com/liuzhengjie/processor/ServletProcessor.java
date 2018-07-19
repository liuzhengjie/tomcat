package com.liuzhengjie.processor;

import com.liuzhengjie.common.SystemConstants;
import com.liuzhengjie.facade.RequestFacade;
import com.liuzhengjie.facade.ResponseFacade;
import com.liuzhengjie.request.Request;
import com.liuzhengjie.response.Response;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * 简易servlet处理器
 */
public class ServletProcessor {

    /**
     * 处理请求响应
     * @param request
     * @param response
     */
    public void process(Request request, Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(SystemConstants.WEB_ROOT);
            String repository =(new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString() ;
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        }catch (IOException e) {
            System.out.println(e.toString() );
        }

        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest) requestFacade, (ServletResponse) responseFacade);
        }catch (Exception e) {
            System.out.println(e.toString());
        }catch (Throwable e) {
            System.out.println(e.toString());
        }
    }
}
