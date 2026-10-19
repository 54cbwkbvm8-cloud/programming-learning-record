#include <stdio.h>  // 引入标准输入输出库，提供 printf()、fgets() 等函数
#include <string.h> // 引入字符串处理库，提供 strlen()、strcpy()、strncmp() 等函数

int main(void) { // 主函数，程序从这里开始执行

    char str[100] = {
        0}; // 创建字符数组 str，用于保存第一行输入的文字，最多保存 99 个字节的内容和一个结束符
    char copied[100] = {0}; // 创建字符数组 copied，用于保存复制后的字符串
    char other[100] = {0};  // 创建字符数组 other，用于保存第二行输入的文字

    size_t len = 0;         // 定义 len，用于保存字符串的字节长度
    int compare_result = 0; // 定义 compare_result，用于保存字符串比较结果

    printf("请输入第一行文字（最多 99 个字节）："); // 提示用户输入第一行文字

    if (fgets(str, sizeof(str), stdin) == NULL) { // 从键盘读取第一行文字，如果读取失败则进入代码块
        printf("读取失败\n");                     // 输出读取失败的提示
        return 1;                                 // 结束程序，并返回错误状态
    }

    len = strlen(str); // 计算第一行字符串的字节长度，不包含字符串结束符 '\0'

    if (len > 0 && str[len - 1] == '\n') { // 判断字符串是否以换行符 '\n' 结尾
        str[len - 1] = '\0';               // 如果存在换行符，就将其替换为字符串结束符 '\0'
        len--;                             // 字符串长度减 1，因为删除了换行符
    }

    len = strlen(str); // 重新计算处理后的字符串长度

    printf("\n-----第一行文字的结果-----\n");         // 输出第一部分的标题
    printf("你输入的内容是：%s\n", str);              // 输出第一行输入的文字
    printf("你输入的内容字符串长度是：%zu\n", len);   // 输出第一行字符串的字节长度
    printf("你输入的数组长度是：%zu\n", sizeof(str)); // 输出字符数组 str 占用的总字节数
    printf(
        "提示：strlen 统计的是字节数，不一定等于中文字符数\n"); // 提醒用户中文字符可能占用多个字节

    strcpy(copied, str); // 将第一行字符串复制到 copied 数组中

    printf("\n-----strcpy 复制后的结果-----\n");           // 输出字符串复制部分的标题
    printf("原字符串：%s\n", str);                         // 输出复制前的原字符串
    printf("复制后的内容是：%s\n", copied);                // 输出复制后的字符串
    printf("复制后的字符串长度是：%zu\n", strlen(copied)); // 输出复制后字符串的字节长度
    printf("复制后的数组长度是：%zu\n", sizeof(copied));   // 输出 copied 数组占用的总字节数

    printf("\n请输入第二行文字用于演示 strncmp："); // 提示用户输入第二行文字

    if (fgets(other, sizeof(other), stdin) ==
        NULL) {               // 从键盘读取第二行文字，如果读取失败则进入代码块
        printf("输入错误\n"); // 输出输入错误的提示
        return 1;             // 结束程序，并返回错误状态
    }

    len = strlen(other); // 计算第二行字符串的字节长度

    if (len > 0 && other[len - 1] == '\n') { // 判断第二行字符串末尾是否存在换行符
        other[len - 1] = '\0';               // 如果存在，就将换行符替换为字符串结束符
    }

    compare_result =
        strncmp(str, other, sizeof(other)); // 比较第一行和第二行字符串，最多比较 100 个字节

    printf("\n-----strncmp 比较后的结果-----\n"); // 输出字符串比较部分的标题
    printf("第一行文字：%s\n", str);              // 输出第一行文字
    printf("第二行文字：%s\n", other);            // 输出第二行文字
    printf("比较结果是：%d\n", compare_result);   // 输出 strncmp() 返回的比较结果

    if (compare_result == 0) {       // 判断比较结果是否等于 0
        printf("两个字符串相同\n");  // 如果等于 0，说明两个字符串相同
    } else if (compare_result < 0) { // 如果结果小于 0，说明第一个字符串小于第二个字符串
        printf("第一个字符串小于第二个字符串\n"); // 输出第一个字符串较小的提示
    } else { // 如果结果大于 0，说明第一个字符串大于第二个字符串
        printf("第一个字符串大于第二个字符串\n"); // 输出第一个字符串较大的提示
    }

    return 0; // 程序正常结束，并返回 0
}
