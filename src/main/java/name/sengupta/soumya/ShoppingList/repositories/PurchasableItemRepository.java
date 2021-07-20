package name.sengupta.soumya.ShoppingList.repositories;

import name.sengupta.soumya.ShoppingList.model.PurchasableItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasableItemRepository extends JpaRepository<PurchasableItem, Long> {
}
