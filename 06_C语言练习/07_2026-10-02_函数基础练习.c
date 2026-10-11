#include <stdio.h>
int max(int a, int b) {
    if (a > b) {
        return a;
    } else {
        return b;
    }
}

int isEven(int number) {
    if (number % 2 == 0) {
        return 1;
    } else {
        return 0;
    }
}
int square(int number) { return number * number; }

int max3(int a, int b, int c) {
    if (max(a, b) > c) {
        return max(a, b);
    } else {
        return c;
    }
}
int main(void) {
    printf("输入两个整数对比大小（用空格或者回车隔开）：\n");
    int a, b, c;
    int result;
    scanf("%d %d", &a, &b);
    result = max(a, b);
    int number = max(a, b);
    printf("这两个数中较大的一个是：%d\n", max(a, b));
    result = isEven(number);
    if (result == 1) {
        printf("这个数是偶数");
    } else {
        printf("这个数是奇数");
    }
    printf("这个数的平方是：%d\n", square(number));
    printf("输入三个整数对比大小（用空格或者回车隔开）：\n");
    scanf("%d %d %d", &a, &b, &c);
    int result2 = max3(a, b, c);
    printf("这三个数中较大的一个是：%d\n", max3(a, b, c));
    result = isEven(result2);
    if (result == 1) {
        printf("这个数是偶数");
    } else {
        printf("这个数是奇数");
    }
    printf("这个最大数的平方是：%d\n", square(result2));
    return 0;
}
