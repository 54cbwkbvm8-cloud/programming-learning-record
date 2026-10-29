/*
 * 文件用途：本文件围绕“学生成绩管理系统”展开。
 * 核心知识点：指针、数组、字符串、结构体、循环、分支判断、函数。
 * 基本流程：根据代码中的输入或初始数据，按条件、循环和函数调用完成处理，再输出结果。
 * 输入：从标准输入读取数据或由调用者传入参数。输出：通过控制台打印处理结果。
 * 阅读提醒：指针和动态内存相关操作必须确认地址有效，并注意释放已申请的内存。 检查数组下标、字符串结束符和缓冲区容量，避免越界访问。
 * 依赖头文件：ctype.h, errno.h, math.h, stdio.h, stdlib.h, string.h。
 */
// 真的写的力竭了，md我真是服了，写的头疼😭😠，全是难点😠，真的写不下去了😭😭😭
#include <ctype.h>
#include <errno.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// 该宏用于集中定义常量或短表达式，修改时需确认所有使用位置。
#define NAME_LENGTH 50
// 该宏用于集中定义常量或短表达式，修改时需确认所有使用位置。
#define MAX_STUDENTS 100

// 定义结构体 该结构体，把相关字段组合成一个自定义数据类型；结尾的 typedef 别名便于后续声明变量。
typedef struct {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int id;
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    char name[NAME_LENGTH];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    float score;
} Student;

// 调用 `read_line()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static void read_line(const char *prompt, char *buffer, size_t size);
// 调用 `read_int_range()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static int read_int_range(const char *prompt, int min_value, int max_value);
// 调用 `read_score()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static float read_score(const char *prompt);
// 调用 `read_name()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static void read_name(char name[NAME_LENGTH]);
// 调用 `find_student_by_id()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static int find_student_by_id(const Student student[], int count, int target_id);
// 调用 `display_students()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static void display_students(const Student students[], int count);
// 调用 `calculate_average()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static float calculate_average(const Student students[], int count);
// 调用 `find_highest_score()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static int find_highest_score(const Student students[], int count);
// 调用 `show_highest_score()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static void show_highest_score(const Student students[], int count);
// 调用 `modify_student_score()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static void modify_student_score(Student students[], int count);
// 调用 `add_student()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static void add_student(Student students[], int *count);
// 调用 `input_student()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static void input_student(Student *student, const Student students[], int existing_count);
// 调用 `show_menu()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
static void show_menu(void);

// 定义函数 read_line；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static void read_line(const char *prompt, char *buffer, size_t size) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int ch;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    size_t length;

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("%s", prompt);

// 判断条件 `(fgets(buffer, (int)size, stdin) == NULL)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (fgets(buffer, (int)size, stdin) == NULL) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("\n检测到输入结束，程序退出。\n");
// 调用 `exit()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        exit(EXIT_FAILURE);
    }

// 调用 `strlen()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    length = strlen(buffer);

// 判断条件 `(length > 0 && buffer[length - 1] == 字符)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (length > 0 && buffer[length - 1] == '\n') {
        buffer[length - 1] = '\0';
// 处理前面条件均不成立时的情况。
    } else {
// 调用 `getchar()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        ch = getchar();

// 判断条件 `(ch != 字符 && ch != EOF)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (ch != '\n' && ch != EOF) {
// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
            while ((ch = getchar()) != '\n' && ch != EOF) {
            }
            buffer[0] = '\0';
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("输入内容过长，请缩短后重试。\n");
// 只有前面条件不成立时才检查这个新条件；多个分支按书写顺序判断。
        } else if (ch == EOF) {
            buffer[length] = '\0';
        }
    }
}

// 定义函数 read_int_range；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static int read_int_range(const char *prompt, int min_value, int max_value) {
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    char input[128];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    char *end;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    long value;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int parsed;

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
    while (1) {
// 调用 `read_line()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        read_line(prompt, input, sizeof(input));

// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
        errno = 0;
// 调用 `strtol()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        value = strtol(input, &end, 10);
// 使用比较运算符判断两个值的关系；注意 `==` 是比较，`=` 才是赋值。
        parsed = (end != input);

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
        while (isspace((unsigned char)*end)) {
// 更新计数变量；`++` 每次加 1，`--` 每次减 1，注意更新发生的位置。
            end++;
        }

// 判断条件 `(parsed && *end == 字符 && errno != ERANGE && value >= min_value && value <= max_value)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (parsed && *end == '\0' && errno != ERANGE && value >= min_value && value <= max_value) {
// 结束当前函数调用，并把表达式 `(int)value` 的结果返回给调用者。
            return (int)value;
        }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("输入无效，请输入%d到%d之间的整数。\n", min_value, max_value);
    }
}

