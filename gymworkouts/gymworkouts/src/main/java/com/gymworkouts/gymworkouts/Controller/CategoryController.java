package com.gymworkouts.gymworkouts.Controller;

import com.gymworkouts.gymworkouts.Entity.CategoryEntity;
import com.gymworkouts.gymworkouts.Repository.CategoryRepository;
import com.gymworkouts.gymworkouts.Requests.CreateCategoryRequest;
import com.gymworkouts.gymworkouts.Requests.UpdateCategoryRequest;
import com.gymworkouts.gymworkouts.Responses.CreateResponse;
import com.gymworkouts.gymworkouts.Responses.DeleteResponse;
import com.gymworkouts.gymworkouts.Responses.UpdateResponse;
import com.gymworkouts.gymworkouts.Service.CategoryService;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

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
    public ResponseEntity<UpdateResponse> updateCategory(
            @PathVariable long id,
            @RequestBody UpdateCategoryRequest categoryRequest
    ) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findById(id);

        if (categoryEntity.isPresent()) {
            return this.categoryService.updateCategory(categoryEntity.get(), categoryRequest);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new UpdateResponse(false, "Entity not found!"));
    }

    @RequestMapping(
            value = "/category/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeleteResponse> deleteCategory(
            @PathVariable long id
    ) {
        return this.categoryService.deleteCategory(id);
    }

    @RequestMapping(
            value = "/category/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateResponse> createCategory(
            @RequestBody CreateCategoryRequest categoryRequest
    ) {
        return this.categoryService.createCategory(categoryRequest);
    }
}
