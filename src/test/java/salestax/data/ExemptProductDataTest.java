package salestax.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class ExemptProductDataTest {

    private ExemptProductData exemptProductData;

    @Before
    public void setup() throws IOException {
        exemptProductData = new ExemptProductData(getClass(), Resources.exemptProductsData);
    }

    @After
    public void tearDown() {
        exemptProductData = null;
    }

    @Test
    public void shouldReturnAList() {
        assertNotNull(exemptProductData.all());
    }

    @Test
    public void shouldDataListFromTextFile() {
        assertNotNull(exemptProductData.all());
        assertTrue(exemptProductData.size() > 0);
    }

    @Test
    public void shouldFindItemInTextFile() {
        assertTrue(exemptProductData.has("book"));
    }

    @Test
    public void shouldAddItemInTextFile() {
        assertTrue(exemptProductData.add("bike").has("bike"));
    }

}
