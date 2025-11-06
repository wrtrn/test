package learning.clearCode.designPatterns.guiAbstactFactory.windows;

public class WindowsWindow implements Window {
    @Override
    public void show() {
        System.out.println("Showing Windows window");
    }
}
