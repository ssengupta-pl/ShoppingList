package name.sengupta.soumya.ShoppingList.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ShoppingList extends AuditableBaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    private List<PurchasableItem> shoppingItems;

    private boolean isPurchased;

    private Date purchaseDate;

    private String name;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<PurchasableItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<PurchasableItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return id.equals(that.id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ShoppingList.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("store=" + store)
                .add("shoppingItems=" + shoppingItems)
                .add("isPurchased=" + isPurchased)
                .add("purchaseDate=" + purchaseDate)
                .add("name='" + name + "'")
                .toString();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
