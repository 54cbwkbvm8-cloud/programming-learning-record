#include <stdio.h>
#include <stdlib.h>
typedef struct Node {
    int data;
    struct Node *next;
} Node;
Node *new_node = NULL;

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
/*// ====================================================================
// 文件名：44_2026-10-29_单链表尾插与遍历练习.c
// 作用：实现单链表的四个基本操作，并做三组测试：
//       creat_node  ：创建一个新节点
//       traverse    ：遍历并输出链表，格式 head->10->20->NULL
//       insert_tall ：在链表末尾插入一个新节点（尾插，函数名里的 tall 是 tail 的笔误）
//       free_list   ：释放整条链表
//       测试：空链表、只有一个节点的链表、有多个节点的链表。
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "44_2026-10-29_单链表尾插与遍历练习.c"
//   运行：./demo
//   说明：编译 = 把人能看懂的 C 代码翻译成电脑能执行的程序。
//         -Wall -Wextra 的意思是"把所有可疑的地方都警告我"，
//         编译时出现警告，一定要弄明白原因，不要忽略。
// ====================================================================


// --------------------------------------------------------------------
// 第一部分：引入"工具箱"
// --------------------------------------------------------------------
// <stdio.h> 是"标准输入输出"工具箱，printf 在里面。
#include <stdio.h>

// <stdlib.h> 里有 malloc（申请内存）和 free（释放内存）。
#include <stdlib.h>


// --------------------------------------------------------------------
// 第二部分：定义"节点"长什么样
// --------------------------------------------------------------------
// 节点由两样东西组成：data（存的整数）和 next（指向下一个节点的指针）。
// struct Node *next：结构体里不能直接包含自己，但可以包含"指向同类型的指针"，这就是链表能"连起来"的原因。
// typedef ... Node; 给它起个短名字 Node，以后直接写 Node。
// 链表像一列火车：每节车厢装着货物 data，挂钩 next 连着下一节；最后一节的挂钩是 NULL。
typedef struct Node {
    int data;
    struct Node *next;
} Node;


// --------------------------------------------------------------------
// 第三部分：全局变量
// --------------------------------------------------------------------
// ⚠ 提醒：这个全局指针 new_node 实际上没有被用到：下面 creat_node 和 insert_tall 里
// 各自又声明了一个同名的局部变量 new_node，同名时离得近的（局部的）优先，把它"遮住"了。
// 保留它不影响运行，但容易让人混淆（这是原代码的写法，这里不改，只提醒你）。
Node *new_node = NULL;


// --------------------------------------------------------------------
// 第四部分：创建一个新节点
// --------------------------------------------------------------------
// 函数名 creat_node（create 的简写）。返回类型 Node *：返回新节点的地址。参数 value 是要存进节点的数据。
Node *creat_node(int value) {

    // malloc(字节数)：在"堆"上申请一块内存，返回起始地址，失败返回 NULL。
    // sizeof(Node) 是一个节点占的字节数；(Node *) 是强制类型转换，把地址转成"指向 Node 的指针"。
    // 这里的 new_node 是局部变量，只在这个函数里有效。
    Node *new_node = (Node *)malloc(sizeof(Node));

    // 申请可能失败（内存不足），必须检查，否则后面访问 NULL 会崩溃。
    if (new_node == NULL) {
        printf("内存分配失败\n");

        // 创建失败，返回 NULL 告诉调用者"没成功"。
        return NULL;
    }

    // -> 叫"通过指针访问成员"：new_node->data 的意思是"new_node 指向的节点里的 data"。
    new_node->data = value;

    // 新节点还没有下一个节点，所以 next 设为 NULL。
    // 为什么必须设置？malloc 申请到的内存里是随机数据，next 如果是乱地址，之后遍历就会崩溃。
    new_node->next = NULL;

    // 把新节点的地址交给调用者。内存在堆上，函数结束后不会消失，所以可以放心返回。
    return new_node;
}


// --------------------------------------------------------------------
// 第五部分：遍历并输出链表
// --------------------------------------------------------------------
// 参数 head 是链表的头指针：指向第一个节点；空链表时是 NULL。
void traverse(Node *head) {

    // 用一个"游标"current 从头开始往后走，不直接移动 head（head 要保留，否则就找不回开头了）。
    Node *current = head;

    // 先输出开头的 head->。
    printf("head->");

    // 只要游标还没有走出链表（不是 NULL），就继续循环。
    while (current != NULL) {
        // 输出当前节点的数据，后面接一个箭头，表示"指向下一个"。
        printf("%d->", current->data);

        // 游标移到下一个节点：这是遍历链表的核心一步。
        // 例：current 指向 10 的节点，current->next 是 20 的节点的地址，执行后 current 就指向 20 了。
        current = current->next;
    }

    // 走到链表末尾，输出 NULL 表示"到头了"。
    // 空链表时 while 一次也不执行，所以直接输出 head->NULL。
    printf("NULL\n");
}


// --------------------------------------------------------------------
// 第六部分：尾插 —— 在链表末尾插入新节点
// --------------------------------------------------------------------
// 参数：head 是链表的头指针，value 是新节点的数据。返回值：新的头指针。
// 为什么要返回头指针？如果原来是空链表，新节点就成了头，头指针变了。
// 而函数里修改参数 head，不会影响 main 里的 head（值传递），
// 所以通过返回值把"新的头"交给调用者，调用者要写：head = insert_tall(head, 10);
Node *insert_tall(Node *head, int value) {

    // 先创建新节点。这里的 new_node 也是局部变量（遮住了第 7 行的全局变量）。
    Node *new_node = creat_node(value);

    // 创建失败（内存不足）：链表保持不变，返回原来的头指针。
    if (new_node == NULL) {
        return head;
    }

    // 链表是空的：新节点就是第一个节点，也就是新的头，直接返回它。
    if (head == NULL) {
        return new_node;
    }

    // 链表不是空的：游标从头出发，去找最后一个节点。
    Node *current = head;

    // 注意条件是 current->next != NULL，而不是 current != NULL：我们要停在"最后一个节点"上（它的 next 是 NULL），
    // 而不是走出链表。
    // 例：链表是 10 -> 20：current 在 10，它的 next 不是 NULL，走到 20；20 的 next 是 NULL，停在 20。
    while (current->next != NULL) {
        // 游标往后走一步。
        current = current->next;
    }

    // 把新节点挂在最后一个节点的后面：最后一个节点的 next 从 NULL 变成新节点的地址。
    current->next = new_node;

    // 头没有变，返回原来的头指针。
    return head;
}


// --------------------------------------------------------------------
// 第七部分：释放整条链表
// --------------------------------------------------------------------
// 每个节点都是 malloc 申请来的，用完必须逐个 free，否则就是"内存泄漏"。
void free_list(Node *head) {
    Node *current = head;
    while (current != NULL) {
        // 先把"下一个节点的地址"保存起来！因为 free(current) 之后，current 指向的内存就不能再访问了（包括 current->next）。
        // 不先保存，就找不到后面的节点，永远释放不完。
        Node *next_node = current->next;

        // 释放当前节点。
        free(current);

        // 游标移到刚才保存的下一个节点，继续释放。
        current = next_node;
    }
}


// --------------------------------------------------------------------
// 第八部分：main 函数 —— 程序从这里开始
// --------------------------------------------------------------------
int main(void) {

    // head 是头指针，指向链表的第一个节点。NULL 表示"空链表"（一个节点也没有）。
    Node *head = NULL;

    // --------------------------------------------------------------------
    // 测试 1：空链表
    // --------------------------------------------------------------------
    printf("测试1：空链表\n");

    // 遍历空链表：while 一次都不执行，只输出 head->NULL。
    traverse(head);
    printf("\n");

    // --------------------------------------------------------------------
    // 测试 2：只有一个节点
    // --------------------------------------------------------------------
    printf("测试2：单节点链表\n");

    // 在空链表里插入 10。链表变成：10 -> NULL。
    // 必须写 head = ...：接住函数返回的新头指针，因为这时头指针从 NULL 变成了新节点。
    head = insert_tall(head, 10);
    traverse(head);
    printf("\n");

    // --------------------------------------------------------------------
    // 测试 3：多个节点
    // --------------------------------------------------------------------
    printf("测试3：多节点链表\n");

    // 依次尾插 20、30、40，每次都挂在链表的最后。链表依次变成：
    // 10 -> 20；10 -> 20 -> 30；10 -> 20 -> 30 -> 40 -> NULL
    head = insert_tall(head, 20);
    head = insert_tall(head, 30);
    head = insert_tall(head, 40);
    traverse(head);

    // 测试结束，释放整条链表。
    free_list(head);

    // 释放之后，head 里保存的地址已经无效（"悬空指针"），把它设成 NULL，防止后面误用。
    head = NULL;

    // return 0：结束 main，把 0 交给系统，表示"程序正常结束"。
    return 0;
}

// ====================================================================
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   测试1：空链表
//   head->NULL
//
//   测试2：单节点链表
//   head->10->NULL
//
//   测试3：多节点链表
//   head->10->20->30->40->NULL
// ====================================================================
*/
