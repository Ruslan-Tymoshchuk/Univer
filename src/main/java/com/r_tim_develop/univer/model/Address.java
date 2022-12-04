package com.r_tim_develop.univer.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "region")
    private String region;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "house_number")
    private String houseNumber;
    
    @Column(name = "postal_code")
    private String postalCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String index) {
        this.postalCode = index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, houseNumber, id, postalCode, region, street);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        return Objects.equals(city, other.city) && Objects.equals(country, other.country)
                && Objects.equals(houseNumber, other.houseNumber) && Objects.equals(id, other.id)
                && Objects.equals(postalCode, other.postalCode) && Objects.equals(region, other.region)
                && Objects.equals(street, other.street);
    }
}