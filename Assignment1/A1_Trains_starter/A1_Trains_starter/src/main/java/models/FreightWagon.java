package models;
public class FreightWagon extends Wagon{
    private int maxWeight;
    public FreightWagon(int wagonId, int maxWeight) {
        super(wagonId);
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
    @Override
    public String toString() {
        return String.format("[Wagon-%d]", getId());
    }
}
