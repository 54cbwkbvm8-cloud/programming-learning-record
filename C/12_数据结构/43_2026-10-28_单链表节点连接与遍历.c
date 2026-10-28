#include <stdio.h>
#include <stdlib.h>
typedef struct Node {
    int data;
    struct Node *next;
} Node;
Node *create_node(int value) {
    Node *new_node = (Node *)malloc(sizeof(Node));
    if (new_node == NULL) {
        printf("错误：内存申请失败，无法创建节点。\n");
        return NULL;
    }
    new_node->data = value;
    new_node->next = NULL;
    return new_node;
}
int main(void) {
    Node *node1 = create_node(10);
    Node *node2 = create_node(20);
    Node *node3 = create_node(30);
    if (node1 == NULL || node2 == NULL || node3 == NULL) {
        printf("错误：至少有一个节点创建失败，程序结束。\n");
        free(node1);
        free(node2);
        free(node3);
        return 1;
    }
    node1->next = node2;
    node2->next = node3;
    printf("=====三个节点的内容与连接关系=====\n");
    printf("node1：地址%p，data = %d，next = %p\n", (void *)node1, node1->data,
           (void *)node1->next);
    printf("node2：地址%p，data = %d，next = %p\n", (void *)node2, node2->data,
           (void *)node2->next);
    printf("node3：地址%p，data = %d，next = %p\n", (void *)node3, node3->data,
           (void *)node3->next);
    if (node3->next == NULL) {
        printf("node3->next = NULL，表示链表在node3这里结束。\n");
    }
    printf("通过node1->next->next->data访问第三节点：%d\n", node1->next->next->data);
    printf("\n=====从节点头出发，沿着next依次遍历=====\n");
    Node *current = node1;
    int position = 1;
    while (current != NULL) {
        printf("第%d个节点：data = %d\n", position, current->data);
        current = current->next;
        position++;
    }
    current = node1;
    while (current != NULL) {
        Node *next_node = current->next;
        free(current);
        current = next_node;
    }
    return 0;
}
