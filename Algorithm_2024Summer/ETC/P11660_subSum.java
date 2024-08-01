
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11660_subSum {

    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int numbOfOp = Integer.parseInt(st.nextToken());

        // 원본 배열 입력 받기
        int [][]arr = new int[size+1][size+1];
        for (int r = 1; r <= size; r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= size; c++){
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 2D 구간 합 배열 계산 - 0, 0에서 r, c까지
        dp = new int[size+1][size+1];
        for (int r = 1; r <= size; r++){
            for (int c = 1; c <= size; c++){
                dp[r][c] = dp[r-1][c] + dp[r][c-1] - dp[r-1][c-1] + arr[r][c];
            }
        }

        // 쿼리 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbOfOp; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }    
}
