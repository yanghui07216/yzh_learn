package com.yzh.learn.collection.stream;

import com.google.common.base.Supplier;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/index.html
 * 总结：
 * Stream 的特性可以归纳为：
 *
 *      不是数据结构
 *      它没有内部存储，它只是用操作管道从 source（数据结构、数组、generator function、IO channel）抓取数据。
 *      它也绝不修改自己所封装的底层数据结构的数据。例如 Stream 的 filter 操作会产生一个不包含被过滤元素的新 Stream，而不是从 source 删除那些元素。
 *      所有 Stream 的操作必须以 lambda 表达式为参数
 *      不支持索引访问
 *      你可以请求第一个元素，但无法请求第二个，第三个，或最后一个。不过请参阅下一项。
 *      很容易生成数组或者 List
 *      惰性化
 *      很多 Stream 操作是向后延迟的，一直到它弄清楚了最后需要多少数据才会开始。
 *      Intermediate 操作永远是惰性化的。
 *      并行能力
 *      当一个 Stream 是并行化的，就不需要再写多线程代码，所有对它的操作会自动并行进行的。
 *      可以是无限的
 *          集合有固定大小，Stream 则不必。limit(n) 和 findFirst() 这类的 short-circuiting 操作可以对无限的 Stream 进行运算并很快完成。
 */
public class StreamTest {
    public static void main(String[] args) throws Exception {

        // 构造流的几种常见方法
        // 1.Individual values
        Stream stream = Stream.of("a", "b", "c");

        // 2.Arrays
        String[] strArray = new String[] {"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);

        // 3.Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

        // 二、数值流的构造
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print);
        System.out.println();
        IntStream.range(1, 5).forEach(System.out::print);
        System.out.println();
        IntStream.rangeClosed(1, 5).forEach(System.out::print);
        System.out.println();
        List<Integer> integers = new ArrayList<Integer>();
        IntStream.rangeClosed(1, 5).forEach(item -> integers.add(item));
        System.out.println(integers);

        // 三、流转换为其他数据结构(一个流只能使用一次)
        // 1.Array
        String[] strArray1 = Stream.of("a", "b", "c").toArray(String[]::new);
        System.out.println(strArray1);
        // 2.Collection
        List<String> list1 = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(list1);
        List<String> list2 = Stream.of("a", "b", "c").collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list2);
        Set set1 = Stream.of("a", "b", "c").collect(Collectors.toSet());
        System.out.println(set1);
        Stack stack1 = Stream.of("a", "b", "c").collect(Collectors.toCollection(Stack::new));
        System.out.println(stack1);
        // 3.String
        String str = Stream.of("a", "b", "c").collect(Collectors.joining()).toString();
        System.out.println(str);

        // 四、流的操作
        // 1.map/flatMap操作
        // 1.1、转换大写
        List<String> output = Stream.of("aa", "bb", "cc", "dd")
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(output);
        // 1.2、平方数(一对一映射flatMap)
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5).stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(nums);
        // 1.3、一对多的映射
        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        Stream<Integer> outputStream = inputStream.flatMap(childList -> childList.stream());
        List<Integer> outputList = outputStream.collect(Collectors.toList());
        System.out.println(outputList);

        // 2.filter:对原始的Stream进行筛选，筛选后的元素留下来生成一个新的Stream
        // 2.1、留下偶数
        List<Integer> evens = Stream.of(1, 2, 3, 4, 5, 6).filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(evens);
        // 2.2 挑选出单词
        BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\test\\reade.txt")));
