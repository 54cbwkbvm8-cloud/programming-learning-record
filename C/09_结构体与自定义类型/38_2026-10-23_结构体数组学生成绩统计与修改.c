// 普通练习版create by YMZ
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
// 进阶练习版 create by GHY
/*#include <stdio.h>
#include <string.h>

#define STUDENT_COUNT 3
#define NAME_LENGTH 50
#define INPUT_LENGTH 256

typedef struct {
    int id;
    char name[NAME_LENGTH];
    float score;
} Student;

static int read_line(char *buffer, size_t size) {
    size_t length;
    int ch;

    if (fgets(buffer, (int)size, stdin) == NULL) {
        return 0;
    }

    length = strlen(buffer);
    if (length > 0 && buffer[length - 1] == '\n') {
        buffer[length - 1] = '\0';
        return 1;
    }

    if (!feof(stdin)) {
        while ((ch = getchar()) != '\n' && ch != EOF) {
        }
        return -1;
    }

    return 1;
}

static int read_student(Student *student, int number) {
    char line[INPUT_LENGTH];
    char extra;
    int status;

    while (1) {
        printf("第 %d 名学生：", number);
        status = read_line(line, sizeof(line));
        if (status == 0) {
            return 0;
        }
        if (status == -1 || sscanf(line, "%d %49s %f %c", &student->id, student->name,
                                   &student->score, &extra) != 3) {
            printf("输入格式错误，请按“学号 姓名 成绩”的格式重新输入，姓名不能包含空格。\n");
            continue;
        }
        return 1;
    }
}

static int read_integer(const char *prompt, int *value) {
    char line[INPUT_LENGTH];
    char extra;
    int status;

    while (1) {
        printf("%s", prompt);
        status = read_line(line, sizeof(line));
        if (status == 0) {
            return 0;
        }
        if (status == -1 || sscanf(line, "%d %c", value, &extra) != 1) {
            printf("输入格式错误，请输入一个整数并重新尝试。\n");
            continue;
        }
        return 1;
    }
}

static int read_score(const char *prompt, float *score) {
    char line[INPUT_LENGTH];
    char extra;
    int status;

    while (1) {
        printf("%s", prompt);
        status = read_line(line, sizeof(line));
        if (status == 0) {
            return 0;
        }
        if (status == -1 || sscanf(line, "%f %c", score, &extra) != 1) {
            printf("成绩输入格式错误，请输入一个数字并重新尝试。\n");
            continue;
        }
        return 1;
    }
}

static void print_students(const Student students[STUDENT_COUNT]) {
    int i;

    for (i = 0; i < STUDENT_COUNT; i++) {
        printf("学号：%d，姓名：%s，成绩：%.2f\n", students[i].id, students[i].name,
               students[i].score);
    }
}

static void calculate_results(const Student students[STUDENT_COUNT], float *average,
                              int *highest_index) {
    int i;
    float total = 0.0f;

    for (i = 0; i < STUDENT_COUNT; i++) {
        total += students[i].score;
    }
    *average = total / STUDENT_COUNT;

    *highest_index = 0;
    for (i = 1; i < STUDENT_COUNT; i++) {
        if (students[i].score > students[*highest_index].score) {
            *highest_index = i;
        }
    }
}

int main(void) {
    Student students[STUDENT_COUNT];
    int i;
    int target_id;
    int found = 0;
    int highest_index;
    float average;

    printf("=== 学生结构体数组与成绩处理 ===\n");
    printf("请依次输入 3 名学生的信息，格式：学号 姓名 成绩\n");

    for (i = 0; i < STUDENT_COUNT; i++) {
        if (!read_student(&students[i], i + 1)) {
            printf("输入已结束，程序退出。\n");
            return 1;
        }
    }

    printf("\n--- 初始学生信息 ---\n");
    print_students(students);
    calculate_results(students, &average, &highest_index);

    printf("\n修改前平均成绩：%.2f\n", average);
    printf("修改前最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highest_index].id,
           students[highest_index].name, students[highest_index].score);

    if (!read_integer("\n请输入要修改成绩的学生学号：", &target_id)) {
        printf("输入已结束，程序退出。\n");
        return 1;
    }

    for (i = 0; i < STUDENT_COUNT; i++) {
        if (students[i].id == target_id) {
            char prompt[INPUT_LENGTH];
            snprintf(prompt, sizeof(prompt), "请输入 %s 的新成绩：", students[i].name);
            if (!read_score(prompt, &students[i].score)) {
                printf("输入已结束，程序退出。\n");
                return 1;
            }
            found = 1;
            break;
        }
    }

    if (found) {
        printf("成绩修改成功。\n");
    } else {
        printf("未找到学号为 %d 的学生，成绩未修改。\n", target_id);
    }

    printf("\n--- 当前学生信息（修改后）---\n");
    print_students(students);
    calculate_results(students, &average, &highest_index);

    printf("\n修改后平均成绩：%.2f\n", average);
    printf("修改后最高分学生：学号 %d，姓名 %s，成绩 %.2f\n", students[highest_index].id,
           students[highest_index].name, students[highest_index].score);

    return 0;
}
*/
