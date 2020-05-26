package com.yzh.learn.reflect.methodtest;

import java.lang.reflect.Method;

/**
 * 一个Method对象包含一个方法的所有信息：
 *
 *      getName()：返回方法名称，例如："getScore"；
 *      getReturnType()：返回方法返回值类型，也是一个Class实例，例如：String.class；
 *      getParameterTypes()：返回方法的参数类型，是一个Class数组，例如：{String.class, int.class}；
 *      getModifiers()：返回方法的修饰符，它是一个int，不同的bit表示不同的含义。
 *
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        String s = "Hello world";
        Method m = String.class.getMethod("substring", int.class);
        // 对Method实例调用invoke就相当于调用该方法，invoke的第一个参数是对象实例，即在哪个实例上调用该方法，后面的可变参数要与方法参数一致，否则将报错
        String r = (String)m.invoke(s, 6);
        System.out.println(r);

        // 如果获取到的Method表示一个静态方法，调用静态方法时，由于无需指定实例对象，所以invoke方法传入的第一个参数永远为null。我们以Integer.parseInt(String)为例：
        Method mi = Integer.class.getMethod("parseInt", String.class);
        // 调用该静态方法并获取结果
        Integer n = (Integer) mi.invoke(null, "12345");
        System.out.println(n);

        // 和Field类似，对于非public方法，我们虽然可以通过Class.getDeclaredMethod()获取该方法实例，但直接对其调用将得到一个IllegalAccessException。为了调用非public方法，我们通过Method.setAccessible(true)允许其调用
        Person02 p = new Person02();
        Method mp = p.getClass().getDeclaredMethod("setName", String.class);
        mp.setAccessible(true);
        mp.invoke(p, "Bob");
        System.out.println(p.getName());
    }
}

class Person02 {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
