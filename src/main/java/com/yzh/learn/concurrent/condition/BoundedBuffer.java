package com.yzh.learn.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 链接：https://segmentfault.com/a/1190000015562196
 * 假定有一个缓冲队列，支持put和take方法。如果试图在空队列中执行take操作，则线程将一直阻塞，直到队列中有可用元素；
 * 如果试图在满队列上执行put操作，则线程也将一直阻塞，直到队列不满。
 */
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            // 防止虚假唤醒，Condition的await调用一般会放在一个循环判断中
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++ putptr == items.length)
                putptr = 0;
            ++ count;

            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++ takeptr == items.length)
                takeptr = 0;
            -- count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
