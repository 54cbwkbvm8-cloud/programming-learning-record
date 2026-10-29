/*
 * 文件用途：本文件围绕“结构体数组学生成绩统计与修改”展开。
 * 核心知识点：指针、数组、字符串、结构体、循环、分支判断、函数。
 * 基本流程：根据代码中的输入或初始数据，按条件、循环和函数调用完成处理，再输出结果。
 * 输入：从标准输入读取数据或由调用者传入参数。输出：通过控制台打印处理结果。
 * 阅读提醒：指针和动态内存相关操作必须确认地址有效，并注意释放已申请的内存。 检查数组下标、字符串结束符和缓冲区容量，避免越界访问。
 * 依赖头文件：stdio.h, string.h。
 */
// 普通练习版create by YMZ
#include <stdio.h>
// 该宏用于集中定义常量或短表达式，修改时需确认所有使用位置。
#define STUDENT_COUNT 3
// 该宏用于集中定义常量或短表达式，修改时需确认所有使用位置。
#define NAME_LENGTH 50

// 定义结构体 该结构体，把相关字段组合成一个自定义数据类型；结尾的 typedef 别名便于后续声明变量。
typedef struct {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int id;
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    char name[NAME_LENGTH];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    float score;
} Student;

// 定义函数 main；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
int main(void) {
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    Student students[STUDENT_COUNT];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int i;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int targetId;
// 声明并初始化变量；`=` 是赋值运算符，用右侧表达式的值设置左侧变量，而不是比较两个值。
    int found = 0;
// 声明并初始化变量；`=` 是赋值运算符，用右侧表达式的值设置左侧变量，而不是比较两个值。
    float total = 0.0f;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    float average;
// 声明并初始化变量；`=` 是赋值运算符，用右侧表达式的值设置左侧变量，而不是比较两个值。
    int highestIndex = 0;

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("=== 学生结构体数组与成绩处理 ===\n");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("请依次输入 3 名学生的信息，格式：学号 姓名 成绩\n");

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("第 %d 名学生：", i + 1);
// 判断条件 `(scanf(字符串, &students[i].id, students[i].name, &students[i].score) != 3)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (scanf("%d %49s %f", &students[i].id, students[i].name, &students[i].score) != 3) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("输入格式错误，请按“学号 姓名 成绩”的顺序输入。\n");
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
            return 1;
        }
    }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n--- 初始学生信息 ---\n");
// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("学号：%d，姓名：%s，成绩：%.2f\n", students[i].id, students[i].name,
               students[i].score);
    }

// 更新变量或保存计算结果；逐步跟踪右侧表达式，有助于理解每轮循环中的数据变化。
    total = 0.0f;
// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 更新变量或保存计算结果；逐步跟踪右侧表达式，有助于理解每轮循环中的数据变化。
        total = total + students[i].score;
    }
// 更新变量或保存计算结果；逐步跟踪右侧表达式，有助于理解每轮循环中的数据变化。
    average = total / STUDENT_COUNT;

// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
    highestIndex = 0;
// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 1; i < STUDENT_COUNT; i++) {
// 判断条件 `(students[i].score > students[highestIndex].score)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (students[i].score > students[highestIndex].score) {
// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
            highestIndex = i;
        }
    }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n修改前平均成绩：%.2f\n", average);
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("修改前最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highestIndex].id,
           students[highestIndex].name, students[highestIndex].score);

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n请输入要修改成绩的学生学号：");
// 判断条件 `(scanf(字符串, &targetId) != 1)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (scanf("%d", &targetId) != 1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("学号输入格式错误，程序结束。\n");
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
        return 1;
    }

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 判断条件 `(students[i].id == targetId)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (students[i].id == targetId) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("请输入 %s 的新成绩：", students[i].name);
// 判断条件 `(scanf(字符串, &students[i].score) != 1)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
            if (scanf("%f", &students[i].score) != 1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
                printf("成绩输入格式错误，程序结束。\n");
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
                return 1;
            }
// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
            found = 1;
            break;
        }
    }

// 判断条件 `(found == 1)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (found == 1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("成绩修改成功。\n");
// 处理前面条件均不成立时的情况。
    } else {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("未找到学号为 %d 的学生，成绩未修改。\n", targetId);
    }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n--- 当前学生信息（修改后）---\n");
// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("学号：%d，姓名：%s，成绩：%.2f\n", students[i].id, students[i].name,
               students[i].score);
    }

// 更新变量或保存计算结果；逐步跟踪右侧表达式，有助于理解每轮循环中的数据变化。
    total = 0.0f;
// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 更新变量或保存计算结果；逐步跟踪右侧表达式，有助于理解每轮循环中的数据变化。
        total = total + students[i].score;
    }
// 更新变量或保存计算结果；逐步跟踪右侧表达式，有助于理解每轮循环中的数据变化。
    average = total / STUDENT_COUNT;

// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
    highestIndex = 0;
// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 1; i < STUDENT_COUNT; i++) {
// 判断条件 `(students[i].score > students[highestIndex].score)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (students[i].score > students[highestIndex].score) {
// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
            highestIndex = i;
        }
    }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n修改后平均成绩：%.2f\n", average);
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("修改后最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highestIndex].id,
           students[highestIndex].name, students[highestIndex].score);

// 结束当前函数调用，并把表达式 `0` 的结果返回给调用者。
    return 0;
}
// 进阶练习版 create by GHY
/*#include <stdio.h>
#include <string.h>

// 该宏用于集中定义常量或短表达式，修改时需确认所有使用位置。
#define STUDENT_COUNT 3
// 该宏用于集中定义常量或短表达式，修改时需确认所有使用位置。
#define NAME_LENGTH 50
// 该宏用于集中定义常量或短表达式，修改时需确认所有使用位置。
#define INPUT_LENGTH 256

// 定义结构体 该结构体，把相关字段组合成一个自定义数据类型；结尾的 typedef 别名便于后续声明变量。
typedef struct {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int id;
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    char name[NAME_LENGTH];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    float score;
} Student;

// 定义函数 read_line；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static int read_line(char *buffer, size_t size) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    size_t length;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int ch;

// 判断条件 `(fgets(buffer, (int)size, stdin) == NULL)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (fgets(buffer, (int)size, stdin) == NULL) {
// 结束当前函数调用，并把表达式 `0` 的结果返回给调用者。
        return 0;
    }

// 调用 `strlen()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    length = strlen(buffer);
// 判断条件 `(length > 0 && buffer[length - 1] == 字符)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (length > 0 && buffer[length - 1] == '\n') {
        buffer[length - 1] = '\0';
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
        return 1;
    }

// 判断条件 `(!feof(stdin))` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (!feof(stdin)) {
// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
        while ((ch = getchar()) != '\n' && ch != EOF) {
        }
// 结束当前函数调用，并把表达式 `-1` 的结果返回给调用者。
        return -1;
    }

// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
    return 1;
}

// 定义函数 read_student；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static int read_student(Student *student, int number) {
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    char line[INPUT_LENGTH];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    char extra;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int status;

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
    while (1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("第 %d 名学生：", number);
// 调用 `read_line()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        status = read_line(line, sizeof(line));
// 判断条件 `(status == 0)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (status == 0) {
// 结束当前函数调用，并把表达式 `0` 的结果返回给调用者。
            return 0;
        }
// 判断条件 `(status == -1 || sscanf(line, 字符串, &student->id, student->name,` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (status == -1 || sscanf(line, "%d %49s %f %c", &student->id, student->name,
                                   &student->score, &extra) != 3) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("输入格式错误，请按“学号 姓名 成绩”的格式重新输入，姓名不能包含空格。\n");
            continue;
        }
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
        return 1;
    }
}

// 定义函数 read_integer；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static int read_integer(const char *prompt, int *value) {
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    char line[INPUT_LENGTH];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    char extra;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int status;

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
    while (1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("%s", prompt);
// 调用 `read_line()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        status = read_line(line, sizeof(line));
// 判断条件 `(status == 0)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (status == 0) {
// 结束当前函数调用，并把表达式 `0` 的结果返回给调用者。
            return 0;
        }
// 判断条件 `(status == -1 || sscanf(line, 字符串, value, &extra) != 1)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (status == -1 || sscanf(line, "%d %c", value, &extra) != 1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("输入格式错误，请输入一个整数并重新尝试。\n");
            continue;
        }
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
        return 1;
    }
}

// 定义函数 read_score；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static int read_score(const char *prompt, float *score) {
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    char line[INPUT_LENGTH];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    char extra;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int status;

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
    while (1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("%s", prompt);
// 调用 `read_line()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        status = read_line(line, sizeof(line));
// 判断条件 `(status == 0)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (status == 0) {
// 结束当前函数调用，并把表达式 `0` 的结果返回给调用者。
            return 0;
        }
// 判断条件 `(status == -1 || sscanf(line, 字符串, score, &extra) != 1)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (status == -1 || sscanf(line, "%f %c", score, &extra) != 1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("成绩输入格式错误，请输入一个数字并重新尝试。\n");
            continue;
        }
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
        return 1;
    }
}

// 定义函数 print_students；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static void print_students(const Student students[STUDENT_COUNT]) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int i;

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("学号：%d，姓名：%s，成绩：%.2f\n", students[i].id, students[i].name,
               students[i].score);
    }
}

// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
static void calculate_results(const Student students[STUDENT_COUNT], float *average,
                              int *highest_index) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int i;
// 声明并初始化变量；`=` 是赋值运算符，用右侧表达式的值设置左侧变量，而不是比较两个值。
    float total = 0.0f;

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 更新变量或保存计算结果；逐步跟踪右侧表达式，有助于理解每轮循环中的数据变化。
        total += students[i].score;
    }
    *average = total / STUDENT_COUNT;

    *highest_index = 0;
    for (i = 1; i < STUDENT_COUNT; i++) {
// 判断条件 `(students[i].score > students[*highest_index].score)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (students[i].score > students[*highest_index].score) {
            *highest_index = i;
        }
    }
}

// 定义函数 main；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
int main(void) {
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    Student students[STUDENT_COUNT];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int i;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int target_id;
// 声明并初始化变量；`=` 是赋值运算符，用右侧表达式的值设置左侧变量，而不是比较两个值。
    int found = 0;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int highest_index;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    float average;

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("=== 学生结构体数组与成绩处理 ===\n");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("请依次输入 3 名学生的信息，格式：学号 姓名 成绩\n");

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 判断条件 `(!read_student(&students[i], i + 1))` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (!read_student(&students[i], i + 1)) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("输入已结束，程序退出。\n");
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
            return 1;
        }
    }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n--- 初始学生信息 ---\n");
// 调用 `print_students()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    print_students(students);
// 调用 `calculate_results()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    calculate_results(students, &average, &highest_index);

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n修改前平均成绩：%.2f\n", average);
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("修改前最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highest_index].id,
           students[highest_index].name, students[highest_index].score);

// 判断条件 `(!read_integer(字符串, &target_id))` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (!read_integer("\n请输入要修改成绩的学生学号：", &target_id)) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("输入已结束，程序退出。\n");
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
        return 1;
    }

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < STUDENT_COUNT; i++) {
// 判断条件 `(students[i].id == target_id)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (students[i].id == target_id) {
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
            char prompt[INPUT_LENGTH];
// 调用 `snprintf()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
            snprintf(prompt, sizeof(prompt), "请输入 %s 的新成绩：", students[i].name);
// 判断条件 `(!read_score(prompt, &students[i].score))` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
            if (!read_score(prompt, &students[i].score)) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
                printf("输入已结束，程序退出。\n");
// 结束当前函数调用，并把表达式 `1` 的结果返回给调用者。
                return 1;
            }
// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
            found = 1;
            break;
        }
    }

// 判断条件 `(found)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (found) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("成绩修改成功。\n");
// 处理前面条件均不成立时的情况。
    } else {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("未找到学号为 %d 的学生，成绩未修改。\n", target_id);
    }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n--- 当前学生信息（修改后）---\n");
// 调用 `print_students()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    print_students(students);
// 调用 `calculate_results()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    calculate_results(students, &average, &highest_index);

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n修改后平均成绩：%.2f\n", average);
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("修改后最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highest_index].id,
           students[highest_index].name, students[highest_index].score);

// 结束当前函数调用，并把表达式 `0` 的结果返回给调用者。
    return 0;
}
*/
