package salestax.factory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import salestax.entity.Product;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@RunWith(value = Parameterized.class)
public class ProductFactoryTest {

    private Product product;

    private String expectedDescription;

    private Double expectedPrice;

    private Boolean expectedExempt;

    private Boolean expectedImported;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"book at 12.49", "book", 12.49, true, false},
                {"chocolate bar at 0.85", "chocolate bar", 0.85, true, false},
                {"imported box of chocolates at 10.00", "imported box of chocolates", 10.00, true, true},
                {"imported bottle of perfume at 47.50", "imported bottle of perfume", 47.50, false, true},
                {"imported bottle of perfume at 27.99", "imported bottle of perfume", 27.99, false, true},
                {"bottle of perfume at 18.99", "bottle of perfume", 18.99, false, false},
                {"packet of headache pills at 9.75", "packet of headache pills", 9.75, true, false},
                {"box of imported chocolates at 11.25", "box of imported chocolates", 11.25, true, true},
                {"box of imported chocolates", "box of imported chocolates", 0.00, true, true},
        });
    }

    public ProductFactoryTest(String productString, String description, Double price, Boolean exempt, Boolean imported) throws Exception {
        product = ProductFactory.create(productString, getClass());
        expectedDescription = description;
        expectedPrice = price;
        expectedImported = imported;
        expectedExempt = exempt;
    }

    @Test
    public void shouldBeAProduct() {
        assertNotNull(product);
    }

    @Test
    public void productShouldHaveDescription() {
        assertEquals(expectedDescription, product.getDescription());
    }

    @Test
    public void productShouldHavePrice() {
        assertEquals(expectedPrice, product.getPrice());
    }

    @Test
    public void productShouldHaveExemptStatus() {
        assertEquals(expectedExempt, product.isExempt());
    }

    @Test
    public void productShouldHaveImportStatus() {
        assertEquals(expectedImported, product.isImported());
    }

}
