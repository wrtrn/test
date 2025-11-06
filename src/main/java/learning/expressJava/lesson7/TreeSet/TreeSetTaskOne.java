package learning.expressJava.lesson7.TreeSet;

import java.util.TreeSet;

public class TreeSetTaskOne {

    private TreeSet<Integer> set = new TreeSet<>();

    public TreeSetTaskOne(){
        set.add(1);
        set.add(6);
        set.add(3);
        set.add(2);
        set.add(5);
    }

    public void print(){
        set.forEach(System.out::println);
    }
}
