#include <stdio.h>
int isNarcissistic(int n) {
    int hundreds = n / 100;
    int tens = (n / 10) % 10;
    int ones = n % 10;
    return n == hundreds * hundreds * hundreds + tens * tens * tens + ones * ones * ones;
}
int main(void) {
    int nums[] = {4, 1, 8, 7, 3};
    int target = 10;
    int size = sizeof(nums) / sizeof(nums[0]);
    int found = 0;
    printf("任务一：两数之和：\n");
    printf("数组：{4,1,8,7,3} 目标值：10\n");
    for (int i = 0; i < size; i++) {
        for (int j = i + 1; j < size; j++) {
            if (nums[i] + nums[j] == target) {
                printf("找到：%d + %d = %d\n", nums[i], nums[j], target);
                found = 1;
            }
        }
    }
    if (!found) {
        printf("未找到两数之和为 %d 的组合\n", target);
    }
    printf("任务二：100到 999 之间的水仙花数：\n");
    for (int n = 100; n <= 999; n++) {
        if (isNarcissistic(n)) {
            printf("%d\n", n);
        }
    }
    return 0;
}
