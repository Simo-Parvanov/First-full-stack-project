package com.svc.myproject.services;

import com.svc.myproject.domain.entities.Image;

import java.util.List;
import java.util.Map;

public interface ImageService {
    List<Image> listImage();
    void saveImage(Map result);
    void deleteImage(String id);
    Image findById(String id);
    Image findByImageId(String id);
    List<Image> findAllByModel(String model);
}
