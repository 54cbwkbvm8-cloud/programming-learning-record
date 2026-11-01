#include <ctype.h>
#include <errno.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NAME_LENGTH 50
#define MAX_STUDENTS 100

typedef struct {
    int id;
    char name[NAME_LENGTH];
    float score;
} Student;

static void read_line(const char *prompt, char *buffer, size_t size);
static int read_int_range(const char *prompt, int min_value, int max_value);
static float read_score(const char *prompt);
static void read_name(char name[NAME_LENGTH]);
static int find_student_by_id(const Student student[], int count, int target_id);
static void display_students(const Student students[], int count);
static float calculate_average(const Student students[], int count);
static int find_highest_score(const Student students[], int count);
static void show_highest_score(const Student students[], int count);
static void modify_student_score(Student students[], int count);
static void add_student(Student students[], int *count);
static void input_student(Student *student, const Student students[], int existing_count);
static void show_menu(void);

static void read_line(const char *prompt, char *buffer, size_t size) {
    int ch;
    size_t length;

    printf("%s", prompt);

    if (fgets(buffer, (int)size, stdin) == NULL) {
        printf("\n检测到输入结束，程序退出。\n");
        exit(EXIT_FAILURE);
    }

    length = strlen(buffer);

    if (length > 0 && buffer[length - 1] == '\n') {
        buffer[length - 1] = '\0';
    } else {
        ch = getchar();

        if (ch != '\n' && ch != EOF) {
            while ((ch = getchar()) != '\n' && ch != EOF) {
            }
            buffer[0] = '\0';
            printf("输入内容过长，请缩短后重试。\n");
        } else if (ch == EOF) {
            buffer[length] = '\0';
        }
    }
}

static int read_int_range(const char *prompt, int min_value, int max_value) {
    char input[128];
    char *end;
    long value;
    int parsed;

    while (1) {
        read_line(prompt, input, sizeof(input));

        errno = 0;
        value = strtol(input, &end, 10);
        parsed = (end != input);

        while (isspace((unsigned char)*end)) {
            end++;
        }

        if (parsed && *end == '\0' && errno != ERANGE && value >= min_value && value <= max_value) {
            return (int)value;
        }

        printf("输入无效，请输入%d到%d之间的整数。\n", min_value, max_value);
    }
}

static float read_score(const char *prompt) {
    char input[128];
    char *end;
    float value;
    int parsed;

    while (1) {
        read_line(prompt, input, sizeof(input));

        errno = 0;
        value = strtof(input, &end);
        parsed = (end != input);

        while (isspace((unsigned char)*end)) {
            end++;
        }

        if (parsed && *end == '\0' && errno == 0 && isfinite(value) && value >= 0.0f &&
            value <= 100.0f) {
            return value;
        }

        printf("输入的成绩无效，请输入0到100之间的数字。\n");
    }
}

static void read_name(char name[NAME_LENGTH]) {
    size_t start;
    size_t end;

    while (1) {
        read_line("请输入姓名：", name, NAME_LENGTH);

        end = strlen(name);
        start = 0;

        while (start < end && isspace((unsigned char)name[start])) {
            start++;
        }

        while (end > start && isspace((unsigned char)name[end - 1])) {
            end--;
        }

        if (end > start) {
            memmove(name, name + start, end - start);
            name[end - start] = '\0';
            return;
        }

        printf("姓名不能为空，请重新输入。\n");
    }
}

static int find_student_by_id(const Student student[], int count, int target_id) {
    int i;

    for (i = 0; i < count; i++) {
        if (student[i].id == target_id) {
            return i;
        }
    }

    return -1;
}

static void display_students(const Student students[], int count) {
    int i;

    if (count <= 0) {
        printf("当前没有学生记录。\n");
        return;
    }

    printf("\n%-12s %-24s %-10s\n", "学号", "姓名", "成绩");
    printf("--------------------------------------------------\n");

    for (i = 0; i < count; i++) {
        printf("%-12d %-24s %-10.2f\n", students[i].id, students[i].name, students[i].score);
    }
}

static float calculate_average(const Student students[], int count) {
    float sum = 0.0f;
    int i;

    if (count <= 0) {
        return 0.0f;
    }

    for (i = 0; i < count; i++) {
        sum += students[i].score;
    }

    return sum / count;
}

