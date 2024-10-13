import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaValidatorTest {

    @Test
    public void requiredValidationTests() {

        var schema = new Validator().number().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-1));
    }

    @Test
    public void positiveValidationTests() {

        var schema = new Validator().number().positive();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-10));
    }


    @Test
    public void rangeValidationTests() {

        var schema = new Validator().number();

        assertTrue(schema.range(0, 5).isValid(0));
        assertTrue(schema.range(0, 5).isValid(2));
        assertFalse(schema.range(0, 5).isValid(-1));
        assertTrue(schema.range(0, 5).isValid(5));
        assertTrue(schema.range(0, 5).isValid(null));
        assertTrue(schema.range(10, 20).range(15, 25).isValid(18));
        assertFalse(schema.range(10, 20).range(21, 25).isValid(18));
    }

    @Test
    public void combinedValidationTests() {

        var schema = new Validator().number().positive();

        assertTrue(schema.range(1, 10).isValid(8));
        assertFalse(schema.range(-1, 10).isValid(-1));
        assertTrue(schema.range(-1, 10).isValid(null));
    }

}
