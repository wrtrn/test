package expressJava.lesson5.zoo;

public class Zoo {
    private Animal animal;

    void demonstrateBehaviour()
    {
        animal.makeSound();
        animal.move();
    }

    void addAnimal (Animal animal)
    {
        this.animal = animal;
    }
}
