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
