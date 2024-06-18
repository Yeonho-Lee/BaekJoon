import java.util.Scanner;

public class googoodan {
    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();

        for (int i = 1; i <= 9; i++){
            System.out.println(A+ " * "+ i+  " = "+A*i);
        }
    }
}