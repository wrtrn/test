package lesson5.aquarium;

public class SeaStar extends Animal implements Crawlable {
    @Override
    public void crawl() {
        System.out.println("Sea star crawls slowly");
    }
}
