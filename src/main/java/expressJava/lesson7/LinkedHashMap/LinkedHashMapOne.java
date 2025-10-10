package expressJava.lesson7.LinkedHashMap;

import java.util.LinkedHashMap;

public class LinkedHashMapOne {

    private LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
    public LinkedHashMapOne(){
        map.put("Ivan", 1);
        map.put("Peter", 2);
        map.put("John", 3);
        map.put("Andreas", 4);
        map.put("Fiveas", 5);
    }

    public void print(){
        map.entrySet().forEach(System.out::println);
    }
}
