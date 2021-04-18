package by.issoft.sample.domain;

import java.util.UUID;

public abstract class Carriage {

    final protected String id;

    final protected int capacity;

    protected int fullness;

    protected Carriage nextCarriage;

    public Carriage(int capacity) {
        this(capacity, null);
    }

    public Carriage(int capacity, Carriage nextCarriage) {
        this.id = UUID.randomUUID().toString();
        this.capacity = capacity;
        this.nextCarriage = nextCarriage;
        this.fullness = 0;
    }

    public int getFullness() {
        return fullness;
    }

    public void setNextCarriage(Carriage nextCarriage) {
        this.nextCarriage = nextCarriage;
    }

    public String getId() {
        return id;
    }


    public int getCapacity() {
        return capacity;
    }

    public Carriage getNextCarriage() {
        return nextCarriage;
    }

    @Override
    public String toString() {
        return String.format("%s{id = %s, fullness = %d/%d, nextCarriage = %s}",
                getClass(), id, fullness, capacity, nextCarriage.getId());
    }

}
