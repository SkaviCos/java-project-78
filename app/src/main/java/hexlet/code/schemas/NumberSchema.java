package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addValidation("positive", v -> v == null || v > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        addValidation("range", v -> v == null || (v >= from && v <= to));
        return this;
    }
}
