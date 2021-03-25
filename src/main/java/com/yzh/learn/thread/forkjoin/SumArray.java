package com.yzh.learn.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 分段累加
 */
public class SumArray {

    static class SumTask extends RecursiveTask<Integer> {
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }


        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                int count = 0;
                for (int i = fromIndex; i < toIndex; i ++) {
                    try {
                        Thread.currentThread().sleep(1);
                        count += src[i];
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return count;
            } else {
                int mid = (fromIndex + toIndex) / 2;
                SumTask left = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();

            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();

        SumTask innerFind = new SumTask(src, 0, src.length - 1);
        long start = System.currentTimeMillis();

        pool.invoke(innerFind); // 同步调用
        System.out.println("Task is Running......");

        System.out.println("The count is " + innerFind.join() + " spend time:" + (System.currentTimeMillis() - start) + "ms.");
    }
}
