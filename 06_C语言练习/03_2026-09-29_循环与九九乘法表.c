#include <stdio.h>
int main(void) {
    printf("==========输出 1～100==========\n");
    for (int i = 1; i <= 100; i++) {
        printf("%d ", i);
    }
    printf("\n");
    printf("================1~100的数字求和================\n");
    int i, sum = 0;
    for (i = 1; i <= 100; i++) {
        sum += i;
    }
    printf("1~100的和为：%d\n", sum);
    printf("=============输出 1～100 的偶数=============\n");
    for (i = 1; i <= 100; i++) {
        if (i % 2 == 0) {
            printf("%d ", i);
        }
    }
    printf("\n");
    printf("======输出 1～100 的偶数和======\n");
    int sum2 = 0;
    for (i = 1; i <= 100; i++) {
        if (i % 2 == 0) {
            sum2 += i;
        }
    }
    printf("1~100的偶数和为：%d\n", sum2);
    printf("=============输出九九乘法表=============\n");
    int j = 0;
    for (i = 1; i <= 9; i++) {
        for (j = 1; j <= i; j++) {
            printf("%d*%d=%d ", i, j, i * j);
        }
        printf("\n");
    }
    return 0;
}
