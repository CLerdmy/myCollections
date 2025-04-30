package dev.clerdmy.collections.lists;

import dev.clerdmy.collections.List;
import dev.clerdmy.collections.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SinglyLinkedList<T> implements List<T> {

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public SinglyLinkedList(T[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        this.head = new Node<>(array[0]);
        Node<T> current = head;
        size = 1;
        for (int i = 1; i < array.length; i++) {
            current.setNext(new Node<>(array[i]));
            current = current.getNext();
            size++;
        }
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
        this.head = null;
        this.size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public boolean set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setData(value);
        return true;
    }

    @Override
    public boolean pushBack(T item) {
        if (size == 0) {
            head = new Node<>(item);
            size++;
            return true;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        Node<T> newNode = new Node<>(item);
        current.setNext(newNode);
        size++;
        return true;
    }

    @Override
    public boolean pushFront(T item) {
        if (size == 0) {
            head = new Node<>(item);
            size++;
            return true;
        }
        Node<T> newNode = new Node<>(item);
        newNode.setNext(head);
        head = newNode;
        size++;
        return true;
    }

    @Override
    public boolean exists(T item) {
        Node<T> current = head;
        while (current != null) {
            if (item == null) {
                if (current.getData() == null) {
                    return true;
                }
            } else if (current.getData() != null && current.getData().equals(item)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public T popBack() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            T value = head.getData();
            head = null;
            size = 0;
            return value;
        }
        Node<T> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        T value = current.getNext().getData();
        current.setNext(null);
        size--;
        return value;
    }

    @Override
    public T popFront() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T value = head.getData();
        head = head.getNext();
        size--;
        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Node<T> current = head;
        while (current != null) {
            action.accept(current.getData());
            current = current.getNext();
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return java.util.Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED);
    }

    private class SinglyLinkedListIterator implements Iterator<T> {

        private Node<T> current = head;

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
            current = current.getNext();
            return data;
        }

    }

}