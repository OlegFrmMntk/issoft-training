package by.issoft.sample.domain;

import java.util.Objects;
import java.util.UUID;

public class Cargo {

    final private String id;

    final private CargoType type;

    final private int weight;

    public Cargo(int weight) {
        this(CargoType.SOLID, weight);
    }

    public Cargo(CargoType type, int weight) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.weight = weight;
    }

    public static Cargo of(int weight) {
        return new Cargo(weight);
    }

    public static Cargo of(CargoType cargoType, int weight) {
        return new Cargo(cargoType, weight);
    }

    public String getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public CargoType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cargo cargo = (Cargo) o;

        return weight == cargo.weight && type == cargo.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, weight);
    }

    @Override
    public String toString() {
        return String.format("Cargo{id = %s, type = %s, weight = %d}", id, type, weight);
    }
}
