package lesson12;

import java.text.DecimalFormat;

public class DebugTask8 {
    public static void main(String[] args) {
        double a = 0.1 * 3.0;
        double b = 0.3;
        a = Double.parseDouble(new DecimalFormat("#.##").format(a));
        b = Double.parseDouble(new DecimalFormat("#.##").format(b));

        if (a == b) {
            System.out.println("Equal");
        } else {
            System.out.println("Not Equal");
        }
    }
}