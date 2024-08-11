package hexlet.code.schemas;


public class StringSchema extends BaseSchema {
    public StringSchema required() {
        getPredicates().add(v -> v instanceof String && !((String) v).isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        getPredicates().add(v -> v.toString().length() >= length);
        return new StringSchema();
    }

    public StringSchema contains(String str) {
        getPredicates().add(v -> v.toString().contains(str));
        return this;
    }

}
