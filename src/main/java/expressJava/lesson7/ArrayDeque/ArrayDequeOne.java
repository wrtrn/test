package expressJava.lesson7.ArrayDeque;

import java.util.ArrayDeque;

public class ArrayDequeOne {

    private ArrayDeque<Integer> deque = new ArrayDeque<>();

    public ArrayDequeOne()
    {
        deque.add(1);
        deque.add(10);
        deque.add(4);
        deque.add(6);
        deque.add(5);
    }

    public void print(){
        deque.forEach(System.out::println);
    }
}
