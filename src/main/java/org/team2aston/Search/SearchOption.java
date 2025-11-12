package org.team2aston.Search;


import java.util.Arrays;

public enum SearchOption {
    EXIT(0), NAME(1), DEPARTMENT(2), SALARY(3);

    private final int value;

    SearchOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SearchOption fromValue(int value) {
        return Arrays.stream(values())
                .filter(option -> option.value == value)
                .findFirst()
                .orElse(EXIT);
    }
}
