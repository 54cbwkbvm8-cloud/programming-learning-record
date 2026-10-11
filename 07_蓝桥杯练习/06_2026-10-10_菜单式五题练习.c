#include <stdio.h>
void p1_abc(void) {
    int a = 0;
    int b = 0;
    scanf("%d%d", &a, &b);
    printf("%d\n", a + b);
}
void p2_sequence_sum(void) {
    long long n = 0;
    scanf("%lld", &n);
    long long sum = 0;
    sum = n * (n + 1) / 2;
    printf("%lld\n", sum);
}
void p3_leap_year(void) {
    int year = 0;
    scanf("%d", &year);
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        printf("yes\n");
    } else {
        printf("no\n");
    }
}
int is_narcissistic(int x) {
    int hundreds = x / 100;
    int tens = x / 10 % 10;
    int units = x % 10;
    int cube_sum = hundreds * hundreds * hundreds + tens * tens * tens + units * units * units;
    if (cube_sum == x) {
        return 1;
    } else {
        return 0;
    }
}
void p4_narcissistic(void) {
    for (int i = 100; i <= 999; i++) {
        if (is_narcissistic(i)) {
            printf("%d\n", i);
        }
    }
    printf("\n");
}
void p5_sort(void) {
    int n = 0;
    scanf("%d", &n);
    int a[205];
    for (int i = 0; i < n; i++) {
        scanf("%d", &a[i]);
    }
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (a[i] > a[j]) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
    }
    for (int i = 0; i < n; i++) {
        if (i > 0) {
            printf(" ");
        }
        printf("%d", a[i]);
    }
    printf("\n");
}
int main(void) {
    int choice = 0;
    printf("请输入题号（1～5）：");
    scanf("%d", &choice);
    if (choice == 1) {
        p1_abc();
    } else if (choice == 2) {
        p2_sequence_sum();
    } else if (choice == 3) {
        p3_leap_year();
    } else if (choice == 4) {
        p4_narcissistic();
    } else if (choice == 5) {
        p5_sort();
    } else {
        printf("输入错误！请输入1～5之间的整数。\n");
    }
    return 0;
}
