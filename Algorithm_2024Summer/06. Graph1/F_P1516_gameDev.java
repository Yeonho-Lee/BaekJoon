import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class F_P1516_gameDev {

	static int N;
	static int[] preBuildTime, buildTimes;
	static int[] inDegree;
	static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		buildTimes = new int[N + 1];
		inDegree = new int[N + 1];
		adjList = new ArrayList[N + 1];
		preBuildTime = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		int from;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			buildTimes[i] = time;

			while (true) {
				from = Integer.parseInt(st.nextToken());
				if (from == -1) {
					break;
				}
				// graph에 add하는 로직 추가
				adjList[from].add(i);
				inDegree[i]++;
			}
		}
		
		topologicalSort();
		
		for (int i = 1; i <= N; i++) {
			System.out.println(buildTimes[i]);
		}
	}
	
	public static void topologicalSort() {
		ArrayDeque <Integer> queue = new ArrayDeque<>();
		//시작점: 차수가 0인 노드 queue에 넣기
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			for (Integer to: adjList[node]) {
				// node -> to 차수 줄이기
				inDegree[to]--;
				// to 정점으로 오는 시작정점의 buildTime 중 max 값이 정점의 preBuildTime
				preBuildTime[to] = Math.max(preBuildTime[to], buildTimes[node]);
				if (inDegree[to] == 0) {
					buildTimes[to] += preBuildTime[to];
					queue.offer(to);
				}
			}
		}
	}

}
