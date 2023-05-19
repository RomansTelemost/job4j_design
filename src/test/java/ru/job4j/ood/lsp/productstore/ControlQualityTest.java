package ru.job4j.ood.lsp.productstore;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {

    @Test
    public void whenExpiration20PercentThenWarehouse() {
        List<Store> storeList = new ArrayList<>();
        storeList.add(new Warehouse(0, 25));
        storeList.add(new Shop(25, 99));
        storeList.add(new Trash(100, 100));
        ControlQuality controlQuality = new ControlQuality(storeList);
        Food food = new Food("Milk",
                LocalDate.of(2023, 5, 20),
                LocalDate.of(2023, 5, 1),
                100,
                0);

        controlQuality.determineFoodStore(LocalDate.of(2023, 05, 18), food);
        assertThat(storeList.get(1).getStoreFoods().size()).isEqualTo(1);
    }
}