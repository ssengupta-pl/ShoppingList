package name.sengupta.soumya.ShoppingList.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class PurchasableItem extends AuditableBaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Item name cannot be blank")
    private String name;

    @Positive(message = "Item quantity cannot be 0 or less")
    private double quantity;

    private String unit;

    private boolean isPurchased;

    private Date purchaseDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasableItem that = (PurchasableItem) o;
        return Double.compare(that.quantity, quantity) == 0 &&
                isPurchased == that.isPurchased &&
                id.equals(that.id) &&
                name.equals(that.name) &&
                unit.equals(that.unit) &&
                purchaseDate.equals(that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, unit, isPurchased, purchaseDate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PurchasableItem.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("quantity=" + quantity)
                .add("unit='" + unit + "'")
                .add("isPurchased=" + isPurchased)
                .add("purchaseDate=" + purchaseDate)
                .toString();
    }
}
