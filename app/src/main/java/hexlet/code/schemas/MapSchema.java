package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema sizeof(int size) {
        addValidation("sizeof", v -> v == null || v.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        addValidation("shape", v -> v == null || createValidation(schemas, v));
        return this;
    }

    private boolean createValidation(Map<String, BaseSchema<String>> schemas, Map<?, ?> map) {
        for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
            String key = entry.getKey();
            if (!map.containsKey(key) || !entry.getValue().isValid((String) map.get(key))) {
                return false;
            }
        }
        return true;
    }
}
