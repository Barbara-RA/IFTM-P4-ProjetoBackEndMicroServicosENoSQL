package com.iftm.startexample.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "user")
@Getter
@Setter
public class User {
    
    @Id //@MongoId ou @MongoID(targetType = FieldType.OBJECT_ID) ou @BsonId private ObjectId id;
    private Object id;
    @Field("name")
    private String name;
    private int age;
    private Address address;

    public User() {
    }

    public User(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
