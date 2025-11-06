package learning.clearCode.designPatterns.adapterMilesToKilimeters;

public class Kilometers implements Distance {
    @Override
    public void convert(double number) {
        System.out.println(number * 0.621371 + " miles");
    }
}
