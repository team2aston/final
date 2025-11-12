package org.team2aston;

import org.team2aston.Input.InputManager;
import org.team2aston.Input.Validator;

import java.util.List;

public class Application {
    private final InputManager inputManager = new InputManager();
    private List<Employee> employees;

    /* классы для реализации функций
    private final SortingManager sortingManager;
    private final SearchManager searchManager;
    private final FileManager fileManager;
     */

    public void run() {
        System.out.println("Starting sorting application...");

        while (true) {
            try {
                MenuOption option = showMenuAndGetChoice();

                if (option == MenuOption.EXIT) {
                    System.out.println("Exiting...");
                    break;
                }

                //вызов выполнения наших операций из вывода консоли
                executeOption(option);

            } catch (Exception e) {
                System.out.println("Error!: " + e.getMessage());
            }
        }
    }

    /*
    здесь взависимости от выбранной опции выполняется выбранная операция
     */
    private void executeOption(MenuOption option) {
        System.out.println("\n=== SELECTED OPTION ===");
        System.out.println(option);

        switch (option) {
            case INPUT_DATA -> {
                //ввод данных из фала
                employees = inputManager.fillEmployeeList();
            }
            case SORT -> {
                //сортировка
                System.out.println("Status: STUB - functionality in development");
                System.out.println("============================\n");
            }
            case SEARCH -> {
                //поиск
                System.out.println("Status: STUB - functionality in development");
                System.out.println("============================\n");
            }
            case SAVE_TO_FILE -> {
                //сохранение в файл
                System.out.println("Status: STUB - functionality in development");
                System.out.println("============================\n");
            }
        }
    }

    private MenuOption showMenuAndGetChoice() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Input data");
        System.out.println("2. Sorting");
        System.out.println("3. Binary search");
        System.out.println("4. Save to file");
        System.out.println("0. Exit");

        int choice = Validator.getValidatedInput(0, 4);
        return MenuOption.fromValue(choice);
    }
}
