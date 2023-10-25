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
     *
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
     *
     * @return
     */
    public boolean isPassengerTrain() {
        return firstWagon instanceof PassengerWagon;   // returns if firstWagon is a passengerWagon
    }

    /**
     * A train is a freight train when its first wagon is a FreightWagon
     * (we do not worry about the posibility of mixed compositions here)
     *
     * @return
     */
    public boolean isFreightTrain() {
        return firstWagon instanceof FreightWagon;   // returns if firstWagon is a freightWagon
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
     *
     * @param wagon the first wagon of a sequence of wagons to be attached (can be null)
     */
    public void setFirstWagon(Wagon wagon) {
        this.firstWagon = wagon;
    }

    /**
     * @return the number of Wagons connected to the train
     */
    public int getNumberOfWagons() {
        Wagon current = firstWagon; // Start from the 'firstWagon'
        int size = 0; // Initialize the size to 0

        // Traverse the sequence while 'current' is not null and increment the size
        while (current != null) {
            current = current.getNextWagon();
            size++;
        }

        return size; // Return the number of wagons in the sequence.
    }

    /**
     * @return the last wagon attached to the train
     */
    public Wagon getLastWagonAttached() {
        Wagon current = firstWagon; // Start from the 'firstWagon'

        // Traverse the sequence while 'current' is not null and it has a next wagon
        while (current != null && current.getNextWagon() != null) {
            current = current.getNextWagon();
        }

        return current; // Return the last attached wagon or the 'firstWagon' if no wagons are attached.
    }


    /**
     * @return the total number of seats on a passenger train
     * (return 0 for a freight train)
     */
    public int getTotalNumberOfSeats() {
        // Check if the train is a freight train
        if (isFreightTrain()) {
            return 0; // Return 0 seats for freight trains.
        }

        Wagon current = firstWagon; // Start from the 'firstWagon'
        int totalSeats = 0; // Initialize the totalSeats to 0

        // Traverse the sequence and accumulate the number of seats in passenger wagons
        while (current != null) {
            if (current instanceof PassengerWagon) {
                totalSeats += ((PassengerWagon) current).getNumberOfSeats();
            }
            current = current.getNextWagon();
        }

        return totalSeats; // Return the total number of seats in the train sequence.
    }

    /**
     * calculates the total maximum weight of a freight train
     *
     * @return the total maximum weight of a freight train
     * (return 0 for a passenger train)
     */
    public int getTotalMaxWeight() {
        // Check if the train is a passenger train
        if (isPassengerTrain()) {
            return 0; // Return 0 for passenger trains.
        }

        Wagon current = firstWagon; // Start from the 'firstWagon'
        int maxWeight = 0; // Initialize the maxWeight to 0

        // Traverse the sequence and accumulate the maximum weight capacity in freight wagons
        while (current != null) {
            if (current instanceof FreightWagon) {
                FreightWagon freightWagon = (FreightWagon) current;
                maxWeight += freightWagon.getMaxWeight();
            }
            current = current.getNextWagon();
        }

        return maxWeight; // Return the total maximum weight capacity of the train sequence.
    }

    /**
     * Finds the wagon at the given position (starting at 0 for the first wagon of the train)
     *
     * @param position
     * @return the wagon found at the given position
     * (return null if the position is not valid for this train)
     */
    public Wagon findWagonAtPosition(int position) {
        // Check if the position is out of bounds (negative or exceeds the number of wagons)
        if (position < 0 || position >= getNumberOfWagons()) {
            return null; // Return null for out-of-bounds positions.
        }

        Wagon current = firstWagon; // Start from the 'firstWagon'
        int currentIndex = 0; // Initialize the current index to 0

        // Traverse the sequence to find the wagon at the specified position
        while (currentIndex < position) {
            current = current.getNextWagon();
            currentIndex++;
        }

        return current; // Return the wagon at the specified position.
    }

    /**
     * Finds the wagon with a given wagonId
     *
     * @param wagonId
     * @return the wagon found
     * (return null if no wagon was found with the given wagonId)
     */
    public Wagon findWagonById(int wagonId) {
        Wagon wagon = firstWagon; // Start from the 'firstWagon'

        // Traverse the sequence to find the wagon with the specified ID
        while (wagon != null) {
            if (wagon.getId() == wagonId) {
                return wagon; // Return the wagon with the specified ID when found.
            }
            wagon = wagon.getNextWagon();
        }

        return null; // Return null if no wagon with the given ID is found.
    }

    /**
     * Determines if the given sequence of wagons can be attached to this train
     * Verifies if the type of wagons match the type of train (Passenger or Freight)
     * Verifies that the capacity of the engine is sufficient to also pull the additional wagons
     * Verifies that the wagon is not part of the train already
     * Ignores the predecessors before the head wagon, if any
     *
     * @param wagon the head wagon of a sequence of wagons to consider for attachment
     * @return whether type and capacity of this train can accommodate attachment of the sequence
     */
    public boolean canAttach(Wagon wagon) {
        // Check if the wagon is null
        if (wagon == null) {
            return false; // Cannot attach a null wagon.
        }

        // Check if a wagon with the same ID already exists in the train
        if (this.findWagonById(wagon.getId()) != null) {
            return false; // Cannot attach a wagon with the same ID.
        }

        // Check compatibility based on the type of train and wagon
        if ((this.isFreightTrain() && !(wagon instanceof FreightWagon)) ||
                (this.isPassengerTrain() && !(wagon instanceof PassengerWagon))) {
            return false; // Incompatible types of train and wagon.
        }

        // Calculate the total weight if attached, including this train and the new wagon,
        // and check if it doesn't exceed the maximum allowed by the engine.
        int totalWeight = wagon.getSequenceLength() + this.getNumberOfWagons();
        return totalWeight <= engine.getMaxWagons();
    }


    /**
     * Tries to attach the given sequence of wagons to the rear of the train
     * No change is made if the attachment cannot be made.
     * (when the sequence is not compatible or the engine has insufficient capacity)
     * if attachment is possible, the head wagon is first detached from its predecessors, if any
     *
     * @param wagon the head wagon of a sequence of wagons to be attached
     * @return whether the attachment could be completed successfully
     */
    public boolean attachToRear(Wagon wagon) {
        // Check if the wagon can be attached based on defined conditions
        if (!canAttach(wagon)) {
            return false; // Attachment is not allowed, return false.
        }

        // If the wagon has a previous wagon, detach it from the front
        if (wagon.hasPreviousWagon()) {
            wagon.detachFront();
        }

        // Attach the wagon to the rear of the train
        if (firstWagon == null) {
            firstWagon = wagon; // If there are no wagons in the train, set the firstWagon to this wagon.
        } else {
            Wagon lastWagon = getLastWagonAttached(); // Find the last attached wagon
            lastWagon.attachTail(wagon); // Attach the new wagon to the tail of the last wagon.
        }

        return true; // Attachment is successful, return true.
    }


    /**
     * Tries to insert the given sequence of wagons at the front of the train
     * (the front is at position one, before the current first wagon, if any)
     * No change is made if the insertion cannot be made.
     * (when the sequence is not compatible or the engine has insufficient capacity)
     * if insertion is possible, the head wagon is first detached from its predecessors, if any
     *
     * @param wagon the head wagon of a sequence of wagons to be inserted
     * @return whether the insertion could be completed successfully
     */
    public boolean insertAtFront(Wagon wagon) {
        // Check if the wagon can be attached based on defined conditions
        if (!canAttach(wagon) || wagon == null) {
            return false; // Insertion is not allowed or the wagon is null, return false.
        }

        // Detach the wagon from its current position if attached elsewhere
        wagon.detachFront();

        // If there are already wagons in the train, attach the wagon's sequence to the front
        if (firstWagon != null) {
            wagon.getLastWagonAttached().attachTail(firstWagon);
        }

        firstWagon = wagon; // Set the inserted wagon as the new firstWagon

        return true; // Insertion is successful, return true.
    }


    /**
     * Tries to insert the given sequence of wagons at/before the given position in the train.
     * (The current wagon at given position including all its successors shall then be reattached
     * after the last wagon of the given sequence.)
     * No change is made if the insertion cannot be made.
     * (when the sequence is not compatible or the engine has insufficient capacity
     * or the given position is not valid for insertion into this train)
     * if insertion is possible, the head wagon of the sequence is first detached from its predecessors, if any
     *
     * @param position the position where the head wagon and its successors shall be inserted
     *                 0 <= position <= numWagons
     *                 (i.e. insertion immediately after the last wagon is also possible)
     * @param wagon    the head wagon of a sequence of wagons to be inserted
     * @return whether the insertion could be completed successfully
     */
    public boolean insertAtPosition(int position, Wagon wagon) {
        // Check if the position is out of bounds, or if wagon attachment is not allowed
        if (position < 0 || position > getNumberOfWagons() || !canAttach(wagon)) {
            return false; // Insertion is not allowed, return false.
        }

        // Detach the wagon from its current position if attached elsewhere
        if (wagon.hasPreviousWagon()) {
            wagon.getPreviousWagon().setNextWagon(null);
            wagon.setPreviousWagon(null);
        }

        // Insert the wagon at the specified position
        if (position == 0) {
            if (this.firstWagon != null) {
                wagon.getLastWagonAttached().setNextWagon(this.firstWagon);
                this.firstWagon.setPreviousWagon(wagon.getLastWagonAttached());
            }
            this.firstWagon = wagon; // Set the inserted wagon as the new firstWagon
            return true;
        } else {
            Wagon previousWagon = findWagonAtPosition(position - 1); // Find the wagon before the specified position

            if (previousWagon.hasNextWagon()) {
                wagon.getLastWagonAttached().setNextWagon(previousWagon.getNextWagon());
                previousWagon.getNextWagon().setPreviousWagon(wagon.getLastWagonAttached());
            }
            wagon.setPreviousWagon(previousWagon);
            previousWagon.setNextWagon(wagon);
            return true;
        }
    }


    /**
     * Tries to remove one Wagon with the given wagonId from this train
     * and attach it at the rear of the given toTrain
     * No change is made if the removal or attachment cannot be made
     * (when the wagon cannot be found, or the trains are not compatible
     * or the engine of toTrain has insufficient capacity)
     *
     * @param wagonId the id of the wagon to be removed
     * @param toTrain the train to which the wagon shall be attached
     *                toTrain shall be different from this train
     * @return whether the move could be completed successfully
     */
    public boolean moveOneWagon(int wagonId, Train toTrain) {
        // Check if the source and target trains are the same or if the target train is null
        if (this == toTrain || toTrain == null) {
            return false; // Cannot move the wagon to the same train or to a null train.
        }

        // Find the wagon with the specified ID
        Wagon wagon = findWagonById(wagonId);
        if (wagon == null) {
            return false; // Wagon with the specified ID not found, return false.
        }

        // Store references to the wagon's front and tail wagons
        Wagon frontWagon = wagon.getPreviousWagon();
        Wagon tailWagon = wagon.getNextWagon();

        // Remove the wagon from this train's sequence
        wagon.removeFromSequence();

        // Check if the wagon can be attached to the target train
        if (!toTrain.canAttach(wagon)) {
            // If not, reattach the wagon to its previous front and tail wagons
            if (frontWagon != null) {
                wagon.reAttachTo(frontWagon);
            }
            if (tailWagon != null) {
                tailWagon.reAttachTo(wagon);
            }
            return false; // Attachment to the target train is not allowed, return false.
        }

        // If the wagon was at the front of this train, update the firstWagon reference
        if (frontWagon == null && tailWagon != null) {
            setFirstWagon(tailWagon);
        }

        // Attach the wagon to the rear of the target train
        return toTrain.attachToRear(wagon);
    }

    /**
     * Tries to split this train before the wagon at given position and move the complete sequence
     * of wagons from the given position to the rear of toTrain.
     * No change is made if the split or re-attachment cannot be made
     * (when the position is not valid for this train, or the trains are not compatible
     * or the engine of toTrain has insufficient capacity)
     *
     * @param position 0 <= position < numWagons
     * @param toTrain  the train to which the split sequence shall be attached
     *                 toTrain shall be different from this train
     * @return whether the move could be completed successfully
     */
    public boolean splitAtPosition(int position, Train toTrain) {
        // Check if the position is valid, toTrain is not null, and it's not the same train
        if (position < 0 || position >= getNumberOfWagons() || toTrain == null || toTrain == this) {
            return false; // Invalid split parameters, return false.
        }

        // Find the wagon at the specified position
        Wagon splitWagon = findWagonAtPosition(position);

        // Check if the split is valid based on the type of toTrain
        if (splitWagon == null || (toTrain.isFreightTrain() && !(splitWagon instanceof FreightWagon)) ||
                (toTrain.isPassengerTrain() && !(splitWagon instanceof PassengerWagon))) {
            return false; // Invalid split, return false.
        }

        // Calculate the total weight of the split and the toTrain
        int totalWeight = splitWagon.getSequenceLength() + toTrain.getNumberOfWagons();

        // Check if the total weight exceeds the capacity of toTrain's engine
        if (totalWeight > toTrain.getEngine().getMaxWagons()) {
            return false; // Exceeds the capacity of the target train's engine, return false.
        }

        // Perform the split by updating references
        if (position == 0) {
            firstWagon = null; // If splitWagon is at the front, set firstWagon to null.
        } else {
            Wagon prevWagon = findWagonAtPosition(position - 1);
            prevWagon.setNextWagon(null);
            splitWagon.setPreviousWagon(null);
        }

        // Try to attach splitWagon to the rear of toTrain
        if (toTrain.attachToRear(splitWagon)) {
            return true; // Split and move successful, return true.
        }
        // If attachment fails, rearrange the wagons
        if (position == 0) {
            firstWagon = splitWagon;
            return false;
        }
        Wagon afterWagon = findWagonAtPosition(position + 1);
        if (afterWagon != null) {
            afterWagon.attachTail(splitWagon);
            return false;
        }
        toTrain.getLastWagonAttached().attachTail(splitWagon);
        return false;

    }


    /**
     * Reverses the sequence of wagons in this train (if any)
     * i.e. the last wagon becomes the first wagon
     * the previous wagon of the last wagon becomes the second wagon
     * etc.
     * (No change if the train has no wagons or only one wagon)
     */
    public void reverse() {
        // Check if the train is empty or contains only one wagon
        if (firstWagon == null || !firstWagon.hasNextWagon()) {
            return; // No need to reverse, return.
        }

        // Reverse the sequence of wagons and update the firstWagon reference
        this.firstWagon = firstWagon.reverseSequence();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        // Append the engine's string representation to the StringBuilder
        stringBuilder.append(engine.toString());

        // Iterate through the wagons and append their string representations to the StringBuilder
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

        // Append information about the number of wagons, origin, and destination
        stringBuilder.append(" with ")
                .append(getNumberOfWagons())
                .append(" wagons ")
                .append("from ")
                .append(origin)
                .append(" to ")
                .append(destination);

        return stringBuilder.toString(); // Return the generated string representation.
    }
}
