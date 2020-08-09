package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.AuthorDto;
import br.com.casadocodigo.entities.Author;
import br.com.casadocodigo.forms.AuthorForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

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
        AuthorDto authorDto = author.toDto();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(authorDto.getId()).toUri();
        return ResponseEntity.created(uri).body(authorDto);
    }

}
