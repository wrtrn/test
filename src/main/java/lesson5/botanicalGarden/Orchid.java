package lesson5.botanicalGarden;

public class Orchid extends Plant{
    @Override
    void giveWater() {
        System.out.println("Ochrid requires high humidity");
    }

    @Override
    void giveLight() {
        System.out.println("Ochrid requires shadow");
    }
}