// 定义函数 read_score；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static float read_score(const char *prompt) {
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    char input[128];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    char *end;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    float value;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int parsed;

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
    while (1) {
// 调用 `read_line()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        read_line(prompt, input, sizeof(input));

// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
        errno = 0;
// 调用 `strtof()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        value = strtof(input, &end);
// 使用比较运算符判断两个值的关系；注意 `==` 是比较，`=` 才是赋值。
        parsed = (end != input);

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
        while (isspace((unsigned char)*end)) {
// 更新计数变量；`++` 每次加 1，`--` 每次减 1，注意更新发生的位置。
            end++;
        }

// 判断条件 `(parsed && *end == 字符 && errno == 0 && isfinite(value) && value >= 0.0f &&` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (parsed && *end == '\0' && errno == 0 && isfinite(value) && value >= 0.0f &&
            value <= 100.0f) {
// 结束当前函数调用，并把表达式 `value` 的结果返回给调用者。
            return value;
        }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("输入的成绩无效，请输入0到100之间的数字。\n");
    }
}

// 定义函数 read_name；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static void read_name(char name[NAME_LENGTH]) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    size_t start;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    size_t end;

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
    while (1) {
// 调用 `read_line()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        read_line("请输入姓名：", name, NAME_LENGTH);

// 调用 `strlen()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        end = strlen(name);
// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
        start = 0;

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
        while (start < end && isspace((unsigned char)name[start])) {
// 更新计数变量；`++` 每次加 1，`--` 每次减 1，注意更新发生的位置。
            start++;
        }

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
        while (end > start && isspace((unsigned char)name[end - 1])) {
// 更新计数变量；`++` 每次加 1，`--` 每次减 1，注意更新发生的位置。
            end--;
        }

// 判断条件 `(end > start)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (end > start) {
// 调用 `memmove()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
            memmove(name, name + start, end - start);
            name[end - start] = '\0';
// 结束当前函数调用；当前函数不再继续执行后续语句。
            return;
        }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("姓名不能为空，请重新输入。\n");
    }
}

// 定义函数 find_student_by_id；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static int find_student_by_id(const Student student[], int count, int target_id) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int i;

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < count; i++) {
// 判断条件 `(student[i].id == target_id)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (student[i].id == target_id) {
// 结束当前函数调用，并把表达式 `i` 的结果返回给调用者。
            return i;
        }
    }

// 结束当前函数调用，并把表达式 `-1` 的结果返回给调用者。
    return -1;
}

// 定义函数 display_students；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static void display_students(const Student students[], int count) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int i;

// 判断条件 `(count <= 0)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (count <= 0) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("当前没有学生记录。\n");
// 结束当前函数调用；当前函数不再继续执行后续语句。
        return;
    }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n%-12s %-24s %-10s\n", "学号", "姓名", "成绩");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("--------------------------------------------------\n");

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < count; i++) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("%-12d %-24s %-10.2f\n", students[i].id, students[i].name, students[i].score);
    }
}

// 定义函数 calculate_average；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static float calculate_average(const Student students[], int count) {
// 声明并初始化变量；`=` 是赋值运算符，用右侧表达式的值设置左侧变量，而不是比较两个值。
    float sum = 0.0f;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int i;

// 判断条件 `(count <= 0)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (count <= 0) {
// 结束当前函数调用，并把表达式 `0.0f` 的结果返回给调用者。
        return 0.0f;
    }

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < count; i++) {
// 更新变量或保存计算结果；逐步跟踪右侧表达式，有助于理解每轮循环中的数据变化。
        sum += students[i].score;
    }

// 结束当前函数调用，并把表达式 `sum / count` 的结果返回给调用者。
    return sum / count;
}

// 定义函数 find_highest_score；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static int find_highest_score(const Student students[], int count) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int i;
// 声明并初始化变量；`=` 是赋值运算符，用右侧表达式的值设置左侧变量，而不是比较两个值。
    int max_index = 0;

// 判断条件 `(count <= 0)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (count <= 0) {
// 结束当前函数调用，并把表达式 `-1` 的结果返回给调用者。
        return -1;
    }

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 1; i < count; i++) {
// 判断条件 `(students[i].score > students[max_index].score)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (students[i].score > students[max_index].score) {
// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
            max_index = i;
        }
    }

// 结束当前函数调用，并把表达式 `max_index` 的结果返回给调用者。
    return max_index;
}

// 定义函数 show_highest_score；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static void show_highest_score(const Student students[], int count) {
// 调用 `find_highest_score()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    int index = find_highest_score(students, count);

// 判断条件 `(index == -1)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (index == -1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("当前没有学生记录，无法查找最高分。\n");
// 结束当前函数调用；当前函数不再继续执行后续语句。
        return;
    }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("最高分学生是：学号%d，姓名%s，成绩%.2f\n", students[index].id, students[index].name,
           students[index].score);
}

// 定义函数 modify_student_score；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static void modify_student_score(Student students[], int count) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int target_id;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int index;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    float new_score;

// 调用 `read_int_range()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    target_id = read_int_range("请输入要修改成绩的学生学号：", 1, 999999999);
// 调用 `find_student_by_id()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    index = find_student_by_id(students, count, target_id);

// 判断条件 `(index == -1)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (index == -1) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("未找到学号为%d的学生，成绩未修改。\n", target_id);
// 结束当前函数调用；当前函数不再继续执行后续语句。
        return;
    }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("当前学生：%s，原成绩：%.2f\n", students[index].name, students[index].score);

