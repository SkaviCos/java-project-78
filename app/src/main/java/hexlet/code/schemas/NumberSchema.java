package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        predicatesList("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        predicatesList("positive", v -> v == null || v > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        predicatesList("range", v -> v != null && v >= from && v <= to);
        return this;
    }
}
