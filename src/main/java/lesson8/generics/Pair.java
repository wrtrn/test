package lesson8.generics;

public class Pair<T, J> {
    private T elementOne;
    private J elementTwo;

    public T getElementOne() {
        return elementOne;
    }

    public void setElementOne(T elementOne) {
        this.elementOne = elementOne;
    }

    public J getElementTwo() {
        return elementTwo;
    }

    public void setElementTwo(J elementTwo) {
        this.elementTwo = elementTwo;
    }
}
