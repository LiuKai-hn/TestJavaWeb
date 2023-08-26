package com.liukai.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author：liukai
 * @Date：2023/8/15 14:21
 */


public class GetDataConfigMap {


    public static Properties props;
    static {
        props = new Properties();
        try {
            props.load(GetDataConfigMap.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
