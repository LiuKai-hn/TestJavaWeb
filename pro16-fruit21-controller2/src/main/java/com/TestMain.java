package com;

/**
 * @Author：liukai
 * @Date：2023/8/24 10:44
 */
public class TestMain {
    public static void main(String[] args) {
        Object[] parameterValues = new Object[4];
        parameterValues[0]=null;
        parameterValues[1]=1;
        parameterValues[2]=null;
        parameterValues[3]=2;

        for (Object parameterValue : parameterValues) {
            System.out.println(parameterValue);
        }
    }
}
