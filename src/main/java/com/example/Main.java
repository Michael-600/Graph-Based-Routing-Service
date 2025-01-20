package com.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Initialize the graph
        GraphProcessor graph = new GraphProcessor();

        // Example: Add a few nodes and edges
        graph.addEdge(1, 2, 5.0);
        graph.addEdge(1, 3, 2.0);
        graph.addEdge(3, 4, 1.0);
        graph.addEdge(2, 4, 2.5);
        graph.addEdge(2, 5, 10.0);
        graph.addEdge(4, 5, 3.0);

        // Dijkstra
        DijkstraRouter dijkstraRouter = new DijkstraRouter();
        List<Integer> dijkstraPath = dijkstraRouter.findShortestPath(graph, 1, 5);
        System.out.println("Dijkstra path from 1 to 5: " + dijkstraPath);

        // A*
        AStarRouter aStarRouter = new AStarRouter();
        List<Integer> aStarPath = aStarRouter.findShortestPath(graph, 1, 5);
        System.out.println("A* path from 1 to 5: " + aStarPath);
    }
}
