package org.team2aston.Search;
/*
Класс подсчета вхождения объекта
 */

import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SearchManager<T> {

    private final Lock collectionLock = new ReentrantLock(true);
    private final int THREAD_COUNT = 8;

    public int count(Iterator<T> collection, T item) {
        return count(collection, item, (o1, o2) -> o1.equals(o2) ? 0 : -1);
    }


    public int count(Iterator<T> collection, T item, Comparator<T> comparator) {
        LongAdder entranceCounter = new LongAdder();
        try (ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT)) {
            threadPool.execute(() -> countUntilEnd(collection, item, comparator, entranceCounter));
        }
        return entranceCounter.intValue();
    }

    private void countUntilEnd(Iterator<T> collection, T item, Comparator<T> comparator, LongAdder entranceCounter) {
        T nextItem;
        while(true) {
            collectionLock.lock();
            if(!collection.hasNext()) {
                collectionLock.unlock();
                break;
            }
            nextItem = collection.next();
            collectionLock.unlock();
            if(comparator.compare(item, nextItem) == 0) {
                entranceCounter.increment();
            }
        }
    }
}