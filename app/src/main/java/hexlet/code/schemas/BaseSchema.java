package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {

    private final List<Predicate<T>> predicates = new ArrayList<>();

    protected final void predicatesList(Predicate<T> validationPredicate) {
        this.predicates.add(validationPredicate);
    }

    public final boolean isValid(T t) {
        return predicates.stream().allMatch(v -> v.test(t));
    }

}
