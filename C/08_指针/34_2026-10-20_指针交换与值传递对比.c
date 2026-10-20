#include <stdio.h>
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}
void swapByValue(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
    printf("普通值传递函数内部：a = %d, b = %d\n", a, b);
}
int main(void) {
    int x = 10;
    int y = 20;
    int m = 10;
    int n = 20;
    printf("=====1.指针参数交换=====\n");
    printf("交换前：x = %d, y = %d\n", x, y);
    swap(&x, &y);
    printf("交换后：x = %d, y = %d\n", x, y);
    printf("=====2.普通值传递版本=====\n");
    printf("交换前：m = %d, n = %d\n", m, n);
    swapByValue(m, n);
    printf("调用后：m = %d, n = %d\n", m, n);
    return 0;
}
