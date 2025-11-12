package org.team2aston;


import org.team2aston.Input.Validator;
import org.team2aston.Search.SearchManager;
import org.team2aston.Search.SearchOption;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {
    private List<Employee> employees; //список для загрузки из файла, сортировки и сохранения в файл
    private SearchManager<Employee> searchManager= new SearchManager<>();

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
                System.out.println("Error: " + e.getMessage());
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
                System.out.println("Status: STUB - functionality in development");
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
                handleSearchElements();
            }
            case SAVE_TO_FILE -> {
                //сохранение в файл
                // ДОП ЗАДАНИЕ 2
                handleSaveToFile();
            }
        }
    }

    /*
    Обработчик подсчета элементов по фильтру
    */
    private void handleSearchElements() {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No data available. Please input data first.");
            System.out.println("============================\n");
            return;
        }

        System.out.println("\n=== Searching parameters ===");
        System.out.println("1. Name");
        System.out.println("2. Department");
        System.out.println("3. Salary");
        System.out.println("0. Exit");

        Employee.Builder employee = new Employee.Builder();
        Scanner scanner = new Scanner(System.in);
        int count = 0;

        switch (SearchOption.fromValue(getValidatedInput(0, 3))) {
            case NAME:
                System.out.println("Input name:");
                count = searchManager.count(
                        employees.iterator(),
                        employee.name(scanner.nextLine()).build(),
                        Employee.Comparators.byName);
                break;
            case DEPARTMENT:
                System.out.println("Input department:");
                String department = scanner.nextLine();
                count = searchManager.count(
                        employees.iterator(),
                        employee.department(Validator.validateDepartment(department)).build(),
                        Employee.Comparators.byDepartment);
                break;
            case SALARY:
                System.out.println("Input salary:");
                String salary = scanner.nextLine();
                count = searchManager.count(
                        employees.iterator(),
                        employee.salary(Validator.validateSalary(salary, Integer.MAX_VALUE)).build(),
                        Employee.Comparators.byDepartment);
                break;
        }

        System.out.println("Found " + count + " entries with given filter");
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

        int choice = getValidatedInput(0, 4);
        return MenuOption.fromValue(choice);
    }

    private int getValidatedInput(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Select option: ");
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Please enter number from " + min + " to " + max);
            } catch (InputMismatchException e) {
                System.out.println("Error: please enter valid number!");
                scanner.nextLine();
            }
        }
    }
}
