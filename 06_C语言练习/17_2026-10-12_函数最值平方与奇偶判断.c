#include <stdio.h>
int max(int a, int b);
int square(int a);
int is_even(int a);
int max(int a, int b) {
    if (a > b) {
        return a;
    } else {
        return b;
    }
}
int square(int a) { return a * a; }
int is_even(int a) {
    if (a % 2 == 0) {
        return 1;
    } else {
        return 0;
    }
}
int main(void) {
    printf("请输入a和b的值：");
    int a, b;
    scanf("%d %d", &a, &b);
    printf("最大值为：%d\n", max(a, b));
    printf("a的平方为：%d\n", square(a));
    printf("a是否为偶数：%d  (1为偶数，0为奇数)\n", is_even(a));
    return 0;
}
