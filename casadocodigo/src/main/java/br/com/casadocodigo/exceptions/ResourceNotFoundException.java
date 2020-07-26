package br.com.casadocodigo.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not found!");
    }

    public ResourceNotFoundException(String country, Long countryId) {
        super("Resource " + country + " with identifier " + countryId + " not found!");
    }

}
