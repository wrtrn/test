package lesson12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DebugTask10 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        List<String> namesForLoop = new ArrayList<>(names);

        for (String name : namesForLoop) {
            if (name.startsWith("A")) {
                names.remove(name);
            }
        }
    }
}
