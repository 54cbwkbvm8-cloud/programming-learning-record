public class LoopBasics {
    public static void main(String[] args) {
        System.out.println("【for】打印1～5的数字：");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("【while】打印1～5的数字：");
        int j = 1;
        int sum = 0;
        while (j <= 5) {
            System.out.print(j + " ");
            sum += j;
            j++;
        }
        System.out.println();
        System.out.println("1~5的和为：" + sum);
        System.out.println();
        System.out.println("【while 与 do-while 的区别】当条件一开始就不成立时：");
        int k = 10;
        while (k < 5) {
            System.out.println("while执行了， k = " + k);
        }
        System.out.println("while 一次都没有执行（因为 k = " + k + "，不满足 k < 5）");
        do {
            System.out.println("do-while执行了，k = " + k);
            k++;
        } while (k < 5);
        System.out.println("do-while 先执行了一次，之后 k 变成 " + k + "，不满足 k < 5，循环结束");
        System.out.println();
        System.out.println("【break】遇到4就停止运行：");
        for (int i = 1; i <= 10; i++) {
            if (i == 4) {
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
        System.out.println("【continue】只打印奇数：");
        for (int i = 1; i <= 6; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
// /**
// * LoopBasics —— 循环语句基础演示
// *
// * 这个程序依次演示了 Java 里最常用的几种循环写法：
// * 1. for 循环 ：知道要循环"几次"时最常用
// * 2. while 循环 ：只知道"循环到什么时候停"时用
// * 3. do-while 循环 ：和 while 很像，但"先做一次，再判断"
// * 4. break ：直接跳出整个循环
// * 5. continue ：跳过"这一轮"，直接进入下一轮
// *
// * 【本次修复的问题】
// * 原代码在循环里用的是 System.out.println(i + " ")。
// * println 的意思是"打印完之后换行"，所以每个数字都单独占了一行，
// * 后面那个空格 " " 白写了（换行之后空格没有任何作用）。
// * 本意是让数字排成一排（1 2 3 4 5），所以应该改成 System.out.print(i + " ")，
// * print 的意思是"打印完之后不换行"。
// * 另外，原代码里演示 while / do-while 区别的那一段没有任何文字说明，
// * 运行结果里看不出"while 为什么什么都没打印"，这里补上了说明文字。
// */
// public class LoopBasics {

// // main 是程序的入口：运行程序时，Java 会从这里的第一行开始，一行一行往下执行。
// public static void main(String[] args) {

// // ==================== 1. for 循环 ====================
// System.out.println("【for】打印1～5的数字：");

// // for 的括号里有三部分，用分号隔开：
// // ① int i = 1 ：初始化。循环开始前只执行一次，创建计数变量 i，初始值是 1
// // ② i <= 5 ：循环条件。每一轮开始前都检查一次，成立（true）就执行循环体，不成立就结束
// // ③ i++ ：每一轮结束后执行。i++ 就是 i = i + 1，让 i 增加 1
// // 执行顺序：① → ② → 循环体 → ③ → ② → 循环体 → ③ → ② ... 直到 ② 不成立
// for (int i = 1; i <= 5; i++) {
// // 【修复点】这里用 print 而不是 println：
// // print 打印后【不换行】，所以 1 2 3 4 5 会排在同一行
// // println 打印后【换行】
// // 数字 i 后面拼接一个空格 " "，是为了让数字之间有间隔，不会挤成 12345
// System.out.print(i + " ");
// }
// // 上面的循环结束后，光标还停在"5 "后面。这里用一个空的 println() 来换行，
// // 这样后面的内容就会从新的一行开始。
// System.out.println();
// // 再多打印一个空行，只是为了让输出结果更好看，把不同的演示分隔开
// System.out.println();

// // ==================== 2. while 循环 ====================
// System.out.println("【while】打印1～5的数字：");

// // while 循环的"零件"要自己分开写：
// // - 计数变量要在循环【外面】先定义好（int j = 1;）
// // - 括号里只写循环条件
// // - 让变量变化的语句（j++）要自己写在循环体【里面】
// int j = 1; // 计数变量，从 1 开始
// int sum = 0; // 用来累加的变量，一开始是 0（还没加任何数）

// // 只要 j <= 5 成立，就一直执行花括号里的内容
// while (j <= 5) {
// System.out.print(j + " "); // 同样改成 print，让数字排成一排
// sum += j; // sum += j 等价于 sum = sum + j，意思是"把 j 加到 sum 里"
// j++; // 【非常重要】让 j 增加 1。
// // 如果忘了写这一行，j 永远是 1，条件永远成立，
// // 程序就会陷入"死循环"，永远停不下来！
// }
// System.out.println(); // 换行
// // 字符串和数字用 + 连在一起，Java 会自动把数字变成文字再拼接
// System.out.println("1~5的和为：" + sum); // 1+2+3+4+5 = 15
// System.out.println();

// // ==================== 3. while 和 do-while 的区别 ====================
// System.out.println("【while 与 do-while 的区别】当条件一开始就不成立时：");

// int k = 10; // 注意：k 从 10 开始，所以 k < 5 一开始就是"假"（false）

// // while：先判断条件，再决定要不要执行。
// // 一开始 k < 5 就不成立，所以花括号里的内容【一次都不会执行】。
// while (k < 5) {
// System.out.println("while执行了， k = " + k); // 这行永远不会被打印
// }
// // 补充的说明文字：让运行结果里能看出 while 确实"什么都没做"
// System.out.println("while 一次都没有执行（因为 k = " + k + "，不满足 k < 5）");

// // do-while：先执行一次，再判断条件。
// // 所以哪怕条件一开始就不成立，花括号里的内容也【至少会执行一次】。
// do {
// System.out.println("do-while执行了，k = " + k); // 这行会打印一次
// k++; // k 变成 11
// } while (k < 5); // 注意：do-while 的末尾要写分号 ;（这是初学者最容易漏的）
// // 执行完一次后判断 k < 5：此时 k 是 11，不成立，循环结束
// System.out.println("do-while 先执行了一次，之后 k 变成 " + k + "，不满足 k < 5，循环结束");
// System.out.println();

// // ==================== 4. break：跳出整个循环 ====================
// System.out.println("【break】遇到4就停止运行：");

// // 这个循环本来想从 1 数到 10，但中途遇到 break 就会立刻结束整个循环
// // 说明：这里又用了变量名 i，不会和上面 for 里的 i 冲突，
// // 因为 for 里定义的 i 只在那个 for 的花括号范围内有效，循环结束就"消失"了
// for (int i = 1; i <= 10; i++) {
// if (i == 4) { // 注意：判断"相等"要用两个等号 ==，一个等号 = 是"赋值"
// break; // 当 i 等于 4 时，立刻跳出整个 for 循环，后面的 5~10 都不会再执行
// }
// System.out.print(i + " "); // 所以只会打印 1 2 3
// }
// System.out.println();
// System.out.println();

// // ==================== 5. continue：跳过本轮 ====================
// System.out.println("【continue】只打印奇数：");

// for (int i = 1; i <= 6; i++) {
// // i % 2 是"取余数"：i 除以 2 的余数。
// // 偶数除以 2 余数是 0（比如 4 % 2 = 0），奇数除以 2 余数是 1（比如 3 % 2 = 1）
// if (i % 2 == 0) {
// continue; // 是偶数就跳过【本轮】剩下的代码（不打印），直接进入下一轮（i++ 然后继续判断）
// }
// System.out.print(i + " "); // 只有奇数能执行到这里：打印 1 3 5
// }
// System.out.println();

// // break 和 continue 的区别（容易混淆，建议记住）：
// // break ：整个循环直接结束，像"下班了，不干了"
// // continue ：只跳过这一轮，下一轮继续，像"这个活跳过，干下一个"
// }
// }
