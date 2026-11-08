import java.util.Scanner;

public class Divisors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean isFirst = true;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                if (!isFirst) {
                    System.out.print(" ");
                }
                System.out.print(i);
                isFirst = false;
            }
        }
        System.out.println();
        sc.close();
    }
}
