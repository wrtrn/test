package expressJava.lesson2;

public class Product {
    String name;
    double price;

    Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    String getName()
    {
        return name;
    }

    double getPrice()
    {
        return price;
    }

    void setPrice(double price)
    {
        this.price=price;
    }

    void applyDiscount(int discount)
    {
        price = price * (1 - discount / 100.0);
    }

    void printInfo()
    {
        System.out.println(name+ " " + price );
    }
}
