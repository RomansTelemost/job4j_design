package ru.job4j.ood.lsp.productstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {

    private List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void determineFoodStore(LocalDate currentDate, Food food) {
        LocalDate createDate = food.getCreateDate();
        LocalDate ex = food.getExpiryDate();
        long days = ChronoUnit.DAYS.between(createDate, ex);
        long daysTillNow = ChronoUnit.DAYS.between(createDate, currentDate);
        long expiredPercent = (long) (((double) daysTillNow / days) * 100);

        for (Store store : storeList) {
            store.evaluate(food, expiredPercent);
        }
    }
}
