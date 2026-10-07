#include <stdio.h>
int max(int a[], int n);
int min(int a[], int n);
float sum(int a[], int n);
int max(int a[], int n) {
    int i, max = a[0];
    for (i = 1; i < n; i++) {
        if (a[i] > max) {
            max = a[i];
        }
    }
    return max;
}
int min(int a[], int n) {
    int i, min = a[0];
    for (i = 1; i < n; i++) {
        if (a[i] < min) {
            min = a[i];
        }
    }
    return min;
}
float sum(int a[], int n) {
    int i, sum = 0;
    for (i = 0; i < n; i++) {
        sum += a[i];
    }
    return sum;
}
int main(void) {
    int age;
    float score;
    printf("请输入年龄：");
    scanf("%d", &age);
    printf("请输入你的成绩：");
    scanf("%f", &score);
    char grade;
    if (score >= 60) {
        grade = 'A';
    } else if (score >= 50) {
        grade = 'B';
    } else if (score >= 40) {
        grade = 'C';
    } else if (score >= 30) {
        grade = 'D';
    } else {
        grade = 'E';
    }
    printf("你的年龄是 %d 岁\n你的成绩是 %f\n你的等级是 %c\n", age, score, grade);
    printf("用 for 循环输出 1 到 10 的和\n");
    int total = 0;
    int i;
    for (i = 1; i <= 10; i++) {
        total += i;
    }
    printf("1 到 10 的和是 %d\n", total);
    printf("用 while 循环输出 1 到 10 的和\n");
    total = 0;
    i = 1;
    while (i <= 10) {
        total += i;
        i++;
    }
    printf("1 到 10 的和是 %d\n", total);
    int a[10];
    printf("请输入 10 个数：\n");
    for (i = 0; i < 10; i++) {
        printf("请输入第 %d 个数：", i + 1);
        scanf("%d", &a[i]);
    }
    printf("这十个数的最大值是：%d\n", max(a, 10));
    printf("这十个数的最小值是：%d\n", min(a, 10));
    printf("这十个数的和是：%.2f\n", sum(a, 10));
    return 0;
}
