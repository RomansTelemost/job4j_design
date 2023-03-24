package ru.job4j.serialization.json;

import java.util.List;

public class StarSystem {

    private String name;
    private int countOfPlanets;
    private List<Planet> habitablePlanets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountOfPlanets() {
        return countOfPlanets;
    }

    public void setCountOfPlanets(int countOfPlanets) {
        this.countOfPlanets = countOfPlanets;
    }

    public List<Planet> getHabitablePlanets() {
        return habitablePlanets;
    }

    public void setHabitablePlanets(List<Planet> habitablePlanets) {
        this.habitablePlanets = habitablePlanets;
    }
}
