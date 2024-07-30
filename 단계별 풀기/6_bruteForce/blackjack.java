import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class blackjack {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String [] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // numb of cards
        int M = Integer.parseInt(input[1]); // the target number

        // reading the card numbers
        input = br.readLine().split(" ");
        int [] cards = new int[N];
        for (int i = 0; i< N; i++){
            cards[i] = Integer.parseInt(input[i]);
        }

        int max = 0;
        // try every combination
        for (int i = 0 ; i < N; i++){
            for (int j = i +1; j < N ; j++){
                for (int k = j+1; k< N; k++){
                    int comb = cards[i]+ cards[j] + cards[k];
                    if ( comb > max && comb <= M){
                        max = comb;
                    }
                }
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
