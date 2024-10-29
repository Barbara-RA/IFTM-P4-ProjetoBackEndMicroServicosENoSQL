package com.iftm.startexample.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iftm.startexample.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    
}
