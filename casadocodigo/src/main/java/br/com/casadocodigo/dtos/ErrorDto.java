package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

@AllArgsConstructor
@Getter
public class ErrorDto {

    private String error;
    private String cause;

    public static ErrorDto cria(FieldError field) {
        return new ErrorDto(field.getField(), field.getDefaultMessage());
    }

}
