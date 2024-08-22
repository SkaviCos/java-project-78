package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        predicatesList().add(v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        predicatesList().add(v -> v.length() >= length);
        return new StringSchema();
    }

    public StringSchema contains(String str) {
        predicatesList().add(v -> v.contains(str));
        return this;
    }

}
