package clearCode.designPatterns.guiAbstactFactory.buttons;

public class MacButton implements Button {
    @Override
    public void show() {
        System.out.println("Showing Mac button");
    }
}
