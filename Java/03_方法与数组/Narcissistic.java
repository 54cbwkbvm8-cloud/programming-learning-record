import java.util.Scanner;

public class Narcissistic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        boolean found = false;
        for (int num = a; num <= b; num++) {
            int ones = num % 10;
            int tens = num / 10 % 10;
            int hundreds = num / 100;
            int sum = ones * ones * ones + tens * tens * tens + hundreds * hundreds * hundreds;
            if (sum == num) {
                System.out.println(num);
                found = true;
            }
        }
        if (!found) {
            System.out.println("NONE");
        }
        sc.close();
    }
}
