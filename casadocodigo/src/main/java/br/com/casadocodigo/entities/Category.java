package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.CategoryDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /**      
     * @deprecated (it is not recommended build an empty object, but jpa needs this guy... =/)      
     * */
    @Deprecated
    protected Category() {}

    public Category(String name) {
        this.name = name;
    }

    public CategoryDto toDto() {
        return new CategoryDto(this.id, this.name);
    }

}
