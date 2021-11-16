package org.danlee.cardealer.dto;

import org.danlee.cardealer.entities.Car;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class CarDTO extends Car {
    private MultipartFile imageFile;

    public CarDTO() {
        this.setId(UUID.randomUUID());
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
