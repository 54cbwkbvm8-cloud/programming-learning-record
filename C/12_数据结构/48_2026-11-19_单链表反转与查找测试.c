#include <stdio.h>
#include <stdlib.h>
typedef struct Node {
    int data;
    struct Node *next;
} Node;
Node *create_node(int value) {
    Node *node = (Node *)malloc(sizeof(Node));
    if (node == NULL) {
        printf("Memory allocation failed\n");
        exit(1);
    }
    node->data = value;
    node->next = NULL;
    return node;
}
Node *push_back(Node *head, int value) {
    Node *new_node = create_node(value);
    if (head == NULL) {
        return new_node;
    }
    Node *tail = head;
    while (tail->next != NULL) {
        tail = tail->next;
    }
    tail->next = new_node;
    return head;
}
Node *build_list(const int value[], int count) {
    Node *head = NULL;
    for (int i = 0; i < count; i++) {
        head = push_back(head, value[i]);
    }
    return head;
}
void print_list(Node *head) {
    Node *current = head;
    while (current != NULL) {
        printf("%d ", current->data);
        current = current->next;
    }
    printf("\n");
}
int list_length(Node *head) {
    int count = 0;
    Node *current = head;
    while (current != NULL) {
        count++;
        current = current->next;
    }
    return count;
}
Node *find_value(Node *head, int target) {
    Node *current = head;
    while (current != NULL) {
        if (current->data == target) {
            return current;
        }
        current = current->next;
    }
    return NULL;
}
Node *reverse_list(Node *head) {
    Node *prev = NULL;
    Node *current = head;
    Node *next = NULL;
    while (current != NULL) {
        next = current->next;
        current->next = prev;
        prev = current;
        current = next;
    }
    return prev;
}
void free_list(Node *head) {
    Node *current = head;
    while (current != NULL) {
        Node *next = current->next;
        free(current);
        current = next;
    }
}
void test_case(const char *name, const int value[], int count, int target) {
    printf("=============== 测试：%s ===============\n", name);
    Node *head = build_list(value, count);
    printf("原始链表：");
    print_list(head);
    printf("长度：%d\n", list_length(head));
    Node *found = find_value(head, target);
    if (found != NULL) {
        printf("查找节点：%d，找到了，节点值是：%d\n", target, found->data);
    } else {
        printf("查找节点：%d，没找到\n", target);
    }
    head = reverse_list(head);
    printf("反转后链表：");
    print_list(head);
    free_list(head);
    head = NULL;
    printf("释放链表：head 已置为 NULL，之后不再访问\n\n");
}
int main(void) {
    int one[] = {5};
    int two[] = {1, 2};
    int three[] = {10, 20, 30};
    test_case("空链表", NULL, 0, 1);
    test_case("单节点", one, 1, 5);
    test_case("两节点", two, 2, 2);
    test_case("三节点(查找中间节点)", three, 3, 20);
    return 0;
}
// /*
//  * linked_list_reverse.c —— 单向链表：创建、尾插、打印、求长度、查找、反转、释放
//  *
//  * 【什么是链表】
//  *   链表是一种存放一串数据的方式：每个数据放在一个独立的"节点"里，
//  *   每个节点除了存放数据，还记着"下一个节点在内存里的地址"。
//  *   可以想成"寻宝游戏"：每张纸条写着一件宝物，还写着下一张纸条藏在哪；
//  *   拿到第一张纸条（头指针 head），就能顺着地址找到所有的纸条；
//  *   最后一张纸条上写着"没有下一张了"（NULL）。
//  *
//  *       head
//  *        |
//  *        v
//  *      [10 | * ] --> [20 | * ] --> [30 | NULL]
//  *      (数据|地址)
//  *
//  * 【动态内存：malloc 与 free】
//  *   节点需要"一直活着"，所以要用 malloc 向系统手动申请内存；用完后必须用 free 归还。
//  *   规则：每一次 malloc，最终都必须有且只有一次对应的 free。
//  *     · 申请了不还   → "内存泄漏"
//  *     · 还了又去用   → "野指针"（访问已经不属于你的内存，会崩溃或出怪错误）
//  *     · 同一块还两次 → "double free"
//  *
//  * 【本次修复的问题】
//  *   1. 编译不通过（最严重）：test_case 里调用了 find_node(...)，
//  *      但文件里定义的查找函数叫 find_value，根本没有 find_node 这个函数，
//  *      编译时会警告"隐式声明"，链接时报错 undefined reference to 'find_node'，程序生成不出来。
//  *      修复：调用处改成 find_value。
//  *   2. 测试函数 test_case 的流程是乱的：
//  *      · "长度"打印的是传进来的参数 count，而不是调用 list_length 求出来的真实长度。
//  *        这样即使 list_length 写错了，测试也发现不了；而且 list_length
//  这个函数定义了却从来没用上。
//  *      · 先 reverse_list 一次，查找之后又 reverse_list 一次，两次反转互相抵消，
//  *        链表最终又变回了原来的顺序，等于什么都没反转。
//  *      · 打印了"反转后链表："这几个字，后面却没有调用 print_list，列表根本没有显示出来。
//  *      修复：改成清晰的顺序：打印原始链表 → 用 list_length 求长度 → 查找 → 反转一次 →
//  打印反转后的链表 → 释放。
//  *   3. 函数名 creat_node 少了一个 e，改成了 create_node。
//  *   4. push_back 里检查 new_node == NULL 的那一段是永远不会执行的"死代码"：
//  *      create_node 内部分配失败时，已经直接用 exit(1) 结束了整个程序，根本不会返回 NULL。
//  *      所以这段检查没有作用，反而会让人误以为 create_node 可能返回 NULL。已删除。
//  *   （链表的创建、尾插、反转、查找、释放这些核心函数本身我都测试过，
//  *    并用内存检查工具验证了没有内存泄漏和野指针，它们是正确的。）
//  */

