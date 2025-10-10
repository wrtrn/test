package expressJava.lesson7.TreeMap;

import java.util.TreeMap;

public class TreeMapOne {

    private TreeMap<String, Integer> map = new TreeMap<>();

    public  TreeMapOne(){
        map.put("John", 3);
        map.put("Peter", 2);
        map.put("Fiveas", 5);
        map.put("Ivan", 1);
        map.put("Andreas", 4);
    }

    public void print()
    {
        map.entrySet().forEach(System.out::println);
    }
}
