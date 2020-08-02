package br.com.casadocodigo.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidTotalPriceException extends CodeHouseHttpException {
    public InvalidTotalPriceException() {
        super("Total Price", "Must be equals to book's price sum.", HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
