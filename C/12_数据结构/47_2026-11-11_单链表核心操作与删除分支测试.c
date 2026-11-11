#include <stdio.h>
#include <stdlib.h>
typedef struct Node {
    int data;
    struct Node *next;
} Node;
Node *create_node(int value) {
    Node *node = (Node *)malloc(sizeof(Node));
    if (node == NULL) {
        printf("内存申请失败，程序退出。\n");
        exit(1);
    }
    node->data = value;
    node->next = NULL;
    return node;
}
Node *insert_tail(Node *head, int value) {
    Node *new_node = create_node(value);
    if (head == NULL) {
        return new_node;
    }
    Node *cur = head;
    while (cur->next != NULL) {
        cur = cur->next;
    }
    cur->next = new_node;
    return head;
}
Node *delete_value(Node *head, int value) {
    if (head == NULL) {
        printf("【删除 %d】链表为空，无法删除。\n", value);
        return NULL;
    }
    if (head->data == value) {
        Node *temp = head;
        head = head->next;
        free(temp);
        printf("【删除 %d】已删除头节点并free成功。\n", value);
        return head;
    }
    Node *prev = head;
    Node *cur = head->next;
    while (cur != NULL) {
        if (cur->data == value) {
            prev->next = cur->next;
            free(cur);
            printf("【删除 %d】已删除节点并free成功。\n", value);
            return head;
        }
        prev = cur;
        cur = cur->next;
    }
    printf("【删除 %d】未找到该值，链表不变。\n", value);
    return head;
}
void print_list(Node *head) {
    printf(" 链表：");
    Node *cur = head;
    while (cur != NULL) {
        printf("%d ", cur->data);
        cur = cur->next;
    }
    printf("NULL\n");
}
void free_list(Node *head) {
    Node *cur = head;
    while (cur != NULL) {
        Node *temp = cur;
        cur = cur->next;
        free(temp);
    }
    printf("【释放链表】已释放所有节点。\n");
}
Node *build_list(const int arr[], int n) {
    Node *head = NULL;
    for (int i = 0; i < n; i++) {
        head = insert_tail(head, arr[i]);
    }
    return head;
}
int main(void) {
    int three[] = {10, 20, 30};
    int one[] = {10};
    int dup[] = {10, 20, 30, 20};
    Node *head = NULL;
    printf("\n测试 1：空链表删除\n");
    head = NULL;
    print_list(head);
    head = delete_value(head, 5);
    print_list(head);
    free_list(head);
    head = NULL;
    printf("\n测试 2：删除头节点\n");
    head = build_list(three, 3);
    print_list(head);
    head = delete_value(head, 10);
    print_list(head);
    free_list(head);
    head = NULL;
    printf("\n测试 3：删除尾节点\n");
    head = build_list(three, 3);
    print_list(head);
    head = delete_value(head, 30);
    print_list(head);
    free_list(head);
    head = NULL;
    printf("\n测试 4：删除不存在的值\n");
    head = build_list(three, 3);
    print_list(head);
    head = delete_value(head, 99);
    print_list(head);
    free_list(head);
    head = NULL;
    printf("\n测试 5：删除唯一节点\n");
    head = build_list(one, 1);
    print_list(head);
    head = delete_value(head, 10);
    print_list(head);
    free_list(head);
    head = NULL;
    printf("\n测试 6（附加）：删除中间节点 + 重复值只删除一个\n");
    head = build_list(dup, 4);
    print_list(head);
    head = delete_value(head, 20);
    print_list(head);
    free_list(head);
    head = NULL;
    printf("\n所有测试结束\n");
    return 0;
}
// /*
//  * linked_list.c —— 单向链表：创建节点、尾部插入、按值删除、打印、释放
//  *
//  * 【什么是链表】
//  *   链表是一种存放一串数据的方式。和数组不同，链表的每个数据放在一个独立的"节点"里，
//  *   每个节点除了存放自己的数据，还记着"下一个节点在内存里的地址"。
//  *   可以想象成"寻宝游戏"：每张纸条上写着一件宝物（数据），还写着"下一张纸条藏在哪"（地址）；
//  *   你只要拿到第一张纸条，就能顺着地址找到所有的纸条；最后一张纸条上写"没有下一张了"（NULL）。
//  *
//  *       head
//  *        |
//  *        v
//  *      [10 | * ] --> [20 | * ] --> [30 | NULL]
//  *      (数据|地址)
//  *
//  *   数组：所有数据挤在一块连续的内存里，通过下标直接访问，但中间插入/删除要搬动很多数据。
//  *   链表：数据散落在内存各处，用地址串起来，插入/删除只需要改几个地址，但只能顺着一个个往后找。
//  *
//  * 【动态内存：malloc 与 free】
//  *   程序里普通的局部变量，在函数结束时会自动消失。但链表的节点需要"一直活着"，
//  *   所以要用 malloc 向系统【手动申请】一块内存；用完后必须用 free 把这块内存【手动归还】。
//  *   规则：每一次 malloc，最终都必须有且只有一次对应的 free。
//  *     · 申请了不还   → "内存泄漏"（程序占着内存不放，越用越多）
//  *     · 还了又去用   → "野指针 / use-after-free"（访问已经不属于你的内存，可能崩溃或出怪错误）
//  *     · 同一块还两次 → "double free"（同样会出错）
//  *
//  * 【检查结果】
//  *   我用 gcc 严格编译（-Wall -Wextra -pedantic，零警告），并开启了内存检查工具
//  *   （AddressSanitizer + UBSanitizer）运行：没有越界、没有野指针、没有内存泄漏。
//  *   链表的所有逻辑（插入、删除头/中/尾节点、删除不存在的值、空链表等）都是正确的。
//  *   所以只做了下面几处小修改：
//  *   1. 函数名 creat_node 拼写错误，正确的英文单词是 create（创建）。已改成 create_node。
//  *   2. free_list 释放链表后，main 里的 head 变量还保存着"已经归还的旧地址"，这叫"野指针"。
//  *      现在虽然每次都在重新赋值后才使用，不会出问题，但这是个隐患：
//  *      以后如果有人在 free 之后不小心又用了 head，就会出错。好习惯是 free 之后马上把指针置为
//  NULL。
//  *      已在 main 里每次 free_list 之后补上 head = NULL;。
//  *   3. 测试 3 的说明文字"删除节点"太笼统，实际删除的是最后一个节点，改成"删除尾节点"；
//  *      测试 6 的标题里有多余的冒号，已整理。
//  */

