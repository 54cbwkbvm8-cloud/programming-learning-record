#include <stddef.h>
#include <stdio.h>
void print_array(const int *arr, size_t length) {
    size_t i;
    printf("数组元素有：");
    for (i = 0; i < length; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    printf("函数内部 sizeof(arr) = %zu字节\n", sizeof(arr));
}
int main(void) {
    int a[] = {10, 20, 30, 40, 50};
    int *p = &a[3];
    size_t length = sizeof(a) / sizeof(a[0]);
    printf("数组长度为：%zu\n", length);
    printf("整个数组占用的空间为：%zu字节\n", sizeof(a));
    printf("一个元素占用的空间为：%zu 字节", sizeof(a[0]));
    print_array(a, length);
    printf("p指向的元素：%d\n", *p);
    printf("p指向的元素的下一个元素*(p + 1)是：%d\n", *(p + 1));
    printf("p指向的元素的上一个元素*(p - 1)是：%d\n", *(p - 1));
    return 0;
}
