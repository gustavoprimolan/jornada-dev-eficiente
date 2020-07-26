package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.CountryDto;
import br.com.casadocodigo.entities.Country;
import br.com.casadocodigo.forms.CountryForm;
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
@RequestMapping("countries")
public class CountryController {

    private final EntityManager entityManager;

    public CountryController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CountryDto> save(@Valid @RequestBody CountryForm form) {
        Country country = form.toEntity();
        entityManager.persist(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(country.toDto());
    }

}
