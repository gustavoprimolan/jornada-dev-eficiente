package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.CategoryDto;
import br.com.casadocodigo.entities.Category;
import br.com.casadocodigo.forms.CategoryForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

//CARGA INTRÍNSECA 3
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final EntityManager entityManager;

    public CategoryController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CategoryDto> save(@Valid @RequestBody CategoryForm form) {
        Category category = form.toEntity();
        entityManager.persist(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category.toDto());
    }

}
