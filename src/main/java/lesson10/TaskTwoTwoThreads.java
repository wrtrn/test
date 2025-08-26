package lesson10;

public class TaskTwoTwoThreads implements Runnable {

    private final char letter;

    public TaskTwoTwoThreads(char letter) {
        this.letter = letter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(letter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
