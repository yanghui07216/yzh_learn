package com.yzh.learn.concurrent.readWriteLock;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 该类通过Lock保证了只有一个线程操作临界区的值，保证了线程安全
 * 缺点：在只读操作时，是允许多个线程同时操作调用的，但这个程序却不满足，所以需要对其进行改进
 */
public class CounterLock {
    private final Lock lock = new ReentrantLock();
    private int[] counts = new int[10];

    public void inc(int index) {
        lock.lock();
        try {
            counts[index] += 1;
        } finally {
            lock.unlock();
        }
    }

    public int[] get() {
        lock.lock();
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            lock.unlock();
        }
    }
}
