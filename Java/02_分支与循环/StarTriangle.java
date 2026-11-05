import java.util.Scanner;

public class StarTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            System.out.println("请输入三角形的行数（正整数）：");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= 1) {
                    break;
                }
                System.out.println("行数必须大于等于1，请重新输入！");
            } else {
                System.out.println("你输入的不是整数，请重新输入！");
                sc.next();
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        sc.close();
    }
}
// // 导入键盘输入工具 Scanner，用来读取用户在键盘上输入的行数
// import java.util.Scanner;

// /**
// * StarTriangle —— 根据输入的行数，打印星号（*）组成的直角三角形
// *
// * 例如输入 4，输出：
// * *
// * **
// * ***
// * ****
// *
// * 规律：第 1 行打印 1 个星号，第 2 行打印 2 个，第 3 行打印 3 个……第 i 行打印 i 个。
// * 这就需要"双层循环"（循环里再套一个循环）：
// * 外层循环：控制"打印第几行"
// * 内层循环：控制"这一行打印几个星号"
// *
// * 【本次修复的问题】
// * 1. 输入 0 或负数时，原程序什么都不打印就结束了，用户不知道发生了什么。
// * 现在要求行数必须 >= 1，否则提示重新输入。
// * 2. 输入的不是整数（比如字母）时，原代码会直接崩溃（InputMismatchException）。
// * 现在先用 hasNextInt() 检查，不是整数就提示重新输入。
// * （双层循环本身的逻辑是正确的，没有改动。）
// */
// public class StarTriangle {

// // main 是程序入口，运行程序时从这里开始一行一行往下执行
// public static void main(String[] args) {

// // 创建 Scanner 对象 sc，负责读取键盘输入
// Scanner sc = new Scanner(System.in);

// // -------------------- 第一步：读取并检查行数 n --------------------
// int n; // 声明变量 n，保存"要打印几行"

// // while (true) 是故意写的"无限循环"，只有拿到合法的 n 才用 break 退出
// while (true) {
// System.out.println("请输入三角形的行数（正整数）：");

// // hasNextInt()：先"看一眼"输入的内容能不能当成整数（不会报错）
// if (sc.hasNextInt()) {
// n = sc.nextInt(); // 确认是整数后再真正读取
// if (n >= 1) {
// break; // 合法，跳出循环
// }
// System.out.println("行数必须大于等于1，请重新输入！");
// } else {
// System.out.println("你输入的不是整数，请重新输入！");
// // 把用户输入的错误内容（如 abc）读出来丢掉，
// // 否则它会一直留在输入缓冲区里，导致下一轮还是读到它，死循环提示。
// sc.next();
// }
// }

// // -------------------- 第二步：双层循环打印三角形 --------------------

// // 外层循环：i 表示"当前是第几行"，从第 1 行到第 n 行，每次循环处理一整行。
// for (int i = 1; i <= n; i++) {

// // 内层循环：j 表示"这一行已经打印到第几个星号"。
// // j 从 1 数到 i，所以第 i 行正好打印 i 个星号。
// //
// // 以 n = 3 为例，一步步看是怎么执行的：
// // i = 1：j 从 1 到 1 → 打印 1 个 * → 本行是 *
// // i = 2：j 从 1 到 2 → 打印 2 个 * → 本行是 **
// // i = 3：j 从 1 到 3 → 打印 3 个 * → 本行是 ***
// for (int j = 1; j <= i; j++) {
// // 用 print（不换行）：这样同一行的多个星号才能连在一起。
// // 如果这里用 println，每个星号都会单独占一行。
// System.out.print("*");
// }

// // 内层循环结束 = 这一行的星号打印完了。
// // 用空的 println() 换行，下一行的星号才会从新的一行开始。
// // 注意：它写在内层循环的【外面】、外层循环的【里面】，
// // 所以是"每打印完一整行才换一次行"。
// System.out.println();
// }

// // 用完后关闭 Scanner
// sc.close();
// }
// }
