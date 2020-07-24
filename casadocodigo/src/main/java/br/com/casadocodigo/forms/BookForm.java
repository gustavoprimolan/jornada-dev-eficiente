package br.com.casadocodigo.forms;

import br.com.casadocodigo.constraints.Unique;
import br.com.casadocodigo.entities.Author;
import br.com.casadocodigo.entities.Book;
import br.com.casadocodigo.entities.Category;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publicationDate;
    @NotNull
    private Long authorId;
    @NotNull
    private Long categoryId;

    public Book toEntity(EntityManager entityManager) {
        Author author = entityManager.find(Author.class, authorId);
        Category category = entityManager.find(Category.class, categoryId);
        return new Book(this.title, this.summary, this.preface, this.price, this.pageNumber, this.isbn, this.publicationDate, author, category);
    }

}
