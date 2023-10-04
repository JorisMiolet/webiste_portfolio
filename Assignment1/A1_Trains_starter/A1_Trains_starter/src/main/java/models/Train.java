package models;

public class Train {
    private final String origin;
    private final String destination;
    private final Locomotive engine;
    private Wagon firstWagon;

    /* Representation invariants:
        firstWagon == null || firstWagon.previousWagon == null
        engine != null
     */

    public Train(Locomotive engine, String origin, String destination) {
        this.engine = engine;
        this.destination = destination;
        this.origin = origin;
    }

    /**
     * Indicates whether the train has at least one connected Wagon
     * @return
     */
    public boolean hasWagons() {
        // TODO

        if (firstWagon != null) {
            return true;
        }

        return false;  // replace by proper outcome
    }

    /**
     * A train is a passenger train when its first wagon is a PassengerWagon
     * (we do not worry about the posibility of mixed compositions here)
     * @return
     */
    public boolean isPassengerTrain() {
        // TODO
        return firstWagon instanceof PassengerWagon;   // replace by proper outcome
    }

    /**
     * A train is a freight train when its first wagon is a FreightWagon
     * (we do not worry about the posibility of mixed compositions here)
     * @return
     */
    public boolean isFreightTrain() {
        // TODO
        return firstWagon instanceof FreightWagon;   // replace by proper outcome
    }

    public Locomotive getEngine() {
        return engine;
    }

    public Wagon getFirstWagon() {
        return firstWagon;
    }

    /**
     * Replaces the current sequence of wagons (if any) in the train
     * by the given new sequence of wagons (if any)
     * @param wagon the first wagon of a sequence of wagons to be attached (can be null)
     */
    public void setFirstWagon(Wagon wagon) {
        // TODO
        this.firstWagon = wagon;
    }

    /**
     * @return  the number of Wagons connected to the train
     */
    public int getNumberOfWagons() {
        // TODO
        Wagon current = firstWagon;
        int size = 0;
        while(current != null){
            current = current.getNextWagon();
            size++;
        }

        return size;   // replace by proper outcome
    }

    /**
     * @return  the last wagon attached to the train
     */
    public Wagon getLastWagonAttached() {
        // TODO
        Wagon current = firstWagon;
        while(current != null && current.getNextWagon() != null){
            current = current.getNextWagon();
        }
        return current;    // replace by proper outcome
    }

    /**
     * @return  the total number of seats on a passenger train
     *          (return 0 for a freight train)
     */
    public int getTotalNumberOfSeats() {
        // TODO
        if(isFreightTrain())return 0;

        Wagon current = firstWagon;
        int totalSeats = 0;
        while(current != null){
            if(current instanceof PassengerWagon)totalSeats += ((PassengerWagon) current).getNumberOfSeats();
            current = current.getNextWagon();
        }

        return totalSeats;   // replace by proper outcome
    }

    /**
     * calculates the total maximum weight of a freight train
     * @return  the total maximum weight of a freight train
     *          (return 0 for a passenger train)
     *
     */
    public int getTotalMaxWeight() {
        // TODO
        if(isPassengerTrain())return 0;

        Wagon current = firstWagon;
        int maxWeight = 0;
        while(current != null){
            if(current instanceof FreightWagon) {
                FreightWagon freightWagon = (FreightWagon) current;
                maxWeight += freightWagon.getMaxWeight();
            }
            current = current.getNextWagon();
        }

        return maxWeight;
    }

     /**
     * Finds the wagon at the given position (starting at 0 for the first wagon of the train)
     * @param position
     * @return  the wagon found at the given position
     *          (return null if the position is not valid for this train)
     */
    public Wagon findWagonAtPosition(int position) {
        // TODO
        if(position < 0 || position > getNumberOfWagons())return null;
        Wagon current = firstWagon;
        int currentIndex = 0;
        while(currentIndex < position){
            current = current.getNextWagon();
            currentIndex++;
        }

        return current;    // replace by proper outcome
    }

    /**
     * Finds the wagon with a given wagonId
     * @param wagonId
     * @return  the wagon found
     *          (return null if no wagon was found with the given wagonId)
     */
    public Wagon findWagonById(int wagonId) {
        // TODO
        Wagon wagon = firstWagon;
        while(wagon != null) {
            if (wagon.getId() == wagonId) {
                return wagon;
            }
            wagon = wagon.getNextWagon();
        }

        return null;    // replace by proper outcome
    }

