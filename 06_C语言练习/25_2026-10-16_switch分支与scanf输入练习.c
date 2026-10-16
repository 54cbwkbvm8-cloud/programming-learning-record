#include <stdio.h>
int main(void) {
    int number = 0;
    char ch = '\0';
    printf("请输入一个字符用于（switch分支演示）：");
    if (scanf("%d", &number) != 1) {
        printf("输入错误，程序退出。\n");
        return 1;
    }
    printf("请输入一个字符（例如：A、B、C）：\n");
    if (scanf(" %c", &ch) != 1) {
        printf("输入错误，程序退出。\n");
        return 1;
    }
    printf("读取到的整数是 %d\n", number);
    printf("读取到的字符是 '%c'\n", ch);
    printf("switch分支执行结果是：\n");
    switch (number) {
    case 1:
        printf("进入 case 1\n");
        break;
    case 2:
        printf("进入 case 2\n");
        break;
    case 3:
        printf("进入 case 3\n");
        break;
    default:
        printf("没有匹配的 case，进入 default 分支\n");
        break;
    }
    return 0;
}
