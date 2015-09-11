package salestax.factory;

import static org.junit.Assert.*;

import org.junit.Test;
import salestax.entity.Basket;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class BasketFactoryTest {

    @Test
    public void shouldFailToReadFile() {
        try {
            BasketFactory.create(new File("no_file.txt"), getClass());

            fail("FileNotFoundException not thrown");
        } catch (FileNotFoundException e) {
            assertNotNull(e.getMessage());
            assertEquals("no_file.txt (No such file or directory)", e.getMessage());
        }
    }

    @Test
    public void shouldFailWithEmptyFile() throws URISyntaxException, FileNotFoundException {
        String filename = "import_test_one.txt";
        URL resourceFile = getClass().getResource(filename);

        try {
            BasketFactory.create(new File(resourceFile.toURI()), getClass());
            fail("IllegalStateException not thrown for empty file");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertEquals(filename + " (Empty file)", e.getMessage());
        }
    }

    @Test
    public void shouldBeABasketWithAllProducts() throws URISyntaxException, FileNotFoundException, IllegalStateException {
        String filename = "import_test_two.txt";
        URL resourceFile = getClass().getResource(filename);

        Basket basket = BasketFactory.create(new File(resourceFile.toURI()), getClass());

        assertEquals(Integer.valueOf(7), basket.getNumberOfProducts());
    }

    @Test
    public void shouldBeABasketWithSomeProducts() throws URISyntaxException, FileNotFoundException, IllegalStateException {
        String filename = "import_test_three.txt";
        URL resourceFile = getClass().getResource(filename);

        Basket basket = BasketFactory.create(new File(resourceFile.toURI()), getClass());

        assertEquals(Integer.valueOf(5), basket.getNumberOfProducts());
    }

}
