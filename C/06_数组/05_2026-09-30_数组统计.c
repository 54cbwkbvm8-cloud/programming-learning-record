#include <stdio.h>
int main(void) {
    int arr[10];
    int max;
    int min;
    int sum = 0;
    int avg;
    printf("请输入10个数字：\n");
    for (int i = 0; i < 10; i++) {
        printf("请输入第%d个数字：", i + 1);
        scanf("%d", &arr[i]);
    }
    printf("=========================================\n");
    printf("你输入的数字是：\n");
    for (int i = 0; i < 10; i++) {
        printf("第%d个数字是:%d\n", i + 1, arr[i]);
    }
    int i = 0;
    max = arr[0];
    for (i = 1; i < 10; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    printf("=========================================\n");
    printf("最大值是：%d\n", max);
    min = arr[0];
    for (i = 1; i < 10; i++) {
        if (arr[i] < min) {
            min = arr[i];
        }
    }
    printf("最小值是：%d\n", min);
    printf("=========================================\n");
    sum = 0;
    for (i = 0; i < 10; i++) {
        sum += arr[i];
    }
    printf("平均值是：%f\n", sum / 10.0);
    return 0;
}
