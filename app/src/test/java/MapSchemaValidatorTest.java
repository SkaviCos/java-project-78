import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaValidatorTest {

    @Test
    public void mapSchemaTest() {
        final MapSchema schema = new Validator().map();

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
    public void mapShapeTest() {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required());
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        assertFalse(schema.isValid(human2));

    }

    @Test
    public void stringAndNumberMapTest() {
        var schema = new Validator().map();
        var schema1 = new Validator().string().required();
        var schema2 = new Validator().number().positive().required();

        Map<Object, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("first name", schema1);
        schemas.put(4, schema2);

        assertTrue(schema.isValid(schemas));
    }

    @Test
    public void complexMapTest() {
        var v = new Validator();
        var schema = new Validator().map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required());
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));
    }

}
