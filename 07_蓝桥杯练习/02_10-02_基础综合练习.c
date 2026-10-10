#include <stdio.h>
int main(void) {
    int a;
    int b;
    printf("请输入第一个整数：\n");
    scanf("%d", &a);
    printf("请输入第二个整数：\n");
    scanf("%d", &b);
    printf("两个整数的和为 %d\n", a + b);
    if (a % 2 == 0 && b % 2 == 0) {
        printf("两个整数都是偶数\n");
    } else if (a % 2 == 0 || b % 2 == 0) {
        printf("两个整数中有一个是偶数\n");
        if (a % 2 == 0) {
            printf("第一个整数是偶数\n");
        } else {
            printf("第二个整数是偶数\n");
        }
    } else {
        printf("两个整数都是奇数\n");
    }
    int c;
    printf("请输入第三个整数：");
    scanf("%d", &c);
    printf("三个整数的和为 %d\n", a + b + c);
    if (a > b && a > c) {
        printf("第一个整数最大\n");
    } else if (b > a && b > c) {
        printf("第二个整数最大\n");
    } else if (c > a && c > b) {
        printf("第三个整数最大\n");
    } else if (a == b && a > c) {
        printf("第一个和第二个整数一样大\n");
    } else if (a == c && a > b) {
        printf("第一个和第三个整数一样大\n");
    } else if (b == c && b > a) {
        printf("第二个和第三个整数一样大\n");
    } else if (a == b && b == c) {
        printf("三个整数一样大\n");
    } else {
        printf("没有最大的整数\n");
    }
    int sum = 0;
    int i = 1;
    for (i = 0; i <= 100; i++) {
        sum = sum + i;
    }
    printf("1到100的和为 %d\n", sum);
    int arr[5];
    printf("请输入5个整数：\n");
    for (i = 0; i < 5; i++) {
        scanf("%d", &arr[i]);
    }
    int max;
    max = arr[0];
    for (i = 0; i < 5; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    printf("最大的整数是 %d\n", max);
    int min;
    min = arr[0];
    for (i = 0; i < 5; i++) {
        if (arr[i] < min) {
            min = arr[i];
        }
    }
    printf("最小的整数是 %d\n", min);
    int sum2;
    sum2 = 0;
    for (i = 0; i < 5; i++) {
        sum2 = sum2 + arr[i];
    }
    float avg;
    avg = (float)sum2 / 5;
    printf("平均数是 %.2f\n", avg);

    return 0;
}
