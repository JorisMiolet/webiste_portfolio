package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.Laptop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LaptopRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public List<Laptop> getAll(){
        TypedQuery<Laptop> query = this.entityManager.createQuery("SELECT l FROM Laptop l", Laptop.class);
        return query.getResultList();
    }

    public Laptop findById(Long id){
        return this.entityManager.find(Laptop.class, id);
    }

    public Laptop create(Laptop newLaptop) {
        this.entityManager.persist(newLaptop);
        return newLaptop;
    }

    public Laptop updateLaptop(Laptop updatedLaptop) {
        return this.entityManager.merge(updatedLaptop);
    }

    public Laptop deleteLaptop(Long id) {
        Laptop laptop = this.entityManager.find(Laptop.class, id);
        if (laptop != null) {
            this.entityManager.remove(laptop);
            this.entityManager.flush();
            this.entityManager.detach(laptop);
        }
        return laptop;
    }


    public void addLaptops(List<Laptop> newLaptops) {
        newLaptops.forEach(this.entityManager::persist);

    }

//    public Laptop saveLaptop(Laptop laptop){
//        if(Integer.parseInt(laptop.getEan()) == 0){
//            int EAN = ((int) (Math.random() * 199999)) + 1600000;
//            while(findByEAN(Integer.toString(EAN)) != null){
//                EAN++;
//            }
//            laptop.setEan(Integer.toString(EAN));
//            long barcode = (long) (Math.random() * 8999999) + 1000000;
//            laptop.setBarcode(barcode + "000000000000");
//            laptops.add(laptop);
//            return laptop;
//        }else{
//            for (int i = 0; i < laptops.size(); i++) {
//                if(laptops.get(i).getEan().equals(laptop.getEan())){
//                    laptops.set(i, laptop);
//                    return laptop;
//                }
//            }
//        }
//        return laptop;
//    }
}
