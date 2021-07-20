package name.sengupta.soumya.ShoppingList.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class PurchaseLineItem extends AuditableBaseClass {
    private PurchasableItem purchasableItem;

    private int quantity;

    private Date purchaseDate;

    public PurchasableItem getPurchasableItem() {
        return purchasableItem;
    }

    public void setPurchasableItem(PurchasableItem purchasableItem) {
        this.purchasableItem = purchasableItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseLineItem that = (PurchaseLineItem) o;
        return quantity == that.quantity &&
                purchasableItem.equals(that.purchasableItem) &&
                purchaseDate.equals(that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchasableItem, quantity, purchaseDate);
    }
}
