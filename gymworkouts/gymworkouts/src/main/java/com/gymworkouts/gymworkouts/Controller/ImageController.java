package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.ImageEntity;
import com.gymworkouts.gymworkouts.Repository.ImageRepository;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import com.gymworkouts.gymworkouts.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    @RequestMapping(
            value = "/image/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateResponse> addImage(
            @RequestBody ImageEntity image
    ) {
        return this.imageService.createImage(image);
    }

    @RequestMapping(
            value = "/image/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeleteResponse> deleteImage(
            @PathVariable long id
    ) {
        return this.imageService.deleteImage(id);
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
    public ResponseEntity<UpdateResponse> updateImage(
            @PathVariable long id,
            @RequestBody ImageEntity image
    ) {
        return this.imageService.updateImage(id, image);
    }
}