// #include <stdio.h>    // 提供 printf（打印）
// #include <stdlib.h>   // 提供 malloc（申请内存）、free（归还内存）、exit（结束程序）

// // ============================================================
// // 定义"节点"的结构
// //
// // struct 叫"结构体"：把几个相关的数据打包成一个新的类型。
// // 这里每个节点包含两样东西：
// //   int data         ：这个节点存放的数据（一个整数）
// //   struct Node *next：指针，里面存的是"下一个节点的地址"
// //     · "指针"就是专门用来保存"地址"的变量。类型名后面加 * 表示它是指针。
// //     · 最后一个节点没有下一个，它的 next 就设为 NULL（空地址，表示"什么都不指向"）。
// //
// // 为什么 next 的类型要写成完整的 struct Node *，而不是 Node *？
// //   因为别名 Node 要等整个 typedef 写完才生效。在结构体内部，它还不存在，所以只能写 struct
// Node。
// //
// // typedef 的作用：给类型起一个别名。写了 typedef ... Node; 之后，
// // 以后就可以直接写 Node，不用每次都写 struct Node，更短更方便。
// // ============================================================
// typedef struct Node {
//     int data;
//     struct Node *next;
// } Node;

// // ============================================================
// // 函数 create_node：创建一个新节点，返回它的地址
// //
// // 函数头怎么读：Node *create_node(int value)
// //   Node *      ：返回值类型：返回一个"指向 Node 的指针"（也就是新节点的地址）
// //   create_node ：函数名
// //   (int value) ：参数：新节点要存放的整数
// //
// // 【修复点】原函数名是 creat_node，少了一个 e，已改成 create_node（所有调用的地方也同步改了）。
// // ============================================================
// Node *create_node(int value) {
//     // malloc(sizeof(Node))：向系统申请一块"刚好能放下一个 Node"的内存。
//     //   sizeof(Node)：计算一个 Node 占多少字节
//     //   malloc 返回的是一个"通用地址"（void *），前面的 (Node *) 是"强制类型转换"，
//     //   告诉编译器："把这个地址当作 Node 的地址来用"。
//     //   （在 C 语言里这个转换可以省略，写上是为了让意思更清楚。）
//     // 申请到的内存放在"堆"里：函数结束后不会自动消失，必须由我们自己 free。
//     Node *node = (Node *)malloc(sizeof(Node));

