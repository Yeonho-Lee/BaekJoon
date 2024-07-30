import java.util.Scanner;

public class Receipt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int X = scanner.nextInt();
        int N = scanner.nextInt();
        int result = 0;
        for (int i = 0; i < N; i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            result += a*b;
        }
        if (X == result){
            System.out.print("Yes");
        } else {
            System.out.print("No");
        }
    }
}
