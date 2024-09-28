package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        super.predicatesList(v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        super.predicatesList(v -> v == null || v.length() >= length);
        return this;
    }

    public StringSchema contains(String str) {
        super.predicatesList(v -> v == null || v.contains(str));
        return this;
    }

}