//     // malloc 可能失败（比如电脑内存不够了），失败时返回 NULL。
//     // 如果不检查就往下用，会访问空地址，导致程序崩溃。所以必须检查。
//     if (node == NULL) {
//         printf("内存申请失败，程序退出。\n");
//         exit(1);   // exit(1)：立即结束整个程序。括号里的 1 表示"异常退出"（0 表示正常退出）
//     }

//     // node->data 的意思是："node 这个指针所指向的那个节点里的 data 成员"。
//     // -> 是"通过指针访问结构体成员"的写法（你可以暂时把它理解成"指向的那个东西里的"）。
//     node->data = value;   // 把数据放进节点
//     node->next = NULL;    // 新节点现在还不连接任何节点，所以 next 先设为 NULL
//     return node;          // 把新节点的地址交还给调用者。
//                           // 因为节点是 malloc 出来的，函数结束后它依然活着，地址依然有效。
// }

// // ============================================================
// // 函数 insert_tail：在链表【尾部】添加一个新节点，返回链表的头指针
// //
// // 参数 head：链表的头指针（指向第一个节点的指针）。空链表的 head 是 NULL。
// //
// // 【为什么要把 head 返回出去？】
// //   C 语言传参是"值传递"：函数拿到的 head 只是调用者那个 head 的【一份复制品】。
// //   如果在函数里改 head（比如让它指向新节点），调用者手里的 head 并不会跟着改变。
// //   所以当链表原来是空的、新节点要成为"第一个节点"时，必须把新的头指针 return 出去，
// //   让调用者用 head = insert_tail(head, 值); 的写法接住它，更新自己的 head。
// // ============================================================
// Node *insert_tail(Node *head, int value) {
//     Node *new_node = create_node(value);   // 先创建好要插入的新节点

//     // 情况 1：链表原来是空的。新节点自己就是第一个节点，直接把它作为新的头返回。
//     if (head == NULL) {
//         return new_node;
//     }

//     // 情况 2：链表不空，要找到【最后一个节点】，让它的 next 指向新节点。
//     // cur（current，"当前"）是一个"游标"，从头开始，一个一个节点往后移动。
//     // 注意：用 cur 去移动，而不要直接移动 head，否则 head 就丢了，找不回链表的开头。
//     Node *cur = head;

//     // 只要当前节点还有下一个节点（cur->next 不是 NULL），就继续往后走。
//     // 循环结束时，cur->next 是 NULL，说明 cur 就是最后一个节点。
//     while (cur->next != NULL) {
//         cur = cur->next;   // 游标往后移一格：cur 变成"下一个节点"
//     }

//     cur->next = new_node;   // 让原来的最后一个节点指向新节点，新节点成为新的最后一个
//     return head;            // 头没有变，原样返回
// }

// // ============================================================
// // 函数 delete_value：删除链表中【第一个】值等于 value 的节点，返回链表的头指针
// //
// // 为什么也要返回头指针？因为被删除的可能正好是头节点，那么"新的头"就变成了原来的第二个节点，
// // 必须把新的头返回出去，调用者才能更新自己的 head。
// //
// // 分三种情况处理：① 链表是空的  ② 要删的是头节点  ③ 要删的在后面（或者根本不存在）
// // ============================================================
// Node *delete_value(Node *head, int value) {

//     // ---------- 情况 ①：空链表，没有东西可删 ----------
//     if (head == NULL) {
//         printf("【删除 %d】链表为空，无法删除。\n", value);   // %d 是占位符，会被后面的 value
//         替换 return NULL;   // 空链表删完还是空链表
//     }

//     // ---------- 情况 ②：要删的是头节点 ----------
//     if (head->data == value) {
//         // 【关键】必须先用 temp 把头节点的地址记下来。
//         // 因为下一行 head 就要往后移动了，如果不先记下来，被删节点的地址就丢了，
//         // 再也没法 free 它，造成"内存泄漏"。
//         Node *temp = head;
//         head = head->next;   // 头指针后移一格：原来的第二个节点成为新的头
//         free(temp);          // 把被删除的旧头节点归还给系统
//         printf("【删除 %d】已删除头节点并free成功。\n", value);
//         return head;         // 返回新的头
//     }

