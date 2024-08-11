package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    public MapSchema required() {
        getPredicates().add(v -> v instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(int size) {
        getPredicates().add(v -> ((Map<?, ?>) v).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        getPredicates().add(v -> formValidation(schemas, (Map<?, ?>) v));
        return this;
    }

    private boolean formValidation(Map<String, BaseSchema> schemas, Map<?, ?> map) {
        for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
            String key = entry.getKey();
            if (!map.containsKey(key) || !entry.getValue().isValid(map.get(key))) {
                return false;
            }
        }
        return true;
    }
}