// 调用 `read_score()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    new_score = read_score("请输入新的成绩（0-100）：");
// 把右侧变量当前保存的值复制到左侧变量；如果右侧是指针，复制的是地址而不是对象内容。
    students[index].score = new_score;

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("成绩修改成功！\n");
}

// 定义函数 input_student；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static void input_student(Student *student, const Student students[], int existing_count) {
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int id;

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
    while (1) {
// 调用 `read_int_range()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        id = read_int_range("请输入学号：", 1, 999999999);

// 判断条件 `(find_student_by_id(students, existing_count, id) == -1)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
        if (find_student_by_id(students, existing_count, id) == -1) {
            break;
        }

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("学号%d已存在，请重新输入。\n", id);
    }

// 通过结构体指针访问并更新指定成员；`p->field` 等价于 `(*p).field`，前提是 p 指向有效结构体。
    student->id = id;
// 调用 `read_name()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    read_name(student->name);
// 通过结构体指针访问并更新指定成员；`p->field` 等价于 `(*p).field`，前提是 p 指向有效结构体。
    student->score = read_score("请输入成绩（0-100）：");
}

// 定义函数 add_student；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static void add_student(Student students[], int *count) {
// 判断条件 `(*count >= MAX_STUDENTS)` 是否成立；条件为真时执行对应分支，否则跳过或转入其他分支。
    if (*count >= MAX_STUDENTS) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("学生人数已满，无法添加，最多保存%d名学生。\n", MAX_STUDENTS);
// 结束当前函数调用；当前函数不再继续执行后续语句。
        return;
    }

// 调用 `input_student()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    input_student(&students[*count], students, *count);
    (*count)++;

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("学生添加成功！目前共有%d名学生。\n", *count);
}

// 定义函数 show_menu；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
static void show_menu(void) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("\n====================学生成绩管理程序V1.0====================\n");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("1.显示全部学生\n");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("2.计算平均成绩\n");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("3.查找最高成绩\n");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("4.修改学生成绩\n");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("5.添加学生记录\n");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("0.退出程序\n");
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("============================================================\n");
}

// 定义函数 main；参数接收调用方传入的数据，函数体完成操作，返回语句（如有）把结果交还调用者。
int main(void) {
// 声明数组并为元素分配连续空间；下标从 0 开始，有效范围必须小于数组长度。
    Student students[MAX_STUDENTS];
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int count;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int choice;
// 声明变量并确定其数据类型；未初始化的局部变量不能直接当作已知值使用。
    int i;

// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
    printf("学生成绩管理系统V1.0\n");

// 调用 `read_int_range()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
    count = read_int_range("请输入学生人数(3-100): ", 3, MAX_STUDENTS);

// 循环按“初始化；条件判断；每轮更新”的顺序执行。先检查条件，再运行循环体，最后更新循环变量；注意边界是否会多执行或少执行一次。
    for (i = 0; i < count; i++) {
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
        printf("\n录入第%d个学生\n", i + 1);
// 调用 `input_student()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        input_student(&students[i], students, i);
    }

// 只要 while 后的条件成立，就重复执行循环体；检查循环体是否会推进相关变量，确保条件最终能变为假。
    while (1) {
// 调用 `show_menu()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        show_menu();
// 调用 `read_int_range()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
        choice = read_int_range("请输入菜单编号（0-5）: ", 0, 5);

// 根据括号中表达式的值选择匹配的 case 分支；C 语言的 case 默认会继续向下执行，除非遇到 break 等控制转移。
        switch (choice) {
// 这是 switch 中的一个匹配标签；表达式值与此常量相同时，从此处开始执行。
        case 1:
// 调用 `display_students()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
            display_students(students, count);
            break;

// 这是 switch 中的一个匹配标签；表达式值与此常量相同时，从此处开始执行。
        case 2:
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("全体学生平均成绩：%.2f\n", calculate_average(students, count));
            break;

// 这是 switch 中的一个匹配标签；表达式值与此常量相同时，从此处开始执行。
        case 3:
// 调用 `show_highest_score()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
            show_highest_score(students, count);
            break;

// 这是 switch 中的一个匹配标签；表达式值与此常量相同时，从此处开始执行。
        case 4:
// 调用 `modify_student_score()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
            modify_student_score(students, count);
            break;

// 这是 switch 中的一个匹配标签；表达式值与此常量相同时，从此处开始执行。
        case 5:
// 调用 `add_student()` 完成相应操作；括号中的表达式是传给函数的实参，具体效果由该函数定义决定。
            add_student(students, &count);
            break;

// 这是 switch 中的一个匹配标签；表达式值与此常量相同时，从此处开始执行。
        case 0:
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("退出系统。\n");
// 结束当前函数调用，并把表达式 `0` 的结果返回给调用者。
            return 0;

// 当 switch 中没有其他 case 匹配时，执行默认分支（如果存在）。
        default:
// 向标准输出打印提示或结果；格式字符串中的占位符必须与后续实参的类型匹配。
            printf("无效的选择，请重新输入。\n");
            break;
        }
    }
}
