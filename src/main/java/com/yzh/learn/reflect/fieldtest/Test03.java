package com.yzh.learn.reflect.fieldtest;

import java.lang.reflect.Field;

/**
 * 利用反射拿到字段的一个Field实例只是第一步，我们还可以拿到一个实例对应的该字段的值
 */
public class Test03 {
    public static void main(String[] args) throws Exception {
        Object p = new Person03("xiao Ming");
        Class c = p.getClass();
        Field f = c.getDeclaredField("name");
        // 暴力访问字段值
        f.setAccessible(true);
        Object value = f.get(p);
        System.out.println(value);

        f.set(p, "Xiao Hong");
        value = f.get(p);
        System.out.println(value);
    }
}

class Person03 {
    private String name;

    public Person03(String name) {
        this.name = name;
    }
}