//     // ---------- 情况 ③：要删的在头节点后面 ----------
//     // 删除链表中间的节点，需要两个游标一前一后一起走：
//     //   cur  ：当前正在检查的节点
//     //   prev ：cur 的前一个节点（prev 是 previous "前一个"的缩写）
//     // 为什么需要 prev？因为删除 cur 时，要让"前一个节点"直接连到"后一个节点"，跳过 cur。
//     // 而链表只能往后走，不能往回看，所以必须提前把前一个节点记着。
//     //
//     //   prev   cur
//     //    |      |
//     //    v      v
//     //   [10] -> [20] -> [30] -> NULL       现在要删除 20
//     //
//     // 删除步骤：prev->next = cur->next;  让 10 直接指向 30
//     //   [10] ---------> [30] -> NULL       20 被"绕过去"了
//     // 然后 free(cur) 把 20 这个节点归还给系统。
//     //
//     // 头节点已经在情况 ② 检查过了，所以从第二个节点开始：prev 是头，cur 是头的下一个。
//     Node *prev = head;
//     Node *cur = head->next;

//     while (cur != NULL) {   // cur 变成 NULL 说明已经走到链表末尾了
//         if (cur->data == value) {
//             prev->next = cur->next;   // ① 先让前一个节点"绕过"cur，连到 cur 的下一个
//             free(cur);                // ② 再归还 cur。顺序不能反：free 之后 cur->next
//             就不能再读了 printf("【删除 %d】已删除节点并free成功。\n", value); return head; //
//             只删第一个找到的，所以删完立刻返回（重复的值不会被删）
//         }
//         prev = cur;        // 没找到：prev 和 cur 都往后走一格
//         cur = cur->next;   // （先让 prev 追上 cur，再让 cur 往后走，顺序不能反）
//     }

//     // 整个链表走完了都没找到
//     printf("【删除 %d】未找到该值，链表不变。\n", value);
//     return head;
// }

// // ============================================================
// // 函数 print_list：把链表从头到尾打印出来，例如：链表：10 20 30 NULL
// //
// // 返回值类型 void：这个函数只负责打印，不返回任何结果。
// // 这里只是读数据、不修改链表，所以不需要返回头指针。
// // ============================================================
// void print_list(Node *head) {
//     printf(" 链表：");
//     Node *cur = head;        // 用游标 cur 遍历，不动 head
//     while (cur != NULL) {    // cur 不是 NULL，说明当前位置还有节点
//         printf("%d ", cur->data);   // 打印当前节点的数据，后面带一个空格隔开
//         cur = cur->next;            // 移动到下一个节点
//     }
//     printf("NULL\n");   // 链表末尾打印 NULL，表示"到头了"。空链表时只会显示 NULL
// }

// // ============================================================
// // 函数 free_list：把整个链表的所有节点逐个释放（归还内存）
// //
// // 链表的每个节点都是 malloc 出来的，用完后必须逐个 free，
// // 不能只 free(head)：那样只归还了第一个节点，其他节点就全部"泄漏"了。
// // ============================================================
// void free_list(Node *head) {
//     Node *cur = head;
//     while (cur != NULL) {
//         Node *temp = cur;   // ① 先把当前节点的地址记在 temp 里
//         cur = cur->next;    // ② 再让 cur 移动到下一个节点
//         free(temp);         // ③ 最后释放刚才记下的那个节点
//         // 【关键】顺序不能乱！必须先把"下一个节点的地址"取出来，才能释放当前节点。
//         // 如果先 free(cur)，再去读
//         cur->next，就是在读"已经归还的内存"，属于严重错误（use-after-free）。
//     }
//     printf("【释放链表】已释放所有节点。\n");

//     // 注意：这个函数收到的 head 只是一份复制品（值传递），
//     // 所以在这里没法把"调用者手里的 head"改成 NULL。
//     // 调用者在 free_list 之后，要自己把 head 置为 NULL（见 main 里的 head = NULL;）。
// }

// // ============================================================
// // 函数 build_list：根据一个整数数组，依次尾插，创建出一条链表，返回头指针
// //
// // 参数 const int arr[]：整数数组。
// //   · const 的意思是"只读"：承诺这个函数里只读取数组，不会修改它的内容。
// //   · C 语言里数组传给函数时，函数并不知道数组有多长，所以必须再用一个参数 n 告诉它长度。
// //     （这和 Java 里用 arr.length 取长度不一样，C 语言的数组没有这个功能。）
// // ============================================================
// Node *build_list(const int arr[], int n) {
//     Node *head = NULL;   // 从空链表开始
//     for (int i = 0; i < n; i++) {
//         head = insert_tail(head, arr[i]);   // 每次尾插一个，并用返回值更新 head
//     }
//     return head;
//     // 说明：每次 insert_tail 都要从头走到尾找最后一个节点，所以建一条长链表会比较慢。
//     // 现阶段先理解清楚原理；以后可以学"同时记住尾节点"的写法来优化。
// }

