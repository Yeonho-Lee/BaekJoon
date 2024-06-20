import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Remainder {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        try {
            int [] remainders = new int [42];
            for (int i = 0; i < 10; i++){
                int num = Integer.parseInt(br.readLine().strip());
                remainders[num % 42]++;
            }
            int count = 0;
            for (int i = 0; i < remainders.length; i++){
                if (remainders[i] != 0){
                    count++;
                }
            }
            bw.write(count + "");
            bw.flush();
            bw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
