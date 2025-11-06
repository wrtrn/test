package learning.expressJava.lesson9.part1.predicateFunctionConsumer;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
//        Predicate<Integer> even = x -> (x % 2)==0;
//        System.out.println(even.test(5));
//        System.out.println(even.test(6));

//        Function<String,Integer> lengthChecker = String::length;
//        System.out.println(lengthChecker.apply("Hello"));
//        System.out.println(lengthChecker.apply("Hello1"));

        Consumer<String> stringWriter = System.out::println;
        stringWriter.accept("Hello");
        stringWriter.accept("Hello everyone!");
    }
}
