package br.com.casadocodigo.exceptions;

import br.com.casadocodigo.dtos.ErrorDto;
import org.springframework.http.HttpStatus;

public class CodeHouseHttpException extends RuntimeException {

    private String error;
    private String cause;
    private HttpStatus httpStatus;

    public CodeHouseHttpException(String error, String cause, HttpStatus httpStatus) {
        this.error = error;
        this.cause = cause;
        this.httpStatus = httpStatus;
    }

    public ErrorDto toErrorDto() {
        return new ErrorDto(error, cause);
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
