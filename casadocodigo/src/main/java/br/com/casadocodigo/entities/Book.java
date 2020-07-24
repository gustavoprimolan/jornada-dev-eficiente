package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.BookDto;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summay", nullable = false, length = 500)
    private String summary;

    @Column(name = "preface")
    private String preface;

    @Min(20)
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Min(100)
    @Column(name = "page_number", nullable = false)
    private int pageNumber;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Future
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @OneToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Author author;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Book(String title, String summary, String preface,
                BigDecimal price, int pageNumber, String isbn,
                LocalDate publicationDate, Author author, Category category) {
        this.title = title;
        this.summary = summary;
        this.preface = preface;
        this.price = price;
        this.pageNumber = pageNumber;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.author = author;
        this.category = category;
    }

    public BookDto toDto() {
        return new BookDto(this.id, this.title, this.summary,
                this.preface, this.price, this.pageNumber,
                this.isbn, this.publicationDate, this.author.toDto(),
                this.category.toDto());
    }
}
