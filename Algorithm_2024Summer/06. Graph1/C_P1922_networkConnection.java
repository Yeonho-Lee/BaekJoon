import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int from;
	int to;
	int cost;
	
	
	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge e1) {
		// TODO Auto-generated method stub
		return this.cost - e1.cost;
	}

	@Override
	public String toString() {
		return "Edge [f=" + from + ", t=" + to + ", c=" + cost + "]\n";
	}
}

public class C_P1922_networkConnection {
	
	static int V, E;
	static LinkedList <Edge> edgeList = new LinkedList<>();
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		parent = new int [V + 1];
		
		StringTokenizer st;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from, to, cost;
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(from, to, cost));
		}
		Collections.sort(edgeList);	
		
		initParent();
		
		System.out.println(kruskal());
	}
	
	static int kruskal() {
		int selectedEdge = 0;
		int totalCost = 0;
		for (int i = 0; i < E; i++) {
			Edge currEdge = edgeList.removeFirst();
			int rootFrom = find(currEdge.from);
			int rootTo = find(currEdge.to);
			if (rootFrom != rootTo) {
				union(rootFrom, rootTo);
				totalCost += currEdge.cost;
				selectedEdge ++;
			}
			
			if (selectedEdge == V - 1) {
				return totalCost;
			}
		}		
		return -1;
	}
	
	static void initParent() {
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
	}
	
	static void union (int a, int b) {
		parent[b] = a;
	}
	
	static int find (int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

}
