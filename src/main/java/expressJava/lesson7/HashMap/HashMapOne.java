package expressJava.lesson7.HashMap;

import java.util.HashMap;

public class HashMapOne {

    private HashMap<String,Integer> hashMap = new HashMap<>();

    public HashMapOne(){
        hashMap.put("Ivan", 1);
        hashMap.put("Peter", 2);
        hashMap.put("John", 3);
        hashMap.put("Andreas", 4);
        hashMap.put("Fiveas", 5);
    }

    public void print(){
        hashMap.entrySet().forEach(System.out::println);
    }
}
