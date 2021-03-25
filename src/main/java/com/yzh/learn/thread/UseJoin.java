package com.yzh.learn.thread;

/**
 * 使用Join
 */
public class UseJoin {

    static class JumpQueue implements Runnable {

        private Thread thread;

        public JumpQueue(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminted.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i ++) {
            // 将主线程插入到i=0的线程的前面，i=0的线程插入到i=1的线程的前面
            Thread thread = new Thread(new JumpQueue(previous), String.valueOf(i));

            System.out.println(previous.getName() + " jump a queue the thread:" + thread.getName());
            thread.start();
            previous = thread;
        }

        Thread.currentThread().sleep(2000);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }
}
