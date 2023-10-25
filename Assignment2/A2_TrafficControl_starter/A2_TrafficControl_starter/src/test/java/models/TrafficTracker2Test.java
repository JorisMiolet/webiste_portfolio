package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrafficTracker2Test {

    TrafficTracker trafficTracker;

    @BeforeEach
    public void setup() {
        trafficTracker = new TrafficTracker();
    }

    @Test
    public void testCalculateTotalFines() {
        Violation violation1 = new Violation(new Car("1-TTT-01", Car.CarType.Truck), "City1");
        violation1.setOffencesCount(2);  // Truck with 2 offenses

        Violation violation2 = new Violation(new Car("1-CCC-01", Car.CarType.Coach), "City2");
        violation2.setOffencesCount(3);  // Coach with 3 offenses

        Violation violation3 = new Violation(new Car("1-TTT-02", Car.CarType.Truck), "City1");
        violation3.setOffencesCount(1);  // Truck with 1 offense

        trafficTracker.getViolations().add(violation1);
        trafficTracker.getViolations().add(violation2);
        trafficTracker.getViolations().add(violation3);

        // Calculate the total fines
        double totalFines = trafficTracker.calculateTotalFines();

        assertEquals(180.0, totalFines, 0.01);
    }

    @Test
    public void testTopViolationsByCar() {
        // Create a list of violations
        Violation violation1 = new Violation(new Car("1-TTT-01", Car.CarType.Truck), "City1");
        violation1.setOffencesCount(2);  // Truck with 2 offenses

        Violation violation2 = new Violation(new Car("1-CCC-01", Car.CarType.Coach), "City2");
        violation2.setOffencesCount(3);  // Coach with 3 offenses

        Violation violation3 = new Violation(new Car("1-TTT-02", Car.CarType.Truck), "City1");
        violation3.setOffencesCount(1);  // Truck with 1 offense

        trafficTracker.getViolations().add(violation1);
        trafficTracker.getViolations().add(violation2);
        trafficTracker.getViolations().add(violation3);

        List<Violation> topViolations = trafficTracker.topViolationsByCar(2);

        List<Violation> expectedTopViolations = new ArrayList<>();
        expectedTopViolations.add(violation2);  // Coach with 3 offenses
        expectedTopViolations.add(violation1);  // Truck with 2 offenses

        assertEquals(expectedTopViolations, topViolations);
    }

    @Test
    public void testTopViolationsByCity() {
        Violation violation1 = new Violation(new Car("1-TTT-01", Car.CarType.Truck), "City1");
        violation1.setOffencesCount(1);

        Violation violation2 = new Violation(new Car("1-CCC-01", Car.CarType.Coach), "City2");
        violation2.setOffencesCount(1);

        trafficTracker.getViolations().add(violation1);
        trafficTracker.getViolations().add(violation2);

        List<Violation> topViolations = new ArrayList<>(trafficTracker.topViolationsByCity(2));

        List<Violation> expectedTopViolations = new ArrayList<>();
        expectedTopViolations.add(new Violation(new Car("1-TTT-01", Car.CarType.Truck), "City1"));
        expectedTopViolations.add(new Violation(new Car("1-CCC-01", Car.CarType.Coach), "City2"));

        Assertions.assertEquals(expectedTopViolations, topViolations);
    }


}
