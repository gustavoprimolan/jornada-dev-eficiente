package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.AuthorDto;
import br.com.casadocodigo.entities.Author;
import br.com.casadocodigo.forms.AuthorForm;
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
@RequestMapping("/authors")
public class AuthorController {

    private final EntityManager entityManager;

    public AuthorController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AuthorDto> saveAuthor(@Valid @RequestBody AuthorForm authorForm) {
        Author author = authorForm.toEntity();
        entityManager.persist(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(author.toDto());
    }

}
