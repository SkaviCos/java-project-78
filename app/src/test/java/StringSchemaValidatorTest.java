import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaValidatorTest {

    @Test
    public void stringSchemaIsValidWithTwoSchema() throws Exception {

        var v = new Validator();
        var schema = v.string();
        var schema2 = v.string();

        assertNotEquals(schema, schema2);

    }

    @Test
    public void stringSchemaIsValidEmptyOrNull() throws Exception {

        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

    }

    @Test
    public void stringSchemaRequiredTest() throws Exception {

        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.isValid(""));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));

    }

    @Test
    public void stringSchemaContainsTest() throws Exception {

        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertFalse(schema.contains("22").isValid("what does the fox say"));

    }

    @Test
    public void stringSchemaMinLengthTest() throws Exception {

        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.minLength(4).isValid("what does the fox say"));
    }

}
