package org.team2aston;

/*
Сортируемый класс
 */
public class Employee implements Comparable<Employee> {
    private final String name;
    private final Department department;
    private final double salary;

    private Employee(Builder builder) {
        this.name = builder.name;
        this.department = builder.department;
        this.salary = builder.salary;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public static class Builder {
        private String name;
        private Department department;
        private double salary;

        public Builder name(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            this.name = name.trim();
            return this;
        }

        public Builder department(Department department) {
            if (department == null) {
                throw new IllegalArgumentException("Department cannot be null");
            }
            this.department = department;
            return this;
        }

        public Builder salary(double salary) {
            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be negative");
            }
            this.salary = salary;
            return this;
        }

        public Employee build() {
            if (name == null) {
                throw new IllegalStateException("Name must be set");
            }
            if (department == null) {
                throw new IllegalStateException("Department must be set");
            }
            return new Employee(this);
        }
    }

    @Override
    public int compareTo(Employee other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', department=%s, salary=%.2f}",
                name, department.getDisplayName(), salary);
    }
}
