package org.team2aston.Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public interface SpecialSortStrategy<T> {               //интерфейс для сортировки по условиям доп. задания 1
    void sort(List<T> list, Comparator<? super T> comparator, ToIntFunction<T> nums);
}
