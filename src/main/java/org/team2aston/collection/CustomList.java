package org.team2aston.collection;

/**
 * Минимальный контракт списка для приложения.
 * Избегает прямого использования java.util.List,
 * сохраняя простой итератор.
 */
public interface CustomList<T> extends Iterable<T> {

    void add(T element);

    T get(int index);

    T set(int index, T element);

    T remove(int index);

    int size();

    boolean isEmpty();

    void clear();
}

