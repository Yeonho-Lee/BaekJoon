import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Stars_2 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        try {
            int N = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (j < N - i - 1) {
                        bw.write(" ");
                    } else {
                        bw.write("*");
                    }
                }
                bw.write("\n");
            }
            br.close();
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
