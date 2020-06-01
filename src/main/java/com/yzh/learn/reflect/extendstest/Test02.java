package com.yzh.learn.reflect.extendstest;

public class Test02 {

    public static void main(String[] args) {
        // 判断一个实例是否是某个类型时，正常情况下，使用instanceof操作符
        Object n = Integer.valueOf(123);
        System.out.println(n instanceof Double);
        System.out.println(n instanceof Integer);
        System.out.println(n instanceof Number);
        System.out.println(n instanceof java.io.Serializable);

        // 如果是两个Class实例，要判断一个向上转型是否成立，可以调用isAssignableFrom()
        System.out.println(Integer.class.isAssignableFrom(Integer.class));  // true,因为Integer可以赋值给Integer
        System.out.println(Number.class.isAssignableFrom(Integer.class));   // true,因为Integer可以赋值给Number
        System.out.println(Object.class.isAssignableFrom(Integer.class));   // true,因为Integer可以赋值给Object
        System.out.println(Integer.class.isAssignableFrom(Number.class));   // false,因为Number不能赋值给Integer
    }
}
