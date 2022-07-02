package com.gymworkouts.gymworkouts.Service;

import com.gymworkouts.gymworkouts.Entity.ImageEntity;
import com.gymworkouts.gymworkouts.Repository.ImageRepository;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public ResponseEntity<DeleteResponse> deleteImage(long id) {
        Optional<ImageEntity> imageEntity = this.imageRepository.findById(id);

        if (imageEntity.isPresent()) {
            try {
                this.imageRepository.deleteById(id);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new DeleteResponse(true, "Successfully deleted image entity!"));
            } catch (Exception exception) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new DeleteResponse(false, exception.getMessage()));
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new DeleteResponse(false, "Entity not found"));
    }

    public ResponseEntity<UpdateResponse> updateImage(long id, ImageEntity image) {
        Optional<ImageEntity> imageEntity = this.imageRepository.findById(id);

        if (imageEntity.isPresent()) {
            try {
                ImageEntity updateImageEntity = imageEntity.get();
                updateImageEntity.setUrlSmall(image.getUrlSmall());
                updateImageEntity.setUrlMedium(image.getUrlMedium());
                updateImageEntity.setUrlHigh(image.getUrlHigh());

                this.imageRepository.save(updateImageEntity);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new UpdateResponse(true, "Successfully updated entity!"));
            } catch (Exception exception){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new UpdateResponse(false, exception.getMessage()));
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new UpdateResponse(false, "Entity not found"));
    }

    public ResponseEntity<CreateResponse> createImage(ImageEntity image) {
        try {
            this.imageRepository.save(image);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new CreateResponse(true, "Successfully create image entity!", image));
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateResponse(false, exception.getMessage(), null));
        }
    }
}
