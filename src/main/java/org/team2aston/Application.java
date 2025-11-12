package org.team2aston;

import java.util.List;
import java.util.Scanner;

import org.team2aston.Input.InputManager;
import org.team2aston.Input.Validator;

public class Application {
    private final InputManager inputManager = new InputManager();
    private List<Employee> employees; //список для загрузки из файла, сортировки и сохранения в файл

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
                //ввод данных из файла
                //вызов обработчика ввода из файла
                employees = inputManager.fillEmployeeList();
                System.out.println("============================\n");
            }
            case SORT -> {
                //сортировка
                //вызов обработчика сотрировки
                System.out.println("Status: STUB - functionality in development");
                System.out.println("============================\n");
            }
            case SEARCH -> {
                //поиск
                //вызов обработчика поиска
                System.out.println("Status: STUB - functionality in development");
                System.out.println("============================\n");
            }
            case SAVE_TO_FILE -> {
                //сохранение в файл
                // ДОП ЗАДАНИЕ 2
                handleSaveToFile();
            }
        }
    }

    /*
    Обработчик сохранения в файл
     */
    private void handleSaveToFile() {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No data available. Please input data first.");
            System.out.println("============================\n");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter filename (default: employees.txt): ");
        String filename = scanner.nextLine().trim();

        //указан путь к файлу?
        if (filename.isEmpty()) {
            filename = "employees.txt";
        }

        //добавление описания к данным
        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            description = "Employee data";
        }

        // ВЫЗОВ МЕТОДА ДОБАВЛЕНИЯ В ФАЙЛ
        FileManager.appendToFile(employees, filename, description);
        System.out.println("============================\n");
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
