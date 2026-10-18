#include <stdio.h>
int main(void) {
    printf("1-100 之间的奇数：\n");
    int i;
    for (i = 1; i <= 100; i++) {
        if (i % 2 != 0) {
            printf("%d\n", i);
        }
    }
    printf("\n");
    printf("两数之和：\n");
    int nums[] = {2, 7, 11, 15};
    int target = 9;
    int m1;
    int n1;
    for (m1 = 0; m1 < 4; m1++) {
        for (n1 = m1 + 1; n1 < 4; n1++) {
            if (nums[m1] + nums[n1] == target) {
                printf("[%d,%d]\n", m1, n1);
            }
        }
    }
    printf("\n");
    printf("100 到 999 之间的水仙花数：\n");
    int numbers = 100;
    int a;
    int b;
    int c;
    for (numbers = 100; numbers < 1000; numbers++) {
        a = numbers / 100;
        b = numbers / 10 % 10;
        c = numbers % 10;
        if (a * a * a + b * b * b + c * c * c == numbers) {
            printf("%d\n", numbers);
        }
    }
    printf("\n");
    printf("巴什博弈：\n");
    int n;
    int m;
    int firstTake;

    printf("规则制定：\n");

    printf("请输入石子的总颗数：\n");
    if (scanf("%d", &n) != 1 || n <= 0) {
        printf("输入错误：石子总数必须是正整数。\n");
        return 1;
    }

    printf("请输入最多能取的石子数：\n");
    if (scanf("%d", &m) != 1 || m <= 0) {
        printf("输入错误：每次最多取石数必须是正整数。\n");
        return 1;
    }

    printf("=================================================\n");

    int remainder = n % (m + 1);

    if (remainder == 0) {
        printf("提示：当前局面先手没有必胜策略。\n");
    } else {
        printf("提示：按照必胜策略，你应该先取 %d 颗石子。\n", remainder);
    }

    printf("请输入你先手取的石子数（1 至 %d）：\n", m);

    if (scanf("%d", &firstTake) != 1 || firstTake < 1 || firstTake > m || firstTake > n) {
        printf("输入错误：先手取石数不符合规则。\n");
        return 1;
    }

    printf("巴什博弈演示：\n");
    printf("============================================\n");
    printf("初始石子数为 %d，每次可以取 1 至 %d 颗。\n", n, m);

    printf("你先取 %d 颗，剩余 %d 颗。\n", firstTake, n - firstTake);

    n -= firstTake;

    printf("============================================\n");

    while (n > 0) {
        int opponentTake;

        printf("请输入对手本轮取几颗（1 至 %d，当前剩余 %d）：", m, n);

        if (scanf("%d", &opponentTake) != 1) {
            printf("输入无效，请重新运行程序并输入整数。\n");
            return 1;
        }

        if (opponentTake < 1 || opponentTake > m || opponentTake > n) {
            printf("这次取法不合法，请重新输入。\n");
            continue;
        }

        n -= opponentTake;

        printf("对手取走 %d 颗后，还剩 %d 颗。\n", opponentTake, n);

        if (n == 0) {
            printf("对手取到了最后一颗石子，对手获胜。\n");
            break;
        }

        int myTake = m + 1 - opponentTake;

        if (myTake > n) {
            myTake = n;
        }

        n -= myTake;

        printf("你回应取走 %d 颗，还剩 %d 颗。\n", myTake, n);

        if (n == 0) {
            printf("你取到了最后一颗石子，你获胜。\n");
            break;
        }
    }
    return 0;
}
