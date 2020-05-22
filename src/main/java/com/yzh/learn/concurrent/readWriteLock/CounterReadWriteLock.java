package com.yzh.learn.concurrent.readWriteLock;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 该类通过Lock保证了只有一个线程操作临界区的值，保证了线程安全，而且在只读操作时，是允许多个线程进行操作的，保证了效率
 * 小结：
 *     ReadWriteLock只允许一个线程写入
 *     ReadWriteLock允许多个线程在没有写入时同时读取
 *     ReadWriteLock适合读多写少的场景
 */
public class CounterReadWriteLock {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock rLock = rwLock.readLock();
    private final Lock wLock = rwLock.writeLock();
    private int[] counts = new int[10];

    public void inc(int index) {
        wLock.lock(); // 加写锁
        try {
            counts[index] += 1;
        } finally {
            wLock.unlock(); // 释放写锁
        }
    }

    public int[] get() {
        rLock.lock(); // 加读锁
        try {
            return Arrays.copyOf(counts, counts.length);
        } finally {
            rLock.unlock(); // 释放读锁
        }
    }
}
