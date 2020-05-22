package com.yzh.learn.concurrent.threadBasis.interrupt;

/**
 * 另一个常用的中断线程的方法是设置标志位。我们通常会用一个running标志位来标识线程是否应该继续运行，在外部线程中，
 * 通过把HelloThread.running置为false，就可以让线程结束
 *
 * 注意到HelloThread的标志位boolean running是一个线程间共享的变量。线程间共享变量需要使用volatile关键字标记，确保每个线程都能读取到更新后的变量值。
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        HelloThread3 t = new HelloThread3();
        t.start();
        Thread.sleep(1);
        t.running = false; // 标志位设置为false
    }
}

class HelloThread3 extends Thread {
    public volatile boolean running = true;
    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}