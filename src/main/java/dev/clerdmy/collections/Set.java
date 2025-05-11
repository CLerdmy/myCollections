package dev.clerdmy.collections;

public interface Set<T> extends Collection<T> {

    boolean contains(T item);
    boolean add(T item);
    boolean remove(T item);
    void clear();

}
