import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个年份（正整数）：");
        if (!sc.hasNextInt()) {
            System.out.println("输入错误：请输入一个整数年份，例如 2024");
        } else {
            int year = sc.nextInt();
            if (year <= 0) {
                System.out.println("输入错误：年份必须大于 0");
            } else {
                boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                if (isLeap) {
                    System.out.println(year + "年 是闰年");
                } else {
                    System.out.println(year + "年 是平年");
                }
                int febDays = isLeap ? 29 : 28;
                System.out.println(year + "年 2 月有 " + febDays + " 天");
            }
        }
        sc.close();
    }
}
