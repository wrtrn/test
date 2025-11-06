package learning.clearCode.programmingPrinciples.interfaceSegregation;

class Programmer implements Workable{
    @Override
    public void work() {
        System.out.println("Программист пишет код");
    }
}