package ru.job4j.ood.isp.carparking;

public class Track extends Vehicle {

    /**
     * Постусловие усилено что допустимо
     * @param size
     */
    public Track(int size) {
        super(size);
        if (size <= 1) {
            throw new IllegalArgumentException("Size must greater then 1");
        }
    }
}
