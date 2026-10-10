#include <stdio.h>

int main(void) {
    int a[5];
    int i;
    int sum = 0;
    int max;
    int min;
    float avg;
    printf("请输入 5 个整数：");
    scanf("%d%d%d%d%d", &a[0], &a[1], &a[2], &a[3], &a[4]);
    for (i = 0; i < 5; i++) {
        sum += a[i];
    }
    printf("这 5 个整数的和为：%d", sum);
    max = a[0];
    for (i = 1; i < 5; i++) {
        if (a[i] > max) {
            max = a[i];
        }
    }
    printf("\n");
    printf("这 5 个整数的最大值为：%d", max);
    printf("\n");
    min = a[0];
    for (i = 1; i < 5; i++) {
        if (a[i] < min) {
            min = a[i];
        }
    }
    printf("这 5 个整数的最小值为：%d", min);
    printf("\n");
    avg = (float)sum / 5;
    printf("这 5 个整数的平均值为：%.2f", avg);
    printf("\n");
    printf("数组的逆序为：");
    for (i = 4; i >= 0; i--) {
        printf("%d ", a[i]);
    }
    printf("\n");
    return 0;
}