// #include <stdio.h>    // 提供 printf（打印）
// #include <stdlib.h>   // 提供 malloc（申请内存）、free（归还内存）、exit（结束程序）

// // ============================================================
// // 定义"节点"的结构
// //
// // struct（结构体）：把几个相关的数据打包成一个新类型。每个节点包含：
// //   int data          ：这个节点存放的整数
// //   struct Node *next ：指针，里面存的是"下一个节点的地址"。
// //                       指针就是专门用来保存地址的变量，类型名后面加 * 表示它是指针。
// //                       最后一个节点没有下一个，它的 next 就是
// NULL（空地址，表示"什么都不指向"）。
// // 在结构体内部，别名 Node 还没有生效，所以 next 的类型只能写完整的 struct Node *。
// // typedef：给类型起别名。有了它，以后可以直接写 Node，不用每次都写 struct Node。
// // ============================================================
// typedef struct Node {
//     int data;
//     struct Node *next;
// } Node;

// // ============================================================
// // create_node：创建一个新节点，返回它的地址
// //
// // 【修复点】函数名原来是 creat_node，少了一个 e，已改成 create_node。
// // ============================================================
// Node *create_node(int value) {
//     // malloc(sizeof(Node))：向系统申请一块"刚好能放下一个 Node"的内存。
//     // sizeof(Node) 是计算一个 Node 占多少字节；malloc 返回一个"通用地址"（void *），
//     // 前面的 (Node *) 是"强制类型转换"，意思是"把它当作 Node 的地址来用"。
//     // 申请到的内存放在"堆"里，函数结束后不会自动消失，必须由我们自己 free。
//     Node *node = (Node *)malloc(sizeof(Node));

//     // malloc 可能失败（比如内存不够），失败时返回 NULL。不检查就往下用会崩溃，所以必须检查。
//     // 失败时用 exit(1) 结束整个程序（括号里的 1 表示"异常退出"，0 表示"正常退出"）。
//     // 所以这个函数要么成功返回一个有效的节点地址，要么直接让程序结束，【永远不会返回 NULL】。
//     if (node == NULL) {
//         printf("Memory allocation failed\n");
//         exit(1);
//     }

//     // node->data：通过指针 node，访问它所指向的那个节点里的 data 成员。
//     // -> 是"通过指针访问结构体成员"的写法，可以暂时理解成"指向的那个东西里的"。
//     node->data = value;
//     node->next = NULL;   // 新节点还没有连接别的节点，所以 next 先设为 NULL
//     return node;         // 把新节点的地址交还给调用者
// }

// // ============================================================
// // push_back：在链表【尾部】添加一个新节点，返回链表的头指针
// //
// // 【为什么要把 head 返回出去】
// //   C 语言传参是"值传递"：函数拿到的 head 只是调用者那个 head 的一份【复制品】。
// //   如果链表原来是空的，新节点要成为第一个节点，在函数里改 head 是影响不到调用者的，
// //   所以要把新的头指针 return 出去，调用者用 head = push_back(head, 值); 接住它。
// // ============================================================
// Node *push_back(Node *head, int value) {
//     Node *new_node = create_node(value);   // 先创建好要插入的新节点

