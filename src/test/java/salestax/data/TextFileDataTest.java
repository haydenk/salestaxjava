package salestax.data;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class TextFileDataTest {

    @Test
    public void shouldCreateTextFileClassWithoutError() throws FileNotFoundException, URISyntaxException {
        File file = new File(getClass().getResource("text_file_test.txt").toURI());
        TextFileData textFileData = new TextFileData(file);

        assertNotNull(file);
        assertNotNull(textFileData);
    }

    @Test
    public void shouldCreateTextFileClassWithFileNotFoundError() throws URISyntaxException {
        try {
            String fileName = "/non_existent_file.txt";
            new TextFileData(new File(fileName));
            fail("FileNotFoundException not thrown for " + fileName);
        } catch (FileNotFoundException e) {
            assertNotNull(e.getMessage());
        }
    }


}
