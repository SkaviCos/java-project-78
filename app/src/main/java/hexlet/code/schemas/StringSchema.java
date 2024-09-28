package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        super.predicatesList(v -> v != null && !v.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        super.predicatesList(v -> v.length() >= length);
//        super.predicatesList(v -> v == null || v.length() >= length);
        /**
         * при добавлении условия выше падает тест в пайпе hexlet-check
         *  ValidatorTest > testStringValidator() FAILED
         * app-1  |     org.opentest4j.AssertionFailedError:
         * app-1  |     Expecting value to be true but was false
         * app-1  |         at java.base@21.0.3/jdk.internal.reflect.DirectConstructorHandleAccessor.newInstance(DirectConstructorHandleAccessor.java:62)
         * app-1  |         at java.base@21.0.3/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:502)
         * app-1  |         at app//hexlet.project.ValidatorTest.testStringValidator(ValidatorTest.java:41)
         * app-1  |
         */
        return this;
    }

    public StringSchema contains(String str) {
        super.predicatesList(v -> v.contains(str));
//        super.predicatesList(v -> v == null || v.contains(str));
        return this;
    }

}
