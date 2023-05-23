package ru.job4j.ood.isp.productstore;

import java.time.temporal.ValueRange;

public class Shop extends AbstractStore {

    private final int discountPercent;

    public Shop(int percentOfProductConditionFrom, int percentOfProductConditionTo, int discountPercent) {
        super(percentOfProductConditionFrom, percentOfProductConditionTo);
        this.discountPercent = discountPercent;
    }

    @Override
    public void evaluate(Food food, long consistency) {
        if (consistency > percentOfProductConditionFrom
                && consistency <= percentOfProductConditionTo) {
            food.setDiscount(discountPercent);
            storeFoods.add(food);
            return;
        }
        ValueRange valueRange = ValueRange.of(percentOfProductConditionFrom, percentOfProductConditionTo);
        if (valueRange.isValidValue(consistency)) {
            storeFoods.add(food);
            return;
        }
    }
}
