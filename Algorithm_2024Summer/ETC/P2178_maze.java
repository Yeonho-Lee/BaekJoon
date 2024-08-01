
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P2178_maze {

    static Queue <MyPoint>queue = new LinkedList<>();
    static int [][] map;
    static int N, M;
    static int[] moveX = {1, -1, 0, 0};
    static int[] moveY = {0, 0, 1, -1};
    static int [][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int [N+1][M+1];
        visited = new int[N+1][M+1];

        for (int i = 1; i <= N; i++){
            String row = br.readLine().trim();
            for (int j = 1; j <= M; j++){
                map[i][j] = row.charAt(j-1)-'0';
            }
        }
        // initializing queue
        queue.add(new MyPoint(1, 1));
        System.out.println( bfs());
    }

    static int bfs(){
        while (!queue.isEmpty()){
            // 1. poll from queue
            MyPoint current = queue.poll();
            // 2. is it dest? -> end
            if (current.x == M && current.y == N){
                // TODO
                return visited[N][M] + 1;
            }
            // 3. traverse the neighbors
            //      4. can go in?
            //          5. check-in
            //          6. put it in the queue
            for (int i = 0; i < 4; i++){
                int x = current.x;
                int y = current.y;
                int tx = x + moveX[i];
                int ty = y + moveY[i];
                if (1<= tx  && tx <= M && 1 <= ty && ty <= N && visited[ty][tx]==0 && map[ty][tx] == 1){
                    visited[ty][tx] = visited[y][x] + 1;
                    queue.add(new MyPoint(tx, ty));
                }
            }
        }
        return -1;
    }
}

class MyPoint{
    int x;
    int y;

    MyPoint (int x, int y){
        this.x = x;
        this.y = y;
    }
}