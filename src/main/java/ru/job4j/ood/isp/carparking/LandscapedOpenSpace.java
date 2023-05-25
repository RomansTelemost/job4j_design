package ru.job4j.ood.isp.carparking;

import java.util.ArrayList;
import java.util.List;

public class LandscapedOpenSpace implements Parking {

    private int parkSize;
    private List<Vehicle> parkedVehicles;

    public LandscapedOpenSpace(int parkSize) {
        this.parkSize = parkSize;
        this.parkedVehicles = new ArrayList<>(parkSize);
    }

    @Override
    public boolean park(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean unPark(Vehicle vehicle) {
        return false;
    }

    @Override
    public int getFreeParkingPlace() {
        return parkSize;
    }

    @Override
    public List<Vehicle> getParkedVehicles() {
        return parkedVehicles;
    }
}
