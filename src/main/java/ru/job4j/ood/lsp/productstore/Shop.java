package ru.job4j.ood.lsp.productstore;

import java.time.temporal.ValueRange;

public class Shop extends AbstractStore {

    public Shop(int percentOfProductConditionFrom, int percentOfProductConditionTo) {
        super(percentOfProductConditionFrom, percentOfProductConditionTo);
    }

    @Override
    public void evaluate(Food food, long consistency) {
        if (consistency > percentOfProductConditionFrom
                && consistency <= percentOfProductConditionTo) {
            food.setDiscount(20);
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
