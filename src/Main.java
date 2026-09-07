import java.util.Arrays;
import java.util.Objects;

class Product {
    private int id;
    private String name;
    private int price;
    private String category;

    public Product(int id, String name, int price, String category) {
        this.id = id; // артикул.
        this.name = name; // название.
        this.price = price; // цена.
        this.category = category; // категория.
    }

    @Override
    public String toString() {
        return"Товар[артикул= "+id+", название= "+name +", цена= "+price +", категория= " + category +"]";
    }
    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                 // один и тот же объект
        if (obj == null || getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        // Сравниваем ТОЛЬКО id и category — как в задании
        return this.id == other.id && Objects.equals(this.category, other.category);
    }
}


class Order {
    private String customer;
    private Product[] basket;

    public Order(String customer, Product[] basket) {
        this.customer = customer;
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "Заказ[клиент=" + customer + ", корзина=" + Arrays.toString(basket) + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order other = (Order) o;

        // Сравниваем customer через Objects.equals (на случай null)
        if (!Objects.equals(this.customer, other.customer)) return false;

        // Сравниваем массивы
        if (this.basket == other.basket) return true;       // та же ссылка
        if (this.basket == null || other.basket == null) return false;
        if (this.basket.length != other.basket.length) return false;

        // Поэлементное сравнение через Product.equals()
        for (int i = 0; i < this.basket.length; i++) {
            Product p1 = this.basket[i];
            Product p2 = other.basket[i];

            if (p1 == p2) continue;               // обе null или та же ссылка
            if (p1 == null || p2 == null) return false;
            if (!p1.equals(p2)) return false;
        }

        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        //Задание 1,2
        Product product1 = new Product(25, "Телефон", 65000, "Электроника");
        Product product2 = new Product(100, "Хлеб", 60, "Продукты");
        Product product3 = new Product(25, "Телефон", 65000, "Электроника");
        Product product4 = new Product(10, "Приставка", 160000, "Электроника");
        Product product5 = new Product(25, "Компьютер", 160000, "Электроника");
        Product product6 = new Product(25, "Багет", 85, "Продукты");
// Вывод задания 1
        System.out.println("\n\tЗадние№1");

        System.out.println("\n=== Товары ===");
        System.out.println(product1);
        System.out.println(product2);
//Вывод задания 2
        System.out.println("\n\tЗадние№2");

        System.out.println("\n=== Результаты сравнений ===");
        System.out.println(product1.equals(product2));
        System.out.println(product1.equals(product3));
        System.out.println(product1.equals(product4));
        System.out.println(product1.equals(product5));
        System.out.println(product5.equals(product6));
// Задания 3
        Product t1 = new Product(1, "Хлеб", 60, "Продукты");
        Product t2 = new Product(2, "Молоко", 90, "Продукты");
        Product t3 = new Product(3, "Чайник", 2500, "Бытовая техника");

        // Заказы
        Order order1 = new Order("Иван", new Product[]{t1, t2, t3});
        Order order2 = new Order("Иван", new Product[]{t1, t2, t3});       // такой же набор
        Order order3 = new Order("Мария", new Product[]{t1, t2, t3});      // другой клиент
        Order order4 = new Order("Иван", new Product[]{t1, t3});          // меньше товаров
        Order order5 = new Order("Иван", new Product[]{t1, t2, t3, t1});   // лишний товар

        System.out.println("\n\tЗадние№3");

        System.out.println("\n=== Заказы ===");
        System.out.println("Клиент №1: " + order1);
        System.out.println("Клиент №2: " + order2);
        System.out.println("Клиент №3: " + order3);
        System.out.println("Клиент №4: " + order4);
        System.out.println("Клиент №5: " + order5);

        System.out.println("\n=== Сравнения ===");
        System.out.println("Заказ№1 и Заказ№2: " + order1.equals(order2)); // true
        System.out.println("Заказ№1 и Заказ№3: " + order1.equals(order3)); // false (другой клиент)
        System.out.println("Заказ№1 и Заказ№4: " + order1.equals(order4)); // false (меньше товаров)
        System.out.println("Заказ№1 и Заказ№5: " + order1.equals(order5));
    }
}