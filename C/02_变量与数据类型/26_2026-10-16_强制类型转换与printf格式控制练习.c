#include <stdio.h>
int main(void) {
    int a = 7;
    int b = 2;
    printf("第一条输出：");
    printf("%d", a / b);
    printf("\n");
    printf("第二条输出：");
    printf("%f", (double)a / b);
    printf("\n");
    printf("第三条输出：");
    printf("%d%%", 80);
    printf("\n");
    printf("补充练习 1（保留两位小数）:");
    printf("%.2f", (double)a / b);
    printf("\n");
    printf("补充练习 2（宽度为两个空格，不足补零）：");
    printf("%02d", 7);
    printf("\n");
    printf("补充练习 3（宽度为五个空格，左对齐）：");
    printf("%-5dEND,", 7);
    printf("\n");
    return 0;
}
