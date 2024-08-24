import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Zelda {
    static int N;
    static int[][] map;
    static final int[] dx = { -1, 1, 0, 0 };
    static final int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = dijkstra();
            System.out.println("Problem " + testCase + ": " + result);
            testCase++;
        }
    }

    static int dijkstra() {
        int[][] cost = new int[N][N];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        PriorityQueue<Node_> pq = new PriorityQueue<>();
        pq.offer(new Node_(0, 0, map[0][0]));
        cost[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Node_ cur = pq.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                return cur.cost;
            }

            if (cost[cur.y][cur.x] < cur.cost)
                continue;

            for (int i = 0; i < 4; i++) {
                int tx = cur.x + dx[i];
                int ty = cur.y + dy[i];

                if (0 <= tx && tx < N && 0 <= ty && ty < N) {
                    int newCost = cur.cost + map[ty][tx];
                    if (newCost < cost[ty][tx]) {
                        cost[ty][tx] = newCost;
                        pq.offer(new Node_(tx, ty, newCost));
                    }
                }
            }
        }
        return cost[N - 1][N - 1];
    }

}

class Node_ implements Comparable<Node_> {

    int x, y, cost;

    Node_(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node_ o) {
        return Integer.compare(this.cost, o.cost);
    }
}
