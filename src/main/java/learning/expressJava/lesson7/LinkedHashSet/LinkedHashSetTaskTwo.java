package learning.expressJava.lesson7.LinkedHashSet;

import java.util.LinkedHashSet;

public class LinkedHashSetTaskTwo {

    private LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();

    public void addElementIntoSet(Integer element){
        hashSet.add(element);
    }

    public void printSet(){
        hashSet.forEach(System.out::println);
    }
}
