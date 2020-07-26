package br.com.casadocodigo.forms;

import br.com.casadocodigo.constraints.Unique;
import br.com.casadocodigo.entities.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CategoryForm {

    @Unique(entityClass = Category.class, entityField = "name")
    @NotEmpty @NotNull
    private String name;

    private CategoryForm() {}

    public Category toEntity() {
        return new Category(this.name);
    }
}
