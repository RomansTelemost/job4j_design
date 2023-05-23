package ru.job4j.ood.isp.productstore;

import java.util.List;

public interface Store {

    void evaluate(Food food, long consistency);

    List<Food> getStoreFoods();

    void setStoreFoods(List<Food> storeFoods);

}
