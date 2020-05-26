package com.yzh.learn.reflect.fieldtest;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 一个Field对象包含了一个字段的所有信息：
 *      getName()：返回字段名称，例如，"name"；
 *      getType()：返回字段类型，也是一个Class实例，例如，String.class；
 *      getModifiers()：返回字段的修饰符，它是一个int，不同的bit表示不同的含义。
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        Field f = String.class.getDeclaredField("value");
        System.out.println(f.getName());
        // class [B 表示byte[]类型，class [C 表示char[]类型
        System.out.println(f.getType());

        int m = f.getModifiers();
        System.out.println(Modifier.isFinal(m));
        System.out.println(Modifier.isPublic(m));
        System.out.println(Modifier.isProtected(m));
        System.out.println(Modifier.isPrivate(m));
        System.out.println(Modifier.isStatic(m));
    }
}
