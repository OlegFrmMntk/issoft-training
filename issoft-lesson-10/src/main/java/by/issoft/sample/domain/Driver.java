package by.issoft.sample.domain;

import static com.google.common.base.Preconditions.checkArgument;

public class Driver {

    private final User user;

    private boolean licence;

    public Driver(User user) {
        this(user, false);
    }

    public Driver(User user, boolean licence) {
        this.user = user;

        checkArgument(user.getAge().intValue() >= 18,
                "by.issoft.sample.domain.Driver Age must be >= 18");

        this.licence = licence;
    }

    public static Driver of(User user) {
        return new Driver(user);
    }

    public static Driver of(User user, boolean licence) {
        return new Driver(user, licence);
    }

    public boolean hasLicence() {
        return this.licence;
    }

    public void setLicence(boolean licence) {
        this.licence = licence;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return String.format("%s{user = %s, hasLicense = %s}", getClass(), user, licence);
    }
}
