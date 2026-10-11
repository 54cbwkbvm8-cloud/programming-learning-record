#include <stdio.h>
int main(void) {
    int number;
    printf("【第一题】请输入一个整数：");
    scanf("%d", &number);
    if (number > 0) {
        printf("这是正数\n");
    } else if (number < 0) {
        printf("这是负数\n");
    } else {
        printf("这是0\n");
    }
    printf("\n【第二题】请输入一个整数：");
    scanf("%d", &number);
    if (number % 2 == 0) {
        printf("这是偶数\n");
    } else {
        printf("这是奇数\n");
    }
    int a;
    int b;
    int c;
    int max;
    printf("\n【第三题】请输入三个整数：(输入一个数字后按回车 或者 输入一个数字后用空格隔开)");
    scanf("%d %d %d", &a, &b, &c);
    max = a;
    if (b > max) {
        max = b;
    }
    if (c > max) {
        max = c;
    }
    printf("最大值是：%d\n", max);
    int score;
    printf("\n【第四题】请输入一个分数：");
    scanf("%d", &score);
    if (score < 0 || score > 100) {
        printf("输入错误,请输入 0～100 的数字\n");
    } else if (score >= 90) {
        printf("优秀\n");
    } else if (score >= 80) {
        printf("良好\n");
    } else if (score >= 70) {
        printf("中等\n");
    } else if (score >= 60) {
        printf("及格\n");
    } else {
        printf("不及格\n");
    }
    int year;
    printf("\n【第五题】请输入一个年份：");
    scanf("%d", &year);
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        printf("%d年是闰年\n", year);
    } else
        printf("%d年不是闰年", year);
    return 0;
}
