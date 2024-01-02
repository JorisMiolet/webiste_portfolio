package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.Laptop;
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
    private EntityManager entityManager;


    public List<User> getAll() {
        TypedQuery<User> query = this.entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public List<User> getActiveUsers(){
        TypedQuery<User> query = this.entityManager.createQuery("SELECT u FROM User u WHERE u.active = true", User.class);
        return query.getResultList();
    }

    public List<User> getDisabledUsers(){
        TypedQuery<User> query = this.entityManager.createQuery("SELECT u FROM User u WHERE u.active = false", User.class);
        return query.getResultList();
    }

    public User getWithLogin(String username, String password) {
        TypedQuery<User> query = this.entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);

        query.setParameter("email", username);

        List<User> users = query.getResultList();
        if (!users.isEmpty()) {
            if(BCrypt.checkpw(password, users.get(0).getPassword())){
                return users.get(0);
            }
            return null;
        }

        return null;
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
        return this.entityManager.merge(newUser);
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
}
