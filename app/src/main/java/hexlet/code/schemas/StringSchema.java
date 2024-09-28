package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        super.predicatesList(v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        super.predicatesList(v -> v.length() >= length);
//        super.predicatesList(v -> v == null || v.length() >= length);
//        при добавлении условия выше падает тест в пайпе hexlet-check
//         ValidatorTest > testStringValidator() FAILED
//        app-1  |     org.opentest4j.AssertionFailedError:
//        app-1  |     Expecting value to be true but was false

        return this;
    }

    public StringSchema contains(String str) {
        super.predicatesList(v -> v.contains(str));
//        super.predicatesList(v -> v == null || v.contains(str));
        return this;
    }

}
