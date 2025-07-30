package lesson7.PriorityQueue;

import java.util.PriorityQueue;

public class PriorityQueueOne {
    private PriorityQueue<Integer> queue = new PriorityQueue<>();

    public PriorityQueueOne() {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
    }

    public void printInOrderOfDeleting() {

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
