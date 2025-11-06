package learning.expressJava.lesson10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskFiveExecutorService {

    public void executor() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.execute(() ->
                System.out.println("We have finished this task"));
        executorService.shutdown();
    }
}
