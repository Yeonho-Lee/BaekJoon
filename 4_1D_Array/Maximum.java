import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Maximum {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int max = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 1; i <= 9; i++){
            int num = Integer.parseInt(br.readLine().strip());
            if (num > max){
                max = num;
                index = i;
            }
        }

        bw.write(max + "\n" + index);
        bw.flush();
        bw.close();
        br.close();
    }
}