static int find_highest_score(const Student students[], int count) {
    int i;
    int max_index = 0;

    if (count <= 0) {
        return -1;
    }

    for (i = 1; i < count; i++) {
        if (students[i].score > students[max_index].score) {
            max_index = i;
        }
    }

    return max_index;
}

static void show_highest_score(const Student students[], int count) {
    int index = find_highest_score(students, count);

    if (index == -1) {
        printf("当前没有学生记录，无法查找最高分。\n");
        return;
    }

    printf("最高分学生是：学号%d，姓名%s，成绩%.2f\n", students[index].id, students[index].name,
           students[index].score);
}

static void modify_student_score(Student students[], int count) {
    int target_id;
    int index;
    float new_score;

    target_id = read_int_range("请输入要修改成绩的学生学号：", 1, 999999999);
    index = find_student_by_id(students, count, target_id);

    if (index == -1) {
        printf("未找到学号为%d的学生，成绩未修改。\n", target_id);
        return;
    }

    printf("当前学生：%s，原成绩：%.2f\n", students[index].name, students[index].score);

    new_score = read_score("请输入新的成绩（0-100）：");
    students[index].score = new_score;

    printf("成绩修改成功！\n");
}

static void input_student(Student *student, const Student students[], int existing_count) {
    int id;

    while (1) {
        id = read_int_range("请输入学号：", 1, 999999999);

        if (find_student_by_id(students, existing_count, id) == -1) {
            break;
        }

        printf("学号%d已存在，请重新输入。\n", id);
    }

    student->id = id;
    read_name(student->name);
    student->score = read_score("请输入成绩（0-100）：");
}

static void add_student(Student students[], int *count) {
    if (*count >= MAX_STUDENTS) {
        printf("学生人数已满，无法添加，最多保存%d名学生。\n", MAX_STUDENTS);
        return;
    }

    input_student(&students[*count], students, *count);
    (*count)++;

    printf("学生添加成功！目前共有%d名学生。\n", *count);
}

static void show_menu(void) {
    printf("\n====================学生成绩管理程序V1.0====================\n");
    printf("1.显示全部学生\n");
    printf("2.计算平均成绩\n");
    printf("3.查找最高成绩\n");
    printf("4.修改学生成绩\n");
    printf("5.添加学生记录\n");
    printf("0.退出程序\n");
    printf("============================================================\n");
}

