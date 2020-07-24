package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.BookDto;
import br.com.casadocodigo.entities.Book;
import br.com.casadocodigo.forms.BookForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final EntityManager entityManager;

    public BookController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ResponseEntity<BookDto> save(@Valid @RequestBody BookForm form) {
        Book book = form.toEntity(entityManager);
        entityManager.persist(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book.toDto());
    }

}
