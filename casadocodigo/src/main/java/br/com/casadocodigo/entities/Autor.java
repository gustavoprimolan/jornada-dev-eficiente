package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.AutorDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="instante", nullable = false)
    private LocalDateTime instante;

    @Email
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="descricao", nullable = false, length = 400)
    private String descricao;

    public Autor(String email, String nome, String descricao) {
        this.instante = LocalDateTime.now();
        this.email = email;
        this.nome = nome;
        this.descricao = descricao;
    }

    public AutorDto toDto() {
        return new AutorDto(this.id, this.instante, this.email, this.nome, this.descricao);
    }

}
