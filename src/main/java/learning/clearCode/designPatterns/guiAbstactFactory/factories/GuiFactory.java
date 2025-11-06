package learning.clearCode.designPatterns.guiAbstactFactory.factories;

import learning.clearCode.designPatterns.guiAbstactFactory.buttons.Button;
import learning.clearCode.designPatterns.guiAbstactFactory.menu.Menu;
import learning.clearCode.designPatterns.guiAbstactFactory.windows.Window;

public interface GuiFactory {
    public Button createButton();

    public Menu createMenu();

    public Window createWindow();
}
