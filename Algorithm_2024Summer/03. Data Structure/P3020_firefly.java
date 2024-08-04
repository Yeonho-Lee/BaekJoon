
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3020_firefly {
    static int [] bottom, top;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        bottom = new int [H + 1];
        top = new int [H + 1];

        for (int i = 0; i < N; i++) {
            int length = Integer.parseInt(br.readLine());
            if (i % 2 == 0){
                bottom[length]++;
            } else {
                top[H - length + 1]++;
                // 실제 지나가게 될 높이로 보정
            }
        }

        // 누적 합 구하기
        for (int i = H-1; i >= 1; i-- ){
            bottom[i] += bottom[i+1];
        }
        //System.out.println(Arrays.toString(bottom));
        for (int i = 2; i <= H; i++ ){
            top[i] += top[i-1];
        }
        //System.out.println(Arrays.toString(top));
        
        int minObstacles = N;
        int count = 0;
        for (int h = 1; h <= H; h++){
            int tempObstacles = bottom[h] + top[h];
            if (minObstacles > tempObstacles){
                count = 1;
                minObstacles = tempObstacles;
            } else if (minObstacles == tempObstacles){
                count ++;
            }
        }

        System.out.println(minObstacles+" "+count);
    }
}
