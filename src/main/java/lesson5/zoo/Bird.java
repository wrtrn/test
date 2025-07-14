package lesson5.zoo;

public class Bird extends Animal{
    @Override
    void makeSound() {
        System.out.println("Bird chiriks");
    }

    @Override
    void move() {
        System.out.println("Bird flies");
    }
}
