package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        predicatesList().add(v -> v != null);
        return this;
    }

    public NumberSchema positive() {
        predicatesList().add(v -> v == null || v > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        predicatesList().add(v -> v >= from && v <= to);
        return this;
    }
}
