package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.entities.Image;
import com.svc.myproject.payload.response.MessageResponse;
import com.svc.myproject.services.CloudinaryService;
import com.svc.myproject.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cloudinary")
public class CloudinaryController {
    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    public CloudinaryController(CloudinaryService cloudinaryService, ImageService imageService) {
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Image>> list(){
        List<Image> list = imageService.listImage();
        return new ResponseEntity(list, HttpStatus.OK);
    }


    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile,
                                    UriComponentsBuilder builder) throws IOException {
        BufferedImage buf = ImageIO.read(multipartFile.getInputStream());
        if (buf == null){
            return new ResponseEntity(new MessageResponse("The file is not valid!"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        imageService.saveImage(result);
        return ResponseEntity.created(builder.path("/cloudinary/create")
                .buildAndExpand().toUri()).build();
//        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> upload(@PathVariable String id) throws IOException {
        System.out.println();
        if (imageService.findByImageId(id) == null){
            return new ResponseEntity(new MessageResponse("The image does not exist!"), HttpStatus.NOT_FOUND);
        }
        Map result = cloudinaryService.delete(id);
        Image image = imageService.findByImageId(id);
        imageService.deleteImage(image.getId());
         return ResponseEntity.noContent().build();
//        return new ResponseEntity(result, HttpStatus.OK);
    }
}
