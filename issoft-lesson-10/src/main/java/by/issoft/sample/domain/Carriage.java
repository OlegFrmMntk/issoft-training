package by.issoft.sample.domain;

import java.util.UUID;

public abstract class Carriage {

    private final String id;

    protected Carriage nextCarriage;

    public Carriage() {
        this(null);
    }

    public Carriage(Carriage nextCarriage) {
        this.id = UUID.randomUUID().toString();
        this.nextCarriage = nextCarriage;
    }


    public void setNextCarriage(Carriage nextCarriage) {
        this.nextCarriage = nextCarriage;
    }

    public String getId() {
        return id;
    }


    public Carriage getNextCarriage() {
        return nextCarriage;
    }

    @Override
    public String toString() {
        return String.format("%s{id = %s, nextCarriage = %s}",
                getClass(), id, nextCarriage.getId());
    }

}
