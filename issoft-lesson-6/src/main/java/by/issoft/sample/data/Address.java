package by.issoft.sample.data;

public class Address {

    private final String country;
    private final String region;
    private final String city;
    private final String street;
    private final String houseNumber;
    private final String flatNumber;

    private final int postalCode;

    public Address(String country, String region, String city, String street, String houseNumber, String flatNumber,
                   int postalCode) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return country + ", " + region + ", " + city + ", " + street + ", " + houseNumber + ", " + flatNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

}

