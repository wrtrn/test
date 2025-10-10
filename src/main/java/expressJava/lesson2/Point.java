package expressJava.lesson2;

public class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void setX(int x) {
        this.x = x;
    }

    int getX() {
        return x;
    }

    void print() {
        System.out.println("X=" + x + " Y=" + y);
    }
}
