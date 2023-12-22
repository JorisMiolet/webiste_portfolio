package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.Image;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

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
}
