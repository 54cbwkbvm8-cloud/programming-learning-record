#include <stdbool.h>
#include <stdio.h>

bool canWin(int n, int m) {
    if (n <= 0 || m <= 0) {
        return false;
    }
    return n % (m + 1) != 0;
}

void testCase(int n, int m, int testNo) {
    bool result = canWin(n, m);

    printf("Test %d: n=%d, m=%d, ", testNo, n, m);

    if (n <= 0 || m <= 0) {
        printf("参数无效\n");
        printf("------------------------------------------\n");
        return;
    }

    int remainder = n % (m + 1);

    printf("remainder=%d, result=%s\n", remainder, result ? "true" : "false");

    if (result) {
        printf("建议先手第一步取 %d 个石头\n", remainder);
    } else {
        printf("先手没有必胜策略，不存在能保证必胜的第一步\n");
    }

    printf("------------------------------------------\n");
}

int main(void) {
    testCase(10, 3, 1);
    testCase(8, 3, 2);
    testCase(1, 3, 3);

    return 0;
}
