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
        if (parkedPassengerCars.contains(vehicle)
                || parkedTracks.contains(vehicle)) {
            return false;
        }
        if (vehicle.getSize() == 1
                && passengerCarParkSize >= 1) {
            parkedPassengerCars.add(vehicle);
            passengerCarParkSize--;
            return true;
        }
        if (vehicle.getSize() > 1
                && trackParkSize >= 1) {
            parkedTracks.add(vehicle);
            trackParkSize--;
            return true;
        }
        if (vehicle.getSize() == 1
                && trackParkSize >= 1) {
            parkedTracks.add(vehicle);
            trackParkSize--;
            return true;
        }
        if (vehicle.getSize() > 1
                && passengerCarParkSize >= vehicle.getSize()) {
            parkedPassengerCars.add(vehicle);
            passengerCarParkSize -= vehicle.getSize();
            return true;
        }
        return false;
    }

    @Override
    public boolean unPark(Vehicle vehicle) {
        if (parkedPassengerCars.contains(vehicle)) {
            parkedPassengerCars.remove(vehicle);
            passengerCarParkSize += vehicle.getSize();
            return true;
        }

        if (parkedTracks.contains(vehicle)) {
            parkedTracks.remove(vehicle);
            trackParkSize += vehicle.getSize();
            return true;
        }
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
