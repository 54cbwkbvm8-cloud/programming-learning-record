#include <stdio.h>
#include <string.h>
int main(void) {
    char str[200];
    int letter_count = 0;
    int digit_count = 0;
    int i;
    int len = 0;
    printf("请输入第一个字符串：");
    if (fgets(str, sizeof(str), stdin) == NULL) {
        return 1;
    }
    str[strcspn(str, "\n")] = '\0';
    len = (int)strlen(str);
    for (i = 0; i < len; i++) {
        char c = str[i];
        if (c >= '0' && c <= '9') {
            digit_count++;
        }
        if (c >= 'a' && c <= 'z') {
            str[i] = c - ('a' - 'A');
        }
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            letter_count++;
        }
    }
    printf("英文字母的数量是：%d\n", letter_count);
    printf("数字的数量是：%d\n", digit_count);
    printf("转换后的字符串：%s\n", str);
    return 0;
}
