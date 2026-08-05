import java.util.*;

public class EmergencyRouteValidation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read number of cities, roads and maximum allowed distance
        int n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();

        // Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Read all roads
        for (int i = 0; i < m; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();

            // Since the graph is undirected
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Distance array
        int[] distance = new int[n + 1];

        // Visited array
        boolean[] visited = new boolean[n + 1];

        // BFS Queue
        Queue<Integer> queue = new ArrayDeque<>();

        // Start BFS from City 1
        queue.offer(1);
        visited[1] = true;
        distance[1] = 0;

        while (!queue.isEmpty()) {

            int currentCity = queue.poll();

            for (int nextCity : adj.get(currentCity)) {

                if (!visited[nextCity]) {

                    visited[nextCity] = true;

                    // Shortest distance to next city
                    distance[nextCity] = distance[currentCity] + 1;

                    queue.offer(nextCity);
                }
            }
        }

        // Count efficiently reachable cities
        int reachableCities = 0;

        for (int i = 1; i <= n; i++) {

            if (visited[i] && distance[i] <= d) {
                reachableCities++;
            }
        }

        System.out.println(reachableCities);

        sc.close();
    }
}
