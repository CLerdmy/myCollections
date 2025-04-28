package dev.clerdmy.collections;

public interface Array<T> extends Collection<T> {

    T get(int index);
    boolean add(T item);
    boolean set(int index, T item);
    boolean insert(int index, T item);
    T remove(int index);
    T pop();

}