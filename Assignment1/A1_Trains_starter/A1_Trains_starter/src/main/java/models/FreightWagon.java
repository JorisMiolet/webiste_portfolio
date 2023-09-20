package models;
// TODO
public class FreightWagon extends Wagon{
    private int maxWeight;
    private int wagonId;
    public FreightWagon(int wagonId, int maxWeight) {
        // TODO
        super(wagonId);
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        // TODO
        return maxWeight;
    }
    @Override
    public String toString() {
        return String.format("[Wagon-%d]", getId());
    }
}
