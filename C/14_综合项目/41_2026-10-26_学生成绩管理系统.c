// 真的写的力竭了，md我真是服了，写的头疼😭😠，全是难点😠，真的写不下去了😭😭😭
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
