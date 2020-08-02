package br.com.casadocodigo.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCountryException extends CodeHouseHttpException {
    public InvalidCountryException() {
        super("Country", "Must be valid", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
