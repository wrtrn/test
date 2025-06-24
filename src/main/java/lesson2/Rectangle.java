package lesson2;

public class Rectangle {
    int width, height;

    Rectangle(int width,int height) {
        this.width=width;
        this.height=height;
    }

    int getWidth(){
        return this.width;
    }

    int getHeight()
    {
        return this.height;
    }

    void setWidth(int width)
    {
        this.width=width;
    }

    int calculateArea()
    {
        return width*height;
    }
}
