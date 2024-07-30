import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ASCII {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            char c = br.readLine().charAt(0);
            System.out.println((int)c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
