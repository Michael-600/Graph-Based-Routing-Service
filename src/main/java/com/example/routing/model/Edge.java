package com.example.routing.model;

/**
 * Represents a directed edge to a target node with a weight.
 */
public class Edge {

    private final int targetId;
    private final double weight;

    public Edge(int targetId, double weight) {
        this.targetId = targetId;
        this.weight = weight;
    }

    public int getTargetId() {
        return targetId;
    }

    public double getWeight() {
        return weight;
    }
}
