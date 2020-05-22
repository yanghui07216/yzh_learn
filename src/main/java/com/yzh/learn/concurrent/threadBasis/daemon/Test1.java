package com.yzh.learn.concurrent.threadBasis.daemon;

/**
 * 守护线程是指为其他线程服务的线程，在JVM中，所有非守护线程都执行完毕后，无论有没有守护线程，虚拟机都会自动退出
 * 因此，JVM退出时，不必关心守护线程是否已结束
 */
public class Test1 {

    public static void main(String[] args) {
        Thread t = new MyThread();
        // 如果注释下一行，观察MyThread的执行情况
        t.setDaemon(true);
        t.start();
        System.out.println("main: wait 3 sec...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        System.out.println("main: end");
    }
}

class MyThread extends Thread {
    public void run() {
        for (;;) {
            System.out.println("Thread-1:running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
    }
}
