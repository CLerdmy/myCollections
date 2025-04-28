package dev.clerdmy.collections;

public interface Collection<T> extends Iterable<T> {

    int size();
    boolean isEmpty();
    void clear();

}