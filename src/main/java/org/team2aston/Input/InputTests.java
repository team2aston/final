package org.team2aston.Input;

import java.util.List;

public class InputTests {

    public static void main(String[] args) {
        InputManager manager = new InputManager();

        List<?> list = manager.fillEmployeeList();

        System.out.println(list);
    }
}