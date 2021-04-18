package by.issoft.sample.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CargoCarriage {

    void loadCargo(@NotNull Cargo cargo);

    void loadCargos(@NotNull List<Cargo> cargo);

    void uploadCargo(Cargo cargo);

    void uploadCargos(List<Cargo> cargo);

    boolean contains(Cargo cargo);
}
