package com.liukai.test_extends;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author：liukai
 * @Date：2023/8/15 14:59
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Fruit<T,V> {

    private String name;
    private Double price;

}
