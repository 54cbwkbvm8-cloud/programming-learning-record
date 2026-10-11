#include <stdio.h>
#define N 1000
int num;
int main(void) {
    long long sum = 0;
    int count = 0;
    printf("请输入数字个数：");
    if (scanf("%d", &num) != 1) {
        printf("输入错误！\n");
        return 1;
    }
    if (num < 1 || num > N) {
        printf("数字个数必须在1到%d之间！\n", N);
        return 1;
    }
    int a[N];
    printf("请输入%d个数字：", num);
    for (int i = 0; i < num; i++) {
        if (scanf("%d", &a[i]) != 1) {
            printf("输入错误！\n");
            return 1;
        }
    }
    int max = a[0];
    int min = a[0];
    for (int i = 0; i < num; i++) {
        sum = sum + a[i];
        if (a[i] < min) {
            min = a[i];
        }
        if (a[i] > max) {
            max = a[i];
        }
        if (a[i] % 2 == 0) {
            count++;
        }
    }
    int m;
    printf("三的倍数有：");
    for (m = 1; m <= 100; m++) {
        if (m % 3 == 0) {
            printf("%d ", m);
        }
    }
    printf("\n");
    printf("这%d个数字的和为：%lld\n", num, sum);
    printf("这%d个数字中偶数的个数为：%d\n", num, count);
    printf("这%d个数字中最大的数为：%d\n", num, max);
    printf("这%d个数字中最小的数为：%d\n", num, min);
    return 0;
}
