
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5573_promenade {

    static int H, W, N;
    static int[][] map;
    static int[][] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int [H][W];
        count = new int [H][W];

        for (int y = 0; y < H; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < W; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        count[0][0] = N-1;

        // calculate the visits of each place
        for (int y = 0; y < H; y++){
            for (int x = 0; x < W; x++){
                int c = count[y][x];
                if (c == 0) continue;
                if (map[y][x] == 1) { // right
                    if (x + 1 < W){
                        count[y][x+1] += (c + 1) / 2; // odd tries
                    }
                    if (y + 1 < H){
                        count[y + 1][x] += c / 2; // even tries
                    }
                } else { // down
                    if (x + 1 < W){
                        count[y][x+1] += c / 2; // even tries
                    }
                    if (y + 1 < H){
                        count[y + 1][x] += (c + 1) / 2; // odd tries
                    }
                }
            }
        }

        int x= 0, y = 0;
        while (y < H && x < W ){
            if ((count[y][x] % 2 == 1) == (map[y][x] == 1)){
                // there was odd tries
                // this is even try && map = right
                // this is odd try && map = down
                y++;
            } else {
                x++;
            }
        }

        System.out.println((y + 1) + " " + (x + 1));

    }
}
