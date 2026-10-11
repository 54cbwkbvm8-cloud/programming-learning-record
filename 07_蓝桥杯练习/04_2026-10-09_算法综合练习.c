#include <stdio.h>
int a;
int b;
int max;
int min;
int main(void) {
    printf("请输入两个整数：");
    scanf("%d %d", &a, &b);
    if (a > b) {
        max = a;
        min = b;
    } else {
        max = b;
        min = a;
    }
    printf("max = %d, min = %d\n", max, min);
    if (a % 2 == 0) {
        printf("a 是偶数\n");
    } else {
        printf("a 是奇数\n");
    }
    if (b % 2 == 0) {
        printf("b 是偶数\n");
    } else {
        printf("b 是奇数\n");
    }
    int sum;
    sum = 0;
    int n;
    int num[10];
    printf("请输入 n 的值: ");
    scanf("%d", &n);
    for (int i = 1; i <= n; i++) {
        sum += i;
    }
    printf("1~n 的和为: %d\n", sum);
    printf("请输入 10 个整数: ");
    for (int i = 0; i < 10; i++) {
        scanf("%d", &num[i]);
    }
    int arr_max = num[0];
    int arr_min = num[0];
    for (int i = 0; i < 10; i++) {
        if (num[i] > arr_max) {
            arr_max = num[i];
        }
        if (num[i] < arr_min) {
            arr_min = num[i];
        }
    }
    printf("max = %d, min = %d\n", arr_max, arr_min);
    int count = 0;
    for (int i = 0; i < 10; i++) {
        if (num[i] % 2 == 0) {
            count++;
        }
    }
    printf("数组的偶数有%d个\n", count);
    printf("数组的奇数有%d个\n", 10 - count);
    return 0;
}
