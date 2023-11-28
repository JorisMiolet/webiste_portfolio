package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.Laptop;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaptopRepository {
    public List<Laptop> laptops;

    public LaptopRepository() {
        this.laptops = new ArrayList<>();
    }
    public List<Laptop> getAllLaptops() {
        return this.laptops;
    }
    public Laptop findByEAN(String EAN) {
        for (Laptop laptop: laptops) {
            if (laptop.getEan().equals(EAN)){
                return laptop;
            }
        }
        return null;
    }

    public void addLaptops(List<Laptop> newLaptops) {
        this.laptops.clear();
        this.laptops.addAll(newLaptops);
    }
    public Laptop saveLaptop(Laptop laptop){
        if(Integer.parseInt(laptop.getEan()) == 0){
            int EAN = ((int) (Math.random() * 199999)) + 1600000;
            while(findByEAN(Integer.toString(EAN)) != null){
                EAN++;
            }
            laptop.setEan(Integer.toString(EAN));
            long barcode = (long) (Math.random() * 8999999) + 1000000;
            laptop.setBarcode(barcode + "000000000000");
            laptops.add(laptop);
            return laptop;
        }else{
            for (int i = 0; i < laptops.size(); i++) {
                if(laptops.get(i).getEan().equals(laptop.getEan())){
                    laptops.set(i, laptop);
                    return laptop;
                }
            }
        }
        return laptop;
    }
}
