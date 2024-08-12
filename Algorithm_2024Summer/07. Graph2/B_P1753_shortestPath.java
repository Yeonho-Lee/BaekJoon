import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int to;
	int weight;

	public Edge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [to=" + to + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(Edge e1) {
		// TODO Auto-generated method stub
		return this.weight - e1.weight;
	}

}

public class B_P1753_shortestPath {

	static int V, E, start;
	static ArrayList<Edge>[] adjList;
	static int[] distance;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());

		distance = new int[V + 1];
		visited = new boolean[V + 1];
		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
			distance[i] = Integer.MAX_VALUE;
		}

		// add edges
		int n1, n2, w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			adjList[n1].add(new Edge(n2, w));
		}

		// do the dijkstra
		dijkstra(start);
		for (int i = 1; i <= V; i++) {
			if (distance[i] != Integer.MAX_VALUE) {
				bw.write(distance[i] + "\n");
			} else {
				bw.write("INF\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

	static void dijkstra(int start) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		// start
		queue.add(new Edge(start, 0));
		distance[start] = 0;
		while (!queue.isEmpty()) {
			Edge curr = queue.poll();
			if (visited[curr.to])
				continue;
			visited[curr.to] = true;
			for (Edge next : adjList[curr.to]) {
				int newDist = curr.weight + next.weight;
				if (newDist < distance[next.to]) {
					distance[next.to] = newDist;
					queue.add(new Edge(next.to, distance[next.to]));
				}

			}
		}
	}

}
