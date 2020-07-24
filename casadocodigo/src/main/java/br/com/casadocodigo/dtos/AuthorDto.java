package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

//0
@AllArgsConstructor
@Getter
public class AuthorDto {

    private Long id;
    private LocalDateTime instant;
    private String email;
    private String name;
    private String description;

}
