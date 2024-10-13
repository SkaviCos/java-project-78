package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class BaseSchema<T> {

    private final Map<String, Predicate<T>> predicates = new HashMap<>();
    /**
     * Добавляет правило валидации, которое требует, чтобы значение было не null.
     *
     * @return для построения цепочки вызовов
     */
    public BaseSchema<T> required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    protected final void addValidation(String validationType, Predicate<T> validationPredicate) {
        this.predicates.put(validationType, validationPredicate);
    }

    public final boolean isValid(T t) {
        return predicates.values().stream().allMatch(v -> v.test(t));
    }


}
