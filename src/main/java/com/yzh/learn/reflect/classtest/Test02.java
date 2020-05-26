package com.yzh.learn.reflect.classtest;

/**
 * 用instanceof不但匹配指定类型，还匹配指定类型的子类。而用==判断class实例可以精确地判断数据类型，但不能作子类型比较。
 *
 * 通常情况下，我们应该用instanceof判断数据类型，因为面向抽象编程的时候，我们不关心具体的子类型。只有在需要精确判断一个类型是不是某个class的时候，我们才使用==判断class实例。
 */
public class Test02 {

    public static void main(String[] args) {
        Integer n = new Integer(123);

        // true，因为n是Integer类型
        System.out.println(n instanceof Integer);
        // true，因为n是Number类型的子类
        System.out.println(n instanceof Number);

        // true，因为n.getClass()返回Integer.class
        System.out.println(n.getClass() == Integer.class);
        // false，因为Integer.class!=Number.class
//        System.out.println(n.getClass() == Number.class);
    }
}
