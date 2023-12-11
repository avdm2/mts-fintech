package ru.mts;

import ru.mts.models.Storage;

import java.text.DecimalFormat;

public class Main {

    public static void getStoragePrice(Storage storage) {
        double price = storage.getAmount() * storage.getPrice();
        double priceWithDiscount = price * (1 - storage.getDiscount() / 100);

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(storage);
        System.out.println("Итоговая сумма без учета скидки: " + df.format(price));
        System.out.println("Итоговая сумма с учетом скидки: " + df.format(priceWithDiscount) + "\n");
    }

    public static void main(String[] args) {
        getStoragePrice(new Storage(10, 100, 0.75));
        getStoragePrice(new Storage(5, 43, 42.575));
        getStoragePrice(new Storage(1000, 754, 59.1));
    }
}