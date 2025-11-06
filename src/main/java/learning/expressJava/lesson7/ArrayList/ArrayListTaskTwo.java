package learning.expressJava.lesson7.ArrayList;

import java.util.ArrayList;

public class ArrayListTaskTwo {
    private ArrayList<Integer> arrayList = new ArrayList<>();

    public ArrayListTaskTwo() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(0);
    }

    public void printEvenFromArray() {
        for (Integer i : arrayList) {
            if (i % 2 == 0 && i != 0)
                System.out.println(i);
        }
    }
}
