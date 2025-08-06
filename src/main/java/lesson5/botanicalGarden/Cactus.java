package lesson5.botanicalGarden;

public class Cactus  extends  Plant{
    @Override
    void giveWater() {
        System.out.println("Cactus requires rare watering");
    }

    @Override
    void giveLight() {
        System.out.println("Cactus requires a lot of light");
    }
}
