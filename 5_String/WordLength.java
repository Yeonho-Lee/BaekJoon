import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WordLength {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String str = br.readLine();
            System.out.println(str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
