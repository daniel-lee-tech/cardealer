package org.danlee.cardealer.services;

import org.danlee.cardealer.entities.Car;
import org.danlee.cardealer.repositories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageUploadService {
    private final CarRepository carRepository;

    public ImageUploadService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void handleImageUpload(String imageName, MultipartFile imageFile) throws IOException {
        Path uploadPath = Paths.get("src/main/resources/static/images");
        InputStream inputStream = imageFile.getInputStream();
        Path filePath = uploadPath.resolve(imageName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    public String validateImageNameUniqueness(MultipartFile imageFile) {
        for (Car loopedCar : carRepository.findAllCars()) {
            if (loopedCar.getImageUrl().equals("/images/" + imageFile.getOriginalFilename())) {
                String originalName = imageFile.getOriginalFilename();
                String imageName = imageFile.getName();
                assert originalName != null;
                String extension = originalName.substring(originalName.lastIndexOf(".") + 1);
                return imageName + (int) Math.ceil(Math.random() * 100000) + "." + extension;
            }
        }

        return imageFile.getOriginalFilename();
    }
}
