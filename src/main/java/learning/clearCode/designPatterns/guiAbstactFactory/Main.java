package learning.clearCode.designPatterns.guiAbstactFactory;

import learning.clearCode.designPatterns.guiAbstactFactory.factories.GuiFactory;
import learning.clearCode.designPatterns.guiAbstactFactory.factories.MacFactory;
import learning.clearCode.designPatterns.guiAbstactFactory.factories.WindowsFactory;

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
