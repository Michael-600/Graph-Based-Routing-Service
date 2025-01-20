package com.example;

import com.example.routing.model.Edge;
import com.example.routing.model.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Models a highway network using an adjacency list in HashMaps.
 */
public class GraphProcessor {

    // Map<nodeId, Node>
    private final Map<Integer, Node> nodes;

    public GraphProcessor() {
        this.nodes = new HashMap<>();
    }

    /**
     * Add a node to the graph.
     */
    public void addNode(int id) {
        nodes.putIfAbsent(id, new Node(id));
    }

    /**
     * Add an edge between two nodes (directed or undirected).
     */
    public void addEdge(int sourceId, int targetId, double weight) {
        if (!nodes.containsKey(sourceId)) {
            addNode(sourceId);
        }
        if (!nodes.containsKey(targetId)) {
            addNode(targetId);
        }
        nodes.get(sourceId).getEdges().add(new Edge(targetId, weight));
        
        // If you want an undirected graph, also add the reverse edge:
        // nodes.get(targetId).getEdges().add(new Edge(sourceId, weight));
    }

    public Map<Integer, Node> getNodes() {
        return nodes;
    }
}
