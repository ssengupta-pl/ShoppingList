package name.sengupta.soumya.ShoppingList.test.repositories;

import name.sengupta.soumya.ShoppingList.model.Store;
import name.sengupta.soumya.ShoppingList.repositories.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StoreRepositoryTests {
    private static int NUM_STORES = 5;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void testStoreNameNotBlankConstraint() throws Exception {
        // Fetch the stores created from data.sql.
        List<Store> stores = storeRepository.findAll();
        int initialCount = stores.size();

        // Create a new Store entity with an empty name.
        Store store = new Store();

        // Assert that an exception is actually thrown during a create.
        ConstraintViolationException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            storeRepository.saveAndFlush(store);
        });

        // Assert that the expected reason matches up with reality. Before checking message make sure that
        // there is only one violation is actually included.
        assertNotNull(exception.getConstraintViolations());
        assertEquals(1, exception.getConstraintViolations().size());
        assertEquals("Store name cannot be blank", exception.getConstraintViolations().iterator().next().getMessage());

        // As an added check make sure that nothing new was added to the database.
        stores = storeRepository.findAll();
        assertEquals(initialCount, stores.size());
    }

    @Test
    @Transactional
    public void testCreateStore() throws Exception {
        // Constants.
        final String storeName = "Target";

        // Fetch the stores created from data.sql.
        List<Store> stores = storeRepository.findAll();

        // Make sure the correct number of stores has been read.
        assertEquals(NUM_STORES, stores.size());

        // Create a new store.
        Store store = new Store();
        store.setName(storeName);

        // Save the newly created store.
        Store savedStore = storeRepository.saveAndFlush(store);

        // Fetch the stores again.
        stores = storeRepository.findAll();

        // Make sure that the number of stores is now one more.
        assertEquals(NUM_STORES + 1, stores.size());

        // Check whether the saved object has the right properties.
        assertNotNull(savedStore);
        assertEquals(storeName, savedStore.getName());
        assertNotNull(store.getCreatedDate());
    }
}
