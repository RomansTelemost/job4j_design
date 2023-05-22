package ru.job4j.ood.lsp.productstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality implements Quality {

    private List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    @Override
    public void determineFoodStore(LocalDate currentDate, Food food) {
        LocalDate createDate = food.getCreateDate();
        long expirationDate = ChronoUnit.DAYS.between(createDate, food.getExpiryDate());
        long expirationDateTillNow = ChronoUnit.DAYS.between(createDate, currentDate);
        long expiredPercent = (long) (((double) expirationDateTillNow / expirationDate) * 100);

        for (Store store : storeList) {
            store.evaluate(food, expiredPercent);
        }
    }

    @Override
    public List<Store> getStoreList() {
        return storeList;
    }
}
