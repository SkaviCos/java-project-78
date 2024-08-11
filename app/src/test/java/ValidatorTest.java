import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {

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
        assertTrue(schema.isValid(new HashMap<>()));
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

    @Test
    public void complexMapTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        schema.sizeof(2);
        assertFalse(schema.isValid(new HashMap<>()));
        Map<String, String> actual1 = new HashMap<>();
        actual1.put("key1", "value1");
        assertFalse(schema.isValid(actual1));
        actual1.put("key2", "value2");
        assertTrue(schema.isValid(actual1));

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().contains("ya").required());
        schemas.put("age", v.number().positive());

        schema.shape(schemas);

        Map<String, Object> actual2 = new HashMap<>();
        actual2.put("name", "Kolya");
        actual2.put("age", 100);
        assertTrue(schema.isValid(actual2));

        Map<String, Object> actual3 = new HashMap<>();
        actual3.put("name", "Maya");
        actual3.put("age", null);
        assertTrue(schema.isValid(actual3));

        Map<String, Object> actual4 = new HashMap<>();
        actual4.put("name", "");
        actual4.put("age", null);
        assertFalse(schema.isValid(actual4));

        Map<String, Object> actual5 = new HashMap<>();
        actual5.put("name", "Valya");
        actual5.put("age", -5);
        assertFalse(schema.isValid(actual5));

        Map<String, Object> actual6 = new HashMap<>();
        actual6.put("name", "Ada");
        actual6.put("age", 15);
        assertFalse(schema.isValid(actual6));

    }
}
