package lesson9.part3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5,2,6,7,2,89,21);
        List<Integer> numbers2 = Arrays.asList(5,7,89,21);
        List<String> words = Arrays.asList("Hello", "Бабушка","Баран", "Гадюка", "List");


//        Integer maxNumber = numbers.stream().max(Comparator.naturalOrder()).get();
//        System.out.println(maxNumber);
//
//        Integer minNumber = numbers.stream().min(Comparator.naturalOrder()).get();
//        System.out.println(minNumber);
//
//        Integer sum = numbers.stream().mapToInt(x->x).sum();
//        System.out.println(sum);

//        String wordWithB = words.stream().filter(word->word.startsWith("Б")).findFirst().get();
//        System.out.println(wordWithB);

//        boolean atLeastOneEven = numbers2.stream().anyMatch(x->(x%2)==0);
//        System.out.println(atLeastOneEven);
    }
}
