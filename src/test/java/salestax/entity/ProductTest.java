package salestax.entity;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.StringUtils;

import java.util.List;
import java.util.Arrays;

@RunWith(value = Parameterized.class)
public class ProductTest {

    private Product product;

    private String expectedDescription;

    private Double expectedPrice;

    private Boolean expectedExempt;

    private Boolean expectedImported;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"book", 12.49, false, true},
                {"music CD", 14.99, false, false},
                {"chocolate bar", 0.85, false, false},
                {"bandages", 5.00, true, false},
                {"syringes", 75.00, true, true}
        });
    }

    public ProductTest(String description, Double price, Boolean exempt, Boolean imported) {
        this.product = new Product(description, price, exempt, imported);
        this.expectedDescription = description;
        this.expectedPrice = price;
        this.expectedImported = imported;
        this.expectedExempt = exempt;
    }

    @Test
    public void shouldBeAProduct() {
        assertTrue(product != null);
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

    @Test
    public void productShouldHaveAStringValue() {
        String expectedOutput="";

        expectedOutput+= product.getDescription();
        expectedOutput+= StringUtils.padRight(" ", Product.defaultPadding);
        expectedOutput+= String.format("$%7.2f", product.getPrice());

        assertEquals(expectedOutput, product.toString());
    }

}
