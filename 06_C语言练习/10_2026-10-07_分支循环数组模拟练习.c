#include <stdio.h>
int max(int a[], int n);
int min(int a[], int n);
int sum(int a[], int n);
float average(int a[], int n);
int main(void) {
    int a;
    int b;
    printf("请输入两个数字：");
    scanf("%d %d", &a, &b);
    printf("两个数字的和是：%d\n", a + b);
    if (a > b) {
        printf("较大的一个数字是：%d\n", a);
    } else if (a < b) {
        printf("较大的一个数字是：%d\n", b);
    } else {
        printf("两个数字相等\n");
    }
    printf("for循环：输出 1～5的数字\n");
    int i;
    for (i = 1; i <= 5; i++) {
        printf("%d\n", i);
    }
    printf("\n");
    printf("while循环：输出 1～5的数字\n");
    i = 1;
    while (i <= 5) {
        printf("%d\n", i);
        i++;
    }
    printf("\n");
    int arr[5];
    printf("请输入 5 个数字：\n");
    scanf("%d%d%d%d%d", &arr[0], &arr[1], &arr[2], &arr[3], &arr[4]);
    int max_value = max(arr, 5);
    int min_value = min(arr, 5);
    int total = sum(arr, 5);
    float avg = average(arr, 5);
    printf("这五个数的最大值是：%d\n", max_value);
    printf("这五个数的最小值是：%d\n", min_value);
    printf("这个五个数的和是：%d\n", total);
    printf("这五个数的平均数是：%.2f\n", avg);
    return 0;
}

int max(int a[], int n) {
    int max_value = a[0];
    for (int i = 1; i < n; i++) {
        if (a[i] > max_value) {
            max_value = a[i];
        }
    }
    return max_value;
}
int min(int a[], int n) {
    int min_value = a[0];
    for (int i = 1; i < n; i++) {
        if (a[i] < min_value) {
            min_value = a[i];
        }
    }
    return min_value;
}
int sum(int a[], int n) {
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += a[i];
    }
    return sum;
}
float average(int a[], int n) { return (float)sum(a, n) / n; }
