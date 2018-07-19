package com.liuzhengjie.processor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {

    public void process(Socket socket){
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
