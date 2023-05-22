package ru.job4j.ood.lsp.productstore;

import java.time.LocalDate;
import java.util.List;

public interface Quality {

    void determineFoodStore(LocalDate currentDate, Food food);

    List<Store> getStoreList();
}
