package br.com.casadocodigo.forms;


import br.com.casadocodigo.constraints.UniqueEmail;
import br.com.casadocodigo.entities.Autor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AutorForm {

    @Email @NotNull @NotEmpty @UniqueEmail
    private String email;
    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty @Length(min = 1, max = 400)
    private String descricao;

    public Autor toEntity() {
        return new Autor(this.email, this.nome, this.descricao);
    }

}
