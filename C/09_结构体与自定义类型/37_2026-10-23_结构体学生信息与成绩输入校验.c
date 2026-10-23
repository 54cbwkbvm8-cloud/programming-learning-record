#include <stdio.h>

struct Student {
    char name[50];
    int age;
    float score;
};

int main(void) {
    struct Student student1 = {"张三", 18, 90.5f};
    struct Student student2 = {"李四", 19, 85.0f};

    printf("==============修改前的成绩==============\n");
    printf("学生1的姓名：%s，年龄：%d，成绩：%.2f\n", student1.name, student1.age, student1.score);
    printf("学生2的姓名：%s，年龄：%d，成绩：%.2f\n", student2.name, student2.age, student2.score);

    printf("==========现在开始修改学生成绩==========\n");

    float a;

    while (1) {
        int ch;

        printf("请输入学生1的成绩（0-100）：\n");

        if (scanf("%f", &a) != 1) {
            while ((ch = getchar()) != '\n' && ch != EOF) {
            }

            if (ch == EOF) {
                return 1;
            }

            printf("输入格式错误，请输入数字。\n");
            continue;
        }

        ch = getchar();

        while (ch == ' ' || ch == '\t' || ch == '\r') {
            ch = getchar();
        }

        if (ch != '\n' && ch != EOF) {
            while (ch != '\n' && ch != EOF) {
                ch = getchar();
            }

            printf("输入格式错误，请输入一个完整的数字。\n");

            if (ch == EOF) {
                return 1;
            }

            continue;
        }

        if (!(a >= 0 && a <= 100)) {
            printf("成绩必须在0到100之间，请重新输入。\n");

            if (ch == EOF) {
                return 1;
            }

            continue;
        }

        break;
    }

    student1.score = a;

    float b;

    while (1) {
        int ch;

        printf("请输入学生2的成绩（0-100）：\n");

        if (scanf("%f", &b) != 1) {
            while ((ch = getchar()) != '\n' && ch != EOF) {
            }

            if (ch == EOF) {
                return 1;
            }

            printf("输入格式错误，请输入数字。\n");
            continue;
        }

        ch = getchar();

        while (ch == ' ' || ch == '\t' || ch == '\r') {
            ch = getchar();
        }

        if (ch != '\n' && ch != EOF) {
            while (ch != '\n' && ch != EOF) {
                ch = getchar();
            }

            printf("输入格式错误，请输入一个完整的数字。\n");

            if (ch == EOF) {
                return 1;
            }

            continue;
        }

        if (!(b >= 0 && b <= 100)) {
            printf("成绩必须在0到100之间，请重新输入。\n");

            if (ch == EOF) {
                return 1;
            }

            continue;
        }

        break;
    }

    student2.score = b;

    printf("==============修改后的成绩==============\n");
    printf("学生1的姓名：%s，年龄：%d岁，成绩：%.2f分\n", student1.name, student1.age,
           student1.score);
    printf("学生2的姓名：%s，年龄：%d岁，成绩：%.2f分\n", student2.name, student2.age,
           student2.score);

    return 0;
}
