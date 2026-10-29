#include <stdio.h>
#include <stdlib.h>
typedef struct Node {
    int data;
    struct Node *next;
} Node;
Node *new_node = NULL; /* 全局变量不能用函数调用初始化，改为在函数内 malloc */

Node *creat_node(int value) {
    Node *new_node = (Node *)malloc(sizeof(Node));
    if (new_node == NULL) {
        printf("内存分配失败\n");
        return NULL;
    }
    new_node->data = value;
    new_node->next = NULL;
    return new_node;
}
void traverse(Node *head) {
    Node *current = head;
    printf("head->");
    while (current != NULL) {
        printf("%d->", current->data);
        current = current->next;
    }
    printf("NULL\n");
}
Node *insert_tall(Node *head, int value) {
    Node *new_node = creat_node(value);
    if (new_node == NULL) {
        return head;
    }
    if (head == NULL) {
        return new_node;
    }
    Node *current = head;
    while (current->next != NULL) {
        current = current->next;
    }
    current->next = new_node;
    return head;
}
void free_list(Node *head) {
    Node *current = head;
    while (current != NULL) {
        Node *next_node = current->next;
        free(current);
        current = next_node;
    }
}
int main(void) {
    Node *head = NULL;
    printf("测试1：空链表\n");
    traverse(head);
    printf("\n");
    printf("测试2：单节点链表\n");
    head = insert_tall(head, 10);
    traverse(head);
    printf("\n");
    printf("测试3：多节点链表\n");
    head = insert_tall(head, 20);
    head = insert_tall(head, 30);
    head = insert_tall(head, 40);
    traverse(head);
    free_list(head);
    head = NULL;
    return 0;
}
