package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.CategoryEntity;
import com.gymworkouts.gymworkouts.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(
            value = "/category",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CategoryEntity>> getCategories()
    {
        return ResponseEntity.ok(this.categoryRepository.findAll());
    }

    @RequestMapping(
            value = "/category/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryEntity> getCategory(
            @PathVariable long id
    ) {
        return ResponseEntity.of(this.categoryRepository.findById(id));
    }

    @RequestMapping(
            value = "/category/update/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity updateCategory(
            @PathVariable long id,
            @RequestBody CategoryEntity category
    ) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findById(id);

        if (categoryEntity.isPresent()) {
            try {
                CategoryEntity updateCategoryEntity = categoryEntity.get();
                updateCategoryEntity.setDescription(category.getDescription());
                updateCategoryEntity.setName(category.getName());
                this.categoryRepository.save(updateCategoryEntity);

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

    @RequestMapping(
            value = "/category/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteCategory(
            @PathVariable long id
    ) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findById(id);

        if (categoryEntity.isPresent()) {
            try {
                this.categoryRepository.deleteById(id);

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
            value = "/category/add",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createCategory(
            @RequestBody CategoryEntity category
    ) {
        try {
            this.categoryRepository.save(category);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"false\"}");
        }
    }
}
