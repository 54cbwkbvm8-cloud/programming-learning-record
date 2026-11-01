#include <stdio.h>
#define STUDENT_COUNT 3
#define NAME_LENGTH 50

typedef struct {
    int id;
    char name[NAME_LENGTH];
    float score;
} Student;

int main(void) {
    Student students[STUDENT_COUNT];
    int i;
    int targetId;
    int found = 0;
    float total = 0.0f;
    float average;
    int highestIndex = 0;

    printf("=== 学生结构体数组与成绩处理 ===\n");
    printf("请依次输入 3 名学生的信息，格式：学号 姓名 成绩\n");

    for (i = 0; i < STUDENT_COUNT; i++) {
        printf("第 %d 名学生：", i + 1);
        if (scanf("%d %49s %f", &students[i].id, students[i].name, &students[i].score) != 3) {
            printf("输入格式错误，请按“学号 姓名 成绩”的顺序输入。\n");
            return 1;
        }
    }

    printf("\n--- 初始学生信息 ---\n");
    for (i = 0; i < STUDENT_COUNT; i++) {
        printf("学号：%d，姓名：%s，成绩：%.2f\n", students[i].id, students[i].name,
               students[i].score);
    }

    total = 0.0f;
    for (i = 0; i < STUDENT_COUNT; i++) {
        total = total + students[i].score;
    }
    average = total / STUDENT_COUNT;

    highestIndex = 0;
    for (i = 1; i < STUDENT_COUNT; i++) {
        if (students[i].score > students[highestIndex].score) {
            highestIndex = i;
        }
    }

    printf("\n修改前平均成绩：%.2f\n", average);
    printf("修改前最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highestIndex].id,
           students[highestIndex].name, students[highestIndex].score);

    printf("\n请输入要修改成绩的学生学号：");
    if (scanf("%d", &targetId) != 1) {
        printf("学号输入格式错误，程序结束。\n");
        return 1;
    }

    for (i = 0; i < STUDENT_COUNT; i++) {
        if (students[i].id == targetId) {
            printf("请输入 %s 的新成绩：", students[i].name);
            if (scanf("%f", &students[i].score) != 1) {
                printf("成绩输入格式错误，程序结束。\n");
                return 1;
            }
            found = 1;
            break;
        }
    }

    if (found == 1) {
        printf("成绩修改成功。\n");
    } else {
        printf("未找到学号为 %d 的学生，成绩未修改。\n", targetId);
    }

    printf("\n--- 当前学生信息（修改后）---\n");
    for (i = 0; i < STUDENT_COUNT; i++) {
        printf("学号：%d，姓名：%s，成绩：%.2f\n", students[i].id, students[i].name,
               students[i].score);
    }

    total = 0.0f;
    for (i = 0; i < STUDENT_COUNT; i++) {
        total = total + students[i].score;
    }
    average = total / STUDENT_COUNT;

    highestIndex = 0;
    for (i = 1; i < STUDENT_COUNT; i++) {
        if (students[i].score > students[highestIndex].score) {
            highestIndex = i;
        }
    }

    printf("\n修改后平均成绩：%.2f\n", average);
    printf("修改后最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highestIndex].id,
           students[highestIndex].name, students[highestIndex].score);

    return 0;
}
/*// ====================================================================
// 文件名：38_2026-10-23_结构体数组学生成绩统计与修改.c
// 作用：用"结构体数组"管理 3 名学生：
//       1. 输入每名学生的学号、姓名、成绩
//       2. 输出学生信息，计算平均成绩，找出最高分的学生
//       3. 按学号查找某名学生，并修改他的成绩
//       4. 修改后重新输出，并再次统计平均成绩和最高分
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "38_2026-10-23_结构体数组学生成绩统计与修改.c"
//   运行：./demo
//   说明：编译 = 把人能看懂的 C 代码翻译成电脑能执行的程序。
//         -Wall -Wextra 的意思是"把所有可疑的地方都警告我"，
//         编译时出现警告，一定要弄明白原因，不要忽略。
// ====================================================================


// --------------------------------------------------------------------
// 第一部分：引入"工具箱"和宏定义
// --------------------------------------------------------------------
// <stdio.h> 是"标准输入输出"工具箱：printf 和 scanf 都在里面。
#include <stdio.h>

// #define 叫"宏定义"：编译之前，程序里所有的 STUDENT_COUNT 会被替换成 3，NAME_LENGTH 会被替换成 50（行末不写分号）。
// 为什么用宏？学生人数、名字长度会在很多地方用到，以后要改人数，只改这一行，不会漏改。
#define STUDENT_COUNT 3
#define NAME_LENGTH 50


// --------------------------------------------------------------------
// 第二部分：定义结构体类型 Student
// --------------------------------------------------------------------
// struct { ... } 把学号、姓名、成绩"打包"成一个整体。
// typedef ... Student; 给这个结构体起一个类型名 Student，以后直接写 Student 就行，不用每次都写 struct。
// 这里 struct 后面没有名字，是"匿名结构体"，名字全靠最后面的 Student 来叫。
// 注意：右大括号后面是类型名 Student，再加分号 ;。
typedef struct {

    // 成员 1：学号。
    int id;

    // 成员 2：姓名，char 数组，大小是 NAME_LENGTH（50），最多存 49 个字节，留 1 个给结束标志 '\0'。
    char name[NAME_LENGTH];

    // 成员 3：成绩，float 可以带小数。
    float score;
} Student;


// --------------------------------------------------------------------
// 第三部分：main 函数 —— 程序从这里开始
// --------------------------------------------------------------------
int main(void) {

    // 结构体数组：一排 3 个 Student。students[0]、students[1]、students[2] 各是一个完整的学生；
    // students[i].score 表示"第 i 个学生的成绩"（点 . 叫成员访问运算符）。
    // "数组 + 结构体"是管理很多同类对象的常用办法。
    Student students[STUDENT_COUNT];

    // 循环用的下标。
    int i;

    // 要修改成绩的学生学号。
    int targetId;

    // found 是"标志变量"：0 表示还没找到，找到后改成 1。用 0 / 1 记录"某件事有没有发生"，是常见写法。
    int found = 0;

    // total 存总分，average 存平均分。
    float total = 0.0f;
    float average;

    // highestIndex 存"最高分学生在数组里的下标"（不是成绩本身）。
    // 记下标的好处：有了下标，这个学生的学号、姓名、成绩都能一次找到。
    int highestIndex = 0;

    // --------------------------------------------------------------------
    // 第四部分：输入 3 名学生的信息
    // --------------------------------------------------------------------
    printf("=== 学生结构体数组与成绩处理 ===\n");
    printf("请依次输入 3 名学生的信息，格式：学号 姓名 成绩\n");

    // i 从 0 数到 2，依次对应 3 个学生。条件写 i < STUDENT_COUNT：数组下标最大是 2。
    for (i = 0; i < STUDENT_COUNT; i++) {
        // i + 1：下标从 0 开始，显示给人看的编号从 1 开始，所以加 1。
        printf("第 %d 名学生：", i + 1);

        // 一条 scanf 一次读 3 个数据：学号（%d）、姓名（%49s）、成绩（%f），输入时用空格隔开。
        // &students[i].id：第 i 个学生的 id 成员的地址（& 是取地址符）。
        // students[i].name 前面没有 &：name 是数组，数组名本身就代表地址。
        // %49s 最多读 49 个字符，防止超过 name 的容量（越界）。
        // scanf 返回成功读到的数据个数，期望是 3；!= 3 表示输入格式不对，例如缺了某一项，或学号写成了字母。
        // 例：输入 1001 Tom 85.5，则 id 是 1001，name 是 Tom，score 是 85.5。
        // 注意：姓名里不能有空格，因为 %s 遇到空格就停止。
        if (scanf("%d %49s %f", &students[i].id, students[i].name, &students[i].score) != 3) {
            // 字符串里的中文引号“ ”只是普通文字，原样显示。
            printf("输入格式错误，请按“学号 姓名 成绩”的顺序输入。\n");

            // 出错就提前结束程序，return 1 表示"出错"（0 表示正常）。
            return 1;
        }
    }

    // --------------------------------------------------------------------
    // 第五部分：输出初始的学生信息
    // --------------------------------------------------------------------
    printf("\n--- 初始学生信息 ---\n");

    // 遍历数组，把每个学生都输出一遍。
    for (i = 0; i < STUDENT_COUNT; i++) {
        // students[i].id、students[i].name、students[i].score 分别取出第 i 个学生的学号、姓名、成绩。
        // 这条 printf 被拆成了两行写：逗号后面换行继续写参数，意思不变。
        printf("学号：%d，姓名：%s，成绩：%.2f\n", students[i].id, students[i].name,
               students[i].score);
    }

    // --------------------------------------------------------------------
    // 第六部分：计算平均成绩和最高分
    // --------------------------------------------------------------------
    // 先把总分清零，再累加。
    total = 0.0f;
    for (i = 0; i < STUDENT_COUNT; i++) {
        // 把每个学生的成绩累加进 total。
        total = total + students[i].score;
    }

    // 总分 ÷ 人数 = 平均分。total 是 float，所以这是小数除法，不会丢掉小数部分。
    // 例：(85.5 + 92 + 78) ÷ 3 = 85.17。
    average = total / STUDENT_COUNT;

    // 找最高分用"打擂台"：先假设第 0 个学生最高，记下他的下标。
    highestIndex = 0;

    // 从第 1 个学生开始挑战（第 0 个已经是"擂主"）。
    for (i = 1; i < STUDENT_COUNT; i++) {
        // 如果第 i 个学生的成绩比当前最高分学生的成绩还高，就换人。
        // students[highestIndex].score 就是"当前最高分"。
        if (students[i].score > students[highestIndex].score) {
            // 更新擂主的下标。
            highestIndex = i;
        }
    }

    // 输出平均成绩，%.2f 表示保留两位小数。
    printf("\n修改前平均成绩：%.2f\n", average);

    // students[highestIndex] 就是最高分的那个学生，取出他的学号、姓名、成绩。
    printf("修改前最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highestIndex].id,
           students[highestIndex].name, students[highestIndex].score);

    // --------------------------------------------------------------------
    // 第七部分：按学号查找并修改成绩
    // --------------------------------------------------------------------
    printf("\n请输入要修改成绩的学生学号：");

    // 读入学号。返回值不是 1，说明输入的不是整数，提示后直接结束程序。
    if (scanf("%d", &targetId) != 1) {
        printf("学号输入格式错误，程序结束。\n");
        return 1;
    }

    // "顺序查找"：从第 0 个学生开始，一个一个看学号是否匹配。
    for (i = 0; i < STUDENT_COUNT; i++) {
        // == 判断相等：这个学生的学号等于要找的学号，就是找到了。
        if (students[i].id == targetId) {
            // %s 显示姓名，让用户知道现在改的是谁的成绩。
            printf("请输入 %s 的新成绩：", students[i].name);

            // 直接把输入的新成绩读进 students[i].score：就地修改，原来的成绩被覆盖。读不成功就提示并结束。
            if (scanf("%f", &students[i].score) != 1) {
                printf("成绩输入格式错误，程序结束。\n");
                return 1;
            }

            // 把标志变量改成 1，记录"已经找到并修改了"。
            found = 1;

            // 找到并修改之后就不用再往后找了，用 break 跳出这个 for 循环。break 只跳出最近的这一层循环。
            break;
        }
    }

    // 循环结束后，用标志变量判断结果：1 表示找到并修改了；0 表示整个数组都没有这个学号。
    if (found == 1) {
        printf("成绩修改成功。\n");
    } else {
        printf("未找到学号为 %d 的学生，成绩未修改。\n", targetId);
    }

    // --------------------------------------------------------------------
    // 第八部分：输出修改后的信息，并重新统计
    // --------------------------------------------------------------------
    printf("\n--- 当前学生信息（修改后）---\n");

    // 再输出一遍所有学生，可以看到被修改的成绩已经变了。
    for (i = 0; i < STUDENT_COUNT; i++) {
        printf("学号：%d，姓名：%s，成绩：%.2f\n", students[i].id, students[i].name,
               students[i].score);
    }

    // 平均分和最高分必须重新计算：成绩变了，之前算出来的结果已经"过期"。
    // 下面这两段代码和第六部分完全相同。
    total = 0.0f;
    for (i = 0; i < STUDENT_COUNT; i++) {
        total = total + students[i].score;
    }
    average = total / STUDENT_COUNT;

    highestIndex = 0;
    for (i = 1; i < STUDENT_COUNT; i++) {
        if (students[i].score > students[highestIndex].score) {
            highestIndex = i;
        }
    }

    printf("\n修改后平均成绩：%.2f\n", average);
    printf("修改后最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highestIndex].id,
           students[highestIndex].name, students[highestIndex].score);

    // return 0：结束 main，把 0 交给系统，表示"程序正常结束"。
    return 0;
}

// ====================================================================
// 测试时依次输入（每行输完按一次回车）：
//   1001 Tom 85.5
//   1002 Amy 92
//   1003 Bob 78
//   1003
//   95
//
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   === 学生结构体数组与成绩处理 ===
//   请依次输入 3 名学生的信息，格式：学号 姓名 成绩
//   第 1 名学生：第 2 名学生：第 3 名学生：
//   --- 初始学生信息 ---
//   学号：1001，姓名：Tom，成绩：85.50
//   学号：1002，姓名：Amy，成绩：92.00
//   学号：1003，姓名：Bob，成绩：78.00
//
//   修改前平均成绩：85.17
//   修改前最高分学生：学号 1002，姓名 Amy，成绩 92.00
//
//   请输入要修改成绩的学生学号：请输入 Bob 的新成绩：成绩修改成功。
//
//   --- 当前学生信息（修改后）---
//   学号：1001，姓名：Tom，成绩：85.50
//   学号：1002，姓名：Amy，成绩：92.00
//   学号：1003，姓名：Bob，成绩：95.00
//
//   修改后平均成绩：90.83
//   修改后最高分学生：学号 1003，姓名 Bob，成绩 95.00
// ====================================================================
*/
