package dev.clerdmy.collections.graph;

import dev.clerdmy.collections.Graph;
import dev.clerdmy.collections.arrays.DynamicArray;

public class AdjacencyMatrixUndirectedGraph<T> implements Graph<T> {

    //vertexIndexMap <T, Integer>
    private DynamicArray<T> vertices;
    private DynamicArray<DynamicArray<Boolean>> adjacencyMatrix;

    public AdjacencyMatrixUndirectedGraph() {
        this.vertices = new DynamicArray<>();
        this.adjacencyMatrix = new DynamicArray<>();
    }

    private int indexOf(T vertex) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    public DynamicArray<T> getVertices() {
        return vertices;
    }

    public DynamicArray<T> getAdjacentVertices(T vertex) {
        int index = indexOf(vertex);
        DynamicArray<T> adjacentVertices = new DynamicArray<>();
        if (index == -1) return adjacentVertices;
        for (int i = 0; i < vertices.size(); i++) {
            if (adjacencyMatrix.get(index).get(i)) {
                adjacentVertices.add(vertices.get(i));
            }
        }
        return adjacentVertices;
    }

    @Override
    public boolean addVertex(T vertex) {
        if (indexOf(vertex) != -1) return false;
        vertices.add(vertex);
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            adjacencyMatrix.get(i).add(false);
        }
        DynamicArray<Boolean> newRow = new DynamicArray<>();
        for (int i = 0; i < vertices.size(); i++) {
            newRow.add(false);
        }
        adjacencyMatrix.add(newRow);
        return true;
    }

    @Override
    public boolean removeVertex(T vertex) {
        int index = indexOf(vertex);
        if (index == -1) return false;
        vertices.remove(index);
        adjacencyMatrix.remove(index);
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            adjacencyMatrix.get(i).remove(index);
        }
        return true;
    }

    @Override
    public boolean addEdge(T from, T to) {
        int i = indexOf(from);
        int j = indexOf(to);
        if (i == -1 || j == -1) return false;
        adjacencyMatrix.get(i).set(j, true);
        adjacencyMatrix.get(j).set(i, true);
        return true;
    }

    @Override
    public boolean removeEdge(T from, T to) {
        int i = indexOf(from);
        int j = indexOf(to);
        if (i == -1 || j == -1) return false;
        adjacencyMatrix.get(i).set(j, false);
        adjacencyMatrix.get(j).set(i, false);
        return true;
    }

    @Override
    public boolean hasEdge(T from, T to) {
        int i = indexOf(from);
        int j = indexOf(to);
        if (i == -1 || j == -1) return false;
        return adjacencyMatrix.get(i).get(j);
    }

}