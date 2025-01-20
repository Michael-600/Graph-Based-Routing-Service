package com.example.routing.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in the graph.
 */
public class Node {

    private final int id;
    private final List<Edge> edges;

    public Node(int id) {
        this.id = id;
        this.edges = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
