package org.team2aston;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

public class FileManager {

    /*
    Записывает коллекцию сотрудников в файл в режиме ДОБАВЛЕНИЯ
     */
    public static void appendToFile(List<Employee> employees, String filename, String description) {
        Path filePath = Paths.get(filename);

        try {
            // Проверяем есть ли родительская директория
            Path parentDir = filePath.getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }


            // Открываем файл в режиме CREATE (создать если нет) и APPEND (добавить в конец)
            try (BufferedWriter writer = Files.newBufferedWriter(filePath,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

                // Добавляем разделитель и заголовок для новой записи
                writer.write("\n" + "=".repeat(60) + "\n");
                writer.write("RECORD ADDED: " + LocalDateTime.now() + "\n");
                writer.write("DESCRIPTION: " + description + "\n");
                writer.write("=".repeat(60) + "\n");

                // Записываем каждого сотрудника
                for (Employee employee : employees) {
                    writer.write(formatEmployee(employee) + "\n");
                }

                writer.write("TOTAL RECORDS: " + employees.size() + "\n");

            }

            System.out.println("Successfully appended " + employees.size() + " records to: " + filename);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /*
    Форматирует сотрудника для записи в файл
     */
    private static String formatEmployee(Employee employee) {
        return String.format("Employee | Name: %-15s | Department: %-12s | Salary: %8.2f",
                employee.getName(),
                employee.getDepartment().name(),
                employee.getSalary());
    }

    /*
    Записывает один элемент (для результатов поиска) в режиме добавления
     */
    public static void appendSearchResultToFile(Employee employee, String filename, String searchCriteria) {
        Path filePath = Paths.get(filename);

        try {
            Files.createDirectories(filePath.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(filePath,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

                writer.write("\n--- SEARCH RESULT ---\n");
                writer.write("Time: " + LocalDateTime.now() + "\n");
                writer.write("Search: " + searchCriteria + "\n");
                writer.write("Found: " + formatEmployee(employee) + "\n");

            }

        } catch (IOException e) {
            System.err.println("Error writing search result: " + e.getMessage());
        }
    }
}
