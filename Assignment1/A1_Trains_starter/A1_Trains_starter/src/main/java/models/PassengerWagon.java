package models;
// TODO
public class PassengerWagon extends Wagon{
        private int wagonId;
        private int numberOfSeats;
    public PassengerWagon(int wagonId, int numberOfSeats) {
        super(wagonId);
        // TODO
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        // TODO

        return numberOfSeats;
    }

    @Override
    public String toString() {
        return String.format("[Wagon-%d]", getId());
    }
}
