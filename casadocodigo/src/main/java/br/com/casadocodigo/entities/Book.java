package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.BookDto;
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

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summary", nullable = false, length = 500)
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

    /**      
     * @deprecated (it is not recommended build an empty object, but jpa needs this guy... =/)      
     * */
    @Deprecated
    Book() {}

    public BookDto toDto() {
        return BookDto.Builder.withId(this.id)
                .withTitle(this.title)
                .withSummary(this.summary)
                .withPreface(this.preface)
                .withPrice(this.price)
                .withPageNumber(this.pageNumber)
                .withIsbn(this.isbn)
                .withPublicationDate(this.publicationDate)
                .withAuthor(this.author.toDto())
                .withCategory(this.category.toDto())
                .build();
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public static final class Builder {

        private Builder() {
        }

        public static BookWithTitle withTitle(String title) {
            Book book = new Book();
            return new BookWithTitle(book, title);
        }

        public static class BookWithTitle {
            private Book book;
            public BookWithTitle(Book book, String title) {
                this.book = book;
                this.book.title = title;
            }

            public BookWithSummary withSummary(String summary) {
                return new BookWithSummary(this.book, summary);
            }

            public class BookWithSummary {
                private Book book;
                public BookWithSummary(Book book, String summary) {
                    this.book = book;
                    this.book.summary = summary;
                }

                public BookWithPreface withPreface(String preface) {
                    return new BookWithPreface(this.book, preface);
                }

                public class BookWithPreface {
                    private Book book;
                    public BookWithPreface(Book book, String preface) {
                        this.book = book;
                        this.book.preface = preface;
                    }

                    public BookWithPrice withPrice(BigDecimal price) {
                        return new BookWithPrice(this.book, price);
                    }

                    public class BookWithPrice {
                        private Book book;
                        public BookWithPrice(Book book, BigDecimal price) {
                            this.book = book;
                            this.book.price = price;
                        }

                        public BookWithPageNumber withPageNumber(int pageNumber) {
                            return new BookWithPageNumber(this.book, pageNumber);
                        }

                        public class BookWithPageNumber {
                            private Book book;

                            public BookWithPageNumber(Book book, int pageNumber) {
                                this.book = book;
                                this.book.pageNumber = pageNumber;
                            }

                            public BookWithIsbn withIsbn(String isbn) {
                                return new BookWithIsbn(this.book, isbn);
                            }

                            public class BookWithIsbn {
                                private Book book;
                                public BookWithIsbn(Book book, String isbn) {
                                    this.book = book;
                                    this.book.isbn = isbn;
                                }

                                public BookWithPublicationDate withPublicationDate(LocalDate publicationDate) {
                                    return new BookWithPublicationDate(this.book, publicationDate);
                                }

                                public class BookWithPublicationDate {
                                    private Book book;
                                    public BookWithPublicationDate(Book book, LocalDate publicationDate) {
                                        this.book = book;
                                        this.book.publicationDate = publicationDate;
                                    }

                                    public BookWithAuthor withAuthor(Author author) {
                                        return new BookWithAuthor(this.book, author);
                                    }

                                    public class BookWithAuthor {
                                        private Book book;
                                        public BookWithAuthor(Book book, Author author) {
                                            this.book = book;
                                            this.book.author = author;
                                        }

                                        public BookWithCategory withCategory(Category category) {
                                            return new BookWithCategory(this.book, category);
                                        }

                                        public class BookWithCategory {
                                            private Book book;
                                            public BookWithCategory(Book book, Category category) {
                                                this.book = book;
                                                this.book.category = category;
                                            }

                                            public Book build() {
                                                return this.book;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
