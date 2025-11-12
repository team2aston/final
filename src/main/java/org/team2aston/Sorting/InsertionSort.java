package org.team2aston.Sorting;

import java.util.Comparator;
import java.util.List;

public class InsertionSort<T> implements ClassicSortStrategy<T> {
    @Override
    public void sort(List<T> list, Comparator<? super T> comparator) {
        int i, j;
        T key;
        for (i = 1; i < list.size(); i++) {
            key = list.get(i);
            j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}
