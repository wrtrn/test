package lesson7.ArrayList;

import java.util.ArrayList;

public class ArrayListTaskOne {

    private ArrayList<Integer> numbersList = new ArrayList<>(5);

    public ArrayListTaskOne()
    {
        numbersList.add(1);
        numbersList.add(2);
        numbersList.add(3);
        numbersList.add(4);
        numbersList.add(5);
    }

    public void addOneNumberToTheEnd(Integer number){
        numbersList.add(number);
    }

    public void printArray()
    {
        System.out.println("Full list:");
        numbersList.forEach(System.out::println);
    }
}
