package org.team2aston.Input;

import org.team2aston.Department;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {
    public static int getValidatedInput(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("\nInput: ");
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

    public static String validateName(String name, int min, int max) {
        if (name.length() < min || name.length() > max)
            throw new IllegalArgumentException("Name length must be from " + min + " to " + max);

        if(!name.chars().allMatch(Character::isLetter))
            throw new IllegalArgumentException("Name length must contain only letters!");

        return name;
    }

    public static Department validateDepartment(String department) {
        try {
            return Department.valueOf(department);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Such department does not exist!", e);
        }
    }

    public static double validateSalary(String salary, int max) {
        try {
            double num = Double.parseDouble(salary);
            if (num > max)
                throw new IllegalArgumentException("Salary must be lower than " + max);
            return num;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Enter valid decimal number!", e);
        }
    }
}
