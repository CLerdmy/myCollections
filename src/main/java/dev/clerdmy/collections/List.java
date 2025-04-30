package dev.clerdmy.collections;

public interface List<T> extends Collection<T> {

    T get(int index);
    boolean set(int index, T item);
    boolean pushBack(T item);
    boolean pushFront(T item);
    boolean exists(T item);
    T popBack();
    T popFront();

}