package dev.clerdmy.collections;

public interface Graph<T> {

    boolean addVertex(T vertex);
    boolean removeVertex(T vertex);
    boolean addEdge(T from, T to);
    boolean removeEdge(T from, T to);
    boolean hasEdge(T from, T to);

}