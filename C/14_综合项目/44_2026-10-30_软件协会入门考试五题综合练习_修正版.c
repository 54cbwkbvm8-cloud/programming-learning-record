/*第一题
#include <stdio.h>
int main(void) {
    int i;
    int count = 0;
    for (i = 100; i > 0; i--) {
        if (i % 4 == 0) {
            printf("%d\n", i);
            count++;
        }
    }
    printf("一共有%d个数\n", count);
    return 0;
}
*/
/*第二题
#include <stdio.h>
int prices[4] = {4, 9, 6, 13};
int main(void) {
    int i;
    int j;
    int len;
    int money = 19;
    len = sizeof(prices) / sizeof(prices[0]);
    for (i = 0; i < len; i++) {
        for (j = i + 1; j < len; j++) {
            if (prices[i] + prices[j] == money) {
                printf("[%d,%d]\n", i, j);
            }
        }
    }
    return 0;
}
*/
/*第三题
#include <stdio.h>
int num = 100;
int num1;
int num2;
int num3;
int main(void) {
    for (num = 100; num <= 999; num++) {
        num1 = num / 100;
        num2 = num % 100 / 10;
        num3 = num % 100 % 10;
        if ((num1 + num2 + num3) * (num1 * num2 * num3) == num) {
            printf("%d是和积数\n", num);
        }
    }
    return 0;
}
*/
/*第四题
#include <stdio.h>
int a;
int b;
int c;
int is_sum_product(int n) {
    a = n / 100;
    b = n % 100 / 10;
    c = n % 100 % 10;
    if ((a + b + c) * (a * b * c) == n) {
        return 1;
    } else {
        return 0;
    }
}
int main(void) {
    int num;
    for (num = 100; num < 1000; num++) {
        if (is_sum_product(num)) {
            printf("%d\n", num);
        }
    }
    return 0;
}
*/
/*第五题
#include <stdbool.h>
#include <stdio.h>
int first_win_num;
bool first_win(int n, int m) {
    first_win_num = n % (m + 1);
    if (first_win_num == 0) {
        return 0;
    } else {
        return 1;
    }
}
int main(void) {
    int n, m;
    scanf("%d %d", &n, &m);
    printf(first_win(n, m) ? "true\n" : "false\n");
    return 0;
}

*/


//以下为ai订正优化改正后的版本




/*
 * 2026年10月30日 C语言练习：五道题（详细中文注释版）
 *
 * 重要说明：
 * 这个文件包含五道题。每道题都需要一个 main 函数，而一个 C 程序只能有一个
 * main 函数。因此，本文件使用“条件编译”来选择要运行的题目。
 *
 * 在 VS Code / 终端中编译时，选择其中一道题：
 *   第一题：gcc 10yue30ruanxiebianshi_detailed.c -o q1 -DRUN_Q1
 *   第二题：gcc 10yue30ruanxiebianshi_detailed.c -o q2 -DRUN_Q2
 *   第三题：gcc 10yue30ruanxiebianshi_detailed.c -o q3 -DRUN_Q3
 *   第四题：gcc 10yue30ruanxiebianshi_detailed.c -o q4 -DRUN_Q4
 *   第五题：gcc 10yue30ruanxiebianshi_detailed.c -o q5 -DRUN_Q5
 *
 * 例如，编译第一题后运行：
 *   ./q1
 *
 * 注意：每次只选择一道题，不要同时定义多个 RUN_Q 宏。
 */

/* ============================ 第一题：输出100以内4的倍数 ============================ */

#if defined(RUN_Q1)

#include <stdio.h> // 引入标准输入输出库，printf 函数在这个库中声明。

int main(void) {
    int i;         // 循环变量：记录当前正在检查的整数。
    int count = 0; // 计数器：记录一共找到了多少个符合条件的数字。
                   // 初始化为0，表示开始时还没有找到任何数字。

    /*
     * for 循环的三个部分：
     * 1. i = 100：循环开始前执行一次，让 i 从100开始。
     * 2. i > 0：每次准备执行循环体前检查条件；条件为真才继续。
     * 3. i--：每次循环体执行结束后，让 i 减少1。
     *
     * 所以，这个循环会依次检查100、99、98……2、1。
     */
    for (i = 100; i > 0; i--) {

        /*
         * % 是“取余”运算符，表示求除法之后剩下的余数。
         * 例如：12 % 4 的结果是0，因为12能够被4整除。
         *
         * 如果 i % 4 == 0，说明 i 除以4没有余数，
         * 也就是 i 是4的倍数。
         */
        if (i % 4 == 0) {
            printf("%d\n", i); // %d 用来输出 int 整数；\n 表示输出后换行。
            count++;           // count++ 等价于 count = count + 1。
                               // 每找到一个4的倍数，数量就增加1。
        }
    }

    // 循环结束后，count 中保存着符合条件的数字总数。
    printf("一共有%d个数\n", count);

    return 0; // 向操作系统返回0，通常表示程序正常结束。
}

