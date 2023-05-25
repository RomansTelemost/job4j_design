package ru.job4j.ood.isp.carparking;

import java.util.List;

public interface Parking {

    boolean park(Vehicle vehicle);

    boolean unPark(Vehicle vehicle);

    int getFreeParkingPlace();
    List<Vehicle> getParkedVehicles();
}
