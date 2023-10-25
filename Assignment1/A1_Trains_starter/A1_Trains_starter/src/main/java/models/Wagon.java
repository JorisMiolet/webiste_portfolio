package models;

public abstract class Wagon {
    protected int id;               // some unique ID of a Wagon
    private Wagon nextWagon;        // another wagon that is appended at the tail of this wagon
                                    // a.k.a. the successor of this wagon in a sequence
                                    // set to null if no successor is connected
    private Wagon previousWagon;    // another wagon that is prepended at the front of this wagon
                                    // a.k.a. the predecessor of this wagon in a sequence
                                    // set to null if no predecessor is connected


    // representation invariant propositions:
    // tail-connection-invariant:   wagon.nextWagon == null or wagon == wagon.nextWagon.previousWagon
    // front-connection-invariant:  wagon.previousWagon == null or wagon = wagon.previousWagon.nextWagon

    public Wagon (int wagonId) {
        this.id = wagonId;
    }

    public int getId() {
        return id;
    }

    public Wagon getNextWagon() {
        return nextWagon;
    }

    public Wagon getPreviousWagon() {
        return previousWagon;
    }

    /**
     * @return  whether this wagon has a wagon appended at the tail
     */
    public boolean hasNextWagon() {

        return this.nextWagon != null;
    }

    /**
     * @return  whether this wagon has a wagon prepended at the front
     */
    public boolean hasPreviousWagon() {

        return this.previousWagon != null;
    }

    /**
     * Returns the last wagon attached to it,
     * if there are no wagons attached to it then this wagon is the last wagon.
     * @return  the last wagon
     */
    public Wagon getLastWagonAttached() {
        Wagon current = this; // Initialize 'current' with this wagon

        // Traverse the sequence while there's a next wagon and update 'current'
        while(current.hasNextWagon()){
            current = current.getNextWagon();
        }

        return current; // Return the last wagon attached, or itself if no wagons are attached.
    }

    /**
     * @return  the length of the sequence of wagons towards the end of its tail
     * including this wagon itself.
     */
    public int getSequenceLength() {
        int size = 1; // Initialize the size to 1 since this wagon is part of the sequence
        Wagon current = this; // Start from this wagon

        // Traverse the sequence while there's a next wagon and increment the size
        while(current.hasNextWagon()){
            current = current.getNextWagon();
            size++;
        }

        return size; // Return the length of the sequence
    }

    /**
     * Attaches the tail wagon and its connected successors behind this wagon,
     * if and only if this wagon has no wagon attached at its tail
     * and if the tail wagon has no wagon attached in front of it.
     * @param tail the wagon to attach behind this wagon.
     * @throws IllegalStateException if this wagon already has a wagon appended to it.
     * @throws IllegalStateException if tail is already attached to a wagon in front of it.
     *          The exception should include a message that reports the conflicting connection,
     *          e.g.: "%s is already pulling %s"
     *          or:   "%s has already been attached to %s"
     */
    public void attachTail(Wagon tail) {
        // Check if this wagon has a next wagon attached
        if (this.hasNextWagon()) {
            throw new IllegalStateException(String.format("%s is already pulling %s", this, this.getNextWagon()));
        }

        // Check if the 'tail' wagon has a previous wagon attached
        if (tail.hasPreviousWagon()) {
            throw new IllegalStateException(String.format("%s has already been attached to %s", tail, tail.getPreviousWagon()));
        }

        // Establish the connection: set 'this' as the previous wagon of 'tail'
        // and set 'tail' as the next wagon of 'this'
        tail.setPreviousWagon(this);
        this.setNextWagon(tail);
    }
    public void setNextWagon(Wagon nextWagon) {
        this.nextWagon = nextWagon;
    }

    public void setPreviousWagon(Wagon previousWagon) {
        this.previousWagon = previousWagon;
    }

