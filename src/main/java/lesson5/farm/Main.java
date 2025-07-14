package lesson5.farm;

import lesson5.homePets.Cat;

public class Main {
    public static void main(String[] args) {

        FarmController farmController = new FarmController();

        Animal cow = new Cow();
        Animal chicken = new Chicken();

        farmController.controlAnimal(cow);
        farmController.controlAnimal(chicken);
    }
}
