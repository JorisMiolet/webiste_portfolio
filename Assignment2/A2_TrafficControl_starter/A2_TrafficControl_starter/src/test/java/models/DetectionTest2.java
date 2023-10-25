package models;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;  // Import LocalDateTime

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DetectionTest2 {

    @Test
    public void testValidatePurpleWithDieselTruckEmissionCategoryAbove6() {
        Car car = new Car("1-TTT-01", Car.CarType.Truck);
        car.setFuelType(Car.FuelType.Diesel);
        car.setEmissionCategory(7);  // Above 6

        LocalDateTime detectionTime = LocalDateTime.now();

        Detection detection = new Detection(car, "City1", detectionTime);

        Violation violation = detection.validatePurple();

        assertNull(violation);
    }

    @Test
    public void testValidatePurpleWithDieselTruckEmissionCategoryBelow6() {
        Car car = new Car("1-TTT-01", Car.CarType.Truck);
        car.setFuelType(Car.FuelType.Diesel);
        car.setEmissionCategory(4);  // Below 6

        LocalDateTime detectionTime = LocalDateTime.now();

        Detection detection = new Detection(car, "City1", detectionTime);

        Violation violation = detection.validatePurple();

        assertEquals("1-TTT-01", violation.getCar().getLicensePlate());
        assertEquals("City1", violation.getCity());
    }

    @Test
    public void testValidatePurpleWithNonDieselCar() {
        Car car = new Car("1-CCC-01", Car.CarType.Coach);
        car.setFuelType(Car.FuelType.Gasoline);
        car.setEmissionCategory(3);

        LocalDateTime detectionTime = LocalDateTime.now();

        Detection detection = new Detection(car, "City2", detectionTime);

        Violation violation = detection.validatePurple();

        assertNull(violation);
    }
}

