package lesson5.botanicalGarden;

public class Main {
    public static void main(String[] args) {
        BotanicalGardenContoller botanicalGardenContoller = new BotanicalGardenContoller();

        Plant orchid = new Orchid();
        Plant cactus = new Cactus();

        botanicalGardenContoller.addPlant(orchid);
        botanicalGardenContoller.giveCare();


        botanicalGardenContoller.addPlant(cactus);
        botanicalGardenContoller.giveCare();
    }
}
