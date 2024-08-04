import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class P5568_placingCard {
    static int n, k;
    static String[] cards;
    static boolean[] used;
    static Set<String> numbers = new HashSet();
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        cards = new String[n];
        used = new boolean [n];

        for (int i = 0; i < n; i++) {
            cards[i] = br.readLine().trim();
        }

        backtrack(new StringBuilder(), count);

        System.out.println(numbers.size());
    }

    static void backtrack (StringBuilder sb, int count){
        if (count == k){
            numbers.add(sb.toString());
            return;
        }

        for (int i = 0; i < n; i++){
            if (!used[i]){
                used[i] = true;
                sb.append(cards[i]);
                count ++;
                backtrack(sb, count);
                count --;
                sb.setLength(sb.length() - cards[i].length());
                used[i] = false;
            }
        }
    }
}
