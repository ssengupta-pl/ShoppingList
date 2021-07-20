package name.sengupta.soumya.ShoppingList.repositories;

import name.sengupta.soumya.ShoppingList.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
