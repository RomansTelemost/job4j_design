package ru.job4j.ood.isp.carparking;

import java.util.List;

public interface Parking {

    boolean park(Vehicle vehicle);
    boolean unPark(Vehicle vehicle);
    int getPassengerCarParkSize();
    int getTrackParkSize();
    List<Vehicle> getParkedPassengerCar();
    List<Vehicle> getParkedTrack();
}
