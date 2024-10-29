package com.iftm.startexample.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String street;
    private int number;

    public Address() {
    }


    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }
}
