#include <stdio.h>
#define N 5
int sum = 0;
int count = 0;
int num;
int max;
int min;
int main(void) {
    int i = 1;
    printf("请输入 5 个数字：\n");
    while (i <= N) {
        printf("请输入第 %d 个数字：", i);
        if (scanf("%d", &num) != 1) {
            printf("输入错误，请重新输入！\n");
            return 1;
        }
        sum += num;
        if (i == 1) {
            max = num;
            min = num;
        }
        if (num % 2 == 0) {
            count++;
        }
        if (num > max) {
            max = num;
        }
        if (num < min) {
            min = num;
        }
        i++;
    }
    int i2 = 1;
    printf("1～30 中能被 7 整除的数有：\n");
    while (i2 <= 30) {
        if (i2 % 7 == 0) {
            printf("%d \n", i2);
        }
        i2++;
    }
    printf("用户输入的%d个数字的和为：%d\n", N, sum);
    printf("你输入的 %d 个数字中偶数的个数为：%d\n", N, count);
    printf("你输入的 %d 个数字中最大值为：%d\n", N, max);
    printf("你输入的 %d 个数字中最小值为：%d\n", N, min);
    return 0;
}
