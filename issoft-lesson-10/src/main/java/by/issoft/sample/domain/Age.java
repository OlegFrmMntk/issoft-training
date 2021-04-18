package by.issoft.sample.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;


public record Age(byte value) {

    private static final Map<Byte, Age> agesPool = new HashMap<>();

    public Age {
        checkArgument(value >= 0, "by.issoft.sample.domain.Age must be positive");
        checkArgument(value <= 125, "by.issoft.sample.domain.Age must be <= 125");
    }

    public int intValue() {
        return value;
    }

    public static Age of(int age) {

        byte byteAge = (byte) age;

        final Age fromPool = agesPool.get((byte) age);
        if (fromPool != null) {
            return fromPool;
        }

        final Age newAge = new Age(byteAge);
        agesPool.put(byteAge, newAge);

        return newAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Age age = (Age) o;
        return value == age.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Age = " + value;
    }
}