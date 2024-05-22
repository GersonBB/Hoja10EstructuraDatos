

import java.util.Arrays;

public class Grafo {
    private final int INF = 99999;
    private int numVertices;
    private int[][] dist;
    private int[][] next;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        dist = new int[numVertices][numVertices];
        next = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
            for (int j = 0; j < numVertices; j++) {
                next[i][j] = j;
            }
        }
    }

    public void addEdge(int src, int dest, int weight) {
        dist[src][dest] = weight;
    }

    public void floydWarshall() {
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    public void printPath(int src, int dest) {
        if (dist[src][dest] == INF) {
            System.out.println("No path exists between " + src + " and " + dest);
            return;
        }
        System.out.print("Shortest path from " + src + " to " + dest + ": " + src);
        int u = src;
        while (u != dest) {
            u = next[u][dest];
            System.out.print(" -> " + u);
        }
        System.out.println("\nDistance: " + dist[src][dest]);
    }

    public int findCenter() {
        int[] eccentricity = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            eccentricity[i] = Arrays.stream(dist[i]).max().getAsInt();
        }
        int center = 0;
        for (int i = 1; i < numVertices; i++) {
            if (eccentricity[i] < eccentricity[center]) {
                center = i;
            }
        }
        return center;
    }
}
