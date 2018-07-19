package com.liuzhengjie.server;

import com.liuzhengjie.common.SystemConstants;
import com.liuzhengjie.processor.ServletProcessor;
import com.liuzhengjie.processor.StaticResoureProcessor;
import com.liuzhengjie.request.Request;
import com.liuzhengjie.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器！
 * @author liuzhengjie
 */
public class HttpServer {

    //服务器存放基本资源的路径
    public static final String WEB_ROOT = SystemConstants.WEB_ROOT;

    //停止服务器的命令
    private static final String SHUTDOWN_COMMAND = SystemConstants.SHUTDOWN_COMMAND;

    //标识
    private boolean shutdown = false;

    /**
     * 服务器启动入口
     * @param args
     */
    public static void main(String[] args){
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    /**
     * 建立socket连接
     */
    public void await(){
        ServerSocket serverSocket = null;
        int port = 8710;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getLocalHost());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                Request request = new Request(inputStream);
                request.parse();
                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();

                // 如果是servlet请求，用servlet处理器处理，否则用静态资源处理器处理
                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                }else{
                    StaticResoureProcessor processor = new StaticResoureProcessor();
                    processor.process(request, response);
                }

                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
