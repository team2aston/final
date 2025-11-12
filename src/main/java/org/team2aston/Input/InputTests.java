package org.team2aston.Input;

import org.team2aston.collection.CustomList;

public class InputTests {

    public static void main(String[] args) {
        InputManager manager = new InputManager();

        CustomList<?> list = manager.fillEmployeeList();

        System.out.println(list);
    }
}