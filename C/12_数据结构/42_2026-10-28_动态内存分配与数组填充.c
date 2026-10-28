// 概念有点怪怪的，先放着😁
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
int main(void) {
    size_t n;
    printf("请输入要分配的整数个数n：");
    if (scanf("%zu", &n) != 1 || n == 0) {
        fprintf(stderr, "输入无效：n必须是大于0的整数。\n");
        return 1;
    }
    if (n > SIZE_MAX / sizeof(int)) {
        fprintf(stderr, "请求的内存n过大，所需内存的字节数会溢出，无法分配。\n");
        return 1;
    }
    int *a = malloc(n * sizeof(int));
    if (a == NULL) {
        perror("malloc");
        return 1;
    }
    for (size_t i = 0; i < n; i++) {
        a[i] = (int)((i + 1) * (i + 1));
    }
    for (size_t i = 0; i < n; i++) {
        printf("a[%zu] = %d ", i, a[i]);
    }
    printf("\n");
    free(a);
    a = NULL;
    return 0;
}
