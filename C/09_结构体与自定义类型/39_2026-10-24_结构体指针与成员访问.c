#include <stdio.h>
#define NAME_LENGTH 50
typedef struct {
    int id;
    char name[NAME_LENGTH];
    float score;
} Student;
void change_score(Student *p, float new_score) { p->score = new_score; }
int main(void) {
    Student s = {1001, "ZhangSan", 80.0f};
    Student *p = &s;
    float new_score = 95.5f;
    printf("=====结构体指针与->运算符=====\n");
    printf("结构体变量s的地址：%p\n", (void *)&s);
    printf("指针p保存的地址：%p\n", (void *)p);
    if (p == &s) {
        printf("检查结果：p指向s。\n");
    }
    printf("\n-----三种成员访问写法-----\n");
    printf("s.score = %.2f\n", s.score);
    printf("p->score = %.2f\n", p->score);
    printf("(*p).score = %.2f\n", (*p).score);
    printf("\n-----通过指针修改成绩-----\n");
    printf("调用函数前，s.score = %.2f\n", s.score);
    change_score(p, new_score);
    printf("调用函数后，s.score = %.2f\n", s.score);
    printf("调用函数后，p->score = %.2f\n", p->score);
    return 0;
}
