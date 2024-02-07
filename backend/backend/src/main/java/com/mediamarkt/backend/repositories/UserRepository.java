package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    public EntityManager entityManager;


    public List<User> getAll() {
        TypedQuery<User> query = this.entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public List<User> getActiveUsers(){
        TypedQuery<User> query = this.entityManager.createQuery("SELECT u FROM User u WHERE u.admin = true", User.class);
        return query.getResultList();
    }

    public List<User> getDisabledUsers(){
        TypedQuery<User> query = this.entityManager.createQuery("SELECT u FROM User u WHERE u.admin = false", User.class);
        return query.getResultList();
    }

    public User getUserByUUID(UUID uuid) {
        return this.entityManager.find(User.class, uuid);
    }

    public User create(User newUser) {
        String encodedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(encodedPassword);
        this.entityManager.persist(newUser);
        return newUser;
    }

    public User updateUser(User newUser) {
        User existingUser = this.entityManager.find(User.class, newUser.getUuid());

        if (existingUser != null) {
            // Retain the existing password if the new password is null or empty
            if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
                newUser.setPassword(existingUser.getPassword());
            } else {
                // If the new password is not empty, encode and set it
                String encodedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
                newUser.setPassword(encodedPassword);
            }

            return this.entityManager.merge(newUser);
        }

        return null;
    }



    public User deleteUser(UUID uuid) {
        User user = this.entityManager.find(User.class, uuid);
        if (user != null) {
            this.entityManager.remove(user);
            this.entityManager.flush();
            this.entityManager.detach(user);
        }
        return user;
    }

    public List<User> search(String filter) {
        StringBuilder jpql = new StringBuilder("SELECT i FROM User i");

        if (filter != null && !filter.isEmpty()) {
            jpql.append(" WHERE i.username LIKE :username");
            jpql.append(" OR i.email LIKE :email");

        }

        TypedQuery<User> query = this.entityManager.createQuery(jpql.toString(), User.class);

        if (filter != null && !filter.isEmpty()) {
            query.setParameter("username", filter + "%");
            query.setParameter("email", filter + "%");

        }

        return query.getResultList();
    }

    public User getByEmail(String email) {
        TypedQuery<User> query = this.entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);

        query.setParameter("email", email);

        List<User> users = query.getResultList();
        if (!users.isEmpty()) {
            return users.get(0);
        }

        return null;
    }
}