    /**
     * Detaches the tail from this wagon and returns the first wagon of this tail.
     * @return the first wagon of the tail that has been detached
     *          or <code>null</code> if it had no wagons attached to its tail.
     */
    public Wagon detachTail() {
        // Check if this wagon has a next wagon
        if (!this.hasNextWagon()) {
            return null; // If there's no next wagon, no detachment occurs, return null.
        }

        // Store the reference to the next wagon, which will be detached
        Wagon toReturn = this.getNextWagon();

        // Update the previous wagon of the next wagon to skip this wagon
        this.getNextWagon().setPreviousWagon(null);

        // Set the next wagon of this wagon to null to detach it
        this.setNextWagon(null);

        return toReturn; // Return the detached wagon
    }

    /**
     * Detaches this wagon from the wagon in front of it.
     * No action if this wagon has no previous wagon attached.
     * @return  the former previousWagon that has been detached from,
     *          or <code>null</code> if it had no previousWagon.
     */
    public Wagon detachFront() {
        // Check if this wagon has a previous wagon attached
        if (!this.hasPreviousWagon()) {
            return null; // If there's no previous wagon, no detachment occurs.
        }

        // Store the reference to the previous wagon, which will be detached
        Wagon toReturn = this.getPreviousWagon();

        // Update the next wagon of the previous wagon to skip this wagon
        this.getPreviousWagon().setNextWagon(null);

        // Set the previous wagon of this wagon to null to detach it
        this.setPreviousWagon(null);

        return toReturn; // Return the detached wagon
    }

    /**
     * Replaces the tail of the <code>front</code> wagon by this wagon and its connected successors
     * Before such reconfiguration can be made,
     * the method first disconnects this wagon form its predecessor,
     * and the <code>front</code> wagon from its current tail.
     * @param front the wagon to which this wagon must be attached to.
     */
    public void reAttachTo(Wagon front) {
        // Check if the 'front' wagon has a next wagon
        if (front.hasNextWagon()) {
            // Disconnect the 'front' wagon from its current tail
            front.getNextWagon().setPreviousWagon(null);
            front.setNextWagon(null);
        }

        // Check if this wagon has a previous wagon
        if (this.hasPreviousWagon()) {
            // Disconnect this wagon from its previous predecessor
            this.getPreviousWagon().setNextWagon(null);
            this.setPreviousWagon(null);
        }

        // Establish new connections: set this wagon as the previous wagon of 'front'
        // and set 'front' as the next wagon of this wagon
        this.setPreviousWagon(front);
        front.setNextWagon(this);
    }

    /**
     * Removes this wagon from the sequence that it is part of,
     * and reconnects its tail to the wagon in front of it, if any.
     */
    public void removeFromSequence() {
        // Check if this wagon has a next wagon
        if(this.hasNextWagon()){
            // Update the previous wagon of the next wagon to skip this wagon
            this.getNextWagon().setPreviousWagon(this.getPreviousWagon());
        }

        // Check if this wagon has a previous wagon
        if(this.hasPreviousWagon()){
            // Update the next wagon of the previous wagon to skip this wagon
            this.getPreviousWagon().setNextWagon(this.getNextWagon());
        }

        // Set the next and previous wagons of this wagon to null to detach it
        this.setNextWagon(null);
        this.setPreviousWagon(null);
    }


    /**
     * Reverses the order in the sequence of wagons from this Wagon until its final successor.
     * The reversed sequence is attached again to the wagon in front of this Wagon, if any.
     * No action if this Wagon has no succeeding next wagon attached.
     * @return the new start Wagon of the reversed sequence (with is the former last Wagon of the original sequence)
     */
    public Wagon reverseSequence() {
        // Check if this wagon is the last in the sequence
        if (!this.hasNextWagon()) {
            return this; // If it's the last, return itself, no reversal needed.
        }

        // Reverse the sequence starting from the next wagon
        Wagon newFront = this.getNextWagon().reverseSequence();

        // Remove this wagon from the sequence
        this.removeFromSequence();

        // Attach the reversed sequence to the last wagon of the original sequence
        newFront.getLastWagonAttached().attachTail(this);

        // Return the new start wagon of the reversed sequence
        return newFront;
    }




}
