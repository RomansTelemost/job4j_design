package ru.job4j.ood.isp.productstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        expiredPercent = expiredPercent > 100 ? 100 : expiredPercent;

        for (Store store : storeList) {
            store.evaluate(food, expiredPercent);
        }
    }

    @Override
    public List<Store> getStoreList() {
        return storeList;
    }

    @Override
    public void resort() {
        final List<Food> foodList = new ArrayList<>();
        storeList.stream()
                .forEach(p -> {
                    p.getStoreFoods().stream()
                        .forEach(food -> foodList.add(food));
                    p.setStoreFoods(new ArrayList<>());
                });
        foodList.stream().forEach(food -> determineFoodStore(LocalDate.now(), food));
    }
}
