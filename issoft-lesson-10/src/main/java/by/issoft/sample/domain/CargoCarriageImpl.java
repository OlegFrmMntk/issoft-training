package by.issoft.sample.domain;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class CargoCarriageImpl extends Carriage implements CargoCarriage {

    final private List<Cargo> cargos = new ArrayList<>();

    public CargoCarriageImpl(int capacity) {
        this(capacity, List.of());
    }

    public CargoCarriageImpl(int capacity, Carriage nextCarriage) {
        this(capacity, nextCarriage, List.of());
    }

    public CargoCarriageImpl(int capacity, List<Cargo> cargo) {
        this(capacity, null, cargo);
    }

    public CargoCarriageImpl(int capacity, Carriage nextCarriage, List<Cargo> cargo) {
        super(capacity, nextCarriage);
        loadCargos(cargo);
    }

    public static CargoCarriageImpl of(int capacity) {
        return new CargoCarriageImpl(capacity);
    }

    public static CargoCarriageImpl of(int capacity, List<Cargo> cargos) {
        return new CargoCarriageImpl(capacity, cargos);
    }

    public static CargoCarriageImpl of(int capacity, Carriage nextCarriage) {
        return new CargoCarriageImpl(capacity, nextCarriage);
    }

    public static CargoCarriageImpl of(int capacity, Carriage nextCarriage, List<Cargo> cargos) {
        return new CargoCarriageImpl(capacity, nextCarriage, cargos);
    }

    public void loadCargo(@NotNull Cargo cargo) {
        checkArgument(fullness + cargo.getWeight() <= capacity,
                "by.issoft.sample.domain.CargoCarriage carriage is full");

        this.cargos.add(cargo);
        fullness += cargo.getWeight();
    }

    public void  loadCargos(@NotNull List<Cargo> cargo) {
        for (Cargo cargoItem : cargo) {
            loadCargo(cargoItem);
        }
    }

    public void uploadCargo(Cargo cargo) {
        checkArgument(this.cargos.contains(cargo), "by.issoft.sample.domain.CargoCarriage cargo not found");

        this.cargos.remove(cargo);
        fullness -= cargo.getWeight();
    }

    public void uploadCargos(@NotNull List<Cargo> cargo) {
        for (Cargo cargoItem : cargo) {
            uploadCargo(cargoItem);
        }
    }

    public List<Cargo> getCargos() {
        return List.copyOf(this.cargos);
    }

    public boolean contains(Cargo cargo) {
        return this.cargos.contains(cargo);
    }

}
