package com.yzh.learn.reflect.extendstest;

import java.time.DayOfWeek;


public class Test01 {
    public static void main(String[] args) {

        // Integer的父类类型是Number，Number的父类是Object，Object的父类是null。
        // 除Object外，其他任何非interface的Class都必定存在一个父类类型
        Class i = Integer.class;
        Class n = i.getSuperclass();
        System.out.println(n);

        Class o = n.getSuperclass();
        System.out.println(o);
        System.out.println(o.getSuperclass());

        // getInterfaces()只返回当前类直接实现的接口类型，并不包括其父类实现的接口类型
        Class s = Integer.class;
        Class[] is = s.getInterfaces();
        for (Class interfaceItem : is) {
            System.out.println(interfaceItem);
        }

    }
}
