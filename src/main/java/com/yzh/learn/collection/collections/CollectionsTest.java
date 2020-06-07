package com.yzh.learn.collection.collections;

import java.util.*;
import java.util.stream.IntStream;

public class CollectionsTest {
    public static void main(String[] args) {
        
        // Collections创建空集合，其是不可变集合，无法向其中添加或删除元素
        List<String> list1 = Collections.emptyList();
        Map<Object, Object> map1 = Collections.emptyMap();
        Set<Object> set1 = Collections.emptySet();

        // 创建单元素集合，其是不可变集合，无法向其中添加或删除元素
        List<String> sList1 = Collections.singletonList("apple");
        Map<String, String> sMap1 = Collections.singletonMap("apple", "red");
        Set<String> sSet1 = Collections.singleton("apple");

        // 排序
        List<String> sortList = new ArrayList<>(Arrays.asList("apple", "pear", "orange"));
        Collections.sort(sortList);
        System.out.println(sortList);

        // 洗牌
        List<Integer> list = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(item -> list.add(item));
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);

        // 不可变集合：这种封装实际上是通过创建一个代理对象，拦截掉所有修改方法实现的
            // 封装成不可变List：List<T> unmodifiableList(List<? extends T> list)
            // 封装成不可变Set：Set<T> unmodifiableSet(Set<? extends T> set)
            // 封装成不可变Map：Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m)

        // 线程安全集合
            // 变为线程安全的List：List<T> synchronizedList(List<T> list)
            // 变为线程安全的Set：Set<T> synchronizedSet(Set<T> s)
            // 变为线程安全的Map：Map<K,V> synchronizedMap(Map<K,V> m)


//        593420612711442398	5000265461251000794
        System.out.println("593420612711442398".length());
        System.out.println("5000265461251000794".length());

//        有：bizOrderId、buyerId、outOrderId、sellerId、status(OrderStatus)、
//          payTime、

//        无：bizOrderIdStr、businessType、subBusinessType、orderChannel、OrderChannelInfo、
//          tGmtCreate、FinishTime、

    }
}
