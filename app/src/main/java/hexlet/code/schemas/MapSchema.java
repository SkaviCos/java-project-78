package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map> {
    public MapSchema required() {
        predicatesList().add(Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        predicatesList().add(v -> ((Map<?, ?>) v).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        predicatesList().add(v -> formValidation(schemas, (Map<?, ?>) v));
        return this;
    }

    private boolean formValidation(Map<String, BaseSchema<String>> schemas, Map<?, ?> map) {
        for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
            String key = entry.getKey();
            if (!map.containsKey(key) || !entry.getValue().isValid((String) map.get(key))) {
                return false;
            }
        }
        return true;
    }
}
