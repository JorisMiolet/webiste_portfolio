package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static models.Car.CarType;
import static models.Car.FuelType;

public class Detection {
    private final Car car;                  // the car that was detected
    private final String city;              // the name of the city where the detector was located
    private final LocalDateTime dateTime;   // date and time of the detection event


    public Detection(Car car, String city, LocalDateTime dateTime) {
        this.car = car;
        this.city = city;
        this.dateTime = dateTime;
    }

    /**
     * Parses detection information from a line of text about a car that has entered an environmentally controlled zone
     * of a specified city.
     * the format of the text line is: lisensePlate, city, dateTime
     * The licensePlate shall be matched with a car from the provided list.
     * If no matching car can be found, a new Car shall be instantiated with the given lisensePlate and added to the list
     * (besides the license plate number there will be no other information available about this car)
     *
     * @param textLine
     * @param cars     a list of known cars, ordered and searchable by licensePlate
     *                 (i.e. the indexOf method of the list shall only consider the lisensePlate when comparing cars)
     * @return a new Detection instance with the provided information
     * or null if the textLine is corrupt or incomplete
     */
    public static Detection fromLine(String textLine, List<Car> cars) {// Instantiate detection
        Detection detection = null;

        // Split text into fields
        String[] fields = textLine.split(",");

        // Check if enough fields are present
        if (fields.length >= 3) {
            try {
                // Create new car
                Car car = new Car(fields[0].trim());
                int carIndex = cars.indexOf(car);

                // Check if car is already in the list
                if (carIndex != -1) {
                    car = cars.get(carIndex);
                } else {
                    cars.add(car);
                }

                // Create a new detection
                detection = new Detection(car, fields[1].trim(), LocalDateTime.parse(fields[2].trim()));
            } catch (Exception e) {
                System.out.printf("Could not parse Detection specification in text line '%s'\n", textLine);
                System.out.println(e.getMessage());
            }
        }

        return detection;
    }

    /**
     * Validates a detection against the purple conditions for entering an environmentally restricted zone
     * I.e.:
     * Diesel trucks and diesel coaches with an emission category of below 6 may not enter a purple zone
     *
     * @return a Violation instance if the detection saw an offence against the purple zone rule/
     * null if no offence was found.
     */
    public Violation validatePurple() {
        //prepare checks
        System.out.println(getCar());
        boolean carIsTruckOrCoach = getCar().getCarType() == CarType.Truck || getCar().getCarType() == CarType.Coach;
        boolean carIsDiesel = getCar().getFuelType() == FuelType.Diesel;
        int emissionCategory = getCar().getEmissionCategory();
        //if checks return true then return new Violation
        if (carIsDiesel && carIsTruckOrCoach && emissionCategory < 6) return new Violation(getCar(), getCity());
        //else return null
        return null;
    }

    public Car getCar() {
        return car;
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    @Override
    public String toString() {
        return String.format("%s/%s/%s", getCar().getLicensePlate(), getCity(), getDateTime());       // replace by a proper outcome
    }

}
