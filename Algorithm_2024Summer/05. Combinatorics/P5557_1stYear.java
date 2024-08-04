
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5557_1stYear {
    static long[][] dp;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        numbers = new int [N];
        dp = new long[N][21];
        for (int i = 0; i < N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][numbers[0]]++;
        for (int i = 1; i < N-1; i++){
            for (int r = 0; r <= 20; r++){
                if (dp[i-1][r] != 0){
                    if (r+numbers[i] <= 20){
                        dp[i][r+numbers[i]] += dp[i-1][r];
                    }
                    if (r - numbers[i] >= 0){
                        dp[i][r-numbers[i]] += dp[i-1][r];
                    }
                }
            }
        }
        System.out.println(dp[N-2][numbers[N-1]]);
    }
}
