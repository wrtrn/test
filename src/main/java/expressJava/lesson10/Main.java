package expressJava.lesson10;

public class Main {

    public static void main(String[] args) throws InterruptedException {

//        TaskOneHelloMessage helloMessage = new TaskOneHelloMessage();
//        Thread th1 = new Thread(helloMessage);
//        th1.start();
//        th1.join();

//        TaskTwoTwoThreads taskTwoTwoThreads1 = new TaskTwoTwoThreads('A');
//        TaskTwoTwoThreads taskTwoTwoThreads2 = new TaskTwoTwoThreads('B');
//        Thread thread1 = new Thread(taskTwoTwoThreads1);
//        Thread thread2 = new Thread(taskTwoTwoThreads2);
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();

//        TaskThreeVolatile taskThreeVolatile = new TaskThreeVolatile();
//        Thread th1 = new Thread(taskThreeVolatile);
//        th1.start();
//        Thread.sleep(2000);
//        taskThreeVolatile.stop();
//        th1.join();

//        TaskFourCounter taskFourCounter = new TaskFourCounter();
//        Thread th1 = new Thread(()-> {
//            for (int i = 0; i < 1000; i++) {
//                taskFourCounter.increment();
//            }
//        });
//
//        Thread th2 = new Thread(()-> {
//            for (int i = 0; i < 1000; i++) {
//                taskFourCounter.increment();
//            }
//        });
//        th1.start();
//        th2.start();
//        th1.join();
//        th2.join();
//        System.out.println(taskFourCounter.i);

//    TaskFiveExecutorService taskFiveExecutorService = new TaskFiveExecutorService();
//    taskFiveExecutorService.executor();

//        TaskSixParallelListProcessing taskSixParallelListProcessing = new TaskSixParallelListProcessing();
//        taskSixParallelListProcessing.processList();

        TaskSevenBankAccount acc1 = new TaskSevenBankAccount(10000);
        TaskSevenBankAccount acc2 = new TaskSevenBankAccount(20000);
        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                acc1.sendMoney(i, acc2);
            }
        });
        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                acc2.sendMoney(i, acc1);
            }
        });
        th2.start();
        th1.start();
        th1.join();
        th2.join();
        acc1.printMoney();
        acc2.printMoney();

    }
}
