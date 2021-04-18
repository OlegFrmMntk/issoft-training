package by.issoft.sample.domain;

import java.util.UUID;

public class User {

    protected String id;

    protected final String firstName;
    protected final String lastName;

    protected Age age;

    public User(String firstName, String lastName, Age age) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public static User of(String firstName, String lastName, Age age) {
        return new User(firstName, lastName, age);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s{id = %s, firstName = %s, lastName = %s, age = %s}",
                getClass(), id, firstName, lastName, age);
    }
}