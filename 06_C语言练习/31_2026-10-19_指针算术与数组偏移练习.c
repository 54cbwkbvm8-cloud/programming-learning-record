#include <stdio.h>
int main(void) {
    int a[5] = {10, 20, 30, 40, 50};
    int *p = a + 2;
    printf("原数组:{10,20,30,40,50}\n");
    printf("p = a + 2,p 指向 a[2],其值为 %d\n", *p);
    printf("p[0] = %d\n", p[0]);
    printf("*(p - 1) = %d\n", *(p - 1));
    printf("*(p + 1) = %d\n", *(p + 1));
    printf("p[-1] = %d\n", p[-1]);
    printf("\n");
    int b[4] = {3, 6, 9, 12};
    int *q = b + 2;
    printf("新数组:{3,6,9,12}\n");
    printf("q = b + 2,q 指向 b[2],其值为 %d\n", *q);
    printf("q[0] = %d\n", q[0]);
    printf("*(q - 1) = %d\n", *(q - 1));
    printf("*(q + 1) = %d\n", *(q + 1));
    printf("q[-1] = %d\n", q[-1]);
    printf("\n");
    return 0;
}
