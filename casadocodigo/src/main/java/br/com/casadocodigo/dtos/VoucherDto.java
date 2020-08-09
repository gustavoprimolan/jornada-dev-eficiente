package br.com.casadocodigo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VoucherDto {

    private Long id;
    private String code;
    private BigDecimal discountPercentage;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expiryDate;

}
