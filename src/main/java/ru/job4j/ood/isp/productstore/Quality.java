package ru.job4j.ood.isp.productstore;

import java.time.LocalDate;
import java.util.List;

public interface Quality {

    void determineFoodStore(LocalDate currentDate, Food food);

    List<Store> getStoreList();
}
