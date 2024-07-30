import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SumOfDigits {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int result = 0;

        for (int i = 1; i < N; i++){
            int sum = 0;
            int num = i;
            sum += num;
            while ( num > 0){
                sum += num %10;
                num /= 10;
            }
            // bw.write(Integer.toString(i)+ ", "+ Integer.toString(sum)+ '\n');
            if (sum == N){
                result = i;
                break;
            }
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