//     // 【修复点】原来这里还有一段 if (new_node == NULL) { ... return head; }，
//     // 但 create_node 失败时已经直接结束程序了，永远不会返回 NULL，那段代码永远执行不到，已删除。

//     // 情况 1：链表原来是空的。新节点自己就是第一个节点。
//     if (head == NULL) {
//         return new_node;
//     }

//     // 情况 2：链表不空。找到【最后一个节点】，让它的 next 指向新节点。
//     // 用游标 tail 去移动，而不是直接移动 head，否则 head 就丢了，找不回链表的开头。
//     Node *tail = head;
//     while (tail->next != NULL) {   // 只要还有下一个节点，就往后走
//         tail = tail->next;
//     }
//     // 循环结束时，tail->next 是 NULL，说明 tail 就是最后一个节点
//     tail->next = new_node;   // 让原来的最后一个节点指向新节点
//     return head;             // 头没有变，原样返回
// }

// // ============================================================
// // build_list：根据一个整数数组，依次尾插，创建出一条链表，返回头指针
// //
// // 参数 const int value[]：整数数组。const 的意思是"只读"，承诺函数里只读取、不修改数组。
// // 参数 count：数组里有几个元素。C 语言里数组传给函数后，函数就不知道它有多长了，
// //             所以必须另外用一个参数告诉它（这和 Java 里用 arr.length 取长度不一样）。
// // 当 count 是 0 时，循环一次都不执行，返回的就是空链表（NULL）。
// // ============================================================
// Node *build_list(const int value[], int count) {
//     Node *head = NULL;   // 从空链表开始
//     for (int i = 0; i < count; i++) {
//         head = push_back(head, value[i]);   // 每次尾插一个，并用返回值更新 head
//     }
//     return head;
// }

// // ============================================================
// // print_list：把链表从头到尾打印出来，数字之间用空格隔开
// // 空链表时，什么都不会打印（只有最后的换行）。
// // ============================================================
// void print_list(Node *head) {
//     Node *current = head;           // 用游标遍历，不动 head
//     while (current != NULL) {       // 游标不是 NULL，说明这个位置还有节点
//         printf("%d ", current->data);   // 打印当前节点的数据（%d 是整数的占位符）
//         current = current->next;        // 移动到下一个节点
//     }
//     printf("\n");
// }

// // ============================================================
// // list_length：数一数链表里有几个节点
// // ============================================================
// int list_length(Node *head) {
//     int count = 0;                  // 计数器，初始值必须是 0
//     Node *current = head;
//     while (current != NULL) {
//         count++;                    // 每经过一个节点，计数器加 1
//         current = current->next;
//     }
//     return count;
// }

// // ============================================================
// // find_value：在链表里查找第一个值等于 target 的节点
// // 找到了，返回那个节点的地址；没找到，返回 NULL。
// //
// // 注意：返回的是指向链表内部某个节点的指针，不是新节点。
// // 所以拿到它之后，不能单独 free 它，整条链表只能统一用 free_list 释放。
// // ============================================================
// Node *find_value(Node *head, int target) {
//     Node *current = head;
//     while (current != NULL) {
//         if (current->data == target) {   // 判断相等用两个等号 ==，一个等号 = 是赋值，别写混
//             return current;              // 找到了，立刻返回这个节点的地址
//         }
//         current = current->next;
//     }
//     return NULL;   // 整个链表走完都没找到
// }

// // ============================================================
// // reverse_list：把链表反转（原来的头变成尾，原来的尾变成头），返回新的头指针
// //
// // 【思路】从头开始，逐个把每个节点的 next 指针"掉头"，让它指向前一个节点。
// // 需要三个游标配合：
// //   prev    ：已经处理完的部分的头（也就是"前一个节点"）。一开始还没处理任何节点，所以是 NULL
// //   current ：当前正在处理的节点
// //   next    ：current 的下一个节点（要提前记下来，否则 current->next
// 一改，后面的链表就找不到了）
// //
// // 以 10 -> 20 -> 30 为例，每一轮做 4 步：
// //   ① next = current->next;     先把"下一个节点"记下来
// //   ② current->next = prev;     让当前节点掉头，指向前一个
// //   ③ prev = current;           prev 前进一格
// //   ④ current = next;           current 前进一格（用事先记下的 next）
// //
// //   开始：  prev=NULL   current=10   10 -> 20 -> 30 -> NULL
// //   第1轮： 10 的 next 改成 NULL。         结果：NULL <- 10     20 -> 30
// //           prev=10     current=20
// //   第2轮： 20 的 next 改成 10。           结果：NULL <- 10 <- 20     30
// //           prev=20     current=30
// //   第3轮： 30 的 next 改成 20。           结果：NULL <- 10 <- 20 <- 30
// //           prev=30     current=NULL    current 是 NULL，循环结束
// //   此时 prev 指向的 30 就是新的头，链表变成 30 -> 20 -> 10 -> NULL。返回 prev。
// //
// // 空链表时：current 一开始就是 NULL，循环不执行，返回 prev（也就是 NULL）。
// // ============================================================
// Node *reverse_list(Node *head) {
//     Node *prev = NULL;
//     Node *current = head;
//     Node *next = NULL;
//     while (current != NULL) {
//         next = current->next;     // ① 先记下下一个节点
//         current->next = prev;     // ② 当前节点掉头
//         prev = current;           // ③ prev 前进一格
//         current = next;           // ④ current 前进一格
//     }
//     return prev;   // 循环结束时，prev 是原来的最后一个节点，它就是反转后的新头
// }

