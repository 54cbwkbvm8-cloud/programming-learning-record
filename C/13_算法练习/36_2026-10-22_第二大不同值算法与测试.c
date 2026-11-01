#include <stddef.h>
#include <stdio.h>
int findSecondLargestDistinct(const int arr[], int n, int *answer) {
    if (arr == NULL || answer == NULL || n < 2) {
        return 0;
    }
    int largest = arr[0];
    int second = 0;
    int hasSecond = 0;
    for (int i = 1; i < n; i++) {
        if (arr[i] > largest) {
            second = largest;
            largest = arr[i];
            hasSecond = 1;
        } else if (arr[i] < largest) {
            if (hasSecond == 0 || arr[i] > second) {
                second = arr[i];
                hasSecond = 1;
            }
        }
    }
    if (hasSecond == 0) {
        return 0;
    }
    *answer = second;
    return 1;
}
void printArray(const int arr[], int n) {
    if (arr == NULL || n <= 0) {
        printf("[]");
        return;
    }
    printf("[");
    for (int i = 0; i < n; i++) {
        printf("%d", arr[i]);
        if (i < n - 1) {
            printf(", ");
        }
    }
    printf("]");
}
void runTest(const char *caseName, const int arr[], int n) {
    int answer = 0;
    printf("%s: ", caseName);
    printArray(arr, n);
    if (findSecondLargestDistinct(arr, n, &answer) == 1) {
        printf(" -> 第二大不同值 = %d\n", answer);
    } else {
        printf(" -> 不存在第二大不同值(或者输入无效)\n");
    }
}
int main(void) {
    int sample[] = {4, 8, 8, 3, 6};
    int allEqual[] = {5, 5, 5, 5};
    int repeatedMax[] = {1, 9, 9, 7, 9};
    int negativeMax[] = {-8, -2, -2, -5};
    int twoDifferent[] = {3, 4};
    int oneElement[] = {42};
    runTest("题目示例", sample, (int)(sizeof(sample) / sizeof(sample[0])));
    runTest("所有元素相同", allEqual, (int)(sizeof(allEqual) / sizeof(allEqual[0])));
    runTest("最大值重复", repeatedMax, (int)(sizeof(repeatedMax) / sizeof(repeatedMax[0])));
    runTest("负数数组", negativeMax, (int)(sizeof(negativeMax) / sizeof(negativeMax[0])));
    runTest("两个不同元素", twoDifferent, (int)(sizeof(twoDifferent) / sizeof(twoDifferent[0])));
    runTest("只有一个元素", oneElement, (int)(sizeof(oneElement) / sizeof(oneElement[0])));
    runTest("空数组", NULL, 0);
    return 0;
}
/*// ====================================================================
// 文件名：36_2026-10-22_第二大不同值算法与测试.c
// 作用：在数组里找"第二大的不同值"（"不同"的意思：重复的最大值不算第二大），并用 7 组测试用例检验。
//       例：{4, 8, 8, 3, 6}：最大是 8，第二大的不同值是 6（不是第二个 8）。
//       边界情况：所有元素相同、元素不足两个、空数组，都要给出"不存在"。
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "36_2026-10-22_第二大不同值算法与测试.c"
//   运行：./demo
//   说明：编译 = 把人能看懂的 C 代码翻译成电脑能执行的程序。
//         -Wall -Wextra 的意思是"把所有可疑的地方都警告我"，
//         编译时出现警告，一定要弄明白原因，不要忽略。
// ====================================================================


// --------------------------------------------------------------------
// 第一部分：引入"工具箱"
// --------------------------------------------------------------------
// <stddef.h> 提供 NULL（空指针）。
#include <stddef.h>

// <stdio.h> 是"标准输入输出"工具箱，printf 在里面。
#include <stdio.h>


// --------------------------------------------------------------------
// 第二部分：查找第二大的不同值
// --------------------------------------------------------------------
// 参数：arr 是数组（const 表示只读不改）；n 是元素个数；answer 是"输出参数"：int * 指向调用者的一个 int 变量，
// 函数通过它把答案"带出去"。
// 返回值：找到返回 1，找不到返回 0（C 里习惯用 1 表示"是"，0 表示"否"）。
// 为什么要用输出参数？返回值只有一个，但这里既要告诉调用者"有没有答案"，又要给出答案本身。
int findSecondLargestDistinct(const int arr[], int n, int *answer) {

    // 先检查参数是否合法：数组指针不能是 NULL（空指针），answer 也不能是 NULL，元素个数至少要有 2 个。
    // || 读作"或者"，任何一个成立都说明参数无效。|| 有"短路"特点：左边成立，右边就不用检查。
    if (arr == NULL || answer == NULL || n < 2) {
        // 参数无效，返回 0，表示"没有结果"。
        return 0;
    }

    // largest 记录目前见过的最大值，先假设第一个元素最大。
    int largest = arr[0];

    // second 记录目前见过的"第二大的不同值"。这里的 0 只是占位，并不代表真有一个值是 0。
    int second = 0;

    // hasSecond 是"标志变量"：0 表示还没找到第二大的值，1 表示 second 里已经有有效的值了。
    // 为什么需要它？不能用 second == 0 来判断，因为 0 也可能是真实的数据（比如负数数组里的 0）。
    int hasSecond = 0;

    // 从第 2 个元素（下标 1）开始扫描，第 1 个已经放进 largest 了。
    for (int i = 1; i < n; i++) {
        // 情况 1：当前这个数比目前的最大值还大。
        if (arr[i] > largest) {
            // 原来的最大值就"降级"成了第二大。
            second = largest;

            // 当前这个数成为新的最大值。
            largest = arr[i];

            // 这时 second 里存的是原来的最大值，是有效的值（而且一定比新的 largest 小，所以是"不同值"）。
            hasSecond = 1;

        // 情况 2：当前这个数比最大值小（严格小于）。等于最大值的数直接忽略，所以重复的最大值不会被当成第二大。
        } else if (arr[i] < largest) {
            // 两种情况都应该更新 second：还没有第二大的值（hasSecond 是 0）；或者当前这个数比已有的第二大还大。
            if (hasSecond == 0 || arr[i] > second) {
                // 更新第二大的值。
                second = arr[i];
                hasSecond = 1;
            }
        }
    }

    // 扫描完之后，如果一直没有找到第二大的值，说明所有元素都等于最大值（全相同），返回 0。
    if (hasSecond == 0) {
        return 0;
    }

    // *answer 的意思是"answer 指向的那个变量"。把 second 写进去，调用者就拿到答案了。
    // 例：数组 {4, 8, 8, 3, 6}：largest 先是 4；8 > 4，second 变 4，largest 变 8；
    //     第二个 8 等于最大值，忽略；3 < 8，但 3 > 4 不成立，不更新；6 < 8 并且 6 > 4，second 变 6。答案是 6。
    *answer = second;

    // 返回 1，表示找到了。
    return 1;
}


// --------------------------------------------------------------------
// 第三部分：输出数组的函数
// --------------------------------------------------------------------
// 把数组输出成 [4, 8, 8, 3, 6] 的样子。void 表示没有返回值。
void printArray(const int arr[], int n) {

    // 空指针或者没有元素时，输出 []。
    if (arr == NULL || n <= 0) {
        printf("[]");

        // return; 用在 void 函数里，表示"提前结束函数"。
        return;
    }
    printf("[");

    // 依次输出每个元素。
    for (int i = 0; i < n; i++) {
        printf("%d", arr[i]);

        // 最后一个元素后面不需要逗号，所以只有"不是最后一个"时才输出逗号和空格。
        if (i < n - 1) {
            printf(", ");
        }
    }
    printf("]");
}


// --------------------------------------------------------------------
// 第四部分：运行一组测试
// --------------------------------------------------------------------
// caseName 是"测试名称"，类型 const char * 表示"指向字符的指针"，用来传字符串（字符串常量不能修改，所以加 const）。
// 作用：输出数组，调用算法，输出结果。
void runTest(const char *caseName, const int arr[], int n) {

    // answer 用来接收算法带回来的答案。
    int answer = 0;

    // %s 是字符串的占位符，输出测试名称。
    printf("%s: ", caseName);
    printArray(arr, n);

    // 调用函数。&answer 是 answer 的地址（& 取地址符），函数通过它把答案写回 answer。
    // 返回 1 表示找到了第二大的不同值，就输出答案；否则输出"不存在"。
    if (findSecondLargestDistinct(arr, n, &answer) == 1) {
        printf(" -> 第二大不同值 = %d\n", answer);
    } else {
        printf(" -> 不存在第二大不同值(或者输入无效)\n");
    }
}


// --------------------------------------------------------------------
// 第五部分：main 函数 —— 准备测试数据，逐组测试
// --------------------------------------------------------------------
int main(void) {

    // 准备 6 个测试数组（方括号里不写大小，由大括号里的数据个数决定）：
    //   sample      ：题目示例，答案是 6；
    //   allEqual    ：所有元素都相同，不存在第二大；
    //   repeatedMax ：最大值重复，答案是 7；
    //   negativeMax ：全是负数，答案是 -5；
    //   twoDifferent：两个不同的数，答案是 3；
    //   oneElement  ：只有一个元素，不存在。
    int sample[] = {4, 8, 8, 3, 6};
    int allEqual[] = {5, 5, 5, 5};
    int repeatedMax[] = {1, 9, 9, 7, 9};
    int negativeMax[] = {-8, -2, -2, -5};
    int twoDifferent[] = {3, 4};
    int oneElement[] = {42};

    // sizeof(sample) / sizeof(sample[0]) 求元素个数：整个数组的字节数 ÷ 一个元素的字节数；
    // 前面的 (int) 是强制类型转换，把结果转成 int 再传给参数 n。
    runTest("题目示例", sample, (int)(sizeof(sample) / sizeof(sample[0])));
    runTest("所有元素相同", allEqual, (int)(sizeof(allEqual) / sizeof(allEqual[0])));
    runTest("最大值重复", repeatedMax, (int)(sizeof(repeatedMax) / sizeof(repeatedMax[0])));
    runTest("负数数组", negativeMax, (int)(sizeof(negativeMax) / sizeof(negativeMax[0])));
    runTest("两个不同元素", twoDifferent, (int)(sizeof(twoDifferent) / sizeof(twoDifferent[0])));
    runTest("只有一个元素", oneElement, (int)(sizeof(oneElement) / sizeof(oneElement[0])));

    // 最后一个测试：传入 NULL 和 0，模拟"空数组"，算法应该给出"不存在"，而不会崩溃。
    runTest("空数组", NULL, 0);

    // return 0：结束 main，把 0 交给系统，表示"程序正常结束"。
    return 0;
}

// ====================================================================
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   题目示例: [4, 8, 8, 3, 6] -> 第二大不同值 = 6
//   所有元素相同: [5, 5, 5, 5] -> 不存在第二大不同值(或者输入无效)
//   最大值重复: [1, 9, 9, 7, 9] -> 第二大不同值 = 7
//   负数数组: [-8, -2, -2, -5] -> 第二大不同值 = -5
//   两个不同元素: [3, 4] -> 第二大不同值 = 3
//   只有一个元素: [42] -> 不存在第二大不同值(或者输入无效)
//   空数组: [] -> 不存在第二大不同值(或者输入无效)
// ====================================================================
*/
