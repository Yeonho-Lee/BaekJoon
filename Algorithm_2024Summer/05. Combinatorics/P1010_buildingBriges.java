import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1010_buildingBriges {

    static int[][] dp = new int [30][30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCases = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int west = Integer.parseInt(st.nextToken());
            int east = Integer.parseInt(st.nextToken());

            sb.append(combination(east, west)).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int combination (int n, int k){
        if (k == n || k == 0){
            return 1;
        } else if (dp[n][k] != 0){
            return dp[n][k];
        } else {
            return dp[n][k] = combination(n-1, k) + combination(n-1, k-1);
        }
    }
}
