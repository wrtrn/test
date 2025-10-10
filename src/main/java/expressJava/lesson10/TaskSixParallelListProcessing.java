package expressJava.lesson10;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskSixParallelListProcessing {

    private List<Integer> numbers = Arrays.asList(5, 1, 6, 8, 9, 2, 6, 9, 12);
    private volatile int counter;
    private volatile int sum;

    public void processList() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);


        executorService.execute(() -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.println(numbers.get(counter) + " " + counter);
                    sum += numbers.get(counter);
                    counter++;
                }
            }
            System.out.println("Elements count: " + counter + " Sum: " + sum);
        });
        executorService.shutdown();
    }
}