    /**
     * Determines if the given sequence of wagons can be attached to this train
     * Verifies if the type of wagons match the type of train (Passenger or Freight)
     * Verifies that the capacity of the engine is sufficient to also pull the additional wagons
     * Verifies that the wagon is not part of the train already
     * Ignores the predecessors before the head wagon, if any
     * @param wagon the head wagon of a sequence of wagons to consider for attachment
     * @return whether type and capacity of this train can accommodate attachment of the sequence
     */
    public boolean canAttach(Wagon wagon) {
        if (wagon == null) {

            return false;
        }

        if (this.findWagonById(wagon.id) != null) {
            return false;
        }

        if (this.isFreightTrain() && !(wagon instanceof FreightWagon)||this.isPassengerTrain() && !(wagon instanceof
                PassengerWagon)) {
            return false;
        }

        int totalWeight=wagon.getSequenceLength()+this.getNumberOfWagons();
        if (totalWeight > engine.getMaxWagons()) {

            return false;
        }
        return true;
    }


    /**
     * Tries to attach the given sequence of wagons to the rear of the train
     * No change is made if the attachment cannot be made.
     * (when the sequence is not compatible or the engine has insufficient capacity)
     * if attachment is possible, the head wagon is first detached from its predecessors, if any
     * @param wagon the head wagon of a sequence of wagons to be attached
     * @return  whether the attachment could be completed successfully
     */
    public boolean attachToRear(Wagon wagon) {
        if (!canAttach(wagon)) {
            return false;
        }

        if (wagon.hasPreviousWagon()) {
            wagon.detachFront();
        }

        if (firstWagon == null) {
            firstWagon = wagon;
        } else {
            Wagon lastWagon = getLastWagonAttached();
            lastWagon.attachTail(wagon);
        }

        return true;
    }



    /**
     * Tries to insert the given sequence of wagons at the front of the train
     * (the front is at position one, before the current first wagon, if any)
     * No change is made if the insertion cannot be made.
     * (when the sequence is not compatible or the engine has insufficient capacity)
     * if insertion is possible, the head wagon is first detached from its predecessors, if any
     * @param wagon the head wagon of a sequence of wagons to be inserted
     * @return  whether the insertion could be completed successfully
     */
    public boolean insertAtFront(Wagon wagon) {
        if (!canAttach(wagon) || wagon == null) {
            return false;
        }

        wagon.detachFront();

        if (firstWagon != null) {
            wagon.getLastWagonAttached().attachTail(firstWagon);
        }

        firstWagon = wagon;

        return true;
    }


    /**
     * Tries to insert the given sequence of wagons at/before the given position in the train.
     * (The current wagon at given position including all its successors shall then be reattached
     *    after the last wagon of the given sequence.)
     * No change is made if the insertion cannot be made.
     * (when the sequence is not compatible or the engine has insufficient capacity
     *    or the given position is not valid for insertion into this train)
     * if insertion is possible, the head wagon of the sequence is first detached from its predecessors, if any
     * @param position the position where the head wagon and its successors shall be inserted
     *                 0 <= position <= numWagons
     *                 (i.e. insertion immediately after the last wagon is also possible)
     * @param wagon the head wagon of a sequence of wagons to be inserted
     * @return  whether the insertion could be completed successfully
     */
    public boolean insertAtPosition(int position, Wagon wagon) {

        if (position < 0 || position > getNumberOfWagons() || !canAttach(wagon)) {
            return false;
        }

        if (wagon.hasPreviousWagon()) {
            wagon.getPreviousWagon().setNextWagon(null);
            wagon.setPreviousWagon(null);
        }


        if (position == 0) {
            if (this.firstWagon != null) {
                wagon.getLastWagonAttached().setNextWagon(this.firstWagon);
                this.firstWagon.setPreviousWagon(wagon.getLastWagonAttached());
            }
            this.firstWagon = wagon;
        }
        else {
            Wagon previousWagon = findWagonAtPosition(position - 1);
            wagon.getLastWagonAttached().setNextWagon(previousWagon.getNextWagon());
            wagon.setPreviousWagon(previousWagon);
            previousWagon.setNextWagon(wagon);
        }
        return true;
    }


