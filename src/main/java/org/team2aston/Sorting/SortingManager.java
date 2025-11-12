package org.team2aston.Sorting;

import java.util.Comparator;
import java.util.List;


public class SortingManager {
    private ClassicSortStrategy strategy;

    public SortingManager(ClassicSortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(List list, Comparator comparator) {
        strategy.sort(list, comparator);
    }

    public void setStrategy(ClassicSortStrategy strategy) {
        this.strategy = strategy;
    }
}
