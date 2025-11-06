package learning.expressJava.lesson10;

public class TaskThreeVolatile implements Runnable {
    private volatile boolean flag;

    @Override
    public void run() {
        int i = 0;
        while (!flag) {
            i++;
            System.out.println(i);
        }

    }

    public void stop() {
        flag = true;
    }
}
