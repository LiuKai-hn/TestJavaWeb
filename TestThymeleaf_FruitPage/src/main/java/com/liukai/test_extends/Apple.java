package com.liukai.test_extends;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author：liukai
 * @Date：2023/8/15 15:00
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Apple extends Fruit<Peer,Cat>{
    private String remark;
}
