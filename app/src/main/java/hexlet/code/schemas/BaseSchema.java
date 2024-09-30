package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {

    private final Map<String, Predicate<T>> predicates = new HashMap<>();

    protected final void predicatesList(String validationType, Predicate<T> validationPredicate) {
        this.predicates.put(validationType, validationPredicate);
    }

    public final boolean isValid(T t) {
        return predicates.values().stream().allMatch(v -> v.test(t));
    }

}
