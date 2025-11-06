package learning.expressJava.lesson7.LinkedList;

import java.util.LinkedList;

public class LinkedListTaskOne {

    private LinkedList<String> linkedList = new LinkedList<>();

     public LinkedListTaskOne(){
        linkedList.add("aweafsd");
        linkedList.add("gdfgxbdtewrd");
        linkedList.add("jytgfhdf");
        linkedList.add("34wresfdx");
        linkedList.add("sdgtrydhtrdr");
    }

    public void printAllElements(){
        linkedList.forEach(System.out::println);
    }
}
