package com.yzh.learn.reflect.dynamicproxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 在运行期动态创建一个interface实例的方法如下：
 *      定义一个InvocationHandler实例，它负责实现接口的方法调用；
 *      通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：
 *      使用的ClassLoader，通常就是接口类的ClassLoader；
 *      需要实现的接口数组，至少需要传入一个接口进去；
 *      用来处理接口方法调用的InvocationHandler实例。
 *      将返回的Object强制转型为接口。
 */
public class Test01 {
    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };

        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, handler);
        hello.morning("Bob");
    }
}

interface Hello {
    void morning(String name);
}