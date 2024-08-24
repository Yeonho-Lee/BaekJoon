
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class moonLightFox {

    static int N, M;
    static ArrayList<Path>[] adjList;
    static long[] distanceFox;
    static long[][] distanceWolf;
    static boolean[] visited;
    static boolean[][] visitedWolf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // process inputs
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        distanceFox = new long[N + 1];
        distanceWolf = new long[2][N + 1];
        visited = new boolean[N + 1];
        visitedWolf = new boolean[2][N + 1];
        Arrays.fill(distanceFox, Long.MAX_VALUE);
        for (int i = 0; i < 2; i++) {
            Arrays.fill(distanceWolf[i], Long.MAX_VALUE);
        }
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            adjList[a].add(new Path(b, d * 2));
            adjList[b].add(new Path(a, d * 2));
        }

        dijkstraFox();
        dijkstraWolf();

        int count = 0;
        for (int i = 2; i <= N; i++) {
            long distWolf = Math.min(distanceWolf[0][i], distanceWolf[1][i]);
            if (distanceFox[i] < distWolf) {
                count++;
            }
        }
        System.out.println(count);
    }

    static void dijkstraFox() {
        PriorityQueue<Path> pq = new PriorityQueue<>();
        // start
        pq.add(new Path(1, 0));
        distanceFox[1] = 0;
        while (!pq.isEmpty()) {
            Path cur = pq.poll();
            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            for (Path next : adjList[cur.to]) {
                long newDist = cur.d + next.d;
                if (newDist < distanceFox[next.to]) {
                    distanceFox[next.to] = newDist;
                    pq.add(new Path(next.to, newDist));
                }
            }
        }
    }

    static void dijkstraWolf() {
        PriorityQueue<Path> pq = new PriorityQueue<>();
        // start
        pq.add(new Path(1, 0, 0));
        distanceWolf[0][1] = 0;
        while (!pq.isEmpty()) {
            Path cur = pq.poll();
            if (visitedWolf[cur.state][cur.to]) {
                continue;
            }
            visitedWolf[cur.state][cur.to] = true;
            for (Path next : adjList[cur.to]) {
                int nextState = 1 - cur.state;
                long newDist = cur.d + (nextState == 1 ? next.d / 2 : next.d * 2);
                if (newDist < distanceWolf[nextState][next.to]) {
                    distanceWolf[nextState][next.to] = newDist;
                    pq.add(new Path(next.to, newDist, nextState));
                }
            }
        }
    }
}

class Path implements Comparable<Path> {
    int to;
    long d;
    int state; // 0: slow, 1: fast

    public Path(int to, long d) {
        this.to = to;
        this.d = d;
    }

    public Path(int to, long d, int state) {
        this.to = to;
        this.d = d;
        this.state = state;
    }

    @Override
    public int compareTo(Path o) {
        return Long.compare(this.d, o.d);
    }
}