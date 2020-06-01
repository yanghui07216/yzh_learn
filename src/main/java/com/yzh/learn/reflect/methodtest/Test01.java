package com.yzh.learn.reflect.methodtest;

import java.util.List;

/**
 * 通过Class实例获取所有Method信息。Class类提供了以下几个方法来获取Method：
 *
 *      Method getMethod(name, Class...)：获取某个public的Method（包括父类）
 *      Method getDeclaredMethod(name, Class...)：获取当前类的某个Method（不包括父类）
 *      Method[] getMethods()：获取所有public的Method（包括父类）
 *      Method[] getDeclaredMethods()：获取当前类的所有Method（不包括父类）
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public方法getScore，参数为String
        System.out.println(stdClass.getMethod("getScore", String.class));
        // 获取继承的public方法getName，无参数
        System.out.println(stdClass.getMethod("getName"));
        // 获取private方法getGrade，参数为int
        System.out.println(stdClass.getMethod("getGrade", int.class));
    }
}

class Person {
    public String getName() {
        return "Person";
    }
}

class Student extends Person {
    public int getScore(String type) {
        return 99;
    }

    public int getGrade(int year) {
        return 1;
    }
}