#include <stdio.h>

int main(void) {
    int stones;
    int maxTake;
    int firstTake;

    printf("规则制定：\n");

    printf("请输入石子的总颗数：\n");
    if (scanf("%d", &stones) != 1 || stones <= 0) {
        printf("输入错误：石子总数必须是正整数。\n");
        return 1;
    }

    printf("请输入最多能取的石子数：\n");
    if (scanf("%d", &maxTake) != 1 || maxTake <= 0) {
        printf("输入错误：每次最多取石数必须是正整数。\n");
        return 1;
    }

    printf("=================================================\n");

    int remainder = stones % (maxTake + 1);

    if (remainder == 0) {
        printf("提示：当前局面先手没有必胜策略。\n");
    } else {
        printf("提示：按照必胜策略，你应该先取 %d 颗石子。\n", remainder);
    }

    printf("请输入你先手取的石子数（1 至 %d）：\n", maxTake);

    if (scanf("%d", &firstTake) != 1 || firstTake < 1 || firstTake > maxTake ||
        firstTake > stones) {
        printf("输入错误：先手取石数不符合规则。\n");
        return 1;
    }

    printf("巴什博弈演示：\n");
    printf("============================================\n");
    printf("初始石子数为 %d，每次可以取 1 至 %d 颗。\n", stones, maxTake);

    printf("你先取 %d 颗，剩余 %d 颗。\n", firstTake, stones - firstTake);

    stones -= firstTake;

    printf("============================================\n");

    while (stones > 0) {
        int opponentTake;

        printf("请输入对手本轮取几颗（1 至 %d，当前剩余 %d）：", maxTake, stones);

        if (scanf("%d", &opponentTake) != 1) {
            printf("输入无效，请重新运行程序并输入整数。\n");
            return 1;
        }

        if (opponentTake < 1 || opponentTake > maxTake || opponentTake > stones) {
            printf("这次取法不合法，请重新输入。\n");
            continue;
        }

        stones -= opponentTake;

        printf("对手取走 %d 颗后，还剩 %d 颗。\n", opponentTake, stones);

        if (stones == 0) {
            printf("对手取到了最后一颗石子，对手获胜。\n");
            break;
        }

        int myTake = maxTake + 1 - opponentTake;

        if (myTake > stones) {
            myTake = stones;
        }

        stones -= myTake;

        printf("程序回应取走 %d 颗，还剩 %d 颗。\n", myTake, stones);

        if (stones == 0) {
            printf("程序取到了最后一颗石子，程序获胜。\n");
            break;
        }
    }

    return 0;
}
