package org.team2aston.Sorting;

import java.util.Comparator;
import java.util.List;

public interface ClassicSortStrategy<T> {                         //интерфейс для классической сортировки
    void sort(List<T> list, Comparator<? super T> comparator);
}
