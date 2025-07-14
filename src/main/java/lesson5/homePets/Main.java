package lesson5.homePets;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        HomePetsController homePetsController = new HomePetsController();

        homePetsController.interactWithPet(cat);
        homePetsController.interactWithPet(dog);
    }
}
