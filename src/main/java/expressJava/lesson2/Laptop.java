package expressJava.lesson2;

public class Laptop {
    String brand;
    int price;

    Laptop(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    void setBrand(String brand) {
        this.brand = brand;
    }

    void setPrice(int price) {
        this.price = price;
    }

    String getBrand() {
        return brand;
    }

    int getPrice() {
        return price;
    }

    void printInfo() {
        System.out.println(brand + " " + price);
    }
}
