package ru.job4j.serialization.json;

public class Planet {

    private String name;
    private long age;
    private int landArea;
    private long distanceFromStar;
    private boolean habitable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public int getLandArea() {
        return landArea;
    }

    public void setLandArea(int landArea) {
        this.landArea = landArea;
    }

    public long getDistanceFromStar() {
        return distanceFromStar;
    }

    public void setDistanceFromStar(long distanceFromStar) {
        this.distanceFromStar = distanceFromStar;
    }

    public boolean isHabitable() {
        return habitable;
    }

    public void setHabitable(boolean habitable) {
        this.habitable = habitable;
    }
}
