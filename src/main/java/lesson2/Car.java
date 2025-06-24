package lesson2;

public class Car {
    String brand;
    int year;

    Car(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    String getBrand() {
        return this.brand;
    }

    int getYear() {
        return this.year;
    }

    void setBrand(String brand) {
        this.brand = brand;
    }

    void setYear(int year) {
        this.year = year;
    }

    void print() {
        System.out.println(brand + " " + year);
    }
}
