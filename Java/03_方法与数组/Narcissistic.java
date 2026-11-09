import java.util.Scanner;

public class Narcissistic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        boolean found = false;
        int start = Math.max(a, 100);
        int end = Math.min(b, 999);
        for (int num = start; num <= end; num++) {
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
// // 导入 Scanner：Java 自带的"键盘输入工具"，用它才能读取键盘输入。
// // 它不在默认范围内，必须先 import，否则 Java 不认识 Scanner 这个名字。
// import java.util.Scanner;

// /**
// * Narcissistic —— 在给定范围 [a, b] 内，找出所有的"水仙花数"
// *
// * 【什么是水仙花数】
// * 一个【三位数】，如果它【每一位数字的 3 次方之和】正好等于它自己，就叫水仙花数。
// * 例如 153：1 的 3 次方 + 5 的 3 次方 + 3 的 3 次方 = 1 + 125 + 27 = 153，所以 153 是水仙花数。
// * 三位数里一共只有 4 个：153、370、371、407。
// *
// * 【输入】一行里的两个整数 a 和 b（用空格隔开），表示范围 a 到 b。
// * 【输出】范围内的每个水仙花数，每个占一行，从小到大；如果一个都没有，输出 NONE。
// *
// * 【关于"为什么没有输入提示语"】
// * 这是评测题风格的程序：评测系统会把程序的输出和标准答案逐字对比，
// * 多打印任何一个字（比如"请输入……"）都会判错。所以这里不打印提示语，也不做"重新输入"。
// *
// * 【本次修复的问题】
// * 原代码默认"范围内的数都是三位数"，如果 a、b 超出了 100~999，会得到错误结果：
// * · 范围里有 1 或 0 时：1 的 3 次方等于 1，原程序会把 1（和 0）也当作水仙花数输出
// * （但它们不是三位数）。
// * · 范围里有四位数时：比如 1000，原来算"百位"用的是 num / 100，得到 10（这不是一位数字了），
// * 10 的 3 次方正好等于 1000，于是 1000 被错误地输出（1001 同理）。
// * · b 取到 int 的最大值（2147483647）时：循环变量 num 加到最大值后再 +1 会"溢出"变成负数，
// * 条件 num <= b 永远成立，程序变成死循环，永远停不下来。
// * 修复方法：只检查三位数，把范围收缩到 100~999 之内（见下面的 start 和 end）。
// * 这样既不会输出错误的数，也不会溢出死循环。
// *
// * 【说明】这里按"水仙花数就是三位数"的标准定义来处理。如果你的题目另有规定
// * （比如包含其他位数的自幂数），告诉我题目原文，我再按题意调整。
// */
// public class Narcissistic {

// // main 是程序的入口，运行程序时从这里开始一行一行往下执行
// public static void main(String[] args) {

// // 创建 Scanner 对象 sc，负责读取键盘输入（System.in 表示键盘）
// Scanner sc = new Scanner(System.in);

// // nextInt() 会读取下一个整数。输入 "100 999" 时，
// // 第一次调用读到 100（存进 a），第二次调用读到 999（存进 b）：空格、换行都会被自动跳过。
// int a = sc.nextInt();
// int b = sc.nextInt();

// // found 是一个"标记"（旗帜），记录"到目前为止有没有找到过水仙花数"。
// // 一开始还没找，所以是 false。只要找到一个，就把它改成 true。
// boolean found = false;

// // 【修复点】把检查范围收缩到三位数 100~999 之内。
// // Math.max(a, 100)：取 a 和 100 中较大的那个。
// // 如果 a 比 100 小（比如 1），起点就按 100 算；如果 a 本来就 >= 100，起点还是 a。
// // Math.min(b, 999)：取 b 和 999 中较小的那个。
// // 如果 b 比 999 大，终点就按 999 算；如果 b 本来就 <= 999，终点还是 b。
// // Math 是 Java 自带的数学工具，max 取较大值，min 取较小值。不用 import，可以直接用。
// // 如果 a > b（范围本身是空的），start 会大于 end，下面的循环一次都不执行，最后输出 NONE。
// int start = Math.max(a, 100);
// int end = Math.min(b, 999);

// // for 循环：num 从 start 开始，一直检查到 end，每次 +1，依次检查范围内的每个数。
// // 因为 end 最大只会是 999，所以 num++ 永远不会溢出，不会死循环。
// for (int num = start; num <= end; num++) {

// // ---------- 拆出三位数的每一位 ----------
// // 以 num = 153 为例：
// // % 是"取余数"：num % 10 是 num 除以 10 的余数，也就是【个位】。
// // 153 % 10 = 3
// // / 是"整数除法"：两个整数相除，结果只保留整数部分，小数直接丢掉。
// // num / 10 = 153 / 10 = 15（丢掉了个位），再 % 10 取余数，就是【十位】。
// // 15 % 10 = 5
// // num / 100 = 153 / 100 = 1（丢掉了后两位），就是【百位】。
// // 注意：num / 10 % 10 的运算顺序是从左到右：先算 num / 10，再对结果 % 10。
// int ones = num % 10; // 个位：3
// int tens = num / 10 % 10; // 十位：5
// int hundreds = num / 100; // 百位：1（因为 num 一定是三位数，所以百位正好是一位数字）

// // 每一位数字的 3 次方，再相加。
// // Java 里没有"次方"的运算符号，3 次方就写成"自己乘自己再乘自己"。
// // 153：3*3*3 + 5*5*5 + 1*1*1 = 27 + 125 + 1 = 153
// int sum = ones * ones * ones + tens * tens * tens + hundreds * hundreds *
// hundreds;

// // 如果三个数字的立方和正好等于这个数本身，它就是水仙花数
// // 注意：判断"相等"要用两个等号 ==，一个等号 = 是"赋值"，不要写混。
// if (sum == num) {
// System.out.println(num); // 找到了，单独占一行输出
// found = true; // 记录："已经找到过了"
// }
// }

// // 整个范围检查完了，如果 found 仍然是 false，说明一个都没找到，按要求输出 NONE。
// // ! 表示"取反"：!found 读作"没有找到过"。
// if (!found) {
// System.out.println("NONE");
// }

// // 用完 Scanner 后关闭它，释放资源。这是个好习惯。
// sc.close();
// }
// }
