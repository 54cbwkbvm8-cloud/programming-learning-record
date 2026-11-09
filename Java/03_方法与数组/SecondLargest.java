import java.util.Scanner;

public class SecondLargest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 1) {
            System.out.println("NO");
            sc.close();
            return;
        }
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        boolean found = false;
        int second = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] < max) {
                if (!found || a[i] > second) {
                    second = a[i];
                    found = true;
                }
            }
        }
        if (found) {
            System.out.println(second);
        } else {
            System.out.println("NO");
        }
        sc.close();
    }
}
// // 导入 Scanner：Java 自带的"键盘输入工具"，用它才能读取键盘输入。
// import java.util.Scanner;

// /**
// * SecondLargest —— 在 n 个整数中，找出"第二大的数"
// *
// * 【这里"第二大"的含义】
// * 指的是：所有【比最大值小】的数里面，最大的那一个。重复的数不会被算成"第二大"。
// * 例如： 3 9 4 9 7 → 最大值是 9；比 9 小的数有 3、4、7，其中最大的是 7，所以答案是 7。
// * 5 5 5 → 所有数都等于最大值，没有比它更小的数，所以没有"第二大"，输出 NO。
// * 8（只有一个数）→ 同样没有第二大，输出 NO。
// *
// * 【输入】第一个整数 n，表示接下来有 n 个数；然后是 n 个整数。
// * 【输出】第二大的数；如果不存在，输出 NO。
// *
// * 【关于"为什么没有输入提示语"】
// * 这是评测题风格的程序：评测系统会把程序的输出和标准答案逐字对比，
// * 多打印任何一个字（比如"请输入……"）都会判错。所以这里不打印提示语。
// *
// * 【本次修复的问题】（这份代码有两处真正的逻辑错误）
// * 1. 读数据的循环写成了 for (int i = 1; i < n; i++)，从下标 1 开始读：
// * 数组的下标是从 0 开始的，所以第一个数被漏掉了：a[0] 从来没有被赋值，一直是默认值 0；
// * 同时只读了 n - 1 个数，最后一个数还留在输入里没被读走。
// * 数据全都"错位"了，后面的所有计算都基于错误的数据。
// * 修复：改成 for (int i = 0; i < n; i++)，从下标 0 开始，正好读满 n 个数。
// * 2. 最后输出的 if / else 写反了：
// * 原代码写的是 if (!found) 输出 second，else 输出 NO。
// * 也就是"没找到"时输出 second（默认值 0），"找到了"时反而输出 NO，完全相反。
// * 修复：改成 if (found) 输出 second，否则输出 NO。
// * 3. （补充的保护）如果 n 是 0 或负数，原代码会在 new int[n] 或 a[0] 处直接崩溃。
// * 现在这种情况直接输出 NO（没有数当然没有第二大）。
// */
// public class SecondLargest {

// // main 是程序的入口，运行程序时从这里开始一行一行往下执行
// public static void main(String[] args) {

// // 创建 Scanner 对象 sc，负责读取键盘输入
// Scanner sc = new Scanner(System.in);

// int n = sc.nextInt(); // 读取数字的个数 n

// // 【补充的保护】n 小于 1，说明一个数都没有，不可能有第二大。
// // 下面的 new int[n] 在 n 为负数时会崩溃，a[0] 在 n 为 0 时也会崩溃，所以要提前处理。
// // return 的作用是"立刻结束 main 方法"，也就是结束整个程序，所以后面的代码不会再执行。
// // 因为要提前结束，所以结束前要先自己把 Scanner 关掉。
// if (n < 1) {
// System.out.println("NO");
// sc.close();
// return;
// }

// // 创建一个能存放 n 个整数的数组 a。
// // 数组 = 一排连续的、同类型的"格子"，可以用 下标 来访问其中的某一格。
// // 【下标从 0 开始】：n 个格子的下标分别是 0, 1, 2, ..., n-1，没有下标 n！
// int[] a = new int[n];

// // ---------- 第一步：把 n 个数依次读进数组 ----------
// // 【修复点】原代码这里写的是 int i = 1，漏掉了下标 0 的格子。
// // 下标要从 0 开始，一直到 n - 1，所以条件是 i < n（不能写 <=，否则 a[n] 越界）。
// for (int i = 0; i < n; i++) {
// a[i] = sc.nextInt(); // 读取一个整数，放进第 i 个格子
// }

// // ---------- 第二步：找出最大值 max ----------
// // 经典的"打擂台"：先假设第一个数是最大的（擂主），
// // 然后让后面的数一个个上台比较，谁大谁当新擂主。
// int max = a[0];
// for (int i = 1; i < n; i++) { // 第一个数已经当擂主了，所以从下标 1 开始比较是对的
// if (a[i] > max) {
// max = a[i]; // 发现更大的，换擂主
// }
// }

// // ---------- 第三步：在"所有比 max 小的数"里，找出最大的那个 ----------
// // found 是"标记"：记录"有没有找到过比 max 小的数"。一开始还没找，所以是 false。
// // second 用来保存目前找到的"第二大的数"。
// //
// // 为什么需要 found，而不是直接判断 second 是不是 0？
// // 因为数据里可能有 0 或者负数，0 也可能是真正的答案（比如 5 和 0，答案是 0），
// // 没法用"second 等于 0"来判断到底有没有找到。所以单独用 found 来记录。
// boolean found = false;
// int second = 0;

// for (int i = 0; i < n; i++) {
// // 只考虑【比最大值小】的数。等于最大值的数（包括重复的最大值）直接跳过。
// if (a[i] < max) {
// // 什么时候要更新 second：
// // · !found：之前还一个都没找到过，那么这个数就是目前能找到的最大的，直接记下来；
// // · a[i] > second：之前找到过，但这个数比之前记下的还要大，就更新。
// // || 表示"或者"，两个条件满足任意一个就执行。
// if (!found || a[i] > second) {
// second = a[i];
// found = true; // 记录："已经找到过了"
// }
// }
// }

// // ---------- 第四步：输出结果 ----------
// // 【修复点】原代码这里写的是 if (!found)，输出的东西和情况正好相反。
// // 正确的逻辑：找到了（found 为 true）→ 输出 second；没找到 → 输出 NO。
// if (found) {
// System.out.println(second);
// } else {
// System.out.println("NO");
// }

// // 用完 Scanner 后关闭它，释放资源。这是个好习惯。
// sc.close();
// }
// }
