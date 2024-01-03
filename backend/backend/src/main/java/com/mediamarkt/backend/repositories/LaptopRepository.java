package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.Image;
import com.mediamarkt.backend.models.Laptop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LaptopRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Laptop> getAll() {
        TypedQuery<Laptop> query = this.entityManager.createQuery("SELECT l FROM Laptop l", Laptop.class);
        return query.getResultList();
    }

    public Laptop findById(Long id) {
        return this.entityManager.find(Laptop.class, id);
    }

    public Laptop create(Laptop newLaptop) {
        this.entityManager.persist(newLaptop);
        return newLaptop;
    }

    public Laptop updateLaptop(Laptop updatedLaptop) {
        return this.entityManager.merge(updatedLaptop);
    }

    @Transactional
    public Laptop deleteLaptop(Long id) {
        Laptop laptop = this.entityManager.find(Laptop.class, id);
        if (laptop != null) {
            this.entityManager.remove(laptop);
            this.entityManager.flush();
        }
        return laptop;
    }


    public void addLaptops(List<Laptop> newLaptops) {
        newLaptops.forEach(this.entityManager::persist);

    }

    public List<Laptop> search(String filter) {
        StringBuilder jpql = new StringBuilder("SELECT l FROM Laptop l");

        if (filter != null) {
            jpql.append(" WHERE l.ean LIKE :ean");
            jpql.append(" OR l.laptopBarcode LIKE :barcode");
            jpql.append(" OR l.laptopBrand LIKE :brand");
        }

        TypedQuery<Laptop> query = this.entityManager.createQuery(jpql.toString(), Laptop.class);

        if (filter != null && !filter.isEmpty()) {
            query.setParameter("ean", filter + "%");
            query.setParameter("barcode", filter + "%");
            query.setParameter("brand",  filter + "%");
        }

        return query.getResultList();
    }



}
