package clearCode.designPatterns.guiAbstactFactory;

import clearCode.designPatterns.guiAbstactFactory.factories.GuiFactory;
import clearCode.designPatterns.guiAbstactFactory.factories.MacFactory;
import clearCode.designPatterns.guiAbstactFactory.factories.WindowsFactory;

public class Main {
    public static void main(String[] args) {
        GuiFactory factory;

        String os = "Windows";

        if (os.equals("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }
        Aplication aplication = new Aplication(factory);
        aplication.show();
    }
}
