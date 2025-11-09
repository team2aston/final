package org.team2aston;

public enum Department {
    IT("IT Department"),
    HR("Human Resources"),
    FINANCE("Finance Department"),
    MARKETING("Marketing Department"),
    SALES("Sales Department"),
    OPERATIONS("Operations Department"),
    LEGAL("Legal Department"),
    RESEARCH("Research and Development");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
