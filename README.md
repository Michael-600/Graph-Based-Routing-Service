# Graph-Based Routing Service

This Java project models a highway network of **90,000+ vertices and edges** and implements both 
A\* and Dijkstra's algorithms using `HashMap`-based data structures for optimized pathfinding.

## Features
- **GraphProcessor** class that loads and stores a large graph
- **DijkstraRouter** for standard shortest-path calculation
- **AStarRouter** that incorporates a heuristic for faster routing
- Example usage in `Main.java`

## Requirements
- Java 8 (or later)
- Maven 3.x

## Setup & Build
1. Clone this repository
2. In the project directory, run:
   ```bash
   mvn clean install

