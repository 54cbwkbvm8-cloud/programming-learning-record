#include <stdio.h>  // 引入标准输入输出库，这样才能使用 printf 和 scanf。

void compare_two_numbers(void)  // 定义函数，完成第一题：输入两个数字并输出较大者。
{  // 函数体开始。
    double first_number;  // 声明第一个数字变量，double 类型可以保存整数和小数。
    double second_number;  // 声明第二个数字变量，double 类型可以保存整数和小数。
    double larger_number;  // 声明较大数字变量，用来保存比较后的结果。

    printf("请输入两个数字，中间用空格分开：\n");  // 输出提示文字，告诉用户怎样输入。
    scanf("%lf %lf", &first_number, &second_number);  // 按 double 类型读取两个数字，并存入两个变量。

    if (first_number > second_number)  // 判断第一个数字是否大于第二个数字。
    {  // if 代码块开始。
        larger_number = first_number;  // 条件成立时，把第一个数字保存到较大数字变量。
    }  // if 代码块结束。
    else  // 如果上面的条件不成立，就执行下面的代码。
    {  // else 代码块开始。
        larger_number = second_number;  // 把第二个数字保存到较大数字变量；两数相等时它也同样正确。
    }  // else 代码块结束。

    printf("较大的数字是：%.2f\n", larger_number);  // 输出较大数字，%.2f 表示保留两位小数。
}  // compare_two_numbers 函数结束。

void check_grade(void)  // 定义函数，完成第二题：输入一个成绩并输出等级。
{  // 函数体开始。
    double score;  // 声明成绩变量，double 类型允许输入 85.5 这样的小数成绩。

    printf("请输入一个成绩（0 到 100）：\n");  // 输出提示文字，说明合法的成绩范围。
    scanf("%lf", &score);  // 按 double 类型读取一个成绩，并存入 score 变量。

    if (score < 0.0 || score > 100.0)  // 如果成绩小于 0 或大于 100，说明输入不在有效范围。
    {  // 无效成绩代码块开始。
        printf("成绩必须在 0 到 100 之间。\n");  // 输出错误提示，不继续判断等级。
    }  // 无效成绩代码块结束。
    else if (score >= 90.0)  // 否则，如果成绩大于或等于 90 分。
    {  // A 等级代码块开始。
        printf("等级：A（优秀）\n");  // 输出 A 等级。
    }  // A 等级代码块结束。
    else if (score >= 80.0)  // 否则，如果成绩大于或等于 80 分；前面的条件已经排除了 90 分以上。
    {  // B 等级代码块开始。
        printf("等级：B（良好）\n");  // 输出 B 等级。
    }  // B 等级代码块结束。
    else if (score >= 70.0)  // 否则，如果成绩大于或等于 70 分；前面的条件已经排除了 80 分以上。
    {  // C 等级代码块开始。
        printf("等级：C（中等）\n");  // 输出 C 等级。
    }  // C 等级代码块结束。
    else if (score >= 60.0)  // 否则，如果成绩大于或等于 60 分；前面的条件已经排除了 70 分以上。
    {  // D 等级代码块开始。
        printf("等级：D（及格）\n");  // 输出 D 等级。
    }  // D 等级代码块结束。
    else  // 前面所有条件都不成立，也就是成绩低于 60 分。
    {  // E 等级代码块开始。
        printf("等级：E（不及格）\n");  // 输出 E 等级。
    }  // E 等级代码块结束。
}  // check_grade 函数结束。

int main(void)  // 定义主函数，程序从 main 开始运行。
{  // main 函数体开始。
    compare_two_numbers();  // 调用第一题函数，完成两个数字的比较。
    printf("\n");  // 输出一个空行，把两道题的运行结果分开。
    check_grade();  // 调用第二题函数，完成成绩等级判断。

    return 0;  // 返回 0，表示程序正常结束。
}  // main 函数结束。