/* ============================ 第二题：两数之和（暴力枚举） ============================ */

#elif defined(RUN_Q2)

#include <stdio.h> // 提供 printf 函数。

int main(void) {
    /*
     * 数组可以理解为一排连续的储物格。
     * 每个格子存放一个整数，每个格子都有自己的下标。
     *
     * prices[0] 是4，prices[1] 是9，
     * prices[2] 是6，prices[3] 是13。
     *
     * C语言数组下标从0开始，而不是从1开始。
     */
    int prices[4] = {4, 9, 6, 13};

    int money = 19; // 目标值：希望找到两个不同位置的数，使它们相加等于19。

    /*
     * sizeof(prices) 得到整个数组占用的字节数。
     * sizeof(prices[0]) 得到一个数组元素占用的字节数。
     * 两者相除，就能得到数组中元素的个数。
     *
     * 例如，如果一个 int 占4字节，那么：
     * sizeof(prices) = 16，sizeof(prices[0]) = 4，
     * len = 16 / 4 = 4。
     *
     * sizeof 的结果类型是 size_t，因此 len 也使用 size_t。
     */
    size_t len = sizeof(prices) / sizeof(prices[0]);

    int found = 0; // 标记变量：0表示还没有找到；1表示至少找到了一组。

    /*
     * 外层循环让 i 依次指向数组中的每个元素。
     * i < len 保证不会访问数组范围之外的位置。
     */
    for (size_t i = 0; i < len; i++) {

        /*
         * 内层循环从 i + 1 开始，而不是从0开始。
         *
         * 这样做有两个目的：
         * 1. 不让一个元素和它自己相加，例如 prices[0] + prices[0]。
         * 2. 不重复检查同一组下标，例如 [0,1] 和 [1,0]。
         */
        for (size_t j = i + 1; j < len; j++) {

            /*
             * 先把第一个数转换为 long long，再进行加法。
             * 这样可以降低两个 int 相加时发生 int 范围溢出的风险。
             * money 也转换为 long long，以便比较时类型一致。
             */
            if ((long long)prices[i] + prices[j] == (long long)money) {

                // 找到一组符合条件的下标，输出的是下标，不是数组中的数值。
                printf("[%zu,%zu]\n", i, j);

                found = 1; // 记录已经找到答案。
            }
        }
    }

    /*
     * 如果所有组合都检查完了，found 仍然是0，
     * 就说明没有任何两个不同位置的数相加等于目标值。
     */
    if (found == 0) {
        printf("没有找到符合条件的组合。\n");
    }

    return 0; // 程序正常结束。
}

/* ============================ 第三题：直接寻找三位和积数 ============================ */

#elif defined(RUN_Q3)

#include <stdio.h> // 提供 printf 函数。

int main(void) {
    int num;  // 当前正在检查的三位数。
    int num1; // 当前数字的百位数字。
    int num2; // 当前数字的十位数字。
    int num3; // 当前数字的个位数字。

    /*
     * 三位数的范围是100到999。
     * i <= 999 表示999也会被检查。
     */
    for (num = 100; num <= 999; num++) {

        /*
         * 提取百位：
         * 整数除法会舍去小数部分。
         * 例如123 / 100 的结果是1。
         */
        num1 = num / 100;

        /*
         * 提取十位：
         * num % 100 先去掉百位，123 % 100 得到23；
         * 再除以10，23 / 10 得到2。
         */
        num2 = num % 100 / 10;

        /*
         * 提取个位：
         * num % 10 可以直接得到个位。
         * 例如123 % 10 的结果是3。
         *
         * 原代码使用 num % 100 % 10，结果也正确；
         * 这里改成 num % 10，表达更直接。
         */
        num3 = num % 10;

        /*
         * 和积数的判断规则：
         * （百位 + 十位 + 个位）×（百位 × 十位 × 个位）= 原数字。
         *
         * 例如123：
         * 数字和 = 1 + 2 + 3 = 6；
         * 数字积 = 1 × 2 × 3 = 6；
         * 和积 = 6 × 6 = 36，不等于123，所以123不是和积数。
         */
        if ((num1 + num2 + num3) * (num1 * num2 * num3) == num) {
            printf("%d是和积数\n", num);
        }
    }

    return 0; // 程序正常结束。
}

/* ============================ 第四题：使用函数判断和积数 ============================ */

#elif defined(RUN_Q4)

#include <stdio.h> // 提供 printf 函数。

/*
 * 函数名称：is_sum_product
 * 参数 n：传入一个整数，表示需要判断的数字。
 * 返回值：
 *   1（真）：n 是符合规则的三位和积数；
 *   0（假）：n 不是符合规则的三位和积数。
 *
 * 把判断过程写进独立函数的好处：
 * 主函数不必重复写数字拆分和判断过程；
 * 以后其他代码也可以调用这个函数。
 */
