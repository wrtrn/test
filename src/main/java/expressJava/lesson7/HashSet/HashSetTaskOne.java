package expressJava.lesson7.HashSet;

import java.util.HashSet;

public class HashSetTaskOne {
    private HashSet<Integer> hashSet = new HashSet<>();

    public HashSetTaskOne(){
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
    }

    public void printElements(){
        hashSet.forEach(System.out::println);
    }
}
