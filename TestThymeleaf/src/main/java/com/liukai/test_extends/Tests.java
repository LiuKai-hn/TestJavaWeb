package com.liukai.test_extends;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author：liukai
 * @Date：2023/8/15 15:01
 */
public class Tests {

    public static void main(String[] args) {

        Fruit apple = new Apple();

        Type genericType = apple.getClass().getGenericSuperclass();
        Type[] genericInterfaces = apple.getClass().getGenericInterfaces();

        System.out.println(genericType);

        //ParameterizedType 参数化类型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();

        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument);
        }
    }
}
