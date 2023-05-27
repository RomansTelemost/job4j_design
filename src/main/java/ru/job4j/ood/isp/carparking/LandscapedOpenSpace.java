package ru.job4j.ood.isp.carparking;

import java.util.ArrayList;
import java.util.List;

public class LandscapedOpenSpace implements Parking {

    private int passengerCarParkSize;
    private int trackParkSize;
    private List<Vehicle> parkedPassengerCars;
    private List<Vehicle> parkedTracks;

    public LandscapedOpenSpace(int carParkSize, int trackParkSize) {
        this.passengerCarParkSize = carParkSize;
        this.trackParkSize = trackParkSize;
        this.parkedPassengerCars = new ArrayList<>(carParkSize);
        this.parkedTracks = new ArrayList<>(trackParkSize);
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
    public int getPassengerCarParkSize() {
        return passengerCarParkSize;
    }

    @Override
    public int getTrackParkSize() {
        return trackParkSize;
    }

    @Override
    public List<Vehicle> getParkedPassengerCar() {
        return parkedPassengerCars;
    }

    @Override
    public List<Vehicle> getParkedTrack() {
        return parkedTracks;
    }
}
