package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.AutorDto;
import br.com.casadocodigo.entities.Autor;
import br.com.casadocodigo.forms.AutorForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final EntityManager entityManager;

    public AutorController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AutorDto> cadastraAutor(@Valid @RequestBody AutorForm autorForm) {
        Autor autor = autorForm.toEntity();
        entityManager.persist(autor);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(autor.toDto());
    }

}
