package learning.clearCode.designPatterns.guiAbstactFactory.factories;

import learning.clearCode.designPatterns.guiAbstactFactory.buttons.Button;
import learning.clearCode.designPatterns.guiAbstactFactory.buttons.MacButton;
import learning.clearCode.designPatterns.guiAbstactFactory.menu.MacMenu;
import learning.clearCode.designPatterns.guiAbstactFactory.menu.Menu;
import learning.clearCode.designPatterns.guiAbstactFactory.windows.MacWindow;
import learning.clearCode.designPatterns.guiAbstactFactory.windows.Window;

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
