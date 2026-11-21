// 修正版：本文件是 [P5_Frequency.java](P5_Frequency.java) 的最小修复版本。
// 原始版有两处语法错误，无法通过编译：
// 1. 读入循环写成 for (int i =   i < n; i++) —— 初始化 int i = 0; 中丢了 "0;"，
//    Java 会在这一行报错，无法判断循环从哪里开始。
// 2. 输出循环写成 f  (int i = 0; i < 101; i++) —— 关键字 for 误写成了 f。
// 本版只修复这两处拼写问题，类名按 Java 规则改为 P5_FrequencyFixed
// （public 类名必须与文件名一致），其余逻辑与原始版完全相同。
import java.util.Scanner;

public class P5_FrequencyFixed {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] cnt = new int[101];

        for (int i = 0; i < n; i++) {
           int x = sc.nextInt();

            cnt[x]++;
        }

        for (int i = 0; i < 101; i++) {

           if (cnt[i] > 0) {
                System.out.println(i + " " + cnt[i]);
            }
        }
    }
}
