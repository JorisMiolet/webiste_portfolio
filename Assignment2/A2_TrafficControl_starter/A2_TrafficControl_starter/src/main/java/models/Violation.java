package models;

import java.util.Comparator;
import java.util.Objects;

public class Violation {
    private final Car car;
    private final String city;
    private int offencesCount;

    public Violation(Car car, String city) {
        this.car = car;
        this.city = city;
        this.offencesCount = 1;
    }

    public static int compareByLicensePlateAndCity(Violation v1, Violation v2) {
        int licensePLateCompare = v1.getCar().getLicensePlate().compareTo(v2.getCar().getLicensePlate());
        if (licensePLateCompare != 0) return licensePLateCompare;

        return v1.getCity().compareTo(v2.getCity());   // replace by a proper outcome
    }


    /**
     * Aggregates this violation with the other violation by adding their counts and
     * nullifying identifying attributes car and/or city that do not match
     * identifying attributes that match are retained in the result.
     * This method can be used for aggregating violations applying different grouping criteria
     *
     * @param other
     * @return a new violation with the accumulated offencesCount and matching identifying attributes.
     */
    public Violation combineOffencesCounts(Violation other) {
        Violation combinedViolation = new Violation(
                // nullify the car attribute iff this.car does not match other.car
                this.car != null && this.car.equals(other.car) ? this.car : null,

                // nullify the city attribute iff this.city does not match other.city
                this.city != null && this.city.equals(other.city) ? this.city : null);

        // add the offences counts of both original violations
        combinedViolation.setOffencesCount(this.offencesCount + other.offencesCount);

        return combinedViolation;
    }

    public static Comparator<Violation> getComparatorByCarAndCity() {
        return Comparator
                .comparing(Violation::getCar)
                .thenComparing(Violation::getCity);
    }

    public Car getCar() {
        return car;
    }

    public String getCity() {
        return city;
    }

    public int getOffencesCount() {
        return offencesCount;
    }

    public void setOffencesCount(int offencesCount) {
        this.offencesCount = offencesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return offencesCount == violation.offencesCount &&
                Objects.equals(car, violation.car) &&
                Objects.equals(city, violation.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car, city, offencesCount);
    }


    @Override
    public String toString() {
        return car.getLicensePlate() + "/" + city + "/" + offencesCount;
    }

}
