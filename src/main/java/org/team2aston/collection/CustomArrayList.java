package org.team2aston.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Облегченная реализация динамического массива,
 * используемая в качестве замены java.util.ArrayList.
 */
public class CustomArrayList<T> implements CustomList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    private Object[] elements;
    private int size;

    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        elements = new Object[initialCapacity];
    }

    @Override
    public void add(T element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T previous = (T) elements[index];
        elements[index] = element;
        return previous;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) elements[index];
        int elementsToMove = size - index - 1;
        if (elementsToMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, elementsToMove);
        }
        elements[--size] = null;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= elements.length) {
            return;
        }

        int newCapacity = elements.length * GROWTH_FACTOR;
        while (newCapacity < requiredCapacity) {
            newCapacity *= GROWTH_FACTOR;
        }

        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append(Objects.toString(elements[i]));
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }
}

