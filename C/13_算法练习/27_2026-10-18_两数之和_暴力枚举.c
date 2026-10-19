#include <stdio.h>
int two_sum(const int num[], int n, int target, int result[2]) {
    result[0] = -1;
    result[1] = -1;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (num[i] + num[j] == target) {
                result[0] = i;
                result[1] = j;
                return 1;
            }
        }
    }
    return 0;
}
int main(void) {
    int nums[] = {2, 7, 11, 15};
    int target = 9;
    int result[2] = {-1, -1};
    int n = sizeof(nums) / sizeof(nums[0]);
    if (two_sum(nums, n, target, result)) {
        printf("[%d,%d]\n", result[0], result[1]);
    } else {
        printf("Not found\n");
    }
    return 0;
}
