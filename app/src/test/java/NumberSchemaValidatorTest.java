import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaValidatorTest {

    @Test
    public void requiredValidationTests() {

        Validator v = new Validator();
        var schema = v.number();

        assertTrue(schema.required().isValid(5));
        assertFalse(schema.required().isValid(null));
        assertTrue(schema.required().isValid(0));
        assertTrue(schema.required().positive().isValid(5));
    }

    @Test
    public void positiveValidationTests() {

        Validator v = new Validator();
        var schema = v.number();

        assertTrue(schema.positive().isValid(5));
        assertFalse(schema.positive().isValid(0));
        assertFalse(schema.positive().isValid(-10));
        assertTrue(schema.required().positive().isValid(10));
    }


    @Test
    public void rangeValidationTests() {

        Validator v = new Validator();
        var schema = v.number();

        assertTrue(schema.range(1, 10).isValid(5));
        assertFalse(schema.range(0, 5).isValid(0));
        assertTrue(schema.range(0, 5).isValid(5));
        assertFalse(schema.range(10, 20).range(15, 25).isValid(18));
    }

    @Test
    public void combinedValidationTests() {

        Validator v = new Validator();
        var schema = v.number();

        assertTrue(schema.required().positive().range(1, 10).isValid(5));
        assertFalse(schema.required().positive().isValid(0));
        assertFalse(schema.required().range(1, 10).isValid(-1));
        assertTrue(schema.required().positive().range(1, 10).isValid(8));
    }

}
