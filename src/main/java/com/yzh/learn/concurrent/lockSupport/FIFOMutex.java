package com.yzh.learn.concurrent.lockSupport;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 链接：https://segmentfault.com/a/1190000015562456
 * 现在需要实现一种FIFO类型的独占锁，可以把这种锁看成是ReentrantLock的公平锁简单版本，且是不可重入的，
 * 就是说当一个线程获得锁后，其它等待线程以FIFO的调度方式等待获取锁。
 *
 * 上述FIFOMutex 类的实现中，当判断锁已被占用时，会调用LockSupport.park(this)方法，将当前调用线程阻塞；当使用完锁时，
 * 会调用LockSupport.unpark(waiters.peek())方法将等待队列中的队首线程唤醒。
 */
public class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
        Thread current = Thread.currentThread();
        waiters.add(current);

        // 如果当前线程不在队首，或锁已被占用，则当前线程阻塞
        // NOTE：这个判断的意图其实就是：锁必须由队首元素拿到
        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
        }
        waiters.remove(); // 删除队首元素
    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }

    public static void main(String[] args) throws InterruptedException {
        FIFOMutex mutex = new FIFOMutex();
        MyThread a1 = new MyThread("a1", mutex);
        MyThread a2 = new MyThread("a2", mutex);
        MyThread a3 = new MyThread("a3", mutex);

        a1.start();
        a2.start();
        a3.start();

        a1.join();
        a2.join();
        a3.join();

        assert MyThread.count == 300;
        System.out.println("Finished");
    }
}

class MyThread extends Thread {
    private String name;
    private FIFOMutex mutex;
    public static int count;

    public MyThread(String name, FIFOMutex mutex) {
        this.name = name;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i ++) {
            mutex.lock();
            count ++;
            System.out.println("name:" + name + " count:" + count);
            mutex.unlock();
        }
    }
}
