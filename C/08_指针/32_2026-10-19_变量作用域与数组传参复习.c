#include <stddef.h>
#include <stdio.h>
void demonstrate_scope(void) {
    int x = 1;
    printf("【第一题：变量作用域】\n");
    {
        int x = 2;
        printf("%d ", x);
    }
    printf("%d\n", x);
}
void demonstrate_pointer(void) {
    int a[5] = {10, 20, 30, 40, 50};
    int *p = a + 2;
    printf("【第二题：指针与数组】\n");
    printf("p 指向的元素a[2] = %d\n", *p);
    printf("*(p - 1) = %d\n", *(p - 1));
    printf("p[0] + *(p - 1) = %d\n", p[0] + *(p - 1));
}
void show_array_parameter_size(int *a, size_t length) {
    printf("【第四题：数组与函数参数】\n");
    size_t pointer_bytes = sizeof(a);
    size_t element_bytes = sizeof(a[0]);
    size_t mistake_count = pointer_bytes / element_bytes;
    printf("函数形参 sizeof(a) = %zu 字节\n", pointer_bytes);
    printf("单个元素 sizeof(a[0]) = %zu 字节\n", element_bytes);
    printf("题目表达式 sizeof(a) / sizeof(a[0]) = %zu（错误结果）\n", mistake_count);
    printf("从调用处传入的真实元素个数 = %zu\n\n", length);
}
int main(void) {
    int a[5] = {10, 20, 30, 40, 50};
    size_t length = sizeof(a) / sizeof(a[0]);
    demonstrate_pointer();
    demonstrate_scope();
    printf("main 中用 sizeof 计算出数组元素个数 = %zu\n", length);
    show_array_parameter_size(a, length);
    return 0;
}
