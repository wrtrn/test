package learning.expressJava.lesson9.part4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> strings = Arrays.asList("Hello", "Awrfsd", "fjfsdfsd", "qset", "Bert", "Allo");
        Map<Character, List<String>> result = strings.stream().collect(Collectors.groupingBy(str -> str.charAt(0)));
        System.out.println(result);

        List<Integer> numbers = Arrays.asList(5, 1, 2, 4, 6, 8, 6, 3, 5, 4, 1, 2, 10, 11);
        Map<Boolean, List<Integer>> result2 = numbers.stream().collect(Collectors.groupingBy(x -> x % 2 == 0));
        System.out.println(result2);

        Double result3 = numbers.stream().collect(Collectors.averagingInt(n -> n));
        System.out.println(result3);
    }
}