// // ============================================================
// // main 函数：程序的入口。运行程序时，从这里的第一行开始，一行一行往下执行。
// //
// // int main(void)：
// //   int  ：main 执行完会返回一个整数给操作系统（0 表示正常结束）
// //   void ：括号里的 void 表示"不需要任何参数"
// // ============================================================
// int main(void) {
//     // 准备三个测试用的数组。{10, 20, 30} 是在创建数组的同时直接放入内容。
//     int three[] = {10, 20, 30};
//     int one[] = {10};
//     int dup[] = {10, 20, 30, 20};   // dup 是 duplicate"重复"的缩写：里面有两个 20

//     Node *head = NULL;   // head 是链表的头指针。NULL 表示一开始链表是空的

//     // 每一组测试的套路都一样：创建 → 打印 → 执行操作 → 再打印 → 释放。
//     // 下面每组测试后面的注释，写的是预期的输出结果，运行后可以对照检查。

//     // ---------- 测试 1：对空链表执行删除 ----------
//     printf("\n测试 1：空链表删除\n");
//     head = NULL;
//     print_list(head);               // 预期： 链表：NULL
//     head = delete_value(head, 5);   // 预期：【删除 5】链表为空，无法删除。
//     print_list(head);               // 预期： 链表：NULL
//     free_list(head);                // 空链表什么也不用释放，只会打印提示
//     head = NULL;                    // 【修复点】释放后立刻把 head 置为 NULL，避免留下"野指针"
//                                     // （后面每一组测试的 free_list 之后也都补上了这一句）

//     // ---------- 测试 2：删除头节点 ----------
//     printf("\n测试 2：删除头节点\n");
//     head = build_list(three, 3);    // 建出链表 10 -> 20 -> 30
//     print_list(head);               // 预期： 链表：10 20 30 NULL
//     head = delete_value(head, 10);  // 预期：【删除 10】已删除头节点并free成功。
//     print_list(head);               // 预期： 链表：20 30 NULL
//     free_list(head);
//     head = NULL;

//     // ---------- 测试 3：删除尾节点 ----------
//     // 【修复点】原标题"删除节点"太笼统。这里删除的 30 是最后一个节点，所以改成"删除尾节点"。
//     printf("\n测试 3：删除尾节点\n");
//     head = build_list(three, 3);
//     print_list(head);               // 预期： 链表：10 20 30 NULL
//     head = delete_value(head, 30);  // 预期：【删除 30】已删除节点并free成功。
//     print_list(head);               // 预期： 链表：10 20 NULL
//     free_list(head);
//     head = NULL;

//     // ---------- 测试 4：删除不存在的值 ----------
//     printf("\n测试 4：删除不存在的值\n");
//     head = build_list(three, 3);
//     print_list(head);               // 预期： 链表：10 20 30 NULL
//     head = delete_value(head, 99);  // 预期：【删除 99】未找到该值，链表不变。
//     print_list(head);               // 预期： 链表：10 20 30 NULL（没有变化）
//     free_list(head);
//     head = NULL;

//     // ---------- 测试 5：删除唯一的节点（链表只有一个节点） ----------
//     printf("\n测试 5：删除唯一节点\n");
//     head = build_list(one, 1);
//     print_list(head);               // 预期： 链表：10 NULL
//     head = delete_value(head, 10);  // 预期：【删除 10】已删除头节点并free成功。
//     print_list(head);               // 预期： 链表：NULL（删完变成空链表，head 被更新为 NULL）
//     free_list(head);
//     head = NULL;

//     // ---------- 测试 6（附加）：删除中间节点，并且有重复值时只删第一个 ----------
//     // 【修复点】标题里原来是"测试 6：（附加）："多了一个冒号，已整理。
//     printf("\n测试 6（附加）：删除中间节点 + 重复值只删除一个\n");
//     head = build_list(dup, 4);      // 建出链表 10 -> 20 -> 30 -> 20
//     print_list(head);               // 预期： 链表：10 20 30 20 NULL
//     head = delete_value(head, 20);  // 预期：【删除 20】已删除节点并free成功。（删的是第一个
//     20，位置在中间） print_list(head);               // 预期： 链表：10 30 20 NULL（后面那个 20
//     还在） free_list(head); head = NULL;

//     printf("\n所有测试结束\n");
//     return 0;   // 返回 0 给操作系统，表示程序正常结束
// }
