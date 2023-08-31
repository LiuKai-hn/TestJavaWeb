package com.liukai.myssm.exception;

/**
 * @Author：liukai
 * @Date：2023/8/28 17:35
 */
public class DAOException extends RuntimeException{

    public DAOException(String msg){
        super(msg);
    }
}
