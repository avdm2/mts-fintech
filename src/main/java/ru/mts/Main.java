package ru.mts;

import ru.mts.models.Storage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

    public static void calculateStoragePrice(Storage storage) {
        double price = storage.getAmount() * storage.getPrice();
        double priceWithDiscount = price * (1 - storage.getDiscount() / 100);

        BigDecimal decimalPrice = new BigDecimal(Double.toString(price))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal decimalPriceWithDiscount = new BigDecimal(Double.toString(priceWithDiscount))
                .setScale(2, RoundingMode.HALF_UP);

        System.out.println(storage);
        System.out.printf("Итоговая сумма без учета скидки: %.2f\n", decimalPrice.doubleValue());
        System.out.printf("Итоговая сумма с учетом скидки: %.2f\n\n", decimalPriceWithDiscount.doubleValue());
    }

    public static void main(String[] args) {
        calculateStoragePrice(new Storage(10, 100, 0.75));
        calculateStoragePrice(new Storage(5, 43, 42.575));
        calculateStoragePrice(new Storage(1000, 754, 59.1));
    }
}