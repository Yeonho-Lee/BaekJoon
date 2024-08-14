import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class roadCheckFromOne {

    static int N, M;
    static ArrayList<Road>[] roads;
    static int[] distFromStart;
    static int[] prev;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // initialize graph
        roads = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            roads[i] = new ArrayList<>();
        }
        // Roads
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            roads[a].add(new Road(b, t));
            roads[b].add(new Road(a, t));
        }

        distFromStart = new int[N + 1];
        prev = new int[N + 1];
        dijkstra(1);

        List<Integer> shortestPath = findShortestPath();

        int maxDelay = 0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            int u = shortestPath.get(i);
            int v = shortestPath.get(i + 1);

            int delay = calculateDelay(u, v);
            if (delay == INF) {
                maxDelay = -1;
                break;
            } else {
                maxDelay = Math.max(maxDelay, delay);
            }
        }
        System.out.println(maxDelay);
    }

    static void dijkstra(int start) {
        Arrays.fill(distFromStart, INF);
        Arrays.fill(prev, -1);
        PriorityQueue<Road> pq = new PriorityQueue<>();
        distFromStart[start] = 0;
        pq.offer(new Road(start, 0));

        while (!pq.isEmpty()) {
            Road current = pq.poll();
            int u = current.to;

            if (current.time > distFromStart[u])
                continue;

            for (Road r : roads[u]) {
                if (distFromStart[r.to] > distFromStart[u] + r.time) {
                    distFromStart[r.to] = distFromStart[u] + r.time;
                    prev[r.to] = u;
                    pq.offer(new Road(r.to, distFromStart[r.to]));
                }
            }
        }
    }

    static List<Integer> findShortestPath() {
        List<Integer> path = new ArrayList<>();
        int current = N;
        while (current != -1) {
            path.add(current);
            current = prev[current];
        }
        Collections.reverse(path);
        return path;
    }

    static int calculateDelay(int u, int v) {
        int originalTime = distFromStart[N];
        int[] newDist = new int[N + 1];
        Arrays.fill(newDist, INF);
        newDist[1] = 0;

        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));

        while (!pq.isEmpty()) {
            Road current = pq.poll();
            int curr = current.to;

            if (current.time > newDist[curr])
                continue;

            for (Road e : roads[curr]) {
                if ((curr == u && e.to == v) || (curr == v && e.to == u))
                    continue; // 제거된 간선 무시
                if (newDist[e.to] > newDist[curr] + e.time) {
                    newDist[e.to] = newDist[curr] + e.time;
                    pq.offer(new Road(e.to, newDist[e.to]));
                }
            }
        }

        return newDist[N] == INF ? INF : newDist[N] - originalTime;
    }
}

class Road implements Comparable<Road> {
    int to;
    int time;

    public Road(int to, int time) {
        this.to = to;
        this.time = time;
    }

    @Override
    public int compareTo(Road o) {
        return this.time - o.time;
    }
}