package org.team2aston.Sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class CustomSort <T> implements SpecialSortStrategy<T> {         //декоратор, чтобы осуществить сортировку по условиям доп.задания 1
    private ClassicSortStrategy<T> classicSortStrategy;

    public CustomSort(ClassicSortStrategy<T> classicSortStrategy) {
        this.classicSortStrategy = classicSortStrategy;
    }

    @Override
    public void sort(List<T> list, Comparator<? super T> comparator, ToIntFunction<T> nums) {
        if (list == null || list.size() <= 1) {
            return;
        }
        List<T> elements = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            if (nums.applyAsInt(item) % 2 == 0) {
                elements.add(item);
                indices.add(i);
            }
        }
        classicSortStrategy.sort(elements, comparator);
        for (int i = 0; i < elements.size(); i++) {
            int index = indices.get(i);
            T sortedElement = elements.get(i);
            list.set(index, sortedElement);
        }
    }
}
