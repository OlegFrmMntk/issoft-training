package by.issoft.sample.domain;

import java.util.UUID;

public class Locomotive {

    final private String id;

    final private int traction;

    final private int lifeTime;

    private Driver driver;

    public Locomotive(int traction, int lifeTime) {
        this(traction, lifeTime, null);
    }

    public Locomotive(int traction, int lifeTime, Driver driver) {
        this.id = UUID.randomUUID().toString();
        this.traction = traction;
        this.lifeTime = lifeTime;
        this.driver = driver;
    }

    public static Locomotive of(int traction, int lifeTime) {
        return new Locomotive(traction, lifeTime);
    }

    public static Locomotive of(int traction, int lifeTime, Driver driver) {
        return new Locomotive(traction, lifeTime, driver);
    }

    public String getId() {
        return id;
    }

    public int getTraction() {
        return traction;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return String.format("Locomotive{id = %s, traction = %d, lifetime = %d, driver = %s}",
                id, traction, lifeTime, driver.getId());
    }

}
