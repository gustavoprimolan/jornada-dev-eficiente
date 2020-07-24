package br.com.casadocodigo.controllers;

import br.com.casadocodigo.dtos.AuthorDto;
import br.com.casadocodigo.dtos.ErrorDto;
import br.com.casadocodigo.forms.AuthorForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

    @LocalServerPort
    private int port;
    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

    @Test
    void givenAValidAuthor_whenMakingPostRequestToAuthorEndpoint_thenReturnCreatedAndTheObject() {
        AuthorForm request = new AuthorForm("teste@teste.com", "name", "description");

        AuthorDto responseBody = given().contentType("application/json")
                .body(request)
                .when()
                .post(uri + "/authors")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .body()
                .as(AuthorDto.class);

        assertThat(responseBody.getEmail()).isEqualTo(request.getEmail());
        assertThat(responseBody.getDescription()).isEqualTo(request.getDescription());
        assertThat(responseBody.getName()).isEqualTo(request.getName());
        assertThat(responseBody.getInstant()).isNotNull();
        assertThat(responseBody.getId()).isNotNull();
    }

    @Test
    void givenADuplicatedAuthor_whenMakingTwicePostRequestToAuthorEndpoint_thenReturnError() {
        AuthorForm request = new AuthorForm("teste@teste.com", "name", "description");
        given().contentType("application/json").body(request).post(uri + "/authors");

        ErrorDto[] responseBody = given().contentType("application/json")
                .body(request)
                .when()
                .post(uri + "/authors")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .extract()
                .body()
                .as(ErrorDto[].class);

        assertThat(responseBody.length).isPositive();
    }

}