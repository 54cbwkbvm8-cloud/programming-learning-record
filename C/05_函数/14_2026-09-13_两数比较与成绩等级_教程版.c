#include <stdio.h>

void compare_two_numbers(void)
{
    double first_number;
    double second_number;
    double larger_number;

    printf("请输入两个数字，中间用空格分开：\n");
    scanf("%lf %lf", &first_number, &second_number);

    if (first_number > second_number)
    {
        larger_number = first_number;
    }
    else
    {
        larger_number = second_number;
    }

    printf("较大的数字是：%.2f\n", larger_number);
}

void check_grade(void)
{
    double score;

    printf("请输入一个成绩（0 到 100）：\n");
    scanf("%lf", &score);

    if (score < 0.0 || score > 100.0)
    {
        printf("成绩必须在 0 到 100 之间。\n");
    }
    else if (score >= 90.0)
    {
        printf("等级：A（优秀）\n");
    }
    else if (score >= 80.0)
    {
        printf("等级：B（良好）\n");
    }
    else if (score >= 70.0)
    {
        printf("等级：C（中等）\n");
    }
    else if (score >= 60.0)
    {
        printf("等级：D（及格）\n");
    }
    else
    {
        printf("等级：E（不及格）\n");
    }
}

int main(void)
{
    compare_two_numbers();
    printf("\n");
    check_grade();

    return 0;
}
/*// ====================================================================
// 文件名：14_2026-09-13_两数比较与成绩等级_教程版.c
// 作用：用两个函数演示"输入 → 判断 → 输出"：
//       compare_two_numbers ：输入两个数，输出较大的那个
//       check_grade         ：输入成绩，输出等级 A～E
//       main 依次调用这两个函数。
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "14_2026-09-13_两数比较与成绩等级_教程版.c"
//   运行：./demo
//   说明：编译 = 把人能看懂的 C 代码翻译成电脑能执行的程序。
//         -Wall -Wextra 的意思是"把所有可疑的地方都警告我"，
//         编译时出现警告，一定要弄明白原因，不要忽略。
// ====================================================================


// --------------------------------------------------------------------
// 第一部分：引入"工具箱"
// --------------------------------------------------------------------
// <stdio.h> 是"标准输入输出"工具箱：printf（输出）和 scanf（输入）都在里面。
#include <stdio.h>


// --------------------------------------------------------------------
// 第二部分：函数 1 —— 比较两个数
// --------------------------------------------------------------------
// 函数头的读法：
//   void                ：不返回值，这个函数只负责"做事"（输入、比较、输出）；
//   compare_two_numbers ：函数名；
//   (void)              ：不需要任何参数。
// 把"输入、比较、输出"都放进这个函数，main 里只需要调用一下。
// 这份代码把 { 单独写一行，是另一种常见的书写风格，意思和写在行尾完全一样。
void compare_two_numbers(void)
{

    // double 是"双精度小数"类型，比 float 精度更高，C 里处理小数一般优先用 double。
    // 这三个变量分别存：第一个数、第二个数、较大的数。
    double first_number;
    double second_number;
    double larger_number;

    printf("请输入两个数字，中间用空格分开：\n");

    // scanf 从键盘读数据。读 double 要用 %lf（l 表示 long，f 表示小数）；注意 printf 输出时用 %f 就可以。
    // 两个 %lf 依次对应 &first_number 和 &second_number，输入时用空格隔开。
    // & 是取地址符：scanf 要把读到的数放进变量，必须知道变量的地址。
    // 例：输入 3.5 8.25，first_number 是 3.5，second_number 是 8.25。
    scanf("%lf %lf", &first_number, &second_number);

    // 比较两个数的大小。
    if (first_number > second_number)
    {
        // 第一个数更大，就把它放进 larger_number。
        larger_number = first_number;
    }

    // 否则（第二个数更大，或者两个数相等），就取第二个数。
    else
    {
        larger_number = second_number;
    }

    // %.2f 表示显示 double 时保留两位小数。例：8.25 显示 8.25。
    printf("较大的数字是：%.2f\n", larger_number);
}


// --------------------------------------------------------------------
// 第三部分：函数 2 —— 成绩等级判断
// --------------------------------------------------------------------
// 同样没有参数、没有返回值。
void check_grade(void)
{

    // 成绩用 double，这样可以输入带小数的成绩，比如 85.5。
    double score;

    printf("请输入一个成绩（0 到 100）：\n");
    scanf("%lf", &score);

    // || 读作"或者"：只要有一边成立，整个条件就成立。先把不合法的成绩挑出来。
    // 成绩是 double，所以比较的数也写成 0.0、100.0，类型一致。
    if (score < 0.0 || score > 100.0)
    {
        printf("成绩必须在 0 到 100 之间。\n");
    }

    // 从上往下依次检查，能走到这一行，说明前面的条件都没成立，所以只需要写下限。
    // 例：输入 85：不合法？否；>= 90？否；>= 80？是，输出"等级：B（良好）"，后面不再检查。
    else if (score >= 90.0)
    {
        printf("等级：A（优秀）\n");
    }
    else if (score >= 80.0)
    {
        printf("等级：B（良好）\n");
    }
    else if (score >= 70.0)
    {
        printf("等级：C（中等）\n");
    }
    else if (score >= 60.0)
    {
        printf("等级：D（及格）\n");
    }

    // 以上条件都不成立，说明成绩在 0 到 59.99 之间，不及格。
    else
    {
        printf("等级：E（不及格）\n");
    }
}


// --------------------------------------------------------------------
// 第四部分：main 函数 —— 程序从这里开始
// --------------------------------------------------------------------
int main(void)
{

    // 调用函数：写出函数名再加一对括号，程序就跳到函数里执行，执行完再回到这里的下一行。
    compare_two_numbers();

    // 输出一个空行，把两个功能的输出隔开。
    printf("\n");
    check_grade();

    // return 0：结束 main，把 0 交给系统，表示"程序正常结束"。
    return 0;
}

// ====================================================================
// 测试时依次输入（每行输完按一次回车）：
//   3.5 8.25
//   85
//
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   请输入两个数字，中间用空格分开：
//   较大的数字是：8.25
//
//   请输入一个成绩（0 到 100）：
//   等级：B（良好）
// ====================================================================
*/
