package name.sengupta.soumya.ShoppingList.test.repositories;

import name.sengupta.soumya.ShoppingList.model.PurchasableItem;
import name.sengupta.soumya.ShoppingList.repositories.PurchasableItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PurchasableItemRepositoryTests {
    @Autowired
    private PurchasableItemRepository purchasableItemRepository;

    /**
     * Standardized template to run tests for one constraint only. The caller of this method needs to
     * supply the appropriately constructed ShoppingItem instance (with only one constraint violation) and
     * the expected error message the validator is supposed to throw.
     *
     * @param purchasableItem The instance with a constraint violation.
     * @param expectedMessage The message the validator is expected to throw.
     */
    private void testSingleShoppingItemConstraint(PurchasableItem purchasableItem, String expectedMessage) {
        // Fetch the shopping items created from data.sql.
        List<PurchasableItem> purchasableItems = purchasableItemRepository.findAll();
        int initialCount = purchasableItems.size();

        // Assert that an exception is actually thrown during a create.
        ConstraintViolationException exception = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            purchasableItemRepository.saveAndFlush(purchasableItem);
        });

        // Assert that the expected reason matches up with reality. Before checking message make sure that
        // there is only one violation is actually included.
        assertNotNull(exception.getConstraintViolations());
        assertEquals(1, exception.getConstraintViolations().size());
        assertEquals(expectedMessage, exception.getConstraintViolations().iterator().next().getMessage());

        // As an added check make sure that nothing new was added to the database.
        purchasableItems = purchasableItemRepository.findAll();
        assertEquals(initialCount, purchasableItems.size());
    }

    @Test
    public void testShoppingItemNameNotBlankConstraint() {
        // Create a new ShoppingItem entity with an empty name but with a valid quantity.
        PurchasableItem purchasableItem = new PurchasableItem();
        purchasableItem.setQuantity(1.0);

        // Expected message if constraint fails.
        String expectedMessage = "Item name cannot be blank";

        // Run the test.
        this.testSingleShoppingItemConstraint(purchasableItem, expectedMessage);
    }

    @Test
    public void testShoppingItemQuantityPositiveConstraint() {
        // Create a new ShoppingItem entity with a valid name but with an invalid quantity.
        PurchasableItem purchasableItem = new PurchasableItem();
        purchasableItem.setName("Tomatoes");
        purchasableItem.setQuantity(-1.0);

        // Expected message if constraint fails.
        String expectedMessage = "Item quantity cannot be 0 or less";

        // Run the test.
        this.testSingleShoppingItemConstraint(purchasableItem, expectedMessage);
    }

    @Test
    public void testCreateShoppingItem() {
        // Constants.
        final String tomatoes = "Tomatoes";
        final double quantity = 5.0;
        final String units = "pieces";

        // Fetch the shopping items created from data.sql.
        List<PurchasableItem> purchasableItems = purchasableItemRepository.findAll();
        int initialCount = purchasableItems.size();

        // Create a new valid ShoppingItem entity.
        PurchasableItem purchasableItem = new PurchasableItem();
        purchasableItem.setName(tomatoes);
        purchasableItem.setQuantity(quantity);
        purchasableItem.setUnit(units);

        // Save the shopping item in the database.
        PurchasableItem savedPurchasableItem = purchasableItemRepository.saveAndFlush(purchasableItem);

        // Check if the item got saved or not.
        purchasableItems = purchasableItemRepository.findAll();
        assertNotNull(purchasableItems);
        assertEquals(initialCount + 1, purchasableItems.size());

        // Now check the item itself.
        assertNotNull(savedPurchasableItem);
        assertEquals(tomatoes, savedPurchasableItem.getName());
        assertEquals(quantity, savedPurchasableItem.getQuantity());
        assertEquals(units, savedPurchasableItem.getUnit());
        assertNotNull(savedPurchasableItem.getCreatedDate());

    }
}
