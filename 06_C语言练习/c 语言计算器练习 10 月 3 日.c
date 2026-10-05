/*
 * 文件名：c 语言计算器练习 10 月 3 日.c
 * 简介：菜单式简易计算器，练习循环、多分支和浮点数输入输出。
 * 功能：循环显示菜单，提供加、减、乘、除四种运算和退出选项，除法会检查除数是否为 0。
 * 学习建议：依次试完 1～5 每个选项，重点比较 scanf 的 %f 和 printf 的 %.2f 用法，
 *           再测试除以 0 和输入菜单以外数字两种情况。完整说明见
 *           简易计算器练习_文件简介与学习建议.md。
 */
#include <stdio.h>
int main(void) {
    int choice;
    float a;
    float b;
    while (1) {
        printf("============简易计算器============\n");
        printf("1.加法\n");
        printf("2.减法\n");
        printf("3.乘法\n");
        printf("4.除法\n");
        printf("5.退出\n");
        printf("=================================\n");
        printf("请选择：");
        scanf("%d", &choice);
        if (choice == 5) {
            break;
        }
        if (choice == 1) {
            printf("请输入第一个数字：");
            scanf("%f", &a); // 修改：scanf 读取 float 必须使用 %f，原来的 %.2f 是错误的
            printf("请输入第二个数字：");
            scanf("%f", &b); // 修改：scanf 读取 float 必须使用 %f，不能使用 %.2f
            printf("%.2f + %.2f = %.2f\n", a, b, a + b);
        } else if (choice == 2) {
            printf("请输入第一个数字：");
            scanf("%f", &a); // 修改：scanf 输入使用 %f；%.2f 只能用于 printf 输出格式
            printf("请输入第二个数字：");
            scanf("%f", &b); // 修改：scanf 输入使用 %f；%.2f 不能用于读取数据
            printf("%.2f - %.2f = %.2f\n", a, b, a - b);
        } else if (choice == 3) {
            printf("请输入第一个数字：");
            scanf("%f", &a); // 修改：scanf 读取 float 使用 %f，原来的 %.2f 写法错误
            printf("请输入第二个数字：");
            scanf("%f", &b); // 修改：原来的 %2f 是输入宽度限制，不是保留两位小数
            printf("%.2f * %.2f = %.2f\n", a, b, a * b);
        } else if (choice == 4) {
            printf("请输入第一个数字：");
            scanf("%f", &a); // 修改：scanf 读取 float 使用 %f，原来的 %.2f 写法错误
            printf("请输入第二个数字：");
            scanf("%f", &b); // 修改：scanf 读取 float 使用 %f，不能使用 %.2f
            if (b == 0) {
                printf("除数不能为 0\n");
            } else {
                printf("%.2f / %.2f = %.2f\n", a, b, a / b);
            }
        } else {
            printf("输入错误，请重新输入！\n"); // 修改：增加无效菜单选项处理，避免输入 6、7
                                                // 等数字后程序没有任何提示
        }
    }
    return 0;
}
