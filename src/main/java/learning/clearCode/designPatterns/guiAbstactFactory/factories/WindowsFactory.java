package learning.clearCode.designPatterns.guiAbstactFactory.factories;

import learning.clearCode.designPatterns.guiAbstactFactory.buttons.Button;
import learning.clearCode.designPatterns.guiAbstactFactory.buttons.WindowsButton;
import learning.clearCode.designPatterns.guiAbstactFactory.menu.Menu;
import learning.clearCode.designPatterns.guiAbstactFactory.menu.WindowsMenu;
import learning.clearCode.designPatterns.guiAbstactFactory.windows.Window;
import learning.clearCode.designPatterns.guiAbstactFactory.windows.WindowsWindow;

public class WindowsFactory implements GuiFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Menu createMenu() {
        return new WindowsMenu();
    }

    @Override
    public Window createWindow() {
        return new WindowsWindow();
    }
}
