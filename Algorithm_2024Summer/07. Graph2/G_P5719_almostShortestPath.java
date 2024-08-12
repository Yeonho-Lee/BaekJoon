import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge {
	int y;
	int x;
	int yf;
	int xf;
	int time;

	public Edge(int y, int x, int yf, int xf, int time) {
		this.y = y;
		this.x = x;
		this.yf = yf;
		this.xf = xf;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Edge [y=" + y + ", x=" + x + ", yf=" + yf + ", xf=" + xf + ", time=" + time + "]";
	}
}

public class G_P5719_almostShortestPath {

	static int W, H;
	static int G, E;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static long[][] time;
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			// 0. read inputs
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if (W == 0 && H == 0)
				break;
			G = Integer.parseInt(br.readLine());
			map = new int[H][W];
			time = new long[H][W];
			edges = new ArrayList<>();
			int x, y;
			for (int i = 0; i < G; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				map[y][x] = -1; // 묘비
			}
			E = Integer.parseInt(br.readLine());
			int x1, y1, x2, y2, t;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				x1 = Integer.parseInt(st.nextToken());
				y1 = Integer.parseInt(st.nextToken());
				x2 = Integer.parseInt(st.nextToken());
				y2 = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				edges.add(new Edge(y1, x1, y2, x2, t));
				map[y1][x1] = 1; // 귀신구멍
			}

			// 1. add edges
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					for (int i = 0; i < 4; i++) {
						if (map[h][w] != 0 || (h == H-1&& w == W-1)) continue;
						int th = h + dy[i];
						int tw = w + dx[i];						
						if (0 <= th && th < H && 0 <= tw && tw < W && map[th][tw] != -1) {
							// 갈 수 있음
							edges.add(new Edge(h, w, th, tw, 1));
						}
					}
				}
			}
			
			// 2. bellmanFord
			boolean hasNegCycle = bellmanFordMoore(0, 0);
			
			StringBuilder sb = new StringBuilder();
			if (hasNegCycle) {
				sb.append("Never\n");
			} else if (time[H-1][W-1] == Long.MAX_VALUE) {
				sb.append("Impossible\n");
			} else {
				sb.append(time[H-1][W-1]).append("\n");
			}
			bw.write(sb.toString());
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	static boolean bellmanFordMoore(int startX, int startY) {
		for (int i = 0; i < H; i++) {
			Arrays.fill(time[i], Long.MAX_VALUE);
		}
		time[startY][startX] = 0;
		
		for (int i = 0; i < W*H - 1; i++) {
			for (Edge edge: edges) {
				if(time[edge.y][edge.x] == Long.MAX_VALUE) {
					continue;
				}
				time[edge.yf][edge.xf] = Math.min(time[edge.yf][edge.xf], time[edge.y][edge.x] + edge.time);
			}
		}
		
		for (Edge edge: edges) {
			if(time[edge.y][edge.x] == Long.MAX_VALUE) {
				continue;
			}
			if (time[edge.yf][edge.xf] > time[edge.y][edge.x] + edge.time) {
				return true;
			}
		}
		return false;
	}
}
