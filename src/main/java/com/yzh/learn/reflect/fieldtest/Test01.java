package com.yzh.learn.reflect.fieldtest;

/**
 * Field getField(name)：根据字段名获取某个public的field（包括父类）
 * Field getDeclaredField(name)：根据字段名获取当前类的某个field（不包括父类）
 * Field[] getFields()：获取所有public的field（包括父类）
 * Field[] getDeclaredFields()：获取当前类的所有field（不包括父类）
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public字段"score"
        System.out.println(stdClass.getField("score"));
        // 获取继承的public字段"name"
        System.out.println(stdClass.getField("name"));
        // 获取private字段"grade"
        System.out.println(stdClass.getDeclaredField("grade"));
    }
}

class Person {
    public String name;
}

class Student extends Person {
    public int score;
    public int grade;
}