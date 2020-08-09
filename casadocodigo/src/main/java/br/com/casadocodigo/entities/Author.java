package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.AuthorDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="instant", nullable = false)
    private LocalDateTime instant;

    @Email
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false, length = 400)
    private String description;

    /**      
     * @deprecated (it is not recommended build an empty object, but jpa needs this guy... =/)      
     * */
    @Deprecated
    public Author() {}

    public Author(String email, String nome, String descricao) {
        this.instant = LocalDateTime.now();
        this.email = email;
        this.name = nome;
        this.description = descricao;
    }

    public AuthorDto toDto() {
        return new AuthorDto(this.id, this.instant, this.email, this.name, this.description);
    }

}
