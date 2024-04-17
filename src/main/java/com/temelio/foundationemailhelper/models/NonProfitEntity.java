package com.temelio.foundationemailhelper.models;

import lombok.Data;

@Data
public class NonProfitEntity {

    private String name;
    private String email;
    private Address address;
}

@Data
class Address{
    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }

    private String city;
    private String state;
    private Integer zipCode;

}
