
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class findingMinimun {
    static int N, M;
    static ArrayList<Road>[] adjList;
    static long[] cost;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        cost = new long[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(cost, Long.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[from].add(new Road(to, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(cost[end]);

    }

    static void dijkstra(int start) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.add(new Road(start, 0));

        while (!pq.isEmpty()) {
            Road cur = pq.poll();
            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            for (Road next : adjList[cur.to]) {
                int newCost = cur.cost + next.cost;
                if (newCost < cost[next.to]) {
                    cost[next.to] = newCost;
                    pq.add(new Road(next.to, newCost));
                }
            }
        }
    }
}

class Road implements Comparable<Road> {
    int to;
    int cost;

    public Road(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Road [to=" + to + ", cost=" + cost + "]";
    }

    @Override
    public int compareTo(Road o) {
        return this.cost - o.cost;
    }

}
