#include <stdio.h>
int main(void) {
    int score;
    printf("请输入你的分数:");
    scanf("%d", &score);
    if (score % 2 == 0) {
        printf("你的分数是偶数\n");
    } else {
        printf("你的分数是奇数\n");
    }
    if (score >= 60) {
        printf("你的成绩及格了\n");
    } else {
        printf("你的成绩不及格\n");
    }
    int i;
    printf("for循环演示，输出 1～10\n");
    for (i = 1; i <= 10; i++) {
        printf("%d\n", i);
    }
    i = 11;
    printf("while循环演示，输出 11～20\n");
    while (i <= 20) {
        printf("%d\n", i);
        i++;
    }
    printf(" 请你再输入三个数字\n");
    int a, b, c;
    scanf("%d %d %d", &a, &b, &c);
    int max;
    if (a > b) {
        max = a;
    } else {
        max = b;
    }
    if (max < c) {
        max = c;
    }
    printf("max=%d\n", max);
    int sum = 0;
    for (i = 1; i <= 100; i++) {
        sum += i;
    }
    printf("1～100 的和是：%d\n", sum);
    int sum2 = 0;
    int i2;
    i2 = 1;
    while (i2 <= 100) {
        sum2 += i2;
        i2++;
    }
    printf("1～100 的和是：%d\n", sum2);
    return 0;
}
