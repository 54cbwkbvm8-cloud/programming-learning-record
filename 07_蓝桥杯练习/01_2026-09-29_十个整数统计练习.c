#include <stdio.h>
int main(void) {
    int max;
    int number;
    printf("========== 输入十个数字（用空格隔开）求最大值 ==========\n");
    scanf("%d", &max);
    for (int i = 1; i < 10; i++) {
        scanf("%d", &number);
        if (number > max) {
            max = number;
        }
    }
    printf("最大值为：%d\n", max);
    printf("========== 输入十个数字（用空格隔开）求和 ==========\n");
    int sum = 0;
    for (int i = 0; i < 10; i++) {
        scanf("%d", &number);
        sum += number;
    }
    printf("和为：%d\n", sum);
    printf("========== "
           "输入十个数字判断是不是偶数，不是偶数就加一，最后输出奇数的个数（用空格隔开）计数器====="
           "======\n");
    int count = 0;
    for (int i = 0; i < 10; i++) {
        scanf("%d", &number);
        if (number % 2 != 0) {
            count++;
        }
    }
    printf("奇数个数为：%d\n", count);
    printf("========== 输入十个数字（用空格隔开）求最大值(数组版)==========\n");
    int a[10];
    for (int i = 0; i < 10; i++) {
        scanf("%d", &a[i]);
    }
    int max2 = a[0];
    for (int i = 1; i < 10; i++) {
        if (a[i] > max2) {
            max2 = a[i];
        }
    }
    printf("最大值为：%d\n", max2);
    return 0;
}
