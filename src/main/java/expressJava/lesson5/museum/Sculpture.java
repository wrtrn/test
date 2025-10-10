package expressJava.lesson5.museum;

public class Sculpture extends Exhibit {
    private String history;

    Sculpture(String history) {
        this.history = history;
    }

    @Override
    void showStorageConditions() {
        System.out.println("Sculpture: requires restavration");
    }

    @Override
    void showHistory() {
        System.out.println("Sculpture history: " + history);
    }
}
