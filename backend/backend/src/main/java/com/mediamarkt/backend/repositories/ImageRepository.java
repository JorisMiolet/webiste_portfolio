package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.Image;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class ImageRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Image> getAll() {
        TypedQuery<Image> query = this.entityManager.createQuery(
                "SELECT i FROM Image i",
                Image.class
        );
        System.out.println(query);
        return query.getResultList();
    }

    public Image findByBarcode(String barcode) {
        TypedQuery<Image> query = this.entityManager.createQuery(
                "SELECT i FROM Image i WHERE i.barcode = :barcode",
                Image.class
        );
        query.setParameter("barcode", barcode);
        return query.getSingleResult();
    }

    public Image findByArticleNr(String articleNr) {
        TypedQuery<Image> query = this.entityManager.createQuery("select i from Image i where i.articleNumber = :articleNr", Image.class);
        query.setParameter("articleNr", articleNr);
        return query.getSingleResult();
    }

    public Image findByEAN(String EAN) {
        TypedQuery<Image> query = this.entityManager.createQuery("select i from Image i where i.ean = :ean", Image.class);
        query.setParameter("ean", EAN);
        return query.getSingleResult();
    }

    public Image create(Image newImage) {
        this.entityManager.persist(newImage);
        return newImage;
    }

    public Image updateImage(Image updatedImage) {
        return this.entityManager.merge(updatedImage);
    }

    public Image deleteImage(String articleNr) {
        Image image = this.entityManager.find(Image.class, articleNr);
        if (image != null) {
            this.entityManager.remove(image);
            this.entityManager.flush();
            this.entityManager.detach(image);
        }
        return image;
    }

    public List<Image> search(String filter) {
        StringBuilder jpql = new StringBuilder("SELECT i FROM Image i");

        if (filter != null && !filter.isEmpty()) {
            jpql.append(" WHERE i.articleNumber LIKE :articleNumber");
            jpql.append(" OR i.ean LIKE :ean");
            jpql.append(" OR i.brand LIKE :brand");

        }

        TypedQuery<Image> query = this.entityManager.createQuery(jpql.toString(), Image.class);

        if (filter != null && !filter.isEmpty()) {
            query.setParameter("articleNumber", filter + "%");
            query.setParameter("ean", filter + "%");
            query.setParameter("brand",filter + "%");

        }

        return query.getResultList();
    }

    public List<Image> getCompletedImages() {
        TypedQuery<Image> query = this.entityManager.createQuery(
                "SELECT i FROM Image i WHERE i.status = 'completed'",
                Image.class
        );
        return query.getResultList();
    }

    public List<Image> getOutdatedImages(){
        TypedQuery<Image> query = this.entityManager.createQuery(
                "SELECT i FROM Image i WHERE i.date < :threeMonthsAgo",
                Image.class
        );
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);

        // Define a DateTimeFormatter for the SQL date format
        DateTimeFormatter sqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the LocalDate to a string in SQL-compatible format
        String sqlDateString = threeMonthsAgo.format(sqlDateFormat);

        query.setParameter("threeMonthsAgo", sqlDateString);
        return query.getResultList();
    }

    public List<Image> getIncompletedImages(){
        TypedQuery<Image> query = this.entityManager.createQuery(
                "SELECT i FROM Image i WHERE i.status != 'completed'",
                Image.class
        );
        return query.getResultList();
    }

}
