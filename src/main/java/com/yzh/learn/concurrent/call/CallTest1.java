package com.yzh.learn.concurrent.call;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class CallTest1 {

    // 创建一个线程池
    private static ExecutorService threadPool = new ThreadPoolExecutor(
            4,
            Runtime.getRuntime().availableProcessors() * 2,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10000000; i ++) {
            list.add(i);
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<Long> itemList = Lists.newArrayList();
        List<Future<Long>> futureList = Lists.newArrayList();

        for (List<Integer> items : Lists.partition(list, 5000)) {
            futureList.add(threadPool.submit(new CallWorker(items)));
        }

        for (Future<Long> futureItem : futureList) {
            itemList.add(futureItem.get());
        }

        stopWatch.stop();
        System.out.println("total cost time:" + (stopWatch.getTime() / 1000));

        itemList.forEach(item -> System.out.print(item + ","));
    }

    public static class CallWorker implements Callable {

        private List<Integer> items;

        public CallWorker(List<Integer> items) {
            this.items = items;
        }

        @Override
        public Object call() throws Exception {
            Long sum = 0L;
            for (Integer item : items) {
                sum += item;
            }
            return sum;
        }
    }
}
