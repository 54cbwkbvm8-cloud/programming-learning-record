#include <stdio.h>
int main(void) {
    printf("===安全使用指针===\n");
    int value = 10;
    int *p = &value;
    printf("value 当前的值是：%d\n", value);
    printf("*p当前读到的值是：%d\n", *p);
    *p = 20;
    printf("通过*p 修改后 value 的值是：%d\n", value);
    printf("\n===switch的执行顺序===\n");
    int choice = 2;
    printf("choice 当前的值是：%d\n ", choice);
    printf("switch输出的值是：\n");
    switch (choice) {
    case 1:
        printf("A");
    case 2:
        printf("B");
    case 3:
        printf("C\n");
        break;
    default:
        printf("我真牛逼\n");
    }
    return 0;
}
