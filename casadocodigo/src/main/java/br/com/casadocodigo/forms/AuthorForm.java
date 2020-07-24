package br.com.casadocodigo.forms;


import br.com.casadocodigo.constraints.Unique;
import br.com.casadocodigo.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class AuthorForm {

    @Unique(entityClass = Author.class, entityField = "email")
    @Email @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty @Length(min = 1, max = 400)
    private String description;

    public Author toEntity() {
        return new Author(this.email, this.name, this.description);
    }

}
