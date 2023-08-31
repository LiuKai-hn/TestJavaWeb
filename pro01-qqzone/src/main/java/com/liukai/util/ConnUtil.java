package com.liukai.util;


import com.liukai.myssm.common.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author：liukai
 * @Date：2023/8/28 15:41
 */
public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal =new ThreadLocal<>();

    private static Connection createConn(){

        try {
            //1.加载驱动
            Class.forName(Common.JDBC_DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(Common.JDBC_URL,
                    Common.JDBC_USER,
                    Common.JDBC_PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static Connection getconn(){
        Connection conn = threadLocal.get();
        if(null == conn){
            conn= createConn();
            threadLocal.set(conn);
        }

        return threadLocal.get();
    }

    public static void closeConn(){
        Connection conn = threadLocal.get();
        if(null == conn){
            return;
        }

        threadLocal.set(null);
    }


}
