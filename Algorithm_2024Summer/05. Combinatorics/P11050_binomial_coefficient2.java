import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11050_binomial_coefficient2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(combination(N, K));
    }
    
    public static int combination (int n, int k){
        int [][] dp = new int[n+1][k+1];

        for (int i = 0; i <=n; i++){
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || i == 0){
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % 10007;
                }
            }
        }

        return dp[n][k];
    }
}
