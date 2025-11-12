package org.team2aston.Input;


import java.util.Arrays;

public enum DataInputOption {
    FILE(1), RANDOM(2), CONSOLE(3), EXIT(0);

    private final int value;

    DataInputOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DataInputOption fromValue(int value) {
        return Arrays.stream(values())
                .filter(option -> option.value == value)
                .findFirst()
                .orElse(EXIT);
    }
}
