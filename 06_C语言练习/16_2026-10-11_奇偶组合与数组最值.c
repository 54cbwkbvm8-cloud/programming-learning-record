#include <stdio.h>
int a;
int b;
int c;
int max;
double sum;
int i = 1;
int main(void) {
    printf("请输入两个整数：");
    scanf("%d %d", &a, &b);
    printf("a + b =%d\n", a + b);
    if (a % 2 == 0 && b % 2 == 0) {
        printf("a 和 b 都是偶数\n");
    } else if (a % 2 == 0 && b % 2 != 0) {
        printf("a 是偶数，b 是奇数\n");
    } else if (a % 2 != 0 && b % 2 == 0) {
        printf("a 是奇数，b 是偶数\n");
    } else {
        printf("a 和 b 都是奇数\n");
    }
    printf("请再输入一个整数：\n");
    scanf("%d", &c);
    max = a;
    if (b > max) {
        max = b;
    }
    if (c > max) {
        max = c;
    }
    printf("最大值是：%d\n", max);
    while (i <= 100) {
        sum = sum + i;
        i++;
    }
    printf("1~100 的和是：%.0f\n", sum);
    printf("请输入 10 个整数求最大值：\n");
    max = 0;
    sum = 0;
    for (i = 0; i < 10; i++) {
        scanf("%d", &a);
        if (i == 0 || a > max) {
            max = a;
        }
        sum = sum + a;
    }
    printf("最大值是：%d\n", max);
    printf("这十个整数的平均值为 %.2f\n", sum / 10.0);
    int arr[10];
    int m;
    double sum2 = 0.0;
    int max2;
    int min2;
    printf("请输入 10 个整数求最大值：\n");
    for (m = 0; m < 10; m++) {
        scanf("%d", &arr[m]);
        if (m == 0 || arr[m] > max2) {
            max2 = arr[m];
        }
        if (m == 0 || arr[m] < min2) {
            min2 = arr[m];
        }
    }
    for (m = 0; m < 10; m++) {
        sum2 += arr[m];
    }
    printf("平均值是：%.2f\n", sum2 / 10.0);
    printf("最大值是：%d\n", max2);
    printf("最小值是：%d\n", min2);
    printf("这十个整数的平均值为 %.2f\n", sum2 / 10.0);
    return 0;
}
