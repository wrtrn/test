package lesson2;

public class Product {
    String name;
    double price;

    Product(String name, int price){
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

    void setPrice(int price)
    {
        this.price=price;
    }

    void applyDiscount(int discount)
    {
        price = price-price*discount/100;
    }

    void printInfo()
    {
        System.out.println(name+ " " + price );
    }
}
