package br.com.casadocodigo.handlers;

import br.com.casadocodigo.dtos.ErrorDto;
import br.com.casadocodigo.exceptions.CodeHouseHttpException;
import br.com.casadocodigo.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDto>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ErrorDto> erros = fieldErrors.stream().map(ErrorDto::cria).collect(Collectors.toList());
        return ResponseEntity.unprocessableEntity().body(erros);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<List<ErrorDto>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList(error));
    }

    @ExceptionHandler(CodeHouseHttpException.class)
    public ResponseEntity<List<ErrorDto>> handleCOdeHouseHttpException(CodeHouseHttpException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(Collections.singletonList(ex.toErrorDto()));
    }

}
