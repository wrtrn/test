package learning.expressJava.lesson5.aquarium;

public class Main {
    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();

        SeaStar seaStar = new SeaStar();
        Shark shark = new Shark();

        aquarium.addAnimal(seaStar);
        aquarium.demostrateCrawlBehaviour();
        aquarium.demonstrateSwimBehaviour();


        aquarium.addAnimal(shark);
        aquarium.demostrateCrawlBehaviour();
        aquarium.demonstrateSwimBehaviour();
    }

}
