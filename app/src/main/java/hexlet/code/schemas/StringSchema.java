package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        addValidation("required", v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addValidation("minLength", v -> v == null || v.length() >= length);
        return this;
    }

    public StringSchema contains(String str) {
        addValidation("contains", v -> v == null || v.contains(str));
        return this;
    }

}
