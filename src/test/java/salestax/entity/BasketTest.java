package salestax.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import salestax.Application;
import util.StringUtils;

public class BasketTest {

    final static private Integer expectedBasketSize = 4;

    final static private Double expectedSubTotal = 199.09;

    final static private Double expectedTotal = 223.12;

    final static private Double expectedSalesTaxTotal = 24.03;

    private Basket basket;

    @Before
    public void setUp() {
        basket = new Basket();

        basket
                .addProduct(new Product("bike", 56.59, false, false))
                .addProduct(new Product("book", 20.00, true, false))
                .addProduct(new Product("box of chocolates", 100.00, false, true))
                .addProduct(new Product("headache medicine", 22.50, true, true));
    }

    @Test
    public void shouldBeABasket() {
        assertTrue(basket != null);
    }

    @Test
    public void basketShouldBeEmpty() {
        Basket basket = new Basket();
        Integer expectedBasketSize = 0;

        assertEquals(expectedBasketSize, basket.getNumberOfProducts());
    }

    @Test
    public void basketShouldHaveMultipleProducts() {
        assertEquals(expectedBasketSize, basket.getNumberOfProducts());
    }

    @Test
    public void basketShouldHaveSubTotal() {
        assertEquals(expectedSubTotal, basket.getSubTotal());
    }

    @Test
    public void basketShouldHaveSalesTaxTotal() {
        assertEquals(expectedSalesTaxTotal, basket.getSalesTaxTotal());
    }

    @Test
    public void basketShouldHaveTotal() {
        assertEquals(expectedTotal, basket.getTotal());
    }

    @Test
    public void basketShouldHaveLengthOfLongestProductDescription() {
        assertEquals(Integer.valueOf(17), Integer.valueOf(basket.getProductWithLongestString().getDescription().length()));
    }

    @Test
    public void basketShouldHaveItemizedOutputAsString() {

        String expectedOutput = "";

        for(Product product : basket.getProducts()) {

            String stringPrice = String.format("$%7.2f", product.getPrice());

            expectedOutput+= product.getDescription();
            expectedOutput+= StringUtils.padLeft(" ", 27 - product.getDescription().length() - stringPrice.length());
            expectedOutput+= stringPrice;
            expectedOutput+= Application.newLine;
        }

        for(int i=0; i<27; i++) expectedOutput+="=";

        expectedOutput+= Application.newLine;
        expectedOutput+= "         Subtotal: " + String.format("$%7.2f", basket.getSubTotal()) + Application.newLine;
        expectedOutput+= "              Tax: " + String.format("$%7.2f", basket.getSalesTaxTotal()) + Application.newLine;
        expectedOutput+= "            Total: " + String.format("$%7.2f", basket.getTotal()) + Application.newLine;

        assertEquals(expectedOutput, basket.toString());

    }

}
