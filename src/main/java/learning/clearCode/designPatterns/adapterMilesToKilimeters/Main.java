package learning.clearCode.designPatterns.adapterMilesToKilimeters;

public class Main {
    public static void main(String[] args) {
        MilesToKilometersAdapter adapter = new MilesToKilometersAdapter();
        adapter.convert(10, "miles");
        adapter.convert(10, "kilometers");
    }
}
