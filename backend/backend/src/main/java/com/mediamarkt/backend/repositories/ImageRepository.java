package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.Image;
import com.mediamarkt.backend.models.Laptop;
import com.mediamarkt.backend.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ImageRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Image> getAll() {
        TypedQuery<Image> query = this.entityManager.createQuery(
                "SELECT i FROM Image i",
                Image.class
        );
        return query.getResultList();
    }

    public Image findByBarcode(String barcode) {
        TypedQuery<Image> query = this.entityManager.createQuery(
                "SELECT i FROM Image i WHERE i.barcode = :barcode",
                Image.class
        );
        query.setParameter("barcode", barcode);
        List<Image> result = query.getResultList();
        if(result.isEmpty()){
            return null;
        }
        Image image = result.get(0);
        return image;
    }

    public Image findByArticleNr(String articleNr) {
        TypedQuery<Image> query = this.entityManager.createQuery("select i from Image i where i.articleNumber = :articleNr", Image.class);
        query.setParameter("articleNr", articleNr);
        List<Image> result = query.getResultList();
        if(result.isEmpty()){
            return null;
        }
        Image image = result.get(0);
        return image;
    }

    public Image findByEAN(String EAN) {
        TypedQuery<Image> query = this.entityManager.createQuery("select i from Image i where i.ean = :ean", Image.class);
        query.setParameter("ean", EAN);
        List<Image> result = query.getResultList();
        if(result.isEmpty()){
            return null;
        }
        Image image = result.get(0);
        return image;
    }

    public Image create(Image newImage) {
        this.entityManager.persist(newImage);
        return newImage;
    }

    public Image updateImage(Image updatedImage) {
        return this.entityManager.merge(updatedImage);
    }

    public Image deleteImage(Long id) {
        Image image = this.entityManager.find(Image.class, id);

        if (image != null) {
            this.entityManager.remove(image);
            this.entityManager.flush();
            return image;
        } else {
            return null;
        }
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

    public Map<String, Long> getSummaryStatistics() {
        Map<String, Long> summaryStatistics = new HashMap<>();

        // Count completed images
        Long completedCount = getCompleted("completed");
        summaryStatistics.put("completed", completedCount);

        // Count in-progress images
        Long inProgressCount = getCountByStatus("completed"); // Assuming "in_progress" is the status for in-progress images
        summaryStatistics.put("in_progress", inProgressCount);

        // Count outdated images
        Long outdatedCount = getCountOfOutdatedImages();
        summaryStatistics.put("outdated", outdatedCount);

        return summaryStatistics;
    }

    private Long getCountByStatus(String status) {
        TypedQuery<Long> query = this.entityManager.createQuery(
                "SELECT COUNT(i) FROM Image i WHERE i.status != :status",
                Long.class
        );
        query.setParameter("status", status);
        return query.getSingleResult();
    }
    private Long getCompleted(String status) {
        TypedQuery<Long> query = this.entityManager.createQuery(
                "SELECT COUNT(i) FROM Image i WHERE i.status = :status",
                Long.class
        );
        query.setParameter("status", status);
        return query.getSingleResult();
    }

    private Long getCountOfOutdatedImages() {
        TypedQuery<Long> query = this.entityManager.createQuery(
                "SELECT COUNT(i) FROM Image i WHERE i.date < :threeMonthsAgo",
                Long.class
        );
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);

        // Define a DateTimeFormatter for the SQL date format
        DateTimeFormatter sqlDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format the LocalDate to a string in SQL-compatible format
        String sqlDateString = threeMonthsAgo.format(sqlDateFormat);

        query.setParameter("threeMonthsAgo", sqlDateString);
        return query.getSingleResult();
    }

    public Image pickup(String articleNumber, UUID userId) {
        Image image = findByArticleNr(articleNumber);
        image.setStatus("in progress");
        User user = this.entityManager.find(User.class, userId);

        if(user != null && image != null){
            user.assosiateOffer(image);
            this.entityManager.merge(image);
            this.entityManager.merge(user);
            return image;
        } else {
            return null;
        }
    }

    public Image markDone(String articleNr) {
        Image image = findByArticleNr(articleNr);
        image.setStatus("completed");
        image.setDate(LocalDate.now().toString());
        return this.entityManager.merge(image);
    }
}
