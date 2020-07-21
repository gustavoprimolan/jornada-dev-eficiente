package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

@AllArgsConstructor
@Getter
public class ErroDto {

    private String erro;
    private String motivo;

    public static ErroDto cria(FieldError field) {
        return new ErroDto(field.getField(), field.getDefaultMessage());
    }

}
