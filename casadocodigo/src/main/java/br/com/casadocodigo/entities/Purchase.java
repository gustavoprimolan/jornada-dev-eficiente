package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.PurchaseDto;
import br.com.casadocodigo.dtos.PurchasedItemDto;
import br.com.casadocodigo.exceptions.InvalidTotalPriceException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "Purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany
    private Set<PurchasedItem> purchasedItems;

    /**      
     * @deprecated (it is not recommended build an empty object, but jpa needs this guy... =/)      
     * */
    @Deprecated
    public Purchase() {}

    public Purchase(String email, String name, String lastName,
                    String cpfCnpj, String address, String complement,
                    String city, String phone, String cep,
                    BigDecimal totalPrice, Country country, State state,
                    List<Function<Purchase, PurchasedItem>> functionPurchasedItems) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.phone = phone;
        this.cep = cep;
        this.totalPrice = totalPrice;
        this.country = country;
        this.state = state;
        this.purchasedItems = functionPurchasedItems.stream().map(function -> function.apply(this)).collect(Collectors.toSet());
    }

    public PurchaseDto toDto() {
        Set<PurchasedItemDto> purchasedItemsDto = this.purchasedItems.stream().map(PurchasedItem::toDto).collect(Collectors.toSet());
        return new PurchaseDto(this.id, this.email, this.name,
                this.lastName, this.cpfCnpj , this.address,
                this.complement, this.city, this.phone,
                this.cep, this.totalPrice, this.country.toDto(),
                this.state.toDto(), purchasedItemsDto);
    }

    public void validateTotalPriceWithTotalPriceOfBooks() {
        BigDecimal totalPriceOfBooks = this.purchasedItems.stream().map(PurchasedItem::sumBookPriceWithQuantity).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        if(!totalPriceOfBooks.equals(this.totalPrice)) throw new InvalidTotalPriceException();
    }

}
