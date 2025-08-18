package lesson9.part2;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        Фильтрация строк по длине больше 5
        List<String> strings = Arrays.asList("Hello", "1", "22", "333", "4444", "666666", "dasdastgtrhsedwsegt", "dasfgrdthfkgjiofjtrlwejigfse", "666666", "666666", "dasdastgtrhsedwsegt");
//        List<String> resultList = strings.stream().filter(str -> str.length() > 5).collect(Collectors.toList());
//        System.out.println(resultList);

//        Фильтрация чисел, кратных 5
        List<Integer> intList = Arrays.asList(5, 1, 7, 9, 4, 10, 15, 2, 25);
//        List<Integer> resultList = intList.stream().filter(str->str%5==0).toList();
//        System.out.println(resultList);

//        Преобразование строк в их длины

//        List<Integer> result = strings.stream().map(String::length).toList();
//        System.out.println(result);

//        List<Integer> result = intList.stream().map(x-> x*x).toList();
//        System.out.println(result);

        List<String> result = strings.stream().distinct().toList();
        System.out.println(result);
    }

}
