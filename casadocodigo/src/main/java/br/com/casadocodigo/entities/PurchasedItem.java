package br.com.casadocodigo.entities;

import br.com.casadocodigo.dtos.PurchasedItemDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "PurchasedItem")
public class PurchasedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    /**      
     * @deprecated (it is not recommended build an empty object, but jpa needs this guy... =/)      
     * */
    @Deprecated
    public PurchasedItem() {}

    public PurchasedItem(int quantity, Book book, Purchase purchase) {
        this.quantity = quantity;
        this.book = book;
        this.purchase = purchase;
    }

    public PurchasedItemDto toDto() {
        return new PurchasedItemDto(this.book.toDto(), this.quantity);
    }

    public BigDecimal sumBookPriceWithQuantity() {
        return this.book.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
