package learning.expressJava.lesson7.LinkedHashSet;

import java.util.LinkedHashSet;

public class LinkedHashSetTaskOne {
    private LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

    public LinkedHashSetTaskOne() {
        linkedHashSet.add("fadsasdas");
        linkedHashSet.add("vcxvxcvxs");
        linkedHashSet.add("kluilujkl");
        linkedHashSet.add("gfdvcx");
        linkedHashSet.add("lsdkfXXX");
    }

    public void printElementsInCorrectOrder()
    {
        linkedHashSet.forEach(System.out::println);
    }
}
