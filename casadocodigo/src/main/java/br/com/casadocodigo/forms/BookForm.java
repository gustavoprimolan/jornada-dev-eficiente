package br.com.casadocodigo.forms;

import br.com.casadocodigo.constraints.Unique;
import br.com.casadocodigo.entities.Author;
import br.com.casadocodigo.entities.Book;
import br.com.casadocodigo.entities.Category;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
public class BookForm {

    @Unique(entityClass = Book.class, entityField = "title")
    @NotNull @NotBlank
    private String title;
    @NotNull @NotBlank @Length(max = 500)
    private String summary;
    private String preface;
    @NotNull @Min(20)
    private BigDecimal price;
    @Min(100)
    private int pageNumber;
    @Unique(entityClass = Book.class, entityField = "isbn")
    @NotNull @NotBlank
    private String isbn;
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate publicationDate;
    @NotNull
    private Long authorId;
    @NotNull
    private Long categoryId;

    private BookForm(){}

    public Book toEntity(EntityManager entityManager) {
        Author author = entityManager.find(Author.class, authorId);
        Category category = entityManager.find(Category.class, categoryId);
        return Book.Builder.withTitle(this.title)
                .withSummary(this.summary)
                .withPreface(this.preface)
                .withPrice(this.price)
                .withPageNumber(this.pageNumber)
                .withIsbn(this.isbn)
                .withPublicationDate(this.publicationDate)
                .withAuthor(author)
                .withCategory(category)
                .build();
    }

}