// // ============================================================
// // free_list：把整个链表的所有节点逐个释放
// //
// // 每个节点都是 malloc 出来的，必须逐个 free，不能只 free(head)（那样其余节点全部泄漏）。
// // ============================================================
// void free_list(Node *head) {
//     Node *current = head;
//     while (current != NULL) {
//         Node *next = current->next;   // ① 先把下一个节点的地址记下来
//         free(current);                // ② 再释放当前节点
//         current = next;               // ③ 移动到下一个
//         // 【顺序不能乱】如果先 free(current) 再读 current->next，
//         // 就是在读"已经归还的内存"，属于严重错误（use-after-free）。
//     }
// }

// // ============================================================
// // test_case：执行一组完整的测试，并把过程打印出来
// //
// // 参数：
// //   const char *name  ：测试的名字。C 语言里字符串就是"指向字符的指针"，用 %s 打印
// //   const int value[] ：要放进链表的数据数组（可以传 NULL，前提是 count 是 0）
// //   int count         ：数组里有几个元素
// //   int target        ：要在链表里查找的值
// // ============================================================
// void test_case(const char *name, const int value[], int count, int target) {
//     printf("=============== 测试：%s ===============\n", name);

//     // ---------- 1. 创建链表并打印 ----------
//     Node *head = build_list(value, count);
//     printf("原始链表：");
//     print_list(head);

//     // ---------- 2. 求长度 ----------
//     // 【修复点】原来这里是先 reverse_list，然后打印参数 count。
//     // 现在改成调用 list_length 真正数一遍。这样如果 list_length 有错误，测试能发现；
//     // 并且它应该等于传进来的 count。
//     printf("长度：%d\n", list_length(head));

//     // ---------- 3. 查找 ----------
//     // 【修复点】原来写的是 find_node（并不存在的函数），导致编译失败。改成 find_value。
//     Node *found = find_value(head, target);
//     if (found != NULL) {
//         printf("查找节点：%d，找到了，节点值是：%d\n", target, found->data);
//     } else {
//         printf("查找节点：%d，没找到\n", target);
//     }

//     // ---------- 4. 反转并打印 ----------
//     // 【修复点】原来在查找之前和之后各反转了一次，互相抵消，等于没反转，
//     // 而且"反转后链表："后面也没有打印。现在只反转一次，并且打印出来。
//     // 用 head = reverse_list(head) 接住新的头，因为反转后头节点变了。
//     head = reverse_list(head);
//     printf("反转后链表：");
//     print_list(head);

//     // ---------- 5. 释放 ----------
//     free_list(head);
//     head = NULL;   // 释放后立刻把指针置为 NULL，避免留下"野指针"（指向已经归还的内存）
//     printf("释放链表：head 已置为 NULL，之后不再访问\n\n");
// }

// // main 函数：程序的入口。运行程序时，从这里的第一行开始，一行一行往下执行。
// // int main(void)：int 表示执行完返回一个整数给操作系统（0 表示正常结束）；void 表示不需要参数。
// int main(void) {
//     // 准备测试用的数组。{5} 这样的写法是在创建数组的同时放入内容。
//     int one[] = {5};
//     int two[] = {1, 2};
//     int three[] = {10, 20, 30};

//     // 四组测试，覆盖了：空链表、只有一个节点、两个节点、三个节点（查找中间的节点）。
//     // 空链表时，value 参数传 NULL，count 传 0：build_list 不会去读数组，所以是安全的。
//     test_case("空链表", NULL, 0, 1);
//     test_case("单节点", one, 1, 5);
//     test_case("两节点", two, 2, 2);
//     test_case("三节点(查找中间节点)", three, 3, 20);

//     return 0;
// }
