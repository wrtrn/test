package expressJava.lesson5.aquarium;

public class Aquarium {

    private Animal animal;

    void addAnimal(Animal animal) {
        this.animal = animal;
    }

    void demostrateCrawlBehaviour() {
        if (animal instanceof Crawlable) {
            ((Crawlable) animal).crawl();
        } else System.out.println("Can't crawl");
    }

    void demonstrateSwimBehaviour() {
        if (animal instanceof Swimable) {
            Swimable swimable = (Swimable) animal;
            swimable.swim();
        } else System.out.println("Can't swim");
    }
}
