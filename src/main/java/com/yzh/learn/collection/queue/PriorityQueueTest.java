package com.yzh.learn.collection.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * PriorityQueue实现了一个优先队列：从队首获取元素时，总是获取优先级最高的元素。
 *
 * PriorityQueue默认按元素比较的顺序排序（必须实现Comparable接口），也可以通过Comparator自定义排序算法（元素就不必实现Comparable接口）。
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        Queue<User> q = new PriorityQueue<>(new UserComparator());
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));

        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
    }
}

class User {
    public final String name;
    public final String number;

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}

class UserComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        if (o1.number.charAt(0) == o2.number.charAt(0)) {
            // 如果两人的号都是A开头或者都是V开头，比较号的大小
            return o1.number.compareTo(o2.number);
        }
        if (o1.number.charAt(0) == 'V') {
            // o1的号码是V开头，优先级高
            return -1;
        } else {
            return 1;
        }
    }
}