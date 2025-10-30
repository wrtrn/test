package clearCode.designPatterns.guiAbstactFactory.factories;

import clearCode.designPatterns.guiAbstactFactory.buttons.Button;
import clearCode.designPatterns.guiAbstactFactory.buttons.WindowsButton;
import clearCode.designPatterns.guiAbstactFactory.menu.Menu;
import clearCode.designPatterns.guiAbstactFactory.menu.WindowsMenu;
import clearCode.designPatterns.guiAbstactFactory.windows.Window;
import clearCode.designPatterns.guiAbstactFactory.windows.WindowsWindow;

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
