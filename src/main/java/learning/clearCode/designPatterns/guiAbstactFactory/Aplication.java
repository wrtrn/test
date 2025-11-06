package learning.clearCode.designPatterns.guiAbstactFactory;

import learning.clearCode.designPatterns.guiAbstactFactory.buttons.Button;
import learning.clearCode.designPatterns.guiAbstactFactory.factories.GuiFactory;
import learning.clearCode.designPatterns.guiAbstactFactory.menu.Menu;
import learning.clearCode.designPatterns.guiAbstactFactory.windows.Window;

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
