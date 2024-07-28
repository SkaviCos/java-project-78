package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringSchema {
    public boolean required = false;
    public boolean isNotAllowed;
    public int minLength = 0;
    public List<String> listStr = new ArrayList<>();

    public boolean isNotAllowed() {
        return this.isNotAllowed;
    }

    public void setNotAllowed(boolean notAllowed) {
        this.isNotAllowed = notAllowed;
    }

    public int minStringLength() {
        return minLength;
    }

    public void setMinStringLength(int length) {
        this.minLength = length;
    }

    public boolean isValid(String str) {
        if (isNotAllowed()) {
            if (Objects.equals(str, null) || Objects.equals(str, "")) {
                return false;
            }
        }
        for (String string : listStr) {
            if (!str.contains(string)) {
                return false;
            }
        }
        return str.length() <= minStringLength();
    }

    public StringSchema contains(String str) {
        if (!this.listStr.contains(str)) {
            this.listStr.add(str);
        }
        return this;
    }

    public StringSchema required() {
        setNotAllowed(true);
        return this;
    }

    public StringSchema minLength(int length) {
        if (length > minLength) {
            setMinStringLength(length);
        }
        return this;
    }

}
