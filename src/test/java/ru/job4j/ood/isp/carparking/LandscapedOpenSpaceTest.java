package ru.job4j.ood.isp.carparking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LandscapedOpenSpaceTest {

    @Test
    public void whenParkSameVehicleThenFalse() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(2, 2);
        Vehicle passengerCar = new PassengerCar();
        parking.park(passengerCar);
        assertThat(parking.park(passengerCar)).isFalse();
    }

    @Test
    public void whenNoSpaceInAllParksThenFalse() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(1, 1);

        Vehicle passengerCar1 = new PassengerCar();
        Vehicle passengerCar2 = new PassengerCar();
        Vehicle track = new Track(3);
        parking.park(passengerCar1);
        parking.park(track);
        assertThat(parking.park(passengerCar2)).isFalse();
        assertThat(parking.getParkedPassengerCar().size()).isEqualTo(1);
        assertThat(parking.getParkedTrack().size()).isEqualTo(1);
    }

    @Test
    public void whenParkPassengerCarThenPassengerCarListContainsOneCar() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(4, 5);

        Vehicle passengerCar = new PassengerCar();
        parking.park(passengerCar);
        assertThat(parking.getParkedPassengerCar().size()).isEqualTo(1);
        assertThat(parking.getParkedPassengerCar().contains(passengerCar)).isTrue();
        assertThat(parking.getParkedTrack().size()).isEqualTo(0);
    }

    @Test
    public void whenParkTackThenTackListContainsOneTack() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(4, 5);

        Vehicle track = new Track(3);
        parking.park(track);
        assertThat(parking.getParkedPassengerCar().size()).isEqualTo(0);
        assertThat(parking.getParkedTrack().size()).isEqualTo(1);
        assertThat(parking.getParkedTrack().contains(track)).isTrue();
    }

    @Test
    public void whenNoSpaceInPassengerCarParkThenTakePlaceInTrackPark() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(4, 5);

        Vehicle passengerCar1 = new PassengerCar();
        Vehicle passengerCar2 = new PassengerCar();
        Vehicle passengerCar3 = new PassengerCar();
        Vehicle passengerCar4 = new PassengerCar();
        Vehicle passengerCar5 = new PassengerCar();
        parking.park(passengerCar1);
        parking.park(passengerCar2);
        parking.park(passengerCar3);
        parking.park(passengerCar4);
        parking.park(passengerCar5);
        assertThat(parking.getParkedPassengerCar().size()).isEqualTo(4);
        assertThat(parking.getParkedTrack().size()).isEqualTo(1);
    }

    @Test
    /**
     * При парковке грузовика на паркорке для грузовиков берется 1 место.
     * При парковке грузовика на паркорке для пассажирских авто берем столько места сколько размер грузовика
     */
    public void whenNoSpaceInTrackParkThenTakePlaceInPassengerCarPark() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(4, 2);

        Vehicle track1 = new Track(3);
        Vehicle track2 = new Track(3);
        Vehicle track3 = new Track(3);
        parking.park(track1);
        parking.park(track2);
        parking.park(track3);
        assertThat(parking.getParkedTrack().size()).isEqualTo(2);
        assertThat(parking.getParkedPassengerCar().size()).isEqualTo(1);
        assertThat(parking.getPassengerCarParkSize()).isEqualTo(1);
    }

    @Test
    public void whenOnePassengerCarInWholeParks_ThenUnParkPassengerCarFromPassengerPark_ThenTrue() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(4, 2);
        Vehicle passengerCar = new PassengerCar();
        parking.park(passengerCar);

        assertThat(parking.unPark(passengerCar)).isTrue();
        assertThat(parking.getParkedPassengerCar().contains(passengerCar)).isFalse();
        assertThat(parking.getParkedTrack().contains(passengerCar)).isFalse();
    }

    @Test
    public void whenPassengerParkIsFull_ThenPassengerCarPlacesInTrackPark_ThenUnParkPassengerCarFromTrackPark_ThenTrue() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(1, 2);
        Vehicle passengerCar1 = new PassengerCar();
        Vehicle passengerCar2 = new PassengerCar();
        parking.park(passengerCar1);
        parking.park(passengerCar2);

        assertThat(parking.unPark(passengerCar2)).isTrue();
        assertThat(parking.getParkedPassengerCar().contains(passengerCar2)).isFalse();
        assertThat(parking.getPassengerCarParkSize()).isEqualTo(0);
        assertThat(parking.getParkedTrack().contains(passengerCar2)).isFalse();
        assertThat(parking.getTrackParkSize()).isEqualTo(2);
    }

    @Test
    public void whenOneTrackInWholeParks_ThenUnParkTrackFromTrackPark_ThenTrue() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(4, 2);
        Vehicle track1 = new Track(3);
        parking.park(track1);

        assertThat(parking.unPark(track1)).isTrue();
        assertThat(parking.getParkedPassengerCar().contains(track1)).isFalse();
        assertThat(parking.getParkedTrack().contains(track1)).isFalse();
    }

    @Test
    public void whenTrackParkIsFull_ThenTrackPlacesInPassengerPark_ThenUnParkTrackFromPassengerPark_ThenTrue() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(4, 2);
        Vehicle track1 = new Track(3);
        Vehicle track2 = new Track(2);
        Vehicle track3 = new Track(4);
        parking.park(track1);
        parking.park(track2);
        parking.park(track3);

        assertThat(parking.unPark(track3)).isTrue();
        assertThat(parking.getParkedPassengerCar().contains(track3)).isFalse();
        assertThat(parking.getPassengerCarParkSize()).isEqualTo(4);
        assertThat(parking.getParkedTrack().contains(track3)).isFalse();
        assertThat(parking.getTrackParkSize()).isEqualTo(0);
    }

    @Test
    public void whenUnParkNotParkingVehicleThenFalse() {
        LandscapedOpenSpace parking = new LandscapedOpenSpace(4, 2);
        Vehicle track1 = new Track(3);
        assertThat(parking.unPark(track1)).isFalse();
    }
}