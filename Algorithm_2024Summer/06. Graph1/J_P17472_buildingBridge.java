import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Island {
	ArrayList<Point> points = new ArrayList<>();

	@Override
	public String toString() {
		return "Island [" + points + "]\n";
	}
}

class Point {
	int y;
	int x;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "[y=" + y + ", x=" + x + "]";
	}

}

class Edge implements Comparable<Edge> {
	int to;
	int cost;

	public Edge(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Edge [to=" + to + ", cost=" + cost + "]";
	}

	@Override
	public int compareTo(Edge arg0) {
		return this.cost - arg0.cost;
	}

}

public class J_P17472_buildingBridge {

	static int rows, cols;
	static int[][] map;

	// 섬 구분을 위한 변수
	static boolean[][] isVisited; // dfs
	static List<Island> islands = new ArrayList<>();
	static int islandCount = 0;

	// 다리 후보를 위한 변수
	static ArrayList<Edge>[] bridgesFrom;
	static boolean[] islandIsVisited;

	static int[] moveC = { -1, 1, 0, 0 };
	static int[] moveR = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		rows = Integer.parseInt(st.nextToken());
		cols = Integer.parseInt(st.nextToken());
		map = new int[rows][cols];
		isVisited = new boolean[rows][cols];

		for (int r = 0; r < rows; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < cols; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 구별
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (!isVisited[r][c] && map[r][c] == 1) {
					Island island = new Island();
					islandCount++;
					dfs(r, c, island);
					islands.add(island);
				}
			}
		}
		bridgesFrom = new ArrayList [islandCount + 1];
		islandIsVisited = new boolean[islandCount + 1];
		for (int i = 1; i <= islandCount; i++) {
			bridgesFrom[i] = new ArrayList<>();
		}

		// 다리 candidates 탐색
		findBridgeCandidates();

		// MST 구하기
		bw.write(prim()+" ");
		bw.flush();
		bw.close();
		br.close();

	}
	
	static int prim() {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		if (bridgesFrom[1].size()== 0) return -1;
		queue.offer(new Edge(1, 0));
		int selectedNodes = 0;
		int totalCost = 0;
		while (!queue.isEmpty()) {
			Edge bridge = queue.poll();
			if (!islandIsVisited[bridge.to]) {
				selectedNodes ++;
				islandIsVisited[bridge.to] = true;
				totalCost += bridge.cost;
				for (Edge nextBridge: bridgesFrom[bridge.to]) {
					queue.offer(nextBridge);
				}
			}
			if (selectedNodes == islandCount) {
				return totalCost;
			}			
		}		
		return -1;
	}
	
	static void findBridgeCandidates() {
		for (Island island : islands) {
			int from = map[island.points.get(0).y][island.points.get(0).x];
			for (Point point : island.points) {
				int y = point.y;
				int x = point.x;
				for (int i = 0; i < 4; i++) { // 4 방향
					int tx = x;
					int ty = y;
					int bridgeLength = 0;
					while (true) {
						tx += moveC[i];
						ty += moveR[i];
						if (!(0 <= tx && tx < cols && 0 <= ty && ty < rows)) {
							break;
						}
						int to = map[ty][tx];
						if (from == to) {
							break;
						}
						if (to != 0) {
							if (bridgeLength > 1) {
								bridgesFrom[from].add(new Edge(to, bridgeLength));
							}
							break;
						}
						bridgeLength++;
					}
				}
			}
		}
	}

	static void dfs(int r, int c, Island island) {
		island.points.add(new Point(r, c));
		isVisited[r][c] = true;
		map[r][c] = islandCount;

		for (int i = 0; i < 4; i++) {
			int tr = r + moveR[i];
			int tc = c + moveC[i];
			if (0 <= tr && tr < rows && 0 <= tc && tc < cols && !isVisited[tr][tc] && map[tr][tc] == 1) {
				dfs(tr, tc, island);
			}
		}
	};

}
