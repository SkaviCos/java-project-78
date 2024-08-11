import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
//        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
//        assertFalse(schema.minLength(10).minLength(4).minLength(8).isValid("Hexlet"));

    }

    @Test
    public void testNumberSchema() {
        final Validator v = new Validator();
        final NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid("5"));

        schema.positive();
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testMapSchema() {
        final Validator v = new Validator();
        final MapSchema schema = v.map();

        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap()));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testMapShape() {
        final Validator validator = new Validator();
        final MapSchema schema = validator.map();

        Map<String, BaseSchema> data = new HashMap<>();
        data.put("name", validator.string().required());
        data.put("age", validator.number().positive());
        schema.shape(data);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Mary");
        human4.put("age", -10);
        assertFalse(schema.isValid(human4));
    }

}
