package com.heaven;


import java.util.List;

class Campsite {

    private static final int NO_PARENT = -1;

    private int nVertices;

    private int[][] adjacencyMatrix;

    private int startVertex;

    private List<Group> groups;

    // shortestDistances[i] will hold the
    // shortest distance from src to i
    private int[] shortestDistances;

    // Parent array to store shortest path tree
    private int[] parents;

    public Campsite(int[][] campsiteGraph, int gate, List<Group> groups) {
        this.adjacencyMatrix = campsiteGraph;
        this.nVertices = adjacencyMatrix[0].length;
        this.startVertex = gate;
        this.groups = groups;
        shortestDistances = new int[nVertices];
        parents = new int[nVertices];
    }

    void calcDijkstraShortestPaths() {

        // The starting vertex does not have a parent
        parents[startVertex] = NO_PARENT;

        // added[i] will true if vertex i is included in shortest path tree
        // or shortest distance from src to i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from itself is always 0
        shortestDistances[startVertex] = 0;

        // Find shortest path for all vertices
        for (int i = 1; i < nVertices; i++) {

            // Pick the minimum distance vertex from the set of vertices not yet processed.
            // nearestVertex is always equal to startNode in first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] &&
                        shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as processed
            added[nearestVertex] = true;

            // Update dist value of the adjacent vertices of the picked vertex.
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0
                        && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }
    }

    void processGroups() {
        Group currentGroup = groups.get(0);
        Group previousGroup;
        Integer timeToDestination = shortestDistances[currentGroup.getDestination()];
        Integer timeToComeBackFromPrevDestination;
        currentGroup.setWaitingTime(timeToDestination);

        for (int i = 1; i < groups.size(); i++) {
            currentGroup = groups.get(i);
            previousGroup = groups.get(i - 1);
            timeToDestination = shortestDistances[currentGroup.getDestination()];
            timeToComeBackFromPrevDestination = shortestDistances[previousGroup.getDestination()];
            currentGroup.setWaitingTime(previousGroup.getWaitingTime() + timeToComeBackFromPrevDestination + timeToDestination);
        }
    }

    // A utility function to print the calculated wait times and the paths used for each group
    void printResults() {
        System.out.println("Group\t Waiting time\tPath");

        for (Group group : groups) {
            System.out.print("\n" + group.getId() + " \t\t ");
            System.out.print(group.getWaitingTime() + "\t\t\t\t");
            printPath(group.getDestination(), parents);
        }
    }

    // Function to print shortest path from source to currentVertex using parents array
    private void printPath(int currentVertex, int[] parents) {

        // Base case : Source node has been processed
        if (currentVertex == NO_PARENT) {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }
}

