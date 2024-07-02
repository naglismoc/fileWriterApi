package org.example;

public class Place {
    private String code;
    private String name;
    private String administrativeDivision;
    private String country;
    private String countryCode;
    private Coordinates coordinates;

    public Place() {
        coordinates = new Coordinates();
    }

    public Place(String code, String name, String administrativeDivision, String country, String countryCode, String latitude,String longitude) {
        this.code = code;
        this.name = name;
        this.administrativeDivision = administrativeDivision;
        this.country = country;
        this.countryCode = countryCode;
        this.coordinates = new Coordinates(latitude,longitude);
    }
    public Place(String code, String name, String administrativeDivision, String country, String countryCode, Coordinates coordinates) {
        this.code = code;
        this.name = name;
        this.administrativeDivision = administrativeDivision;
        this.country = country;
        this.countryCode = countryCode;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Place{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", administrativeDivision='" + administrativeDivision + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
