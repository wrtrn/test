package learning.clearCode.programmingPrinciples.liskov;

class Penguin extends Bird implements NotFlyable {

    @Override
    public void walk() {
        System.out.println("пингвин ходит");
    }
}
