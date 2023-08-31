package com.liukai.myssm.servlets.ioc;

/**
 * @Author：liukai
 * @Date：2023/8/24 15:07
 */
public interface BeanFactory {
    // 根据 id 获取 实体
    Object getBean(String id);
}
