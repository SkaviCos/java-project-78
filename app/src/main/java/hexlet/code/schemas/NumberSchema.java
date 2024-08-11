package hexlet.code.schemas;


public class NumberSchema extends BaseSchema<Object> {
    public NumberSchema required() {
        getPredicates().add(v -> v instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        getPredicates().add(v -> v == null || v instanceof Integer && (Integer) v > 0);
        return this;
    }

    public NumberSchema range(int from, int to) {
        getPredicates().add(v -> (Integer) v >= from && (Integer) v <= to);
        return this;
    }
}
