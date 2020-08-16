package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.CustomerDto;
import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@AllArgsConstructor
public class Customer {

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "cpf_cnpj", nullable = false)
    private String cpfCnpj;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name = "complement", nullable = false)
    private String complement;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "cep", nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    /**      
     * @deprecated (it is not recommended build an empty object, but jpa needs this guy... =/)      
     * */
    @Deprecated
    public Customer() {}

    public CustomerDto toDto() {
        return new CustomerDto(this.email, this.name, this.lastName,
                this.cpfCnpj, this.address, this.complement,
                this.city, this.phone, this.cep, this.state.toDto());
    }

}
