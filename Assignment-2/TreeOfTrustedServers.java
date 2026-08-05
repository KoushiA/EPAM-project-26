import java.util.*;

public class TreeOfTrustedServers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read number of servers and threshold
        int n = sc.nextInt();
        long k = sc.nextLong();

        // Read security keys (1-indexed)
        int[] key = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            key[i] = sc.nextInt();
        }

        // Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Read tree edges
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Array to store XOR from root to each node
        int[] pathXor = new int[n + 1];

        // Visited array
        boolean[] visited = new boolean[n + 1];

        // BFS Queue
        Queue<Integer> queue = new ArrayDeque<>();

        // Start BFS from root (server 1)
        queue.offer(1);
        visited[1] = true;
        pathXor[1] = key[1];

        while (!queue.isEmpty()) {

            int currentServer = queue.poll();

            for (int nextServer : adj.get(currentServer)) {

                if (!visited[nextServer]) {

                    visited[nextServer] = true;

                    // XOR from root to current server, then include next server
                    pathXor[nextServer] = pathXor[currentServer] ^ key[nextServer];

                    queue.offer(nextServer);
                }
            }
        }

        // Count trusted servers
        int trustedServers = 0;

        for (int i = 1; i <= n; i++) {
            if (pathXor[i] >= k) {
                trustedServers++;
            }
        }

        System.out.println(trustedServers);

        sc.close();
    }
} 
