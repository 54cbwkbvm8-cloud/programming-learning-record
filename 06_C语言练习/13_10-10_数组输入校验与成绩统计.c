#include <stdio.h>
#define N 5
int main(void) {
    float scores[N];
    float sum = 0.0f;
    float average = 0.0f;
    float max;
    float min;
    printf("==========学生成绩管理系统==========\n");
    printf("请输入%d 个学生的成绩:\n", N);
    for (int i = 0; i < N; i++) {
        printf("请输入第%d 个学生的成绩:", i + 1);
        if (scanf("%f", &scores[i]) != 1) {
            printf("输入错误，请重新输入\n");
            int c;
            while ((c = getchar()) != '\n' && c != EOF)
                ;
            i--;
            continue;
        }
    }
    for (int i = 0; i < N; i++) {
        sum += scores[i];
    }
    average = sum / N;
    max = scores[0];
    min = scores[0];
    for (int i = 1; i < N; i++) {
        if (scores[i] > max) {
            max = scores[i];
        }
        if (scores[i] < min) {
            min = scores[i];
        }
    }
    printf("平均分：%.2f\n", average);
    printf("最高分：%.2f\n", max);
    printf("最低分：%.2f\n", min);
    return 0;
}
