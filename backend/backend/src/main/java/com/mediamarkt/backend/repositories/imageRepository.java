package com.mediamarkt.backend.repositories;

import com.mediamarkt.backend.models.image;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class imageRepository {

    private List<image> images;

    public imageRepository(){
        images = new ArrayList<>();
        images.add(new image(
                "3801105",
                "4718235328610",
                "ACER",
                "ASPIRE 1681 WLMI MMN (LX.A2705.401)",
                "Intel Core i5",
                "8GB",
                "256GB SSD",
                "NVIDIA GeForce GTX 1650",
                "15.6",
                "39.62"
                ));
        images.add(new image(
                "3801104",
                "4718235312787",
                "HP",
                "AS 1804 WSMIB_100 DX11 (LX.A3905.031)",
                "AMD Ryzen 7",
                "16GB",
                "512GB SSD",
                "NVIDIA GeForce RTX 3060",
                "17.3",
                "43.94"
        ));
        images.add(new image(
                "3801078",
                "4718235303136",
                "ACER",
                "ASPIRE 1680 WLMI MMM (LX.A2705.241)",
                "Intel Core i7",
                "16GB",
                "1TB HDD",
                "Intel UHD Graphics",
                "14.0",
                "35.56"
        ));
        images.add(new image(
                "3801051",
                "4718235291754",
                "ACER",
                "ASPIRE 1801WSMI DX7 (LX.A2905.091)",
                "AMD Ryzen 5",
                "12GB",
                "512GB SSD",
                "AMD Radeon RX 560",
                "15.6",
                "39.62"
        ));
        images.add(new image(
                "3801050",
                "4718235292270",
                "ACER",
                "ASPIRE 1671 WLMI DX7 (LX.A3505.183)",
                "Intel Core i5",
                "8GB",
                "256GB SSD",
                "Intel HD Graphics",
                "13.3",
                "33.78"
        ));
        images.add(new image(
                "3801018",
                "4718235291976",
                "ACER",
                "ASP 1804 WSMIB DX11 (LX.A2905.113)",
                "AMD Ryzen 9",
                "32GB",
                "1TB SSD",
                "NVIDIA GeForce RTX 3080",
                "17.3",
                "43.94"
        ));
        images.add(new image(
                "3800966",
                "4718235271800",
                "ACER",
                "ASPIRE 1671 WLMI DX7 (LX.A3505.134)",
                "Intel Core i7",
                "16GB",
                "512GB SSD",
                "NVIDIA GeForce GTX 1660 Ti",
                "15.6",
                "39.62"
        ));
        images.add(new image(
                "3800930",
                "4718235271466",
                "ACER",
                "ASPIRE 1612 LMI DX7 (LX.A1905.015)",
                "AMD Ryzen 7",
                "12GB",
                "512GB SSD",
                "AMD Radeon Vega 8",
                "14.0",
                "35.56"
        ));
        images.add(new image(
                "3800924",
                "4718235246952",
                "HP",
                "ASPIRE 1802 WSMIB DX9 (LX.A2905.044)",
                "Intel Core i5",
                "8GB",
                "256GB SSD",
                "Intel UHD Graphics 620",
                "13.3",
                "33.78"
        ));
        images.add(new image(
                "3800894",
                "4718235233587",
                "HP",
                "ASPIRE 1661 WLM DX7 (LX.A3005.117)",
                "AMD Ryzen 5",
                "12GB",
                "512GB SSD",
                "NVIDIA GeForce GTX 1050",
                "15.6",
                "39.62"
        ));
    }

    public List<image> getAll() {
        return images;
    }

    public image findById(String articleNr) {
        for(image imageById: images){
            if (imageById.getArticleNumber().equals(articleNr)) {
                return imageById;
            }
        }
        return null;
    }
}
