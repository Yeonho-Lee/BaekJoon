import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge {
	int from;
	int to;
	int time;

	public Edge(int from, int to, int time) {
		this.from = from;
		this.to = to;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", time=" + time + "]";
	}
}

public class C_P11657_timeMachine {

	static int N, M;
	static ArrayList<Edge> edges;
	static long[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // V
		M = Integer.parseInt(st.nextToken()); // E

		dist = new long[N + 1];
		edges = new ArrayList<>();

		int from, to, time;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			edges.add(new Edge(from, to, time));
		}

		boolean hasNegCycle = bellmanFordMoore(1, N);

		StringBuilder sb = new StringBuilder();
		if (hasNegCycle) {
			sb.append(-1);
		} else {
			for (int i = 2; i <= N; i++) {
				if (dist[i] == Long.MAX_VALUE) {
					sb.append("-1");
				} else {
					sb.append(dist[i]);
				}
				if (i != N) {
					sb.append("\n");
				}
			}
		}
		System.out.println(sb.toString());
		br.close();

	}

	static boolean bellmanFordMoore(int start, int V) {
		// 0. 거리 초기화
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[start] = 0;

		// 1. V-1번 E개의 edge들을 돌면서 dist를 업데이트
		for (int i = 0; i < V - 1; i++) {
			for (Edge edge : edges) {
				if (dist[edge.from] == Long.MAX_VALUE) continue;
				dist[edge.to] = Math.min(dist[edge.to], dist[edge.from] + edge.time);
			}
		}

		for (Edge edge : edges) {
			if (dist[edge.from] == Long.MAX_VALUE) continue;
			if (dist[edge.to] > dist[edge.from] + edge.time) {
				return true;
			}
		}
		return false;
	}
}
