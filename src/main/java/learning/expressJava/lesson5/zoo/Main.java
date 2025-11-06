package learning.expressJava.lesson5.zoo;

public class Main {

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        Animal bird = new Bird();
        zoo.addAnimal(bird);
        zoo.demonstrateBehaviour();
        Animal elephant = new Elephant();
        zoo.addAnimal(elephant);
        zoo.demonstrateBehaviour();
    }
}
