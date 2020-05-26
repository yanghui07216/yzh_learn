package com.yzh.learn.reflect.classtest;

/**
 * 因为反射的目的是为了获得某个实例的信息。因此，当我们拿到某个Object实例时，我们可以通过反射获取该Object的class信息
 */
public class Test03 {

    public static void main(String[] args) {
        printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class);
        printClassInfo(int.class);
    }

    private static void printClassInfo(Class cls) {
        System.out.println(cls.getName() + "===========================================");

        System.out.println("Class name:" + cls.getName());
        System.out.println("Simple name:" + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name:" + cls.getPackage().getName());
        }
        System.out.println("is interface:" + cls.isInterface());
        System.out.println("is enum:" + cls.isEnum());
        System.out.println("is array:" + cls.isArray());
        System.out.println("is primitive:" + cls.isPrimitive());
    }
}
