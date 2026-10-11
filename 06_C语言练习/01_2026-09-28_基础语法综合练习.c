#include <stdio.h>
int main(void) {
    char name[50]; // 预留名字空间
    int age;       // 存年龄
    float height;  // 存身高
    char initial;  // 存输入的个人信息(练习一的任务)
    int sum;       // 存两个数字的和}
    int number1;   //(练习三的任务){存入第一个数字
    int number2;   // 存入第二个数字
    int maximum;   // 存入两个数字的最大值}
    float score;   // 用来保存成绩
    char grade;    // 用来保存成绩等级
    printf("=== 练习一:个人信息输入与输出 ===\n");
    printf("请输入你的个人信息（名字、年龄、身高、个人信息)\n");
    printf("请输入你的名字：");
    scanf("%49s", name);
    printf("请输入你的年龄：");
    scanf("%d", &age);
    printf("请输入你的身高：");
    scanf("%f", &height);
    scanf(" %c", &initial);
    printf("====你的个人信息====\n");
    printf("你的名字是：%s\n", name);
    printf("你的年龄是：%d\n", age);
    printf("你的身高是：%.2f\n", height);
    printf("=== 练习二:两个数字的相加 ==\n");
    printf("请输入第一个数字：");
    scanf("%d", &number1);
    printf("请输入第二个数字：");
    scanf("%d", &number2);
    sum = number1 + number2;
    printf("两个数字的和是：%d\n", sum);
    printf("=== 练习三:两个数字的最大值 ==\n");
    printf("请输入第一个数字：");
    scanf("%d", &number1);
    printf("请输入第二个数字：");
    scanf("%d", &number2);
    maximum = number1 > number2 ? number1 : number2;
    printf("两个数字的最大值是：%d\n", maximum);
    printf("=== 练习四:两个数字的奇偶数判断 ===\n");
    printf("请输入一个数字：");
    scanf("%d", &number1);
    if (number1 % 2 == 0) {
        printf("这个数字是偶数\n");
    } else {
        printf("这个数字是奇数\n");
    }
    printf("请输入第二个数字：");
    scanf("%d", &number2);
    if (number2 % 2 == 0) {
        printf("这个数字是偶数\n");
    } else {
        printf("这个数字是奇数\n");
    }
    printf("=== 练习五:成绩等级判断 ===\n");
    printf("请输入 0 到 100 之间的成绩:");
    scanf("%f", &score);
    if (score < 0 || score > 100) {
        printf("成绩不合法,请输入 0～100 之间的数字\n");
    } else if (score >= 90) {
        grade = 'A';
    } else if (score >= 80) {
        grade = 'B';
    } else if (score >= 70) {
        grade = 'C';
    } else if (score >= 60) {
        grade = 'D';
    } else {
        grade = 'E';
    }
    if (score >= 0 && score <= 100) {
        printf("成绩 %.2f 分，等级是 %c。\n", score, grade);
    }
    printf("五个练习已全部完美完成。恭喜你！！！\n");
    return 0;
}
