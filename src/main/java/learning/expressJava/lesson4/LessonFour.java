package learning.expressJava.lesson4;

import java.util.Scanner;

public class LessonFour {

    void determineNumber() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number > 0) System.out.println("Число положительное");
        else if (number < 0) System.out.println("Число отрицательное");
        else System.out.println("Число равно нулю");
    }

    void biggestNumber() {
        Scanner scanner = new Scanner(System.in);
        int numberOne = scanner.nextInt();
        int numberTwo = scanner.nextInt();
        System.out.println(Math.max(numberOne, numberTwo));
    }

    void valueToText() {
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt();
        if (value == 5) {
            System.out.println("Отлично");
        } else if (value == 4) {
            System.out.println("Хорошо");
        } else if (value == 3) {
            System.out.println("Удовлетворительно");
        } else if (value == 2 || value == 1) {
            System.out.println("Неудовлетворительно");
        }
    }

    void evenChecker() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (number % 2 == 0) System.out.println("Even");
        else System.out.println("Odd");
    }

    void determineDiscount() {
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();
        if (age < 18) {
            System.out.println("25%");
        } else {
            if (age >= 65) {
                System.out.println("30%");
            } else System.out.println("No discount");
        }
    }

    void testResult() {
        Scanner scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        if (result >= 90) {
            System.out.println("Отлично");
        } else {
            if (result <= 89 && result >= 75) {
                System.out.println("Хорошо");
            } else {
                if (result <= 74 && result >= 60) {
                    System.out.println("Удовлетворительно");
                } else {
                    if (result < 60) {
                        System.out.println("Неудовлетворительно");
                    }
                }
            }
        }
    }

    void weekNumber() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
                System.out.println("Вторник");
                break;
            case 3:
                System.out.println("Среда");
                break;
            case 4:
                System.out.println("Четверг");
                break;
            case 5:
                System.out.println("Пятница");
                break;
            case 6:
                System.out.println("Суббота");
                break;
            case 7:
                System.out.println("Воскресенье");
                break;
            default:
                System.out.println("Нет такого дня");
        }
    }

    void ticketCost() {
        Scanner scanner = new Scanner(System.in);
        int day = scanner.nextInt();
        switch (day) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("300 рублей");
                break;
            case 6:
            case 7:
                System.out.println("450 рублей");
                break;
            default:
                System.out.println("Ошибка");
        }
    }

    void convertEvaluationsIntoLetters() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number / 10) {
            case 10:
            case 9:
                System.out.println("A");
                break;
            case 8:
                System.out.println("B");
                break;
            case 7:
                System.out.println("C");
                break;
            case 6:
                System.out.println("D");
                break;
            default:
                System.out.println("F");
        }
    }

    void textCommandsProcessing() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        switch (text) {
            case "start":
                System.out.println("Система запущена");
                break;
            case "stop":
                System.out.println("Система остановлена");
                break;
            case "restart":
                System.out.println("Система перезапущена");
                break;
            case "status":
                System.out.println("Статус");
                break;
            default:
                System.out.println("Нет такого ключевого слова");
        }
    }

    void calculator() {
        Scanner scanner = new Scanner(System.in);
        int numberOne = scanner.nextInt();
        int numberTwo = scanner.nextInt();
        char operator = scanner.next().charAt(0);

        switch (operator) {
            case '+':
                System.out.println(numberOne + numberTwo);
                break;
            case '-':
                System.out.println(numberOne - numberTwo);
                break;
            case '/':
                System.out.println((double) numberOne / (double) numberTwo);
                break;
            case '*':
                System.out.println(numberOne * numberTwo);
                break;
        }
    }

    void numbersFromOneToOneHundred() {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) System.out.println(i);
        }
    }

    void sumOfNumbersFromOneToN() {
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();

        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    void multiplicationTable() {
        Scanner console = new Scanner(System.in);
        int number = console.nextInt();

        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " * " + number + " = " + i * number);
        }
    }

    void checkingThatNumberPrime() {
        Scanner console = new Scanner(System.in);
        int number = console.nextInt();
        boolean isPrime = true;

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime) System.out.println("Простое");
        else System.out.println("Не простое");
    }

    void numbersFromOneToTen() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }

    void factorial() {
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        int i = 1;
        int factorial = 1;
        while (i <= n) {
            factorial = factorial * i;
            i++;
        }
        System.out.println(factorial);
    }

    void evenNumbersFromOneUntilN() {
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        int i = 1;
        while (i <= n) {
            if (i % 2 == 0) System.out.println(i);
            i++;
        }
    }

    void countdown() {
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();

        while (n > 0) {
            System.out.println(n);
            n--;
        }
    }

    void positiveNumber() {
        Scanner console = new Scanner(System.in);
        int n;
        boolean status = false;
        do {
            n = console.nextInt();
            if (n > 0) status = true;
        }
        while (!status);

        System.out.println(n);
    }

    void passwordCheck() {
        Scanner console = new Scanner(System.in);
        String correctPassword = "123456";
        String password;
        boolean status = false;
        do {
            password = console.next();
            if (password.equals(correctPassword)) {
                status = true;
                System.out.println("Пароль верный");
            } else System.out.println("Пароль неверный");
        }
        while (!status);
    }

    void numbersFromOneTillTen() {
        int i = 1;

        do {
            System.out.println(i);
            i++;
        }
        while (i <= 10);
    }

    void exitFinishing() {
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.next();
            if (command.equals("exit")) {
                System.out.println("Finishing");
                break;
            } else {
                System.out.println("Continue");
            }
        }
        while (true);
    }

    void figuresCountInNumber() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int count = 0;
        boolean status = false;
        do {
            number = number / 10;
            if (number < 1) status = true;
            count++;
        }
        while (!status);
        System.out.println(count);
    }

    void sumOfNumbersUntilNegative() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;

        while (true) {
            int number = scanner.nextInt();
            if (number >= 0)
                sum = sum + number;
            else break;
        }

        System.out.println(sum);
    }

    void skipNumbersDividesByThree() {
        for (int i = 1; i <= 20; i++) {
            if (i % 3 == 0) continue;
            else System.out.println(i);
        }
    }

    void positiveNumbers() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int number = scanner.nextInt();
            if (number < 0) {
                continue;
            }
            if (number == 0) {
                System.out.println("Finished");
                break;
            } else {
                System.out.println(number);
            }
        }
    }

    void askingStringsUntilStop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.next();
            if (command.equals("stop")) {
                System.out.println("Stop");
                break;
            } else {
                System.out.println("Continue");
            }
        }
    }
}
