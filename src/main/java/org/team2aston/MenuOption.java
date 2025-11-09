package org.team2aston;

import java.util.Arrays;

enum MenuOption {
    INPUT_DATA(1), SORT(2), SEARCH(3), SAVE_TO_FILE(4), EXIT(0);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuOption fromValue(int value) {
        return Arrays.stream(values())
                .filter(option -> option.value == value)
                .findFirst()
                .orElse(EXIT);
    }
}
