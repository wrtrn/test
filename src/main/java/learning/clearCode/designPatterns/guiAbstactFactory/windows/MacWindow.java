package learning.clearCode.designPatterns.guiAbstactFactory.windows;

public class MacWindow implements Window {
    @Override
    public void show() {
        System.out.println("Showing Mac window");
    }
}
