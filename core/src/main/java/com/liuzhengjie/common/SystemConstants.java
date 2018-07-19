package com.liuzhengjie.common;

import java.io.File;

public class SystemConstants {

    //服务器存放基本资源的路径
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    //停止服务器的命令
    public static final String SHUTDOWN_COMMAND = "/bye";


}
