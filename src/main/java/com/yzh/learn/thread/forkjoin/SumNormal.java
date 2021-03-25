package com.yzh.learn.thread.forkjoin;

/**
 * 普通单线程运行
 */
public class SumNormal {
    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        int[] src = MakeArray.makeArray();

        long start = System.currentTimeMillis();
        for (int i = 0; i < src.length; i ++) {
            Thread.currentThread().sleep(1);
            count += src[i];
        }
        System.out.println("The count is " + count + " spend time: " + (System.currentTimeMillis() - start) + "ms");
    }
}
