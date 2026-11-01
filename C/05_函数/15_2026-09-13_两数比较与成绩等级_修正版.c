#include <stdio.h>

void compare_two_numbers(void)
{
    double first_number;
    double second_number;
    double larger_number;

    printf("请输入两个数字，两个数字用空格隔开：\n");
    scanf("%lf %lf", &first_number, &second_number);

    if (first_number > second_number)
    {
        larger_number = first_number;
    }
    else if (first_number < second_number)
    {
        larger_number = second_number;
    }
    else
    {
        printf("两个数字一样大：%.2f\n", first_number);
        return;
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
        printf("成绩不合法，必须在 0 到 100 之间。\n");
    }
    else if (score >= 90.0)
    {
        printf("优秀\n");
    }
    else if (score >= 80.0)
    {
        printf("良好\n");
    }
    else if (score >= 70.0)
    {
        printf("中等\n");
    }
    else if (score >= 60.0)
    {
        printf("及格\n");
    }
    else
    {
        printf("不及格\n");
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
// 文件名：15_2026-09-13_两数比较与成绩等级_修正版.c
// 作用：用两个函数演示"输入 → 判断 → 输出"：
//       compare_two_numbers ：输入两个数，输出较大的那个；两数相等时给出单独的提示
//       check_grade         ：输入成绩，输出等级（优秀 / 良好 / 中等 / 及格 / 不及格）
//       main 依次调用这两个函数。
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "15_2026-09-13_两数比较与成绩等级_修正版.c"
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
// 函数头的读法：void 表示这个函数不返回值，只负责做事；compare_two_numbers 是函数名；(void) 表示不需要参数。
// { 单独写一行是另一种书写风格，和写在行尾意思一样。
void compare_two_numbers(void)
{

    // double 是"双精度小数"类型，比 float 更精确。三个变量分别存：第一个数、第二个数、较大的数。
    double first_number;
    double second_number;
    double larger_number;

    printf("请输入两个数字，两个数字用空格隔开：\n");

    // 读 double 要用 %lf；两个 %lf 依次对应 &first_number、&second_number，输入时用空格隔开。
    // & 是取地址符：scanf 要把读到的数放进变量，必须知道变量的地址。
    // 例：输入 6 6，两个变量都是 6.0。
    scanf("%lf %lf", &first_number, &second_number);

    // 这个版本把比较分成三种情况：第一个更大、第二个更大、两个一样大。
    if (first_number > second_number)
    {
        larger_number = first_number;
    }

    // 第二个更大。
    else if (first_number < second_number)
    {
        larger_number = second_number;
    }

    // 走到这里，说明既不是"第一个更大"，也不是"第二个更大"，只剩"两个一样大"。
    // 这种情况没有"较大的数"可言，所以单独处理。
    else
    {
        // %.2f 表示显示小数时保留两位。
        printf("两个数字一样大：%.2f\n", first_number);

        // return; 用在 void 函数里，表示"提前结束这个函数"（void 函数没有返回值，所以 return 后面什么都不写）。
        // 这样下面"输出较大的数字"那一行就不会执行了。
        return;
    }

    // 只有前两种情况才会走到这里，此时 larger_number 一定已经被赋过值，可以安全输出。
    printf("较大的数字是：%.2f\n", larger_number);
}


// --------------------------------------------------------------------
// 第三部分：函数 2 —— 成绩等级判断
// --------------------------------------------------------------------
void check_grade(void)
{

    // 成绩用 double，可以输入带小数的成绩，比如 72.5。
    double score;

    printf("请输入一个成绩（0 到 100）：\n");
    scanf("%lf", &score);

    // || 读作"或者"：先把不合法的成绩挑出来（小于 0 或大于 100）。
    if (score < 0.0 || score > 100.0)
    {
        printf("成绩不合法，必须在 0 到 100 之间。\n");
    }

    // 从上往下依次检查，能走到这一行，说明前面的条件都没成立，所以只需要写下限。
    // 例：输入 72.5：不合法？否；>= 90？否；>= 80？否；>= 70？是，输出"中等"，后面不再检查。
    else if (score >= 90.0)
    {
        printf("优秀\n");
    }
    else if (score >= 80.0)
    {
        printf("良好\n");
    }
    else if (score >= 70.0)
    {
        printf("中等\n");
    }
    else if (score >= 60.0)
    {
        printf("及格\n");
    }

    // 以上都不成立，说明成绩在 0 到 59.99 之间，不及格。
    else
    {
        printf("不及格\n");
    }
}


// --------------------------------------------------------------------
// 第四部分：main 函数 —— 程序从这里开始
// --------------------------------------------------------------------
int main(void)
{

    // 调用函数：写出函数名再加一对括号，程序就跳进函数里执行，执行完再回到下一行。
    compare_two_numbers();

    // 输出一个空行，把两个功能的输出隔开。
    printf("\n");
    check_grade();

    // return 0：结束 main，把 0 交给系统，表示"程序正常结束"。
    return 0;
}

// ====================================================================
// 测试时依次输入（每行输完按一次回车）：
//   6 6
//   72.5
//
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   请输入两个数字，两个数字用空格隔开：
//   两个数字一样大：6.00
//
//   请输入一个成绩（0 到 100）：
//   中等
// ====================================================================
*/
