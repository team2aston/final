package org.team2aston;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    /* классы для реализации функций
    private final DataManager dataManager;
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
                //ввод данных из фала
                System.out.println("Status: STUB - functionality in development");
                System.out.println("============================\n");
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
