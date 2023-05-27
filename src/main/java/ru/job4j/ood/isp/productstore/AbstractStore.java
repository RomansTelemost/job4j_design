package ru.job4j.ood.isp.productstore;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store, Cloneable {

    protected int percentOfProductConditionFrom;
    protected int percentOfProductConditionTo;
    protected List<Food> storeFoods;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public AbstractStore(int percentOfProductConditionFrom, int percentOfProductConditionTo) {
        this.percentOfProductConditionFrom = percentOfProductConditionFrom;
        this.percentOfProductConditionTo = percentOfProductConditionTo;
        this.storeFoods = new ArrayList<>();
    }

    @Override
    public void evaluate(Food food, long consistency) {
        ValueRange valueRange = ValueRange.of(percentOfProductConditionFrom, percentOfProductConditionTo);
        if (valueRange.isValidValue(consistency)) {
            storeFoods.add(food);
            return;
        }
    }

    @Override
    public List<Food> getStoreFoods() {
        return storeFoods;
    }

    @Override
    public void setStoreFoods(List<Food> storeFoods) {
        this.storeFoods = storeFoods;
    }
}
