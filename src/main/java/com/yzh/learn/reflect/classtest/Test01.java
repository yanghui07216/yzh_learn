package com.yzh.learn.reflect.classtest;

/**
 * 由于JVM为每个加载的class创建了对应的Class实例，并在实例中保存了该class的所有信息，包括类名、包名、父类、实现的接口、所有方法、字段等，因此，如果获取了某个Class实例，我们就可以通过这个Class实例获取到该实例对应的class的所有信息。
 */
public class Test01 {

    public static void main(String[] args) throws ClassNotFoundException {

        // 因为Class实例在JVM中是唯一的，所以，下面方法获取到的Class实例是同一个实例，通过==比较是相同的
        Class cls1 = String.class;

        String s = "Hello";
        Class cls2 = s.getClass();

        Class cls3 = Class.forName("java.lang.String");

        System.out.println(cls1 == cls2);
        System.out.println(cls1 == cls3);
    }
}
