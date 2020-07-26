package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.PurchaseDto;
import br.com.casadocodigo.entities.Purchase;
import br.com.casadocodigo.forms.PurchaseForm;
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
@RequestMapping("purchases")
public class PurchaseController {

    private final EntityManager entityManager;

    public PurchaseController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PurchaseDto> save(@Valid @RequestBody PurchaseForm form) {
//        Purchase purchase = form.toEntity(entityManager);
//        entityManager.persist(purchase);
//        return ResponseEntity.status(HttpStatus.CREATED).body(purchase.toDto());
    }

}
