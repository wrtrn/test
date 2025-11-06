package learning.clearCode.designPatterns.adapterMilesToKilimeters;

public class Miles implements Distance {
    @Override
    public void convert(double number) {
        System.out.println(number * 1.60934 + " kilometers");
    }
}