    /**
     * Tries to remove one Wagon with the given wagonId from this train
     * and attach it at the rear of the given toTrain
     * No change is made if the removal or attachment cannot be made
     * (when the wagon cannot be found, or the trains are not compatible
     * or the engine of toTrain has insufficient capacity)
     * @param wagonId   the id of the wagon to be removed
     * @param toTrain   the train to which the wagon shall be attached
     *                  toTrain shall be different from this train
     * @return  whether the move could be completed successfully
     */
    public boolean moveOneWagon(int wagonId, Train toTrain) {
        if (this == toTrain || toTrain == null) {
            return false;
        }

        Wagon wagon = findWagonById(wagonId);
        if (wagon == null) {
            return false;
        }

        Wagon frontWagon = wagon.getPreviousWagon();
        Wagon tailWagon = wagon.getNextWagon();
        wagon.removeFromSequence();
        if (!toTrain.canAttach(wagon)) {
            if (frontWagon != null) {
                wagon.reAttachTo(frontWagon);
            }
            if (tailWagon != null) {
                tailWagon.reAttachTo(wagon);
            }
            return false;
        }

        if (frontWagon == null && tailWagon != null) {
            setFirstWagon(tailWagon);
        }
        return toTrain.attachToRear(wagon);
    }


    /**
     * Tries to split this train before the wagon at given position and move the complete sequence
     * of wagons from the given position to the rear of toTrain.
     * No change is made if the split or re-attachment cannot be made
     * (when the position is not valid for this train, or the trains are not compatible
     * or the engine of toTrain has insufficient capacity)
     * @param position  0 <= position < numWagons
     * @param toTrain   the train to which the split sequence shall be attached
     *                  toTrain shall be different from this train
     * @return  whether the move could be completed successfully
     */
    public boolean splitAtPosition(int position, Train toTrain) {
        if (position < 0 || position >= getNumberOfWagons() || toTrain == null || toTrain == this) return false;

        Wagon splitWagon = findWagonAtPosition(position);

        if (splitWagon == null) return false;
        if (toTrain.isFreightTrain()){
            if(!(splitWagon instanceof FreightWagon))return false;
        }
        if(toTrain.isPassengerTrain()){
            if(!(splitWagon instanceof  PassengerWagon))return false;
        }

        int totalWeight = splitWagon.getSequenceLength() + toTrain.getNumberOfWagons();

        if (totalWeight > toTrain.getEngine().getMaxWagons()) return false;

        if (position == 0) {
            firstWagon = null;
        } else {
            Wagon prevWagon = findWagonAtPosition(position - 1);
            prevWagon.setNextWagon(null);
            splitWagon.setPreviousWagon(null);
        }

        if (!toTrain.attachToRear(splitWagon)) {
            if (position == 0) {
                firstWagon = splitWagon;
            } else {
                Wagon afterWagon = findWagonAtPosition(position + 1);
                if (afterWagon != null) {
                    afterWagon.attachTail(splitWagon); // Reattach after the found wagon
                } else {
                    Wagon lastWagon = toTrain.getLastWagonAttached();
                    lastWagon.attachTail(splitWagon);
                }
            }
            return false;
        }

        return true;
    }


    /**
     * Reverses the sequence of wagons in this train (if any)
     * i.e. the last wagon becomes the first wagon
     *      the previous wagon of the last wagon becomes the second wagon
     *      etc.
     * (No change if the train has no wagons or only one wagon)
     */
    public void reverse() {
        if (firstWagon == null || !firstWagon.hasNextWagon()) {
            return;
        }

        this.firstWagon = firstWagon.reverseSequence();
    }

    // TODO string representation of a train
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[Loc-").append(engine.getLocNumber()).append("]");


        Wagon currentWagon = firstWagon;
        boolean isFirstWagon = true;

        while (currentWagon != null) {
            if (!isFirstWagon) {
                stringBuilder.append(",");
            } else {
                isFirstWagon = false;
            }

            stringBuilder.append(currentWagon);

            currentWagon = currentWagon.getNextWagon();
        }

        stringBuilder.append(" with ")
                .append(getNumberOfWagons())
                .append(" wagons ")
                .append("from ")
                .append(origin)
                .append(" to ")
                .append(destination);

        return stringBuilder.toString();
    }
}