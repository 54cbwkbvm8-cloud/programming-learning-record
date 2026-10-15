#include <stdio.h>
void print_array(const int arr[], int length) {
    if (arr == NULL) {
        printf("错误：数组指针为空，不能访问数组。\n");
    }
    if (length <= 0) {
        printf("错误：数组长度必须大于0,数组长度为 0 或者小于 0，没有可以打印的元素\n");
        return;
    }
    printf("数组元素为：");
    for (int i = 0; i < length; i++) {
        printf("%d ", arr[i]);
        if (i < length - 1)
            printf(",");
    }
    printf("\n");
}
int main(void) {
    int number = 42;
    int *p = NULL;
    printf("===1.指针初始化演示===\n");
    printf("指针初始化时为 NULL，此时不解引用。\n");
    p = &number;
    printf("p现在保存 number 的地址。\n");
    printf("number = %d\n", number);
    printf("解引用运算符*访问了p 指向的对象，所以*p = %d\n", *p);
    int numbers[] = {10, 20, 30, 40, 50};
    int length = (int)sizeof(numbers) / sizeof(numbers[0]);
    printf("\n2.数组参数与长度演示\n");
    printf("调用函数前计算得到的数组长度：%d\n", length);
    print_array(numbers, 0);
    print_array(NULL, 3);
    return 0;
}
