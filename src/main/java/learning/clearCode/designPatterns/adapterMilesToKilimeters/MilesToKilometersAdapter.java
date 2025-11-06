package learning.clearCode.designPatterns.adapterMilesToKilimeters;

public class MilesToKilometersAdapter {
    private Kilometers kilometers = new Kilometers();
    private Miles miles = new Miles();

    public void convert(double number, String format) {
        if (format.equals("miles")) {
            miles.convert(number);
        } else {
            kilometers.convert(number);
        }
    }
}
