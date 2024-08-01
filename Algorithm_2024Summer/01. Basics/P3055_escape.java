import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class P3055_escape {

	// left, right, up down
	static final int[] MX = { -1, 1, 0, 0 };
	static final int[] MY = { 0, 0, -1, 1 };
	static char[][] Map;
	static int[][] visited;
	static Queue<Point> queue = new LinkedList<>();
	static int targetX, targetY;
	static int min = 10000;
	static int R, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// read inputs
		String[] input = br.readLine().trim().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		Map = new char[R][C];
		visited = new int[R][C];
		Point s = null;
		// 0. 시작점들 넣기
		for (int r = 0; r < R; r++) {
			input = br.readLine().trim().split("");
			for (int c = 0; c < C; c++) {
				char now = input[c].charAt(0);
				visited[r][c] = 0;
				Map[r][c] = now;
				switch (now) {
				case '*':
					Point water = new Point(r, c, Map[r][c]);
					queue.add(water);
					break;
				case 'S':
					s = new Point(r, c, Map[r][c]);
					// visited[r][c] = -100;
					break;
				case 'D':
					targetX = c;
					targetY = r;
					break;
				default:
					break;
				}
			}
		}
		queue.add(s);

		// BFS
		int result = bfs();
		if (result == 10000) {
			bw.write("KAKTUS");
		} else {
			bw.write(Integer.toString(result));
		}
		bw.flush();
		br.close();
		bw.close();
	}

	static int bfs() {
		while (!queue.isEmpty()) {
			// 1. 큐에서 꺼내옴
			Point current = queue.poll();
			// 2. 목적지?
			// 물 x / 고슴도치 o
			if (current.type == 'S' && current.x == targetX && current.y == targetY) {
				// 목적지 도착시 실행할 코드 입력
				return visited[current.y][current.x];
			}
			// 3. 연결된 곳 순회 (for문 이용)
			for (int i = 0; i < 4; i++) {
				int tx = current.x + MX[i];
				int ty = current.y + MY[i];
				// 4. 갈 수 있는가?
				// 물 - 맵, . / 고슴도치 - 맵, ., D
				// 5. 체크인 - 방문할 예정이다
				// 물 - 맵을 바꿈 / 고슴도치 - visited[]에 숫자를 넣음
				// 6. 큐에 넣음
				if (current.type == '*') {
					if (tx >= 0 && tx < C && ty >= 0 && ty < R && (Map[ty][tx] == '.')) {
						Map[ty][tx] = '*';
						queue.add(new Point(ty, tx, '*'));
					}
				} else if (current.type == 'S') {
					if (tx >= 0 && tx < C && ty >= 0 && ty < R && (Map[ty][tx] == '.' || Map[ty][tx] == 'D')) {
						if (visited[ty][tx] == 0) {
							visited[ty][tx] = visited[current.y][current.x] + 1;
							queue.add(new Point(ty, tx, 'S'));
						}
					}
				}
			}
		}
		return 10000;
	}
}

class Point {
	int y;
	int x;
	char type;

	public Point(int y, int x, char type) {
		this.y = y;
		this.x = x;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + ", type=" + type + "]";
	}
}