int is_sum_product(int n) {

    /*
     * 先检查参数是否为三位数。
     * 如果不是100到999之间的整数，就不符合本题的输入范围。
     *
     * 提前 return 0 表示立即结束函数，并告诉调用者“不是”。
     */
    if (n < 100 || n > 999) {
        return 0;
    }

    /*
     * 这三个变量只在 is_sum_product 函数内部使用，
     * 因此把它们定义在函数内部，称为局部变量。
     *
     * 局部变量比不必要的全局变量更安全：
     * 函数外部的代码不能直接通过变量名修改它们，
     * 不同函数也不会意外共用同一份数据。
     */
    int a = n / 100;     // 提取百位。
    int b = n / 10 % 10; // 提取十位。
    int c = n % 10;      // 提取个位。

    /*
     * 比较表达式的结果本身就是一个真假值：
     * 条件成立时结果为真，C语言中真通常表示为1；
     * 条件不成立时结果为假，通常表示为0。
     *
     * 因此可以直接 return，不必再写 if/else。
     */
    return (a + b + c) * (a * b * c) == n;
}

int main(void) {
    int num; // 保存当前要检查的三位数。

    // 依次检查所有三位数。
    for (num = 100; num <= 999; num++) {

        /*
         * 把 num 传给 is_sum_product 函数。
         * 函数返回1时，if 条件成立，就输出这个数字。
         */
        if (is_sum_product(num)) {
            printf("%d\n", num);
        }
    }

    return 0; // 程序正常结束。
}

/* ============================ 第五题：取石子博弈 ============================ */

#elif defined(RUN_Q5)

#include <limits.h>  // 提供 LLONG_MAX 等 long long 类型的边界常量。
#include <stdbool.h> // 提供 bool、true 和 false。
#include <stdio.h>   // 提供 scanf 和 printf 函数。

/*
 * 函数名称：first_win
 * 参数 n：石子的总数量。
 * 参数 m：每次最多可以取走的石子数量。
 *
 * 本题采用的数学结论：
 * 当 n % (m + 1) 不等于0时，先手有必胜策略；
 * 当 n % (m + 1) 等于0时，先手处于必败局面。
 *
 * 返回值：
 * true  表示先手获胜；
 * false 表示先手不能获胜。
 *
 * 这里使用 long long，而不是 int：
 * long long 能表示的整数范围通常比 int 更大，
 * 可以处理更大的输入数值。
 */
bool first_win(long long n, long long m) {

    /*
     * 本程序按照“石子数量和每次最多取石子数量都必须为正整数”
     * 这一输入约定处理。
     * 非法参数直接返回 false；main 函数也会在调用前提示输入错误。
     */
    if (n <= 0 || m <= 0) {
        return false;
    }

    /*
     * 特殊边界：m 等于 long long 能表示的最大值时，
     * 直接计算 m + 1 会超出 long long 的表示范围。
     *
     * 但 n 是一个正的 long long 数，因此 n <= LLONG_MAX = m。
     * 数学上 n % (m + 1) 就是 n，不可能等于0，
     * 所以先手获胜，直接返回 true。
     */
    if (m == LLONG_MAX) {
        return true;
    }

    /*
     * 经过前面的检查，m 是正数且不等于 LLONG_MAX，
     * 所以 m + 1 可以安全计算。
     *
     * % 是取余运算：
     * 余数不为0，表达式 != 0 就为 true；
     * 余数为0，表达式 != 0 就为 false。
     */
    return n % (m + 1) != 0;
}

int main(void) {
    long long n; // 保存石子的总数量。
    long long m; // 保存每次最多可以取走的石子数量。

    /*
     * scanf 用来读取键盘输入。
     * %lld 对应 long long 类型的整数。
     * &n 和 &m 中的 & 是取地址运算符：
     * 它告诉 scanf 应该把读取到的数据存放到哪个变量中。
     *
     * scanf 的返回值表示成功读取了几个数据项。
     * 我们要求读取两个整数，所以返回值必须等于2。
     */
    if (scanf("%lld %lld", &n, &m) != 2) {
        printf("输入错误：请输入两个整数。\n");
        return 1; // 返回非0值，表示程序因输入错误而结束。
    }

    // 检查题目要求的正整数条件，避免把非法数据交给判断函数。
    if (n <= 0 || m <= 0) {
        printf("输入错误：石子总数n和每次最多取数m都必须是正整数。\n");
        return 1;
    }

    /*
     * 调用 first_win 函数：
     * 如果返回 true，就输出 true；
     * 如果返回 false，就输出 false。
     *
     * 三元运算符的格式是：
     * 条件 ? 条件成立时的结果 : 条件不成立时的结果。
     */
    printf(first_win(n, m) ? "true\n" : "false\n");

    return 0; // 程序正常结束。
}

#else

/*
 * 如果编译时没有选择 RUN_Q1 到 RUN_Q5 中的任何一个，
 * 或者选择宏写错了，就会进入这里。
 * #error 会让编译器给出明确错误提示。
 */
#error "请在编译时选择一道题：-DRUN_Q1、-DRUN_Q2、-DRUN_Q3、-DRUN_Q4 或 -DRUN_Q5"

#endif
