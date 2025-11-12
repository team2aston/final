package org.team2aston.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.team2aston.Employee;
import org.team2aston.collection.CustomArrayList;
import org.team2aston.collection.CustomList;

public class InputManager {
    private static final int MAX_NUMBER_OF_EMPLOYEES = 10000;
    private static final int MIN_NAME_SIZE = 3;
    private static final int MAX_NAME_SIZE = 15;
    private static final int MAX_SALARY_SIZE = 1000000;
    RandomEmployeeGenerator reg = new RandomEmployeeGenerator();

    public CustomList<Employee> fillEmployeeList() {
        while (true) {
            try{
                CustomList<Employee> employees;
                DataInputOption option = showMenuAndGetChoice();

                switch (option) {
                    case FILE -> employees = fillFromFile();
                    case RANDOM -> employees = fillRandom();
                    case CONSOLE -> employees = fillFromConsole();
                    default -> employees = null;
                }

                return employees;
            } catch (Exception e) {
                System.out.println("Error!: " + e.getMessage());
            }

        }
    }

    /*
    Формат файла - три строки - один employee. В первой имя, во второй департамент, в третьей зарплата
     */
    private CustomList<Employee> fillFromFile() throws IOException {
        System.out.println("\nFile path");
        try (Scanner consoleReader = new Scanner(System.in)) {
            String path = consoleReader.nextLine();

            CustomList<Employee> employees;
            try (Scanner fileReader = new Scanner(Files.newInputStream(Path.of(path)))) {
                employees = new CustomArrayList<>();
                while (fileReader.hasNext()) {
                    Employee.Builder employee = new Employee.Builder();

                    employee.name(Validator.validateName(fileReader.nextLine(), MIN_NAME_SIZE, MAX_NAME_SIZE));
                    employee.department(Validator.validateDepartment(fileReader.nextLine()));
                    employee.salary(Validator.validateSalary(fileReader.nextLine(), MAX_SALARY_SIZE));

                    employees.add(employee.build());
                }
            }
            return employees;
        }
    }

    private CustomList<Employee> fillFromConsole() throws IOException {
        System.out.print("\nNumber of employees");
        int size = Validator.getValidatedInput(0, MAX_NUMBER_OF_EMPLOYEES);

        CustomList<Employee> employees = new CustomArrayList<>();
        try (Scanner consoleReader = new Scanner(System.in)) {
            for (int i = 0; i < size; i++) {
                Employee.Builder employee = new Employee.Builder();

                System.out.println("\nName of a new employee");
                employee.name(Validator.validateName(consoleReader.nextLine(), MIN_NAME_SIZE, MAX_SALARY_SIZE));

                System.out.println("\nDepartment of a new employee");
                employee.department(Validator.validateDepartment(consoleReader.nextLine()));

                System.out.println("\nSalary of a new employee");
                employee.salary(Validator.validateSalary(consoleReader.nextLine(), MAX_SALARY_SIZE));

                employees.add(employee.build());
            }
        }
        return employees;
    }

    private CustomList<Employee> fillRandom() {
        System.out.print("\nNumber of employees");
        int size = Validator.getValidatedInput(0, MAX_NUMBER_OF_EMPLOYEES);

        CustomList<Employee> employees = new CustomArrayList<>();
        for(int i = 0; i < size; i++) {
            employees.add(reg.generateRandom());
        }
        return employees;
    }


    private DataInputOption showMenuAndGetChoice() {
        System.out.println("\n=== Source of data ===");
        System.out.println("1. File");
        System.out.println("2. Generate random");
        System.out.println("3. Console Input");
        System.out.println("0. Back");

        int choice = Validator.getValidatedInput(0, 3);
        return DataInputOption.fromValue(choice);
    }
}
