package org.team2aston.Sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort<T> implements ClassicSortStrategy<T> {
    @Override
    public void sort(List<T> list, Comparator<? super T> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }
        int mid = list.size() / 2;
        List<T> list1 = new ArrayList<>(list.subList(0, mid));
        List<T> list2 = new ArrayList<>(list.subList(mid, list.size()));

        sort(list1, comparator);
        sort(list2, comparator);

        merge(list, list1, list2, comparator);
    }


    private void merge(List<T> result, List<T> list1, List<T> list2, Comparator<? super T> comparator) {
        int indexResult = 0;
        int indexList1 = 0;
        int indexList2 = 0;

        while (indexList1 < list1.size() && indexList2 < list2.size()) {
            if (comparator.compare(list1.get(indexList1), list2.get(indexList2)) < 0) {
                result.set(indexResult, list1.get(indexList1));
                indexList1++;
            } else {
                result.set(indexResult, list2.get(indexList2));
                indexList2++;
            }
            indexResult++;
        }
        while (indexList1 < list1.size()) {
            result.set(indexResult, list1.get(indexList1));
            indexList1++;
            indexResult++;
        }
        while (indexList2 < list2.size()) {
            result.set(indexResult, list2.get(indexList2));
            indexList2++;
            indexResult++;
        }
    }
}
