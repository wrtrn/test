package clearCode.designPatterns.guiAbstactFactory;

import clearCode.designPatterns.guiAbstactFactory.buttons.Button;
import clearCode.designPatterns.guiAbstactFactory.factories.GuiFactory;
import clearCode.designPatterns.guiAbstactFactory.menu.Menu;
import clearCode.designPatterns.guiAbstactFactory.windows.Window;

public class Aplication {
    private Button button;
    private Menu menu;
    private Window window;

    public Aplication(GuiFactory factory) {
        button = factory.createButton();
        menu = factory.createMenu();
        window = factory.createWindow();
    }

    public void show() {
        button.show();
        menu.show();
        window.show();
    }
}
