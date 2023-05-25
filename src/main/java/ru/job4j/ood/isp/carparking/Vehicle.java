package ru.job4j.ood.isp.carparking;

public abstract class Vehicle {

    private int size = 1;

    public Vehicle(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
