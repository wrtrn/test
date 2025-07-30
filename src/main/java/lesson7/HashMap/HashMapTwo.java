package lesson7.HashMap;

import java.util.HashMap;

public class HashMapTwo {
    private HashMap<String, Integer> hashMap = new HashMap<>();

    public HashMapTwo() {
        hashMap.put("Ivan", 1);
        hashMap.put("Peter", 2);
        hashMap.put("John", 3);
        hashMap.put("Andreas", 4);
        hashMap.put("Fiveas", 5);
    }

    public void checkThatMapHasSpecificName(String name) {
        if (hashMap.containsKey(name))
            System.out.println("Map contains this key");
        else System.out.println("Map doesn't contain this key");

    }
}
