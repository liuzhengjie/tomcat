package com.liuzhengjie.connector;

import com.liuzhengjie.processor.HttpProcessor;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * http连接器
 */
public class HttpConnector implements Runnable{

    //结束标志
    boolean stopFlag = false;

    public void run() {
        ServerSocket serverSocket = null;
        int port = 8710;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getLocalHost());
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        while(!stopFlag){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            HttpProcessor httpProcessor = new HttpProcessor();
            httpProcessor.process(socket);
        }
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
}
