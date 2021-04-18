package by.issoft.sample.domain;

import static com.google.common.base.Preconditions.checkArgument;

public class Driver extends User {

    private boolean licence;

    public Driver(String firstName, String lastName, Age age) {
        this(firstName, lastName, age, false);
    }

    public Driver(String firstName, String lastName, Age age, boolean licence) {
        super(firstName, lastName, age);

        checkArgument(age.intValue() >= 18, "by.issoft.sample.domain.Driver Age must be >= 18");

        this.licence = licence;
    }

    public static Driver of(String firstName, String lastName, Age age) {
        return new Driver(firstName, lastName, age);
    }

    public static Driver of(String firstName, String lastName, Age age, boolean licence) {
        return new Driver(firstName, lastName, age, licence);
    }

    public boolean hasLicence() {
        return this.licence;
    }

    public void setLicenceStatus(boolean licence) {
        this.licence = licence;
    }

    @Override
    public void setAge(Age age) {
        checkArgument(age.intValue() >= 18, "by.issoft.sample.domain.Driver Age must be >= 18");
        this.age = age;
    }

}
