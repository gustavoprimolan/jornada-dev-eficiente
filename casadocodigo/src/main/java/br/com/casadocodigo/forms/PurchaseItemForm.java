package br.com.casadocodigo.forms;

import br.com.casadocodigo.entities.Book;
import br.com.casadocodigo.entities.Purchase;
import br.com.casadocodigo.entities.PurchasedItem;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.function.Function;

@Setter
public class PurchaseItemForm {

    @NotNull
    private Long bookId;
    @NotNull @Min(1)
    private Integer quantity;

    public Function<Purchase, PurchasedItem> toEntity(EntityManager entityManager) {
        return (purchase) -> {
            Book book = entityManager.find(Book.class, bookId);
            return new PurchasedItem(quantity, book, purchase);
        };
    }
}
