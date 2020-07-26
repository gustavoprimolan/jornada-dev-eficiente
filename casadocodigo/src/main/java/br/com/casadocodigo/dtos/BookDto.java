package br.com.casadocodigo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class BookDto {

    private Long id;
    private String title;
    private String summary;
    private String preface;
    private BigDecimal price;
    private int pageNumber;
    private String isbn;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate publicationDate;
    private AuthorDto author;
    private CategoryDto category;

    @Deprecated
    public BookDto(){}

    public static final class Builder {

        private Builder() {}

        public static BookWithId withId(Long id) {
            BookDto book = new BookDto();
            return new BookWithId(book, id);
        }

        public static class BookWithId {
            private BookDto book;
            public BookWithId(BookDto book, Long id) {
                this.book = book;
                this.book.id = id;
            }

            public BookWithTitle withTitle(String title) {
                return new BookWithTitle(this.book, title);
            }

            public static class BookWithTitle {
                private BookDto book;
                public BookWithTitle(BookDto book, String title) {
                    this.book = book;
                    this.book.title = title;
                }

                public BookWithSummary withSummary(String summary) {
                    return new BookWithSummary(this.book, summary);
                }

                public class BookWithSummary {
                    private BookDto book;
                    public BookWithSummary(BookDto book, String summary) {
                        this.book = book;
                        this.book.summary = summary;
                    }

                    public BookWithPreface withPreface(String preface) {
                        return new BookWithPreface(this.book, preface);
                    }

                    public class BookWithPreface {
                        private BookDto book;
                        public BookWithPreface(BookDto book, String preface) {
                            this.book = book;
                            this.book.preface = preface;
                        }

                        public BookWithPrice withPrice(BigDecimal price) {
                            return new BookWithPrice(this.book, price);
                        }

                        public class BookWithPrice {
                            private BookDto book;
                            public BookWithPrice(BookDto book, BigDecimal price) {
                                this.book = book;
                                this.book.price = price;
                            }

                            public BookWithPageNumber withPageNumber(int pageNumber) {
                                return new BookWithPageNumber(this.book, pageNumber);
                            }

                            public class BookWithPageNumber {
                                private BookDto book;

                                public BookWithPageNumber(BookDto book, int pageNumber) {
                                    this.book = book;
                                    this.book.pageNumber = pageNumber;
                                }

                                public BookWithIsbn withIsbn(String isbn) {
                                    return new BookWithIsbn(this.book, isbn);
                                }

                                public class BookWithIsbn {
                                    private BookDto book;
                                    public BookWithIsbn(BookDto book, String isbn) {
                                        this.book = book;
                                        this.book.isbn = isbn;
                                    }

                                    public BookWithPublicationDate withPublicationDate(LocalDate publicationDate) {
                                        return new BookWithPublicationDate(this.book, publicationDate);
                                    }

                                    public class BookWithPublicationDate {
                                        private BookDto book;
                                        public BookWithPublicationDate(BookDto book, LocalDate publicationDate) {
                                            this.book = book;
                                            this.book.publicationDate = publicationDate;
                                        }

                                        public BookWithAuthor withAuthor(AuthorDto author) {
                                            return new BookWithAuthor(this.book, author);
                                        }

                                        public class BookWithAuthor {
                                            private BookDto book;
                                            public BookWithAuthor(BookDto book, AuthorDto author) {
                                                this.book = book;
                                                this.book.author = author;
                                            }

                                            public BookWithCategory withCategory(CategoryDto category) {
                                                return new BookWithCategory(this.book, category);
                                            }

                                            public class BookWithCategory {
                                                private BookDto book;
                                                public BookWithCategory(BookDto book, CategoryDto category) {
                                                    this.book = book;
                                                    this.book.category = category;
                                                }

                                                public BookDto build() {
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
    
    
    
    
}
