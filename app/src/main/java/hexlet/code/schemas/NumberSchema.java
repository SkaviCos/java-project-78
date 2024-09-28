package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        predicatesList(Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        predicatesList(v -> v == null || v > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        predicatesList(v -> v != null && v >= from && v <= to);
        return this;
    }
}
