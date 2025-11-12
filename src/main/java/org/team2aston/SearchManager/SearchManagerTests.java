package org.team2aston.SearchManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class SearchManagerTests {

    public static void main(String[] args) {
        Collection<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(1);
        arr.add(2);
        arr.add(2);
        arr.add(2);
        arr.add(14);
        arr.add(1);
        arr.add(15);
        arr.add(1);
        arr.add(2);
        arr.add(16);
        arr.add(2);
        arr.add(2);

        SearchManager<Integer> manager = new SearchManager<>();

        System.out.println(manager.count(arr.iterator(), 1));
        System.out.println(manager.count(arr.iterator(), 2));
        System.out.println(manager.count(arr.iterator(), 2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2 + 1);
            }
        }));
    }
}
