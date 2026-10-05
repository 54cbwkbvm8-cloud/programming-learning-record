/*
 * 文件名：C 语言数组练习10 月 4 日.c
 * 简介：函数版一维数组统计练习，练习把统计逻辑拆成独立函数。
 * 功能：输入 5 个整数，用 sum、max、min、average 四个函数分别求总和、最大值、最小值和平均值。
 * 学习建议：在纸上跟踪每个函数内部的 i、a[i] 和统计变量，并对比 (float)sum / n 与
 *           sum / n 的结果差异。完整说明见 C语言数组函数统计_文件简介与学习建议.md。
 */
#include <stdio.h>
int max(int a[], int n);
int min(int a[], int n);
int sum(int a[], int n);
float average(int a[], int n);
int max(int a[], int n) {
    int max = a[0];
    for (int i = 1; i < n; i++) {
        if (a[i] > max) {
            max = a[i];
        }
    }
    return max;
}
int min(int a[], int n) {
    int min = a[0];
    for (int i = 1; i < n; i++) {
        if (a[i] < min) {
            min = a[i];
        }
    }
    return min;
}
float average(int a[], int n) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += a[i];
    }
    return (float)sum / n;
}
int sum(int a[], int n) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += a[i];
    }
    return sum;
}
int main(void) {
    int a[5];
    int i;
    int max_value;
    int min_value;
    float average_value;
    int sum_value;
    printf("请输入5个整数：\n");
    for (i = 0; i < 5; i++) {
        scanf("%d", &a[i]);
    }
    sum_value = sum(a, 5);
    printf("五个数的和为：%d\n", sum_value);
    printf("\n");
    max_value = max(a, 5);
    min_value = min(a, 5);
    average_value = average(a, 5);
    printf("最大值是：%d\n", max_value);
    printf("\n");
    printf("最小值是：%d\n", min_value);
    printf("\n");
    printf("平均数是：%.2f\n", average_value);
    return 0;
}
