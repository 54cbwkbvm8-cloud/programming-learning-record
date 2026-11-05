import java.util.Scanner;

public class MaxMin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            System.out.println("请输入你想要输入的数字的个数（正整数）:");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= 1) {
                    break;
                }
                System.out.println("个数必须大于等于1，请重新输入！");
            } else {
                System.out.println("你输入的不是整数，请重新输入！");
                sc.next();
            }
        }
        System.out.println("请输入第1个数:");
        while (!sc.hasNextInt()) {
            System.out.println("你输入的不是整数，请重新输入第1个数:");
            sc.next();
        }
        int max = sc.nextInt();
        int min = max;
        for (int i = 2; i <= n; i++) {
            System.out.println("请输入第" + i + "个数:");
            while (!sc.hasNextInt()) {
                System.out.println("你输入的不是整数，请重新输入第" + i + "个数:");
                sc.next();
            }
            int x = sc.nextInt();
            if (x > max) {
                max = x;
            }
            if (x < min) {
                min = x;
            }
        }
        System.out.println("最大值为：" + max + "\n最小值为：" + min);
        sc.close();
    }
}
// import java.util.Scanner;

// public class MaxMin {
// public static void main(String[] args) {
// Scanner sc = new Scanner(System.in);
// int n;
// while (true) {
// System.out.println("请输入你想要输入的数字的个数（正整数）:");
// if (sc.hasNextInt()) {
// n = sc.nextInt();
// if (n >= 1) {
// break;
// }
// System.out.println("个数必须大于等于1，请重新输入！");
// } else {
// System.out.println("你输入的不是整数，请重新输入！");
// sc.next();
// }
// }
// System.out.println("请输入第1个数:");
// while (!sc.hasNextInt()) {
// System.out.println("你输入的不是整数，请重新输入第1个数:");
// sc.next();
// }
// int max = sc.nextInt();
// int min = max;
// for (int i = 2; i <= n; i++) {
// System.out.println("请输入第" + i + "个数:");
// while (!sc.hasNextInt()) {
// System.out.println("你输入的不是整数，请重新输入第" + i + "个数:");
// sc.next();
// }
// int x = sc.nextInt();
// if (x > max) {
// max = x;
// }
// if (x < min) {
// min = x;
// }
// }
// System.out.println("最大值为：" + max + "\n最小值为：" + min);
// sc.close();
// }
// }
