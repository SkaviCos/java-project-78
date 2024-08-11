package hexlet;

import hexlet.code.MapSchema;
import hexlet.code.NumberSchema;
import hexlet.code.StringSchema;

public class Validator {

    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }

}
