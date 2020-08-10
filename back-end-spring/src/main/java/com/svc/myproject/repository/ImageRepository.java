package com.svc.myproject.repository;

import com.svc.myproject.domain.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findAll();
    Optional<Image> findByImageId(String id);
    List<Image> findAllByModel(String model);
}
