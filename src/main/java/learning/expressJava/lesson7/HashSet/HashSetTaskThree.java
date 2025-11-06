package learning.expressJava.lesson7.HashSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetTaskThree {
    private final Set<String> set = new HashSet<>();

    public Set<String> returnWithoutDuplicates(List<String> list) {
        set.addAll(list);
        return set;
    }
}