int main(void) {
    Student students[MAX_STUDENTS];
    int count;
    int choice;
    int i;

    printf("学生成绩管理系统V1.0\n");

    count = read_int_range("请输入学生人数(3-100): ", 3, MAX_STUDENTS);

    for (i = 0; i < count; i++) {
        printf("\n录入第%d个学生\n", i + 1);
        input_student(&students[i], students, i);
    }

    while (1) {
        show_menu();
        choice = read_int_range("请输入菜单编号（0-5）: ", 0, 5);

        switch (choice) {
        case 1:
            display_students(students, count);
            break;

        case 2:
            printf("全体学生平均成绩：%.2f\n", calculate_average(students, count));
            break;

        case 3:
            show_highest_score(students, count);
            break;

        case 4:
            modify_student_score(students, count);
            break;

        case 5:
            add_student(students, &count);
            break;

        case 0:
            printf("退出系统。\n");
            return 0;

        default:
            printf("无效的选择，请重新输入。\n");
            break;
        }
    }
}
/*// ====================================================================
// 文件名：41_2026-10-26_学生成绩管理系统.c
// 作用：一个带菜单的"学生成绩管理系统"（最多 100 名学生）：
//       先输入学生人数（3～100）并逐个录入学号、姓名、成绩；然后进入菜单：
//       1 显示全部学生   2 计算平均成绩   3 查找最高成绩
//       4 修改学生成绩   5 添加学生记录   0 退出程序
//       重点是"可靠的输入"：每次都先读一整行文字，再检查它是不是合法的整数 / 成绩 / 姓名，
//       不合法就提示并让你重新输入，不会因为乱输入而卡住或崩溃。
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "41_2026-10-26_学生成绩管理系统.c"
//   运行：./demo
//   说明：编译 = 把人能看懂的 C 代码翻译成电脑能执行的程序。
//         -Wall -Wextra 的意思是"把所有可疑的地方都警告我"，
//         编译时出现警告，一定要弄明白原因，不要忽略。
// ====================================================================


// --------------------------------------------------------------------
// 第一部分：引入"工具箱"和宏定义
// --------------------------------------------------------------------
// <ctype.h> ：字符分类工具，isspace 判断一个字符是不是空白（空格、制表符、换行等）。
#include <ctype.h>

// <errno.h> ：errno 是"最近一次出错的原因编号"，ERANGE 表示"数字太大，超出范围"。
#include <errno.h>

// <math.h>  ：数学工具箱，这里用到 isfinite（判断一个小数是不是正常的数，排除 nan 和无穷大）。
#include <math.h>

// <stdio.h> ：printf、fgets、getchar 等输入输出函数。
#include <stdio.h>

// <stdlib.h>：strtol（字符串转整数）、strtof（字符串转小数）、exit（结束程序）、EXIT_FAILURE（"失败"的退出码）。
#include <stdlib.h>

// <string.h>：strlen（求字符串长度）、memmove（移动一块内存里的数据）。
#include <string.h>

// #define 叫"宏定义"：编译之前，程序里所有的 NAME_LENGTH 都会被替换成 50，MAX_STUDENTS 替换成 100（行末不写分号）。
// 这样姓名最大长度、学生人数上限只在这里写一次，要改只改这里。
#define NAME_LENGTH 50
#define MAX_STUDENTS 100


// --------------------------------------------------------------------
// 第二部分：定义结构体类型 Student
// --------------------------------------------------------------------
// struct { ... } 把一个学生的学号、姓名、成绩"打包"成一个整体；typedef ... Student; 给它起类型名 Student。
//   id    ：学号（整数）；
//   name  ：姓名（字符数组，最多 49 个字节，留 1 个放结束标志 '\0'）；
//   score ：成绩（float，可以带小数）。
typedef struct {
    int id;
    char name[NAME_LENGTH];
    float score;
} Student;


// --------------------------------------------------------------------
// 第三部分：函数声明
// --------------------------------------------------------------------
// 函数声明（函数原型）：只写函数头，末尾加分号。提前告诉编译器"后面有这些函数"，
// 这样函数的完整代码就可以放在后面，调用顺序也不受限制。
// static 表示这些函数只在本文件内部使用。const 表示函数只读不改那份数据。
// size_t 是表示"大小、个数"的无符号整数类型。
static void read_line(const char *prompt, char *buffer, size_t size);
static int read_int_range(const char *prompt, int min_value, int max_value);
static float read_score(const char *prompt);
static void read_name(char name[NAME_LENGTH]);
static int find_student_by_id(const Student student[], int count, int target_id);
static void display_students(const Student students[], int count);
static float calculate_average(const Student students[], int count);
static int find_highest_score(const Student students[], int count);
static void show_highest_score(const Student students[], int count);
static void modify_student_score(Student students[], int count);
static void add_student(Student students[], int *count);
static void input_student(Student *student, const Student students[], int existing_count);
static void show_menu(void);


// --------------------------------------------------------------------
// 第四部分：read_line —— 安全地读一行文字
// --------------------------------------------------------------------
// 作用：先显示提示语 prompt，再读一整行文字存进 buffer（最多 size - 1 个字符），并去掉末尾的换行符。
// 为什么不直接用 scanf？scanf 遇到格式不对的输入，会把没读完的内容留在输入里，下一次读取又出错，容易卡死。
// "先整行读进来，再检查"更可靠。后面所有输入都靠这个函数。
// 参数：prompt 提示语（只读）；buffer 存放结果的字符数组；size 是 buffer 的总大小。
static void read_line(const char *prompt, char *buffer, size_t size) {

    // ch 用来读单个字符。用 int 而不用 char：因为 getchar 在输入结束时返回 EOF（一个特殊的负数），char 可能装不下。
    int ch;
    size_t length;

    // %s 是字符串的占位符，显示提示语。
    printf("%s", prompt);

    // fgets(放在哪里, 最多读多少, 从哪里读)：读一整行文字，可以带空格。读失败或输入已经结束时返回 NULL。
    // (int)size 是强制类型转换，因为 fgets 要的是 int。
    if (fgets(buffer, (int)size, stdin) == NULL) {
        printf("\n检测到输入结束，程序退出。\n");

        // 输入已经没有了（比如按了 Ctrl+D），继续要求输入没有意义。exit 会立刻结束整个程序，不管现在在哪一层函数里。
        exit(EXIT_FAILURE);
    }

    // strlen 求字符串里（'\0' 之前）有多少个字符。
    length = strlen(buffer);

    // fgets 读到的一整行末尾通常带着换行符 '\n'。如果最后一个字符（下标 length - 1）是 '\n'，说明整行都读进来了。
    if (length > 0 && buffer[length - 1] == '\n') {
        // 把 '\n' 改成 '\0'（字符串结束标志），换行符就被去掉了。
        buffer[length - 1] = '\0';

    // 最后一个字符不是换行符，有两种可能：这一行太长，buffer 装不下；或者输入的最后一行没有以回车结束。
    } else {
        // 再偷看一个字符来区分这两种情况。
        ch = getchar();

        // 读到的既不是换行也不是输入结束，说明这一行还有内容没读完——输入太长了。
        if (ch != '\n' && ch != EOF) {
            // 把这一行剩下的内容全部读掉丢弃，免得影响下一次输入。循环体是空的：读掉字符这件事，条件里已经做完了。
            while ((ch = getchar()) != '\n' && ch != EOF) {
            }

            // 把 buffer 清成空字符串，表示这次输入作废。
            buffer[0] = '\0';
            printf("输入内容过长，请缩短后重试。\n");

        // 是"最后一行没有回车"的情况：内容已经完整在 buffer 里，补上结束标志即可。
        } else if (ch == EOF) {
            buffer[length] = '\0';
        }
    }
}


// --------------------------------------------------------------------
// 第五部分：read_int_range —— 读一个在指定范围内的整数
// --------------------------------------------------------------------
// 作用：反复提示，直到用户输入一个在 [min_value, max_value] 范围内的整数，然后返回它。
static int read_int_range(const char *prompt, int min_value, int max_value) {

    // input 存读进来的一行文字，最多 127 个字符。
    char input[128];

    // end 是"指向 char 的指针"，strtol 会用它告诉我们"数字读到哪里为止"。
    char *end;

    // value 存转换出来的整数（用 long，范围比 int 大，便于检查是否超出 int 的范围）。
    long value;

    // parsed 是标志变量：1 表示成功读到了数字，0 表示一个数字也没读到。
    int parsed;

    // 无限循环：输入合法就 return 退出函数；不合法就提示后重新来一轮。
    while (1) {
        // 读一整行文字存进 input。sizeof(input) 是 input 的总字节数（128）。
        read_line(prompt, input, sizeof(input));

        // 先把错误编号清成 0。因为 strtol 只在出错时才设置 errno，不先清零，就可能看到之前遗留的旧错误。
        errno = 0;

        // strtol(字符串, &end, 进制)：把字符串开头的数字转成 long。10 表示十进制。
        // &end 是 end 的地址（& 取地址符）：strtol 会把 end 设成"第一个没有被转换的字符的位置"。
        // 例：输入 "12abc"，转出 12，end 指向 'a'；输入 "abc"，一个数字也没转，end 就等于 input 的开头。
        value = strtol(input, &end, 10);

        // end 不等于 input 的开头，说明至少转换了一个数字；等于就说明一个数字也没读到。
        parsed = (end != input);

        // 允许数字后面有空白：跳过 end 处连续的空白字符。*end 是 end 指向的那个字符。
        // (unsigned char) 是强制类型转换：isspace 要求参数是非负数，所以要先转一下，避免出现负数而出错。
        while (isspace((unsigned char)*end)) {
            end++;
        }

        // 合法的整数要同时满足四个条件（&& 读作"并且"）：
        //   1) parsed：读到了数字；
        //   2) *end == '\0'：跳过空白后到达字符串末尾，说明整行都是这个数字，没有多余的字符（排除 12abc）；
        //   3) errno != ERANGE：没有超出 long 的范围；
        //   4) 值在 min_value 到 max_value 之间。
        // （这个条件被拆成了几个部分写，意思是一整个条件。）
        if (parsed && *end == '\0' && errno != ERANGE && value >= min_value && value <= max_value) {
            // 返回结果。(int) 是强制类型转换，把 long 转回 int（前面已经检查过范围，不会丢数据）。
            return (int)value;
        }

        // 不合法：提示后回到 while 的开头，让用户重新输入。
        printf("输入无效，请输入%d到%d之间的整数。\n", min_value, max_value);
    }
}


// --------------------------------------------------------------------
// 第六部分：read_score —— 读一个 0 到 100 的成绩
// --------------------------------------------------------------------
// 作用：反复提示，直到用户输入一个 0 到 100 之间的数字，然后返回它。结构和上一个函数几乎一样，只是读的是小数。
static float read_score(const char *prompt) {
    char input[128];
    char *end;
    float value;
    int parsed;

    while (1) {
        // 先整行读进来。
        read_line(prompt, input, sizeof(input));

        errno = 0;

        // strtof(字符串, &end)：把字符串开头的数字转成 float（小数）。end 的用法和 strtol 一样。
        value = strtof(input, &end);
        parsed = (end != input);

        // 跳过数字后面的空白。
        while (isspace((unsigned char)*end)) {
            end++;
        }

        // 合法的成绩要同时满足：读到了数字；整行没有多余字符；转换时没出错（errno == 0）；
        // isfinite(value)：是一个正常的数（排除 nan"不是数字"和 inf"无穷大"，它们 strtof 也能读出来）；
        // 并且值在 0.0 到 100.0 之间。0.0f 末尾的 f 表示 float 类型的小数。
        if (parsed && *end == '\0' && errno == 0 && isfinite(value) && value >= 0.0f &&
            value <= 100.0f) {
            // 合法，返回成绩。
            return value;
        }

        printf("输入的成绩无效，请输入0到100之间的数字。\n");
    }
}


// --------------------------------------------------------------------
// 第七部分：read_name —— 读姓名（去掉前后空格，不能为空）
// --------------------------------------------------------------------
// 参数 char name[NAME_LENGTH] 是字符数组，传进来的是数组的起始地址，所以函数里修改的就是调用者的数组。
static void read_name(char name[NAME_LENGTH]) {

    // start 和 end 是两个下标：start 指向姓名的第一个非空白字符，end 指向最后一个非空白字符的后面。
    size_t start;
    size_t end;

    while (1) {
        // 读一整行姓名。
        read_line("请输入姓名：", name, NAME_LENGTH);

        // 先让 end 指向字符串的末尾（'\0' 的位置），start 指向开头。
        end = strlen(name);
        start = 0;

        // 从前往后，跳过开头的空白：只要 start 还没到 end，并且 name[start] 是空白，start 就往后移一位。
        while (start < end && isspace((unsigned char)name[start])) {
            start++;
        }

        // 从后往前，跳过末尾的空白：end 往前移，直到 name[end - 1] 不是空白。
        // 例：输入 " 李四 "：开头 1 个空格，结尾 1 个空格，处理后 start 指向"李"，end 指向最后一个空格的位置。
        while (end > start && isspace((unsigned char)name[end - 1])) {
            end--;
        }

        // end 大于 start，说明去掉空白之后还有内容，姓名有效。
        if (end > start) {
            // memmove(目的地, 来源, 字节数)：把"去掉前后空白后的有效内容"挪到字符串的最前面。
            // name + start 是"从有效内容开头算起的地址"（指针加整数，按元素个数移动）。
            // 为什么用 memmove 而不是 memcpy？这里来源和目的地的范围可能有重叠，memmove 专门保证重叠时也能正确复制。
            memmove(name, name + start, end - start);

            // 在有效内容的后面补上结束标志 '\0'，把后面多余的空白截掉。
            name[end - start] = '\0';

            // return; 用在 void 函数里，表示"提前结束函数"。姓名处理好了，退出。
            return;
        }

        // 去掉空白后什么都没有（全是空格或者没输入）：提示后重新输入。
        printf("姓名不能为空，请重新输入。\n");
    }
}


// --------------------------------------------------------------------
// 第八部分：按学号查找学生
// --------------------------------------------------------------------
// 作用：在前 count 个学生里找学号等于 target_id 的，找到返回数组下标，找不到返回 -1。
// -1 不是合法的下标，所以可以表示"没有找到"。
static int find_student_by_id(const Student student[], int count, int target_id) {
    int i;

    // "顺序查找"：从第 0 个开始，一个一个看。
    for (i = 0; i < count; i++) {
        // 点 . 叫"成员访问运算符"：student[i].id 是第 i 个学生的学号。== 判断相等。
        if (student[i].id == target_id) {
            // 找到了，返回它的下标。return 会立刻结束函数。
            return i;
        }
    }

    // 整个数组都看完了也没找到。
    return -1;
}


// --------------------------------------------------------------------
// 第九部分：显示全部学生
// --------------------------------------------------------------------
static void display_students(const Student students[], int count) {
    int i;

    // 没有学生时，提示后用 return; 提前结束函数。
    if (count <= 0) {
        printf("当前没有学生记录。\n");
        return;
    }

    // %-12s 表示"字符串，左对齐，至少占 12 个字符宽"：- 表示左对齐，12 是最小宽度，不够的位置补空格。
    // 这样表头和下面每一行的各列才能对齐。注意：宽度是按"字节"算的，一个汉字占 3 个字节，所以含汉字的列可能没有完全对齐。
    printf("\n%-12s %-24s %-10s\n", "学号", "姓名", "成绩");
    printf("--------------------------------------------------\n");

    // 遍历每个学生，输出一行。
    for (i = 0; i < count; i++) {
        // %-12d 整数左对齐；%-24s 字符串左对齐；%-10.2f 是小数左对齐、保留两位小数、至少占 10 个字符宽。
        printf("%-12d %-24s %-10.2f\n", students[i].id, students[i].name, students[i].score);
    }
}


// --------------------------------------------------------------------
// 第十部分：计算平均成绩
// --------------------------------------------------------------------
static float calculate_average(const Student students[], int count) {

    // sum 存总分，先设成 0（0.0f 末尾的 f 表示 float）。
    float sum = 0.0f;
    int i;

    // 没有学生时，平均分没有意义，直接返回 0，避免下面做除以 0。
    if (count <= 0) {
        return 0.0f;
    }

    for (i = 0; i < count; i++) {
        // += 是"加上再赋值"：把每个学生的成绩累加进 sum。
        sum += students[i].score;
    }

    // 总分 ÷ 人数。sum 是 float，所以是小数除法，不会丢掉小数部分。
    return sum / count;
}


// --------------------------------------------------------------------
// 第十一部分：查找最高分
// --------------------------------------------------------------------
// 作用：返回最高分学生的下标，没有学生时返回 -1。
static int find_highest_score(const Student students[], int count) {
    int i;

    // "打擂台"：先假设第 0 个学生最高，记下他的下标（记下标而不是成绩，这样学号、姓名都能顺便找到）。
    int max_index = 0;

    if (count <= 0) {
        return -1;
    }

    // 从第 1 个学生开始挑战。
    for (i = 1; i < count; i++) {
        // 第 i 个学生的成绩比当前最高分学生还高，就换人。
        if (students[i].score > students[max_index].score) {
            max_index = i;
        }
    }

    return max_index;
}

// 先调用上面的函数拿到最高分学生的下标，再输出。
static void show_highest_score(const Student students[], int count) {
    int index = find_highest_score(students, count);

    // 返回 -1 说明没有学生记录，提示后 return; 结束函数。
    if (index == -1) {
        printf("当前没有学生记录，无法查找最高分。\n");
        return;
    }

    // students[index] 就是最高分的那名学生，取出他的学号、姓名、成绩。
    printf("最高分学生是：学号%d，姓名%s，成绩%.2f\n", students[index].id, students[index].name,
           students[index].score);
}


// --------------------------------------------------------------------
// 第十二部分：修改学生成绩
// --------------------------------------------------------------------
static void modify_student_score(Student students[], int count) {
    int target_id;
    int index;
    float new_score;

    // 调用前面写好的函数，读一个学号（合法范围 1 到 999999999）。输入检查都在函数里做好了，这里直接用。
    target_id = read_int_range("请输入要修改成绩的学生学号：", 1, 999999999);

    // 按学号查找，得到下标。
    index = find_student_by_id(students, count, target_id);

    // 没找到：提示后用 return; 提前结束函数，成绩不修改。
    if (index == -1) {
        printf("未找到学号为%d的学生，成绩未修改。\n", target_id);
        return;
    }

    // 先把这个学生的姓名和原成绩显示出来，让用户确认改的是谁。
    printf("当前学生：%s，原成绩：%.2f\n", students[index].name, students[index].score);

    // 读新成绩（0 到 100，不合法会一直要求重输）。
    new_score = read_score("请输入新的成绩（0-100）：");

    // 把新成绩写进这个学生的 score 成员，原来的成绩被覆盖。
    students[index].score = new_score;

    printf("成绩修改成功！\n");
}


// --------------------------------------------------------------------
// 第十三部分：录入一个学生
// --------------------------------------------------------------------
// 参数 Student *student：指向"要录入的那个学生"的指针，函数通过它直接填写 main 里的那个学生。
// students 和 existing_count：已有的学生和人数，用来检查学号有没有重复。
static void input_student(Student *student, const Student students[], int existing_count) {
    int id;

    // 无限循环：学号不重复就 break 退出，重复就提示后重新输入。
    while (1) {
        // 读一个合法的学号。
        id = read_int_range("请输入学号：", 1, 999999999);

        // 在已有的学生里查这个学号。返回 -1 表示没找到，学号没有被用过，可以使用。
        if (find_student_by_id(students, existing_count, id) == -1) {
            // break 的意思是"立刻跳出整个循环"。
            break;
        }

        printf("学号%d已存在，请重新输入。\n", id);
    }

    // -> 叫"通过指针访问成员"：student->id 的意思是"student 指向的那个学生的 id"。
    student->id = id;

    // student->name 是字符数组，作为参数传过去的就是数组的地址，所以 read_name 能直接把姓名写进去。
    read_name(student->name);

    // 读成绩并存进去。
    student->score = read_score("请输入成绩（0-100）：");
}


// --------------------------------------------------------------------
// 第十四部分：添加学生
// --------------------------------------------------------------------
// 参数 int *count：指向"学生人数"的指针。为什么用指针？函数里要把人数加 1，
// 如果只传 int 的值（复制品），函数里改了，main 里的人数不会变。传地址，函数就能直接修改 main 里的变量。
static void add_student(Student students[], int *count) {

    // *count 的意思是"count 指向的那个变量的值"。人数已经达到上限就不能再添加了。
    if (*count >= MAX_STUDENTS) {
        printf("学生人数已满，无法添加，最多保存%d名学生。\n", MAX_STUDENTS);
        return;
    }

    // &students[*count] 是"下一个空位的地址"（& 取地址符）：新学生就录入到这个位置。
    input_student(&students[*count], students, *count);

    // 人数加 1。括号不能省：(*count)++ 是"把 count 指向的值加 1"；
    // 写成 *count++ 会变成"先取值，再把指针往后移"，意思完全不同。
    (*count)++;

    printf("学生添加成功！目前共有%d名学生。\n", *count);
}


// --------------------------------------------------------------------
// 第十五部分：显示菜单
// --------------------------------------------------------------------
static void show_menu(void) {
    printf("\n====================学生成绩管理程序V1.0====================\n");
    printf("1.显示全部学生\n");
    printf("2.计算平均成绩\n");
    printf("3.查找最高成绩\n");
    printf("4.修改学生成绩\n");
    printf("5.添加学生记录\n");
    printf("0.退出程序\n");
    printf("============================================================\n");
}


// --------------------------------------------------------------------
// 第十六部分：main 函数 —— 程序从这里开始
// --------------------------------------------------------------------
int main(void) {

    // 结构体数组：能放 MAX_STUDENTS（100）个学生。count 是目前实际有几个学生。
    Student students[MAX_STUDENTS];
    int count;
    int choice;
    int i;

    printf("学生成绩管理系统V1.0\n");

    // 读学生人数，范围 3 到 MAX_STUDENTS。
    count = read_int_range("请输入学生人数(3-100): ", 3, MAX_STUDENTS);

    // for 循环：i 从 0 数到 count - 1，依次录入每个学生。
    for (i = 0; i < count; i++) {
        printf("\n录入第%d个学生\n", i + 1);

        // &students[i] 是第 i 个学生的地址，函数会往里面填数据。
        // 已有的学生是前 i 个，所以最后一个参数传 i，用来检查学号有没有和前面的重复。
        input_student(&students[i], students, i);
    }

    // 主菜单循环：无限循环，直到用户选 0 退出（case 0 里的 return）。
    while (1) {
        show_menu();

        // 读菜单编号，范围 0 到 5。
        choice = read_int_range("请输入菜单编号（0-5）: ", 0, 5);

        // switch 拿 choice 的值和每个 case 比较，匹配到哪个就从那里开始执行，遇到 break 就跳出 switch。
        // 适合"一个变量对应多个固定取值"的情况，比一长串 if ... else if 更清楚。
        switch (choice) {
        case 1:

            // 调用函数：数组名 students 传过去的是地址，所以函数能访问全部学生。
            display_students(students, count);

            // 每个 case 末尾都要写 break，否则会"穿透"，继续执行下一个 case 的代码。
            break;

        case 2:
            printf("全体学生平均成绩：%.2f\n", calculate_average(students, count));
            break;

        case 3:
            show_highest_score(students, count);
            break;

        case 4:
            modify_student_score(students, count);
            break;

        case 5:

            // &count 是 count 的地址：把地址传给 add_student，它才能修改 main 里的人数。
            add_student(students, &count);
            break;

        // 选 0：输出提示，然后 return 0 结束整个程序（跳出 while 和 main）。
        case 0:
            printf("退出系统。\n");
            return 0;

        // default 表示"所有 case 都不匹配时"。因为 read_int_range 已经限制了范围，这里其实不会走到，是以防万一。
        default:
            printf("无效的选择，请重新输入。\n");
            break;
        }
    }
}

// ====================================================================
// 测试时依次输入（每行输完按一次回车）：
//   3
//   1001
//   张三
//   90.5
//   1002
//    李四 
//   85
//   1001
//   1003
//   王五
//   abc
//   78
//   9
//   1
//   2
//   3
//   4
//   1002
//   95
//   5
//   1004
//   赵六
//   60
//   1
//   0
//
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   学生成绩管理系统V1.0
//   请输入学生人数(3-100):
//   录入第1个学生
//   请输入学号：请输入姓名：请输入成绩（0-100）：
//   录入第2个学生
//   请输入学号：请输入姓名：请输入成绩（0-100）：
//   录入第3个学生
//   请输入学号：学号1001已存在，请重新输入。
//   请输入学号：请输入姓名：请输入成绩（0-100）：输入的成绩无效，请输入0到100之间的数字。
//   请输入成绩（0-100）：
//   ====================学生成绩管理程序V1.0====================
//   1.显示全部学生
//   2.计算平均成绩
//   3.查找最高成绩
//   4.修改学生成绩
//   5.添加学生记录
//   0.退出程序
//   ============================================================
//   请输入菜单编号（0-5）: 输入无效，请输入0到5之间的整数。
//   请输入菜单编号（0-5）:
//   学号       姓名                   成绩
//   --------------------------------------------------
//   1001         张三                   90.50
//   1002         李四                   85.00
//   1003         王五                   78.00
//
//   ====================学生成绩管理程序V1.0====================
//   1.显示全部学生
//   2.计算平均成绩
//   3.查找最高成绩
//   4.修改学生成绩
//   5.添加学生记录
//   0.退出程序
//   ============================================================
//   请输入菜单编号（0-5）: 全体学生平均成绩：84.50
//
//   ……（中间省略 46 行）
//
//   ====================学生成绩管理程序V1.0====================
//   1.显示全部学生
//   2.计算平均成绩
//   3.查找最高成绩
//   4.修改学生成绩
//   5.添加学生记录
//   0.退出程序
//   ============================================================
//   请输入菜单编号（0-5）: 退出系统。
// ====================================================================
*/
