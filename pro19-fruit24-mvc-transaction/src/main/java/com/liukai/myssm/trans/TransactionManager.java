package com.liukai.myssm.trans;

import com.liukai.util.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author：liukai
 * @Date：2023/8/28 15:37
 */
public class TransactionManager {


    // 开启事务
    public static void beginTrans() throws SQLException {
        Connection conn = ConnUtil.getconn();
        conn.setAutoCommit(false);
    }

    // 提交事务
    public static void commit() throws SQLException {

        Connection conn = ConnUtil.getconn();
        conn.commit();
        ConnUtil.closeConn();
    }

    // 回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnUtil.getconn();
        conn.rollback();
        ConnUtil.closeConn();
    }

}
