import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Counting {
    public static void main (String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputLine = br.readLine().trim();
        int N = Integer.parseInt(inputLine);
        inputLine = br.readLine().trim();
        String []input = inputLine.split(" ");
        int count = 0;
        inputLine = br.readLine().trim();
        int target = Integer.parseInt(inputLine);
        for(int i=0; i<N; i++){
            if(Integer.parseInt(input[i]) == target){
                count++;
            }
        }
        bw.write(Integer.toString(count));
        
        bw.flush();
        bw.close();
        br.close();
    }
}
