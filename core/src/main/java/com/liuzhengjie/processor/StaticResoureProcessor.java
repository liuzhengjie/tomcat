package com.liuzhengjie.processor;

import com.liuzhengjie.request.Request;
import com.liuzhengjie.response.Response;

/**
 * 静态资源处理器
 * @author liuzhengjie
 */
public class StaticResoureProcessor {

    public void process(Request request, Response response){
        try{
            response.sendStaticResource();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
