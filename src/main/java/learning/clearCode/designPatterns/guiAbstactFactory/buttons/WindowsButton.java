package learning.clearCode.designPatterns.guiAbstactFactory.buttons;

public class WindowsButton implements Button {
    @Override
    public void show() {
        System.out.println("Showing Windows button");
    }
}
