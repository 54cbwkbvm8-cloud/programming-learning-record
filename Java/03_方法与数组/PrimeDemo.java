public class PrimeDemo {
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] tests = { -7, 0, 1, 2, 3, 4, 9, 17, 25, 97, 100, 7919, 2147483647 };
        for (int n : tests) {
            System.out.println(n + " is prime? " + isPrime(n));
        }
    }
}
