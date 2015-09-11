package util;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void shouldHaveStringPaddedLeft() {
        String input = "hello";
        String expected = "  hello";
        Integer totalLength = 7;

        assertEquals(expected, StringUtils.padLeft(input, totalLength));
    }

    @Test
    public void shouldErrorWhenPaddedLeftWithNegativeNumber() {
        try {
            String input = "hello";
            Integer totalLength = -1;

            StringUtils.padLeft(input, totalLength);
            fail("IllegalFormatException was not thrown.");

        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertEquals("Cannot pad with a number less than 1.", e.getMessage());
        }
    }

    @Test
    public void shouldHaveStringPaddedRight() {
        String input = "hello";
        String expected = "hello  ";
        Integer totalLength = 7;

        assertEquals(expected, StringUtils.padRight(input, totalLength));
    }

    @Test
    public void shouldErrorWhenPaddedRightWithNegativeNumber() {
        try {
            String input = "hello";
            Integer totalLength = -1;

            StringUtils.padRight(input, totalLength);
            fail("IllegalFormatException was not thrown.");

        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertEquals("Cannot pad with a number less than 1.", e.getMessage());
        }
    }

}
