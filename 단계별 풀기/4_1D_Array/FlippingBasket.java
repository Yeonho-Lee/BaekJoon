import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FlippingBasket {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String [] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int [] baskets = new int[N];
        for(int i=0; i<N; i++){
            baskets[i] = i+1;
        }

        for(int i=0; i<M; i++){
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            flipping(baskets, start, end);
        }

        for (int i =0; i<N; i++){
            bw.write(baskets[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void flipping(int [] baskets, int start, int end){
        while (start < end) {
            int temp = baskets[start-1];
            baskets[start-1] = baskets[end-1];
            baskets[end-1] = temp;
            start++;
            end--;
        }
    }
}