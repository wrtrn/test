package clearCode.designPatterns.guiAbstactFactory.factories;

import clearCode.designPatterns.guiAbstactFactory.buttons.Button;
import clearCode.designPatterns.guiAbstactFactory.buttons.MacButton;
import clearCode.designPatterns.guiAbstactFactory.menu.MacMenu;
import clearCode.designPatterns.guiAbstactFactory.menu.Menu;
import clearCode.designPatterns.guiAbstactFactory.windows.MacWindow;
import clearCode.designPatterns.guiAbstactFactory.windows.Window;

public class MacFactory implements GuiFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Menu createMenu() {
        return new MacMenu();
    }

    @Override
    public Window createWindow() {
        return new MacWindow();
    }
}