//        正则表达式中“\d”表示[0-9]的数字，“\d+”表示由[0-9]的数字组成的数字，“\w”表示[A-Z0-9]，“\w+”表示由数字、26个英文字母或者下划线组成的字符串，“\d+.+\d+”表示小数
//        String REGEXP = "\\d+.\\d+|\\w+";
        String REGEXP = "\\W+";
        List<String> readerOutput = reader.lines()
                .flatMap(line -> Stream.of(line.split(REGEXP)))
                .filter(word -> word.length() > 0)
                .collect(Collectors.toList());
        reader.close();
        System.out.println(readerOutput);

        // 3.forEach:forEach方法接收一个Lambda表达式，然后在Stream的每一个元素上执行该表达式
        // forEach是terminal操作，因此他执行后，Stream元素就被消费掉了，无法对一个Stream进行两次terminal运算
        List<Person> roster = new ArrayList<>();
        roster.add(new Person(Person.Sex.MALE, "Bob"));
        roster.add(new Person(Person.Sex.MALE, "Cc"));
        roster.add(new Person(Person.Sex.WOMEN, "Rose"));
        roster.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .forEach(p -> System.out.println(p.getName()));

        // 4.peek对每个元素执行操作并返回一个新的Stream
        List<String> peekList = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println(peekList);

        // 5.findFirst:这是一个 termimal 兼 short-circuiting 操作，它总是返回 Stream 的第一个元素，或者空，
        //   这里比较重点的是它的返回值类型：Optional。这也是一个模仿 Scala 语言中的概念，作为一个容器，它可能含有某值，
        //   或者不包含。使用它的目的是尽可能避免 NullPointerException。
        Optional<String> first = Stream.of("a", "b", "c").findFirst();
        System.out.println(first);
        // 5.1 Optional的两个例子
        //     (Stream 中的 findAny、max/min、reduce 等方法等返回 Optional 值。还有例如 IntStream.average() 返回 OptionalDouble 等等)
        String strA = " abcd", strB = null;
        print(strA);
        print("");
        print(strB);

        System.out.println(getLength(strA));
        System.out.println(getLength(""));
        System.out.println(getLength(strB));

        // 6.reduce:主要作用是把Stream元素结合起来，他提供一个其实值，然后依照运算规则(BinaryOperator)和前面Stream的第一个、第二个、
        //   第n个元素组合。从这个意义上说，字符串拼接，数值的sum,min,max,average都是特殊的reduce.
        //   PS:Stream的sum就相当于:
        //         Integer sum = integers.reduce(0,(a,b) -> a+b);
        //      or Integer sum = integers.reduce(0, Integer::sum);
        // reduce的用例
        // 字符串连接，concat = "ABCDE";
        String concat = Stream.of("A", "B", "C", "D", "E").reduce("", String::concat);
        System.out.println(concat);
        // 求最小值，minValue = ?
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        System.out.println(minValue);
        // 求和，sumValue = ?，有起始值
        int sumValue = IntStream.rangeClosed(1, 5).reduce(0, Integer::sum);
        System.out.println(sumValue);
        // 求和，sumValue = ?，无起始值
        sumValue = Stream.of(1, 2, 3, 4, 5).reduce(Integer::sum).get();
        System.out.println(sumValue);
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F")
                .filter(x -> x.compareTo("Z") > 0)
                .reduce("", String::concat);
        System.out.println(concat);

        // 7.limit/skip:limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素（它是由一个叫 subStream 的方法改名而来）
        // 7.1、limit和skip对运行次数的影响
            // 在short-circuiting 操作 limit 和 skip 的作用下，管道中 map 操作指定的 getName() 方法的执行次数为 limit 所限定的10次，
            // 而最终返回结果在跳过前 3 个元素后只有后面 7 个返回
        List<Student> students = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 100; i ++) {
            students.add(new Student(i, "name" + i, random.nextInt(100)));
        }
        List<String> student2 = students.stream()
                .map(Student::getName)
                .limit(10)
                .skip(3)
                .collect(Collectors.toList());
        System.out.println(student2);

        // 7.2 有一种情况是 limit/skip 无法达到 short-circuiting 目的的，就是把它们放在 Stream 的排序操作后，原因跟 sorted 这个
        // intermediate 操作有关：此时系统并不知道 Stream 排序后的次序如何，所以 sorted 中的操作看上去就像完全没有被 limit 或者 skip 一样

        List<Student> students2 = students.subList(0, 5).stream()
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(students2);

        // 8. sorted:Stream的排序
        // 8.1 优化：排序前进行limit和skip
        Collections.shuffle(students);
        students2 = students.subList(0, 5).stream()
                .limit(2)
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .collect(Collectors.toList());
        System.out.println("students2:" + students2);

        // 9. min/max/distinct
        // 9.1 使用max找出最长一行的长度
        reader = new BufferedReader(new FileReader(new File("D:\\test\\reade.txt")));
        int longest = reader.lines()
                .mapToInt(String::length)
                .max()
                .getAsInt();
        reader.close();
        System.out.println(longest);
        // 9.2 使用distinct找出不重复的单词
        reader = new BufferedReader(new FileReader(new File("D:\\test\\reade.txt")));
        List<String> words = reader.lines()
                .flatMap(line -> Stream.of(line.split(REGEXP)))
                .filter(word -> word.length() > 0)
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        reader.close();
        System.out.println(words);

        // 10. Match:Stream有三个match方法，从语义上说：
        //      allMatch:Stream中全部元素符合传入的predicate,返回true;
        //      anyMatch:Stream中只要有一个元素符合传入的predicate,返回true;
        //      noneMatch:Stream中没有一个元素符合传入的predicate,返回true;
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "name1", 10));
        studentList.add(new Student(2, "name2", 21));
        studentList.add(new Student(3, "name3", 34));
        studentList.add(new Student(4, "name4", 6));
        studentList.add(new Student(5, "name5", 55));
        boolean isAllAdult = studentList.stream()
                .allMatch(p -> p.getAge() > 18);
        System.out.println("All are adult?" + isAllAdult);
        boolean isThereAnyChild = studentList.stream()
                .anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child?" + isThereAnyChild);

        // 五、自己生成流
        // 1. Stream.generate
        // 1.1 生成10个随机数
        Supplier<Integer> randoms = random::nextInt;
        Stream.generate(randoms)
                .limit(10)
                .forEach(System.out::print);
        System.out.println();
        // Another way
        IntStream.generate(() -> (int)(System.nanoTime() % 100))
                .limit(10)
                .forEach(System.out::print);
        System.out.println();

        // 1.2 Stream.generate() 还接受自己实现的 Supplier。例如在构造海量测试数据的时候，用某种自动的规则给每一个变量赋值；
        //     或者依据公式计算 Stream 的每个元素值。这些都是维持状态信息的情形
        Stream.generate(new StudentSupplier())
                .limit(10)
                .forEach(p -> System.out.println(p));

        // 2. Stream.iterate:iterate 跟 reduce 操作很像，接受一个种子值，和一个 UnaryOperator（例如 f）。
        //    然后种子值成为 Stream 的第一个元素，f(seed) 为第二个，f(f(seed)) 第三个，以此类推
        Stream.iterate(0, n -> n + 3)
                .limit(10)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();

        // 六、用Collectors来进行reduction操作
        // 1. groupingBy/partitioningBy
        // 1.1 按照年龄分组
        Map<Integer, List<Student>> studentGroups = Stream.generate(new StudentSupplier())
                .limit(20)
                .collect(Collectors.groupingBy(Student::getAge));
        Iterator it = studentGroups.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<Student>> studentMapItem = (Map.Entry)it.next();
            System.out.println("Age " + studentMapItem.getKey() + " = " + studentMapItem.getValue().size());
        }

        // 1.2 按照未成年人和成年人归组
        Map<Boolean, List<Student>> childrenMap = Stream.generate(new StudentSupplier())
                .limit(20)
                .collect(Collectors.partitioningBy(s -> s.getAge() < 18));
        System.out.println("Children number: " + childrenMap.get(true).size());
        System.out.println("Adult number: " + childrenMap.get(false).size());


    }

    private static class StudentSupplier implements Supplier<Student> {
        private int index = 0;
        private Random random = new Random();

        @Override
        public Student get() {
            return new Student(++ index, "StormTestUser" + index, random.nextInt(20));
        }
    }
    public static void print(String text) {
        Optional.ofNullable(text).ifPresent(System.out::println);
    }

    public static int getLength(String text) {
        return Optional.ofNullable(text).map(String::length).orElse(-1);
    }
}

class Person {
    enum Sex {
        MALE,WOMEN
    }

    private Sex gender;
    private String name;

    public Person(Sex gender, String name) {
        this.gender = gender;
        this.name = name;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Student {
    private int nom;
    private String name;
    private int age;

    public Student(int nom, String name, int age) {
        this.nom = nom;
        this.name = name;
        this.age = age;
    }

    public int getNom() {
        return nom;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nom=" + nom +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}