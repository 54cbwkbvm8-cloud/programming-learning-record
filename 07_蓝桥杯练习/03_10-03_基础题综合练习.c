/*
 * 文件名：10 月 3 日练习.c
 * 简介：蓝桥杯基础题综合练习，集中练习循环、数组和嵌套循环。
 * 功能：1～100 求和、统计 3 的倍数个数、输入 10 个整数求最大值和最小值、
 *       打印九九乘法表和星号三角形。
 * 学习建议：逐段运行、逐段理解。数组部分的最小值逻辑目前有误：min 没有赋初值，
 *           请先按 10月3日练习_文件简介与学习建议.md 里的最小改动自行修正再运行。
 */
#include <stdio.h>
int main(void) {
    int i2;
    int sum = 0;
    for (i2 = 1; i2 <= 100; i2++) {
        sum = sum + i2;
    }
    printf("1到 100的和是 %d\n", sum);
    // main 函数是 C 程序的入口
    int i;                       // 定义变量 i，用来保存当前正在检查的数字
    int count = 0;               // 定义计数器 count，初始值为 0
    for (i = 1; i <= 100; i++) { // 从 1 枚举到 100，每次让 i 增加 1
        if (i % 3 == 0) {        // 判断 i 除以 3 的余数是否为 0
            count++;             // 如果余数为 0，说明 i 是 3 的倍数，计数器加 1
        } // 结束 if 判断
    } // 结束 for 循环

    printf("1～100 中 3 的倍数有 %d 个\n", count); // 输出最终统计出来的数量
    int max;
    int min;
    int arr[10];
    printf("=========请输入 10 个整数：=========\n");
    printf("用回车键隔开 或者 空格键隔开\n");
    for (int i = 0; i < 10; i++) {
        scanf("%d", &arr[i]);
    }
    max = arr[0];
    for (int i = 1; i < 10; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    printf("max = %d\n", max);
    for (int i = 0; i < 10; i++) {
        if (arr[i] < min) {
            min = arr[i];
        }
    }
    printf("min = %d\n", min);
    printf("========九九乘法表========\n");
    int j;
    for (i = 1; i <= 9; i++) {
        for (j = 1; j <= i; j++) {
            printf("%d*%d=%d\t", i, j, i * j);
        }
        printf("\n");
    }
    printf("=======打印三角形========\n");
    for (i = 1; i <= 9; i++) {
        for (j = 1; j <= i; j++) {
            printf("*");
        }
        printf("\n");
    }
    return 0;
}
