package br.com.casadocodigo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BookDto {

    private Long id;
    private String title;
    private String summay;
    private String preface;
    private BigDecimal price;
    private int pageNumber;
    private String isbn;
    private LocalDate publicationDate;
    private AuthorDto authorDto;
    private CategoryDto categoryDto;

}
