package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AutorDto {

    private Long id;
    private LocalDateTime instante;
    private String email;
    private String nome;
    private String descricao;

}
