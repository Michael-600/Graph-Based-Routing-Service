package com.example;

import com.example.routing.model.Edge;
import com.example.routing.model.Node;

import java.util.*;

/**
 * Implements Dijkstra's algorithm for shortest paths in a weighted graph.
 */
public class DijkstraRouter {

    /**
     * @param graph        the graph processor with adjacency data
     * @param startId      the starting node
     * @param targetId     the target node
     * @return a list of node IDs representing the shortest path from start to target
     */
    public List<Integer> findShortestPath(GraphProcessor graph, int startId, int targetId) {
        Map<Integer, Node> nodes = graph.getNodes();

        // Distances from start to each node
        Map<Integer, Double> distances = new HashMap<>();
        // Keep track of how we reached each node (for path reconstruction)
        Map<Integer, Integer> predecessors = new HashMap<>();

        // Initialize all distances to infinity
        for (Integer nodeId : nodes.keySet()) {
            distances.put(nodeId, Double.POSITIVE_INFINITY);
        }
        distances.put(startId, 0.0);

        // Min-heap/priority queue for selecting the node with the smallest distance
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        pq.add(startId);

        while (!pq.isEmpty()) {
            int current = pq.poll();

            // Early exit if we've reached the target
            if (current == targetId) {
                break;
            }

            for (Edge edge : nodes.get(current).getEdges()) {
                double newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(edge.getTargetId())) {
                    distances.put(edge.getTargetId(), newDist);
                    predecessors.put(edge.getTargetId(), current);

                    // Update the priority queue
                    pq.remove(edge.getTargetId());
                    pq.add(edge.getTargetId());
                }
            }
        }

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        if (distances.get(targetId) == Double.POSITIVE_INFINITY) {
            // No path found
            return path;
        }

        // Follow predecessors from target back to start
        for (Integer at = targetId; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
