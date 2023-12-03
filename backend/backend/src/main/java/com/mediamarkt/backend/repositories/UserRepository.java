package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {

//    private final List<User> users;
//
//    public UserRepository(){
//        users = new ArrayList<>();
//
//        users.add(new User("John", "test123", true));
//        users.add(new User("Tessa", "test456", false));
//        users.add(new User("Tim", "nogEenTest", false));
//        users.add(new User("Jeffry", "enNogEenTest", true));
//        users.add(new User("Pieter", "laatsteTest", false));
//    }
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        TypedQuery<User> query = this.entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public User getUserByUUID(UUID uuid) {
        return this.entityManager.find(User.class, uuid);
    }

    public User create(User newUser) {
        this.entityManager.persist(newUser);
        return newUser;
    }

    public User updateUser(User newUser) {
        return this.entityManager.merge(newUser);
    }

    public User deleteUser(UUID uuid){
        User user = this.entityManager.find(User.class, uuid);
        if (user != null){
            this.entityManager.remove(user);
            this.entityManager.flush();
            this.entityManager.detach(user);
        }
        return user;
    }
}
