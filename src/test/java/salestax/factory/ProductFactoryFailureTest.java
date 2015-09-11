package salestax.factory;

import org.junit.Test;
import salestax.entity.Product;

import static org.junit.Assert.*;

public class ProductFactoryFailureTest {

    @Test
    public void shouldFailToCreateProductIfEmptyString() {
        Product product = null;

        try {
            product = ProductFactory.create("", getClass());
        } catch (Exception e) {
            assertNotNull(e.getMessage());
            assertEquals("You did not provide me anything to be able to create a product.", e.getMessage());
        }

        assertNull(product);
    }

    @Test
    public void shouldFailToCreateProductDueToDescription() {

        Product product = null;

        try {
            product = ProductFactory.create("11.25", getClass());
        } catch (Exception e) {
            assertNotNull(e.getMessage());
            assertEquals("Unable to find product description from: 11.25", e.getMessage());
        }

        assertNull(product);

    }


}
