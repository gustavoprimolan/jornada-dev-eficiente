package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.BookDto;
import br.com.casadocodigo.entities.Book;
import br.com.casadocodigo.exceptions.ResourceNotFoundException;
import br.com.casadocodigo.forms.BookForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final EntityManager entityManager;

    public BookController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        List<Book> books = entityManager.createQuery("SELECT b FROM Book as b").getResultList();
        List<BookDto> booksDto = books.stream().map(Book::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(booksDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") Long id) {
        Book book = Optional.ofNullable(entityManager.find(Book.class, id)).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(book.toDto());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<BookDto> save(@Valid @RequestBody BookForm form) {
        Book book = form.toEntity(entityManager);
        entityManager.persist(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book.toDto());
    }

}
