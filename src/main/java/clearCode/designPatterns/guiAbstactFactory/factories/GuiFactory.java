package clearCode.designPatterns.guiAbstactFactory.factories;

import clearCode.designPatterns.guiAbstactFactory.buttons.Button;
import clearCode.designPatterns.guiAbstactFactory.menu.Menu;
import clearCode.designPatterns.guiAbstactFactory.windows.Window;

public interface GuiFactory {
    public Button createButton();

    public Menu createMenu();

    public Window createWindow();
}
