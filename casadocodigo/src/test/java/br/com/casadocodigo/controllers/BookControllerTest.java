package br.com.casadocodigo.controllers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.PostConstruct;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @LocalServerPort
    private int port;
    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

//    @Test
//    void test() {
//        BookForm request = new BookForm("title", "summary", "preface", BigDecimal.valueOf(25.5), 100, "isbn", LocalDate.now().plusMonths(1), 1, 1);
//
//    }

}
