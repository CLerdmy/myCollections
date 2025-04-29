package dev.clerdmy.collections.arrays;

import dev.clerdmy.collections.Array;
import dev.clerdmy.collections.IndexedNode;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SinglyLinkedArray<T> implements Array<T> {

    private IndexedNode<T> head;
    private int size;

    public SinglyLinkedArray() {
        this.head = null;
        this.size = 0;
    }

    public SinglyLinkedArray(T[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        IndexedNode<T> current = new IndexedNode<>(array[0], null, 0);
        this.head = current;
        this.size = array.length;
        for (int i = 1; i < array.length; i++) {
            IndexedNode<T> newNode = new IndexedNode<>(array[i], null, i);
            current.setNext(newNode);
            current = newNode;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private IndexedNode<T> getNodeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        IndexedNode<T> current = head;
        for (int i = 0; i < index; i++) {
            current = (IndexedNode<T>) current.getNext();
        }
        return current;
    }

    public T get(int index) {
        IndexedNode<T> current = getNodeByIndex(index);
        return current.getData();
    }

    public T get(T value) {
        IndexedNode<T> current = head;
        for (int i = 0; i < size(); i++) {
            current = (IndexedNode<T>) current.getNext();
            if (current.getData().equals(value)) {
                return current.getData();
            }
        }
        return null;
    }

    public boolean add(T value) {
        IndexedNode<T> newNode = new IndexedNode<>(value, null, size());
        if (head == null) {
            head = newNode;
        } else {
            IndexedNode<T> current = head;
            while (current.getNext() != null) {
                current = (IndexedNode<T>) current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        return true;
    }

    public boolean set(int index, T value) {
        IndexedNode<T> current = getNodeByIndex(index);
        current.setData(value);
        return true;
    }

    public boolean insert(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        IndexedNode<T> newNode = new IndexedNode<>(value, null, index);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            IndexedNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = (IndexedNode<T>) current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        increaseIndexesAfter(index);
        size++;
        return true;
    }

    private void increaseIndexesAfter(int index) {
        IndexedNode<T> current = head;
        while(current != null){
            if(current.getIndex() > index){
                current.setIndex(current.getIndex() + 1);
            }
            current = (IndexedNode<T>) current.getNext();
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T value;
        if (index == 0) {
            value = head.getData();
            head = (IndexedNode<T>) head.getNext();
        } else {
            IndexedNode<T> current = getNodeByIndex(index - 1);
            value = current.getNext().getData();
            current.setNext(current.getNext().getNext());
        }
        decreaseIndexesAfter(index);
        size--;
        return value;
    }

    private void decreaseIndexesAfter(int index) {
        IndexedNode<T> current = head;
        while(current != null){
            if(current.getIndex() > index){
                current.setIndex(current.getIndex() - 1);
            }
            current = (IndexedNode<T>) current.getNext();
        }
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T value;
        if (size() == 1) {
            value = head.getData();
            head = null;
        } else {
            IndexedNode<T> current = getNodeByIndex(size() - 2);
            value = current.getNext().getData();
            current.setNext(null);
        }
        size--;
        return value;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Iterator<T> iter = this.iterator();
        while (iter.hasNext()) {
            stringBuilder.append(iter.next());
            if (iter.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SinglyLinkedArray<?> that = (SinglyLinkedArray<?>) object;
        if (size != that.size) return false;
        Iterator<?> thisIterator = this.iterator();
        Iterator<?> thatIterator = that.iterator();
        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            if (!Objects.equals(thisIterator.next(), thatIterator.next())) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        for (T item : this) {
            result = 31 * result + Objects.hashCode(item);
        }
        return result;

    }

    @Override
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T item : this) {
            action.accept(item);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return java.util.Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }

    private class SinglyLinkedListIterator implements Iterator<T> {

        private IndexedNode<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.getData();
            current = (IndexedNode<T>) current.getNext();
            return data;
        }
    }

}