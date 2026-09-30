#include <stdio.h>
int main(void) {
    int i = 0;
    int sum = 0;
    while (i <= 100) {
        sum = sum + i;
        i = i + 1;
    }
    printf("1～100 的和是：%d\n", sum);
    int i2 = 0;
    int sum2 = 0;
    while (i2 <= 100) {
        if (i2 % 2 != 0) {
            sum2 = sum2 + i2;
        }
        i2 = i2 + 1;
    }
    printf("1～100 的奇数和是：%d\n", sum2);
    int number;
    printf("请输入一个整数：");
    scanf("%d", &number);
    while (number != 0) {
        printf("你输入的是 %d", number);
        printf("请重新输入数字，直到输入 0 为止：");
        scanf("%d", &number);
    }
    printf("好的程序结束了\n");
    int number2;
    int counts = 10;
    printf("猜数字游戏开始\n");
    printf("请输入一个数字：");
    scanf("%d", &number2);
    while (number2 != 6) {
        printf("你猜错了，请重新输入：");
        scanf("%d", &number2);
        counts--;
        if (counts <= 1) {
            printf("游戏结束！！！你猜错了，你一共猜了 %d 次\n", 10);
            break;
        }
    }
    int a = 11 - counts;
    if (number2 == 6) {
        printf("你猜对了，你一共猜了 %d 次\n", a);
    }
    return 0;
}
