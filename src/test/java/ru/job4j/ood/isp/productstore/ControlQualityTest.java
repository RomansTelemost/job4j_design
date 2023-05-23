package ru.job4j.ood.isp.productstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {

    public Quality controlQuality;

    @BeforeEach
    public void ps() {
        List<Store> storeList = new ArrayList<>();
        storeList.add(new Warehouse(0, 25));
        storeList.add(new Shop(25, 99, 10));
        storeList.add(new Trash(100, 100));
        this.controlQuality = new ControlQuality(storeList);
    }

    @Test
    public void whenExpiration89PercentThenShopWithDiscount() {
        Food food = new Food("Milk",
                LocalDate.of(2023, 5, 20),
                LocalDate.of(2023, 5, 1),
                100,
                0);

        controlQuality.determineFoodStore(LocalDate.of(2023, 05, 18), food);
        List<Food> foodList = controlQuality.getStoreList().get(1).getStoreFoods();
        assertThat(foodList.size()).isEqualTo(1);
        assertThat(foodList.get(0).discount).isEqualTo(10);
    }

    @Test
    public void whenExpiration20PercentThenWarehouse() {
        Food food = new Food("Milk",
                LocalDate.of(2023, 5, 10),
                LocalDate.of(2023, 5, 1),
                100,
                0);

        controlQuality.determineFoodStore(LocalDate.of(2023, 05, 2), food);
        List<Food> foodList = controlQuality.getStoreList().get(0).getStoreFoods();
        assertThat(foodList.size()).isEqualTo(1);
        assertThat(foodList.get(0).discount).isEqualTo(0);
    }

    @Test
    public void whenExpiration100PercentThenTrash() {
        Food food = new Food("Milk",
                LocalDate.of(2023, 5, 10),
                LocalDate.of(2023, 5, 1),
                100,
                0);

        controlQuality.determineFoodStore(LocalDate.of(2023, 05, 10), food);
        List<Food> foodList = controlQuality.getStoreList().get(2).getStoreFoods();
        assertThat(foodList.size()).isEqualTo(1);
        assertThat(foodList.get(0).discount).isEqualTo(0);
    }
}