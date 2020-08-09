package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.VoucherDto;
import br.com.casadocodigo.entities.Voucher;
import br.com.casadocodigo.forms.VoucherForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("vouchers")
public class VoucherController {

    private final EntityManager entityManager;

    public VoucherController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<VoucherDto> save(@Valid @RequestBody VoucherForm form) {
        Voucher voucher = form.toEntity();
        entityManager.persist(voucher);
        VoucherDto voucherDto = voucher.toDto();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(voucherDto.getId()).toUri();
        return ResponseEntity.created(uri).body(voucherDto);
    }

}
