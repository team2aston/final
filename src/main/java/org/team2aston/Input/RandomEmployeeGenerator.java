package org.team2aston.Input;

import org.team2aston.Department;
import org.team2aston.Employee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomEmployeeGenerator {
    private static final int MAX_SALARY = 1000000;
    private static final String NAMES = "Oliver, Amelia, George, Charlotte, Noah, Ava, Leo, Isla, Harry, Sophia, Arthur, Isabella, Muhammad, Mia, Freddie, Luna, Jack, Evelyn, Charlie, Harper, Oscar, Scarlett, Theo, Grace, Archie, Chloe, Leo, Lily, Alfie, Eleanor, Theodore, Riley, Alexander, Freya, Joshua, Ella, William, Willow, Edward, Violet, Jacob, Stella, James, Hazel, Stanley, Zoe, Henry, Ivy, David, Amelia";
    private final List<String> names = new ArrayList<>();

    public RandomEmployeeGenerator() {
        names.addAll(Arrays.asList(NAMES.split(", ")));
    }

    public Employee generateRandom() {
        Employee.Builder employee = new Employee.Builder();
        Random rnd = new Random(LocalDateTime.now().getNano());
        employee.name(names.get(rnd.nextInt(0, names.size())));
        employee.department(Department.values()[rnd.nextInt(0, Department.values().length)]);
        employee.salary(rnd.nextDouble(0, MAX_SALARY));
        return employee.build();
    }
}
