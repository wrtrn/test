package lesson5.botanicalGarden;

public class BotanicalGardenContoller {
    private Plant plant;

    void addPlant (Plant plant)
    {
        this.plant = plant;
    }

    void giveCare()
    {
        plant.giveLight();
        plant.giveWater();
    }
}
