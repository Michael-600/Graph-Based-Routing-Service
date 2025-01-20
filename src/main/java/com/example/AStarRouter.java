package com.example;

import com.example.routing.model.Edge;
import com.example.routing.model.Node;

import java.util.*;

/**
 * A* pathfinding algorithm:
 *   f(n) = g(n) + h(n),
 * where
 *   g(n) = distance from start to n
 *   h(n) = heuristic estimate of distance from n to target
 */
public class AStarRouter {

    /**
     * Example heuristic: Straight-line distance or any domain-specific estimate.
     * For simplicity here, we just return 0.0 (same as Dijkstra).
     * Replace with a real heuristic if you have coordinates, etc.
     */
    private double heuristic(int nodeId, int targetId) {
        // TODO: Implement a real heuristic if available
        return 0.0;
    }

    public List<Integer> findShortestPath(GraphProcessor graph, int startId, int targetId) {
        Map<Integer, Node> nodes = graph.getNodes();

        // g(n): cost from start node
        Map<Integer, Double> gCost = new HashMap<>();
        // f(n): total estimated cost (g + h)
        Map<Integer, Double> fCost = new HashMap<>();
        // Predecessors for path reconstruction
        Map<Integer, Integer> predecessors = new HashMap<>();

        for (Integer nodeId : nodes.keySet()) {
            gCost.put(nodeId, Double.POSITIVE_INFINITY);
            fCost.put(nodeId, Double.POSITIVE_INFINITY);
        }
        gCost.put(startId, 0.0);
        fCost.put(startId, heuristic(startId, targetId));

        // PriorityQueue comparing nodes by fCost
        PriorityQueue<Integer> openSet = new PriorityQueue<>(Comparator.comparingDouble(fCost::get));
        openSet.add(startId);

        while (!openSet.isEmpty()) {
            int current = openSet.poll();

            if (current == targetId) {
                break; // Found the target
            }

            for (Edge edge : nodes.get(current).getEdges()) {
                double tentativeG = gCost.get(current) + edge.getWeight();
                if (tentativeG < gCost.get(edge.getTargetId())) {
                    gCost.put(edge.getTargetId(), tentativeG);
                    double estimatedF = tentativeG + heuristic(edge.getTargetId(), targetId);
                    fCost.put(edge.getTargetId(), estimatedF);
                    predecessors.put(edge.getTargetId(), current);

                    // Update priority queue
                    if (!openSet.contains(edge.getTargetId())) {
                        openSet.add(edge.getTargetId());
                    } else {
                        // Re-heapify by removing and re-adding
                        openSet.remove(edge.getTargetId());
                        openSet.add(edge.getTargetId());
                    }
                }
            }
        }

        // Reconstruct the path if found
        List<Integer> path = new ArrayList<>();
        if (gCost.get(targetId) == Double.POSITIVE_INFINITY) {
            // No path found
            return path;
        }

        for (Integer at = targetId; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
