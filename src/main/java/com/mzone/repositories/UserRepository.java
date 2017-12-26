package com.mzone.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mzone.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
    public List<User> findByEmail(String email);
}
