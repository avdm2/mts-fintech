package ru.mts.models;

/**
 * Класс, имитирующий работу склада.
 * С помощью него можно рассчитать стоимость всех товаров с учетом скидки.
 **/
public class Storage {

    // Количество товара. Целочисленное значение.
    private final int amount;

    // Цена одной единицы товара. Вещественное значение.
    private final double price;

    // Размер скидки на товар. Вещественное значение.
    private final double discount;

    public Storage(int amount, double price, double discount) {
        this.amount = amount;
        this.price = price;
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public String toString() {
        return "Storage: [amount = " + amount + "; price = " + price + "; discount = " + discount + "]";
    }
}
