import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NumberSchemaTest {
    @Test
    public void simpleTest() throws Exception {

        var v = new Validator();
        var schema = v.number();
        var schema2 = v.number();

        assertNotEquals(schema, schema2);

    }

    @Test
    public void simpleTest2() throws Exception {

        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

    }

    @Test
    public void simpleTest3() throws Exception {

        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.isValid(""));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));

    }

    @Test
    public void simpleTest4() throws Exception {

        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertFalse(schema.contains("22").isValid("what does the fox say"));

    }

    @Test
    public void simpleTest5() throws Exception {

        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.minLength(4).isValid("what does the fox say"));
        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
//        assertFalse(schema.minLength(10).minLength(4).minLength(8).isValid("Hexlet"));

    }

}
