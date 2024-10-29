package com.iftm.startexample.models.dto;

import java.io.Serializable;

import com.iftm.startexample.models.Address;
import com.iftm.startexample.models.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class UserDTO implements Serializable{
    private String Id;
    private String name;
    private int age;
    private Address address;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.Id = user.getId().toString();
        this.name = user.getName();
        this.age = user.getAge();
        this.address = user.getAddress();
    }

    public UserDTO(String name, int age){
        this.name = name;
        this.age = age;
    }
}
