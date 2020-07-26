package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.StateDto;
import br.com.casadocodigo.entities.State;
import br.com.casadocodigo.forms.StateForm;
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
@RequestMapping("states")
public class StateController {

    private final EntityManager entityManager;

    public StateController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<StateDto> save(@Valid @RequestBody StateForm form) {
        State state = form.toEntity(entityManager);
        entityManager.persist(state);
        return ResponseEntity.status(HttpStatus.CREATED).body(state.toDto());
    }

}
