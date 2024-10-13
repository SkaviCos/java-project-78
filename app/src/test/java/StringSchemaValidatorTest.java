import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaValidatorTest {


    @Test
    public void stringSchemaRequiredTest() throws Exception {

        var schema = new Validator().string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));

    }

    @Test
    public void stringSchemaContainsTest() throws Exception {

        var schema = new Validator().string();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertFalse(schema.contains("22").isValid("what does the fox say"));
        assertTrue(schema.contains(" ").isValid("what does the fox say"));

    }

    @Test
    public void stringSchemaMinLengthTest() throws Exception {

        var schema = new Validator().string();

        assertTrue(schema.minLength(4).isValid("what does the fox say"));
        assertTrue(schema.minLength(4).isValid("what"));
        assertFalse(schema.minLength(4).isValid("w"));
        assertFalse(schema.minLength(4).isValid(" "));
        assertTrue(schema.minLength(0).isValid(" "));
        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
        assertFalse(schema.minLength(10).minLength(4).isValid("Hex"));
    }

    @Test
    public void combinedStringSchemaTest() throws Exception {

        var schema = new Validator().string();

        assertFalse(schema.minLength(4).contains("22").isValid("what does the fox say"));
        assertFalse(schema.minLength(10).contains("H").isValid("Hexlet"));
        assertTrue(schema.minLength(3).contains("H").isValid("Hex"));
    }

}
