package com.yzh.learn.reflect.methodtest;

import java.lang.reflect.Method;

/**
 * 多态
 *      问：我们来考察这样一种情况：一个Person类定义了hello()方法，并且它的子类Student也覆写了hello()方法，那么，从Person.class获取的Method，作用于Student实例时，调用的方法到底是哪个?
 *      答：运行代码，发现打印出的是Student:hello，因此，使用反射调用方法时，仍然遵循多态原则：即总是调用实际类型的覆写方法（如果存在）
 */
public class Test03 {
    public static void main(String[] args) throws Exception {
        Method h = Person03.class.getMethod("hello");
        h.invoke(new Student03()); // 相当于：Person p = new Student(); p.hello();
    }
}

class Person03 {
    public void hello() {
        System.out.println("Person:hello");
    }
}

class Student03 extends Person03 {
    @Override
    public void hello() {
        System.out.println("Student:hello");
    }
}