package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.ImageEntity;
import com.gymworkouts.gymworkouts.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Optional;

@RestController
public class ImageController {
    @Autowired
    ImageRepository imageRepository;

    @RequestMapping(
            value = "/image/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addImage(
            @RequestBody ImageEntity image
    ) {
        try {
            this.imageRepository.save(image);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"false\"}");
        }
    }

    @RequestMapping(
            value = "/image/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteImage(
            @PathVariable long id
    ) {
        Optional<ImageEntity> imageEntity = this.imageRepository.findById(id);

        if (imageEntity.isPresent()) {
            try {
                this.imageRepository.deleteById(id);

                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"result\":\"true\"}");
            } catch (Exception exception) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"" + exception.getMessage() + "\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\":\"Entity not found\"}");
    }

    @RequestMapping(
            value = "/image/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ImageEntity> getImage(
            @PathVariable long id
    ) {
        return ResponseEntity.of(this.imageRepository.findById(id));
    }

    @RequestMapping(
            value = "/image/update/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity updateImage(
            @PathVariable long id,
            @RequestBody ImageEntity image
    ) {
        Optional<ImageEntity> imageEntity = this.imageRepository.findById(id);

        if (imageEntity.isPresent()) {
            try {
                ImageEntity updateImageEntity = imageEntity.get();
                updateImageEntity.setUrlSmall(image.getUrlSmall());
                updateImageEntity.setUrlMedium(image.getUrlMedium());
                updateImageEntity.setUrlHigh(image.getUrlHigh());

                this.imageRepository.save(updateImageEntity);
                return ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"result\":\"true\"}");
            } catch (Exception exception){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"" + exception.getMessage() + "\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"error\":\"Entity not found\"}");
    }
}
