package lesson7.LinkedList;

import java.util.LinkedList;

public class LinkedListTaskThree {

    private LinkedList<String> linkedList = new LinkedList<>();

    public LinkedListTaskThree() {
        linkedList.add("fsdkgrwk");
        linkedList.add("asdfesgdzxfgregfdv");
        linkedList.add("sdghngfd");
        linkedList.add("fsdgfhgfdg");
        linkedList.add("gshfjghj");
        linkedList.add("gdfcvhert");
    }

    public void printFirstAndLastElements() {
        System.out.println("First: " + linkedList.getFirst());
        System.out.println("Last: " + linkedList.getLast());
    }
}
