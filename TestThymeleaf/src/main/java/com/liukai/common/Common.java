package com.liukai.common;

import com.liukai.util.GetDataConfigMap;

import java.util.Properties;

/**
 * @Author：liukai
 * @Date：2023/8/15 14:45
 */
public class Common {

    // 从配置文件中读取配置
    private static final Properties props = GetDataConfigMap.props;

    public static final String JDBC_DRIVER = props.getProperty("jdbc.driverClassName");
    public static final String JDBC_URL = props.getProperty("jdbc.url");
    public static final String JDBC_USER = props.getProperty("jdbc.username");
    public static final String JDBC_PWD = props.getProperty("jdbc.pwd");




}
