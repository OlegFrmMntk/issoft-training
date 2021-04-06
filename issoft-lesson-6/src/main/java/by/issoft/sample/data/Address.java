package by.issoft.sample.data;

public class Address {

    private String country;
    private String region;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;

    private int postalCode;

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

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
