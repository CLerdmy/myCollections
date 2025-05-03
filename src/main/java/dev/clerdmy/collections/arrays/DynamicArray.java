package dev.clerdmy.collections.arrays;

import dev.clerdmy.collections.Array;
import dev.clerdmy.util.Constants;

import java.util.*;
import java.util.function.Consumer;

public class DynamicArray<T> implements Array<T> {

    private T[] data;
    private int size;
    private int capacity;

    public DynamicArray() {
        this(Constants.DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    private DynamicArray(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public DynamicArray(T[] array) {
        this.data = array;
        this.size = array.length;
        this.capacity = Constants.DEFAULT_CAPACITY;
        checkCapacity(size);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int capacity() {
        return capacity;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    public boolean add(T value) {
        checkCapacity(size + 1);
        data[size++] = value;
        return true;
    }

    public boolean set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        data[index] = value;
        return true;
    }

    public boolean insert(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        checkCapacity(size + 1);
        shiftElementsToRight(index);
        data[index] = value;
        size++;
        return true;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        shiftElementsToLeft(index);
        T value = data[--size];
        data[size] = null;
        return value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T value = data[--size];
        data[size] = null;
        return value;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i < size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        DynamicArray<?> other = (DynamicArray<?>) object;
        if (size != other.size) {
            return false;
        }
        return Arrays.equals(Arrays.copyOf(data, size), Arrays.copyOf(other.data, other.size));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Objects.hashCode(size);
        result = 31 * result + Arrays.hashCode(Arrays.copyOf(data, size));
        return result;
    }

    private void checkCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            int newCapacity = Math.max(minCapacity, capacity * 2);
            data = copyWithNewCapacity(data, newCapacity);
            capacity = newCapacity;
        }
    }

    @SuppressWarnings("unchecked")
    private T[] copyWithNewCapacity(T[] copyData, int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(copyData, 0, newData, 0, copyData.length);
        return newData;
    }

    private void shiftElementsToRight(int index) {
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
    }

    private void shiftElementsToLeft(int index) {
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DynamicArrayIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < size; i++) {
            action.accept(data[i]);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliterator(data, 0, size, Spliterator.ORDERED | Spliterator.NONNULL | Spliterator.SIZED);
    }

    private class DynamicArrayIterator implements Iterator<T> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}