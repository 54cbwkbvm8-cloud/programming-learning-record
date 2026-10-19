#include <stdio.h>
int i;
int sum = 0;
int main(void) {
    for (i = 1; i <= 10; i++) {
        if (i % 2 == 0) {
            sum += i;
        }
    }
    printf("1到 10 之间的偶数和为：%d\n", sum);
    sum = 0;
    for (i = 1; i <= 10; i++) {
        if (i % 2 != 0) {
            sum += i;
        }
    }
    printf("1到 10 之间的奇数和为：%d\n", sum);
    sum = 0;
    for (i = 1; i <= 10; i = i + 2) {
        sum += i;
    }
    printf("1到 10 之间的奇数和为：%d\n", sum);
    int j;
    j = sum;
    sum = 0;
    for (i = 1; i <= 10; i++) {
        sum += i;
    }
    printf("1到 10 之间的和为：%d\n", sum);
    printf("1到 10 之间的偶数和为：%d\n", sum - j);
    return 0;
}
