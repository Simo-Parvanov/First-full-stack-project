package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.Image;
import com.svc.myproject.repository.ImageRepository;
import com.svc.myproject.services.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Override
    public List<Image> listImage() {
        return imageRepository.findAll();
    }

    @Override
    public void saveImage(Map result) {
        String[] model = result.get("original_filename").toString().split("@");
        Image image = new Image(
                result.get("original_filename").toString(),
                result.get("url").toString(),
                result.get("public_id").toString(),
                model[0]);
    imageRepository.saveAndFlush(image);
    }

    @Override
    public void deleteImage(String id) {
    imageRepository.deleteById(id);
    }

    @Override
    public Image findById(String id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image findByImageId(String id) {
        Optional<Image> image = imageRepository.findByImageId(id);
        if (image.isEmpty()){
            return null;
        }
        return image.get();
    }

    @Override
    public List<Image> findAllByModel(String model) {
       return imageRepository.findAllByModel(model);
    }
}
