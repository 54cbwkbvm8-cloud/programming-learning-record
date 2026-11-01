
#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *next;
} Node;

int created_count = 0;
int freed_count = 0;
int total_checks = 0;
int failed_checks = 0;

Node *create_node(int value) {

    Node *new_node = (Node *)malloc(sizeof(Node));

    if (new_node == NULL) {
        printf("内存申请失败，程序结束。\n");
        exit(EXIT_FAILURE);
    }

    new_node->data = value;

    new_node->next = NULL;

    created_count++;

    return new_node;
}

void destroy_node(Node *node) {

    if (node == NULL) {
        return;
    }

    free(node);

    freed_count++;
}

Node *insert_head(Node *head, int value) {

    Node *new_node = create_node(value);

    new_node->next = head;

    return new_node;
}

Node *insert_tail(Node *head, int value) {

    Node *new_node = create_node(value);

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

Node *delete_by_value(Node *head, int target, int *is_deleted) {

    *is_deleted = 0;

    if (head == NULL) {
        return NULL;
    }

    if (head->data == target) {

        Node *to_delete = head;

        head = head->next;

        destroy_node(to_delete);

        *is_deleted = 1;

        return head;
    }

    Node *prev = head;
    Node *current = head->next;

    while (current != NULL) {

        if (current->data == target) {

            prev->next = current->next;

            destroy_node(current);

            *is_deleted = 1;

            return head;
        }

        prev = current;
        current = current->next;
    }

    return head;
}

Node *free_list(Node *head) {

    while (head != NULL) {

        Node *next_node = head->next;

        destroy_node(head);

        head = next_node;
    }

    return NULL;
}

void print_list(Node *head) {

    Node *current = head;

    printf("head");

    while (current != NULL) {

        printf(" -> %d", current->data);

        current = current->next;
    }

    printf(" -> NULL\n");
}

int count_nodes(Node *head) {

    int count = 0;
    Node *current = head;

    while (current != NULL) {

        count++;

        current = current->next;
    }

    return count;
}

int memory_ok(Node *head) { return created_count - freed_count == count_nodes(head); }

int list_equals(Node *head, const int expected[], int expected_count) {

    Node *current = head;

    for (int i = 0; i < expected_count; i++) {

        if (current == NULL || current->data != expected[i]) {
            return 0;
        }

        current = current->next;
    }

    return current == NULL;
}

void check(int condition, const char *description) {

    total_checks++;

    if (condition) {

        printf("[通过] %s\n", description);

    } else {

        printf("[失败] %s\n", description);

        failed_checks++;
    }
}

void report_memory(void) {

    printf("[内存报告] 创建 %d 个节点，释放 %d 个节点，"
           "尚未释放 %d 个节点\n",
           created_count, freed_count, created_count - freed_count);
}

int main(void) {

    Node *head = NULL;

    int is_deleted = 0;

    int freed_before = 0;

    printf("===== 开始测试 =====\n");

    printf("\n[测试 1] 空链表删除 10\n");

    freed_before = freed_count;

    head = delete_by_value(head, 10, &is_deleted);

    print_list(head);

    check(is_deleted == 0, "空链表：没有删除任何节点");
    check(head == NULL, "空链表：头指针仍为 NULL");
    check(freed_count == freed_before, "空链表：没有释放节点");
    check(memory_ok(head), "空链表：内存计数平衡");

    printf("\n[准备] 尾插 20、30、40、50\n");

    head = insert_tail(head, 20);
    head = insert_tail(head, 30);
    head = insert_tail(head, 40);
    head = insert_tail(head, 50);

    print_list(head);

    int list_a[] = {20, 30, 40, 50};

    check(list_equals(head, list_a, 4), "尾插后链表应为 20 -> 30 -> 40 -> 50");

    check(memory_ok(head), "尾插后：内存计数平衡");

    printf("\n[测试 2] 删除头节点 20\n");

    freed_before = freed_count;

    head = delete_by_value(head, 20, &is_deleted);

    print_list(head);

    int list_b[] = {30, 40, 50};

    check(is_deleted == 1, "删除头节点 20 应成功");

    check(list_equals(head, list_b, 3), "删除后链表应为 30 -> 40 -> 50");

    check(freed_count == freed_before + 1, "删除头节点：恰好释放 1 个节点");

    check(memory_ok(head), "删除头节点后：内存计数平衡");

    printf("\n[测试 3] 删除尾节点 50\n");

    freed_before = freed_count;

    head = delete_by_value(head, 50, &is_deleted);

    print_list(head);

    int list_c[] = {30, 40};

    check(is_deleted == 1, "删除尾节点 50 应成功");

    check(list_equals(head, list_c, 2), "删除后链表应为 30 -> 40");

    check(freed_count == freed_before + 1, "删除尾节点：恰好释放 1 个节点");

    check(memory_ok(head), "删除尾节点后：内存计数平衡");

    printf("\n[测试 4] 删除不存在的值 99\n");

    freed_before = freed_count;

    head = delete_by_value(head, 99, &is_deleted);

    print_list(head);

    check(is_deleted == 0, "找不到 99：删除应失败");

    check(list_equals(head, list_c, 2), "找不到 99：链表保持 30 -> 40 不变");

    check(freed_count == freed_before, "找不到 99：释放次数不变");

    check(memory_ok(head), "删除不存在的值后：内存计数平衡");

    printf("\n[测试 5] 删除剩余节点，最终删除唯一节点 40\n");

    head = delete_by_value(head, 30, &is_deleted);

    check(is_deleted == 1, "删除节点 30 应成功");

    freed_before = freed_count;

    head = delete_by_value(head, 40, &is_deleted);

    print_list(head);

    check(is_deleted == 1, "删除唯一节点 40 应成功");
    check(head == NULL, "删除唯一节点后，链表为空");
    check(freed_count == freed_before + 1, "删除唯一节点：恰好释放 1 个节点");
    check(memory_ok(head), "空链表：内存计数平衡");

    printf("\n[测试 6] 重复值：5、7、5 中只删除第一个 5\n");

    head = insert_tail(head, 5);
    head = insert_tail(head, 7);
    head = insert_tail(head, 5);

    print_list(head);

    freed_before = freed_count;

    head = delete_by_value(head, 5, &is_deleted);

    print_list(head);

    int list_d[] = {7, 5};

    check(is_deleted == 1, "删除第一个值为 5 的节点应成功");

    check(list_equals(head, list_d, 2), "删除后链表应为 7 -> 5，第二个 5 仍然保留");

    check(freed_count == freed_before + 1, "重复值测试：恰好释放 1 个节点");

    check(memory_ok(head), "重复值测试后：内存计数平衡");

    printf("\n[收尾] 释放所有剩余节点\n");

    head = free_list(head);

    print_list(head);

    check(head == NULL, "释放整条链表后，head 为 NULL");

    check(created_count == freed_count, "创建和释放次数相等，没有遗留未释放节点");

    report_memory();

    printf("\n测试合计：%d 项，失败：%d 项\n", total_checks, failed_checks);

    if (failed_checks > 0) {
        return 1;
    }

    printf("\n===== 所有测试通过，测试结束 =====\n");

    return 0;
}
/*// ====================================================================
// 文件名：45_2026-10-29_单链表尾插遍历删除与测试.c
// 作用：一个完整的单链表练习，并带有"自动测试"：
//       create_node    ：创建节点            destroy_node  ：释放节点（并计数）
//       insert_head    ：头插（定义了，测试里没有用到）
//       insert_tail    ：尾插                delete_by_value：按值删除（只删第一个匹配的）
//       free_list      ：释放整条链表        print_list    ：输出链表
//       count_nodes    ：统计节点个数
//       check / list_equals / memory_ok：测试用的"检查工具"
//       main 里做 6 组测试，每一项检查输出 [通过] 或 [失败]，
//       并用"创建的节点数 - 释放的节点数 = 链表里现有的节点数"来核对有没有内存泄漏。
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "45_2026-10-29_单链表尾插遍历删除与测试.c"
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

// <stdlib.h> 里有 malloc（申请内存）、free（释放内存）、exit（结束程序）、EXIT_FAILURE（表示"失败"的退出码）。
#include <stdlib.h>


// --------------------------------------------------------------------
// 第二部分：定义"节点"长什么样
// --------------------------------------------------------------------
// 节点由两样东西组成：data（存的整数）和 next（指向下一个节点的指针）。
// struct Node *next：结构体里不能直接包含自己，但可以包含"指向同类型的指针"，这就是链表能"连起来"的原因。
// typedef ... Node; 给它起个短名字 Node，以后直接写 Node。
// 类比：链表像一列火车，每节车厢装着货物 data，挂钩 next 连着下一节；最后一节的挂钩是 NULL。
typedef struct Node {
    int data;
    struct Node *next;
} Node;


// --------------------------------------------------------------------
// 第三部分：全局计数器
// --------------------------------------------------------------------
// 写在函数外面的变量叫"全局变量"，所有函数都能使用。这四个计数器都从 0 开始：
//   created_count ：一共创建了多少个节点（每创建一个加 1）；
//   freed_count   ：一共释放了多少个节点（每释放一个加 1）；
//   total_checks  ：一共做了多少项检查；
//   failed_checks ：其中失败了几项。
// 为什么要数创建和释放的个数？用来检查"内存泄漏"：用 malloc 借来的内存，必须用 free 还；
// 程序结束时，创建数和释放数应该相等。
int created_count = 0;
int freed_count = 0;
int total_checks = 0;
int failed_checks = 0;


// --------------------------------------------------------------------
// 第四部分：创建一个新节点
// --------------------------------------------------------------------
// 返回类型 Node *：返回"指向 Node 的指针"，也就是新节点的地址。参数 value 是要存进节点的数据。
Node *create_node(int value) {

    // malloc(字节数)：在"堆"上向系统申请一块内存，返回起始地址；失败返回 NULL。
    // sizeof(Node)：一个 Node 占多少字节。(Node *) 是强制类型转换，把地址转成"指向 Node 的指针"。
    Node *new_node = (Node *)malloc(sizeof(Node));

    // 申请可能失败（内存不足），失败时 malloc 返回 NULL，必须检查，否则后面访问 NULL 会崩溃。
    if (new_node == NULL) {
        printf("内存申请失败，程序结束。\n");

        // exit 会立刻结束整个程序，不管现在在哪一层函数里（比 return 更彻底）。EXIT_FAILURE 表示"失败"。
        // 这里选择直接退出，好处是调用者不用每次都检查"有没有创建成功"。
        exit(EXIT_FAILURE);
    }

    // -> 叫"通过指针访问成员"：new_node->data 的意思是"new_node 指向的节点里的 data"。把传进来的数据存进去。
    new_node->data = value;

    // 新节点还没有下一个节点，所以 next 设为 NULL。
    // 为什么必须设置？malloc 申请到的内存里是随机数据，next 如果是乱地址，之后遍历就会崩溃。
    new_node->next = NULL;

    // 成功创建一个节点，计数器加 1。++ 是"自增"，等价于 created_count = created_count + 1。
    created_count++;

    // 把新节点的地址交给调用者。内存在堆上，函数结束后不会消失，所以可以放心返回。
    return new_node;
}


// --------------------------------------------------------------------
// 第五部分：释放一个节点
// --------------------------------------------------------------------
// void 表示没有返回值。所有释放节点的操作都走这个函数，这样 freed_count 才不会漏记。
void destroy_node(Node *node) {

    // 如果传进来的是空指针，什么都不用做，直接返回。
    if (node == NULL) {
        // return; 用在 void 函数里，表示"提前结束这个函数"。
        return;
    }

    // free(node)：把 malloc 借来的这块内存还给系统。free 之后不能再使用这个节点。
    free(node);

    // 成功释放一个节点，计数器加 1。
    freed_count++;
}


// --------------------------------------------------------------------
// 第六部分：头插 —— 把新节点插在链表最前面
// --------------------------------------------------------------------
// 参数：head 是链表的头指针，value 是新节点的数据。返回值：新的头指针。
// 例：原链表 20 -> 30，头插 10，得到 10 -> 20 -> 30。头插不需要遍历，速度很快。
// 注意：这个函数在本程序里只是定义了，测试里并没有调用它。
Node *insert_head(Node *head, int value) {

    // 先创建新节点。
    Node *new_node = create_node(value);

    // 让新节点的 next 指向原来的第一个节点。
    // 顺序很重要：先让新节点"连上后面"，再去改头指针；反过来的话，原来的链表就找不到了。
    new_node->next = head;

    // 新节点成为新的头，把它的地址返回给调用者。
    return new_node;
}


// --------------------------------------------------------------------
// 第七部分：尾插 —— 把新节点插在链表末尾
// --------------------------------------------------------------------
// 参数：head 是头指针，value 是新节点的数据。返回值：新的头指针。
// 为什么要返回头指针？如果原来是空链表，新节点就成了头，头指针变了；
// 函数里改参数 head 不会影响 main 里的 head（值传递），所以通过返回值把"新的头"交给调用者：head = insert_tail(head, 20);
Node *insert_tail(Node *head, int value) {

    // 先创建新节点。
    Node *new_node = create_node(value);

    // 链表是空的：新节点就是第一个节点，也就是新的头，直接返回它。
    if (head == NULL) {
        return new_node;
    }

    // 链表不是空的：用游标 current 从头出发，去找最后一个节点。
    Node *current = head;

    // 注意条件是 current->next != NULL，而不是 current != NULL：我们要停在"最后一个节点"上（它的 next 是 NULL），
    // 而不是走出链表。
    // 例：链表是 20 -> 30：current 在 20，next 不是 NULL，走到 30；30 的 next 是 NULL，停在 30。
    while (current->next != NULL) {
        current = current->next;
    }

    // 把新节点挂在最后一个节点后面：最后一个节点的 next 从 NULL 变成新节点的地址。
    current->next = new_node;

    // 头没有变，返回原来的头指针。
    return head;
}


// --------------------------------------------------------------------
// 第八部分：按值删除 —— 删除第一个值等于 target 的节点
// --------------------------------------------------------------------
// 参数：head 是头指针；target 是要删除的值；is_deleted 是一个"输出参数"。
//   int *is_deleted：指向调用者的一个 int 变量。函数通过它把"有没有删掉"告诉调用者（0 没有，1 有）。
// 返回值：删除之后新的头指针（如果删的是头节点，头会变）。
// 为什么既要返回值，又要输出参数？返回值已经用来交回"新的头指针"，
// "是否删除成功"是另一个结果，只能通过指针参数带出去。
Node *delete_by_value(Node *head, int target, int *is_deleted) {

    // *is_deleted 的意思是"is_deleted 指向的那个变量"。先设成 0，表示默认"没删到"。
    *is_deleted = 0;

    // 空链表：什么都没有，没法删除，直接返回 NULL（头保持为空）。
    if (head == NULL) {
        return NULL;
    }

    // 情况 1：要删除的就是头节点。
    if (head->data == target) {

        // 先记住要删除的节点，因为马上要移动 head。
        Node *to_delete = head;

        // 头指针后移，指向第二个节点：原来的头节点就从链表里"脱离"了。
        // 例：10 -> 20 -> 30，删除 10：head 变成指向 20 的节点。
        head = head->next;

        // 释放脱离出来的节点。注意顺序：先让 head 后移，再释放；如果先释放，就读不到 head->next 了。
        destroy_node(to_delete);

        // 通过输出参数告诉调用者：删除成功。
        *is_deleted = 1;

        // 返回新的头指针（第二个节点）。
        return head;
    }

    // 情况 2：要删除的不是头节点。这时需要两个指针一前一后：
    //   prev   ：前一个节点；
    //   current：当前正在检查的节点。
    // 为什么需要 prev？要删除 current，必须让它前一个节点的 next "绕过"它，指向 current 的下一个；
    // 单链表不能回头，所以一路都要带着 prev。
    Node *prev = head;

    // 从第二个节点开始检查（头节点已经检查过，不是目标）。
    Node *current = head->next;

    // 只要还没走出链表，就继续往后找。
    while (current != NULL) {

        // 找到了目标节点。
        if (current->data == target) {

            // 关键一步：让前一个节点的 next 绕过 current，直接指向 current 的下一个节点。
            // 例：20 -> 30 -> 40，删除 30：prev 是 20，current 是 30；执行后 20 的 next 指向 40，30 就从链表里脱离了。
            prev->next = current->next;

            // 把脱离出来的节点释放。必须在 prev->next 改好之后再释放。
            destroy_node(current);

            // 通知调用者：删除成功。
            *is_deleted = 1;

            // 头没有变，返回原来的头指针。找到之后立刻返回，所以只会删第一个匹配的节点，后面同值的节点保持不动。
            return head;
        }

        // 这个节点不是目标：prev 和 current 同时往后挪一格，继续检查下一个。
        prev = current;
        current = current->next;
    }

    // 走完整条链表都没找到：不删除，is_deleted 保持 0，头指针不变。
    return head;
}


// --------------------------------------------------------------------
// 第九部分：释放整条链表
// --------------------------------------------------------------------
// 每个节点都是 malloc 申请的，用完必须逐个释放。返回 NULL，让调用者把头指针也设成 NULL。
Node *free_list(Node *head) {

    // 这里直接用参数 head 当游标往后走（它是函数内部的拷贝，改它不会影响 main 里的 head）。
    while (head != NULL) {

        // 先把"下一个节点的地址"保存起来！因为释放当前节点之后，就不能再访问它的 next 了。
        Node *next_node = head->next;

        // 释放当前节点。
        destroy_node(head);

        // 移到刚才保存的下一个节点。
        head = next_node;
    }

    // 全部释放完，返回 NULL。调用者写 head = free_list(head);，这样 main 里的 head 也变成 NULL，不会留下"悬空指针"。
    return NULL;
}


// --------------------------------------------------------------------
// 第十部分：输出链表
// --------------------------------------------------------------------
void print_list(Node *head) {

    // 用游标从头走到尾，不直接移动 head。
    Node *current = head;

    // 先输出开头的 head。
    printf("head");

    // 只要游标还没走出链表（不是 NULL），就继续循环。
    while (current != NULL) {

        // 输出一个箭头和当前节点的数据。
        printf(" -> %d", current->data);

        // 游标移到下一个节点：这是遍历链表的核心一步。
        current = current->next;
    }

    // 走到末尾，输出 NULL 表示"到头了"，空链表时只会输出 head -> NULL。
    printf(" -> NULL\n");
}


// --------------------------------------------------------------------
// 第十一部分：统计节点个数
// --------------------------------------------------------------------
// 遍历一遍链表，每经过一个节点就数一个。
int count_nodes(Node *head) {

    // count 存节点个数，从 0 开始。
    int count = 0;
    Node *current = head;

    while (current != NULL) {

        // 每走到一个节点，计数加 1。
        count++;

        current = current->next;
    }

    // 把节点个数返回给调用者。
    return count;
}


// --------------------------------------------------------------------
// 第十二部分：检查内存是否"平衡"
// --------------------------------------------------------------------
// 逻辑：创建的节点数 - 释放的节点数，应该等于链表里现在实际有的节点数（count_nodes(head)）。
// 不相等就说明有问题：有节点被"丢"了没释放（内存泄漏），或者重复释放了。
// == 比较的结果是 1（真）或 0（假），所以函数可以直接 return 这个比较的结果。运算顺序：先算减法，再比较。
int memory_ok(Node *head) { return created_count - freed_count == count_nodes(head); }


// --------------------------------------------------------------------
// 第十三部分：检查链表内容是否符合预期
// --------------------------------------------------------------------
// 把链表的内容和期望的数组逐个对比：个数和每个值都完全一致，返回 1；否则返回 0。
// const int expected[] 是期望的数据数组，const 表示函数只读不改。
int list_equals(Node *head, const int expected[], int expected_count) {

    // 游标从链表头开始。
    Node *current = head;

    // i 从 0 数到 expected_count - 1，依次对比期望的每个数据。
    for (int i = 0; i < expected_count; i++) {

        // 两种不一致的情况：链表比期望的短（走到 NULL 了），或者当前节点的值不同。
        // || 有"短路"特点：左边 current == NULL 成立时，右边的 current->data 不会被计算，避免访问 NULL 出错。
        if (current == NULL || current->data != expected[i]) {
            // 不一致，返回 0。
            return 0;
        }

        // 对上了，游标走向下一个节点。
        current = current->next;
    }

    // 期望的数据都对上之后，还要确认链表没有多余的节点：此时 current 必须是 NULL。
    // 这个比较的结果（1 或 0）直接作为返回值。
    return current == NULL;
}


// --------------------------------------------------------------------
// 第十四部分：测试辅助函数 —— check
// --------------------------------------------------------------------
// check 是一个简单的"断言"函数：传入一个条件（真或假）和一段说明文字，
// 条件成立输出 [通过]，不成立输出 [失败]，同时统计次数。
// const char *description 是字符串参数（指向字符的指针）；字符串常量不能被修改，所以加 const。
// 为什么写成函数？每一项检查都要输出一行并计数，写成函数就不用重复写。
void check(int condition, const char *description) {

    // 每调用一次，检查总数加 1。
    total_checks++;

    // C 语言里 0 是假，非 0 是真，所以可以直接写 if (condition)。
    if (condition) {

        // %s 是字符串的占位符，输出说明文字。
        printf("[通过] %s\n", description);

    } else {

        printf("[失败] %s\n", description);

        // 失败的检查数加 1。
        failed_checks++;
    }
}


// --------------------------------------------------------------------
// 第十五部分：输出内存报告
// --------------------------------------------------------------------
void report_memory(void) {

    // 这条 printf 的格式字符串被拆成了相邻的两段字符串，C 会自动把它们拼成一句。
    // 三个 %d 依次对应：created_count（创建数）、freed_count（释放数）、created_count - freed_count（还没释放的数）。
    printf("[内存报告] 创建 %d 个节点，释放 %d 个节点，"
           "尚未释放 %d 个节点\n",
           created_count, freed_count, created_count - freed_count);
}


// --------------------------------------------------------------------
// 第十六部分：main 函数 —— 开始测试
// --------------------------------------------------------------------
int main(void) {

    // head 是头指针，指向链表的第一个节点。NULL 表示"空链表"。
    Node *head = NULL;

    // 用来接收 delete_by_value 通过输出参数带回来的结果。
    int is_deleted = 0;

    // 记录"删除之前已经释放了多少个节点"。删除后拿现在的 freed_count 去对比，就能验证"这一次恰好释放了几个"。
    int freed_before = 0;

    printf("===== 开始测试 =====\n");

    // --------------------------------------------------------------------
    // 测试 1：在空链表里删除 10
    // --------------------------------------------------------------------
    printf("\n[测试 1] 空链表删除 10\n");

    freed_before = freed_count;

    // &is_deleted 是 is_deleted 的地址（& 叫取地址符），传进去后，函数就能把结果写回这个变量。
    // 返回值接住新的头指针（这里仍然是 NULL）。
    head = delete_by_value(head, 10, &is_deleted);

    // 输出链表：此时应是 head -> NULL。
    print_list(head);

    check(is_deleted == 0, "空链表：没有删除任何节点");   // 期望：没有删除任何节点，所以 is_deleted 应该是 0
    check(head == NULL, "空链表：头指针仍为 NULL");   // 期望：头指针仍然是 NULL
    check(freed_count == freed_before, "空链表：没有释放节点");   // 期望：没有释放任何节点，释放次数不变
    check(memory_ok(head), "空链表：内存计数平衡");   // 期望：创建数和释放数的差，等于链表里现有的节点数

    // --------------------------------------------------------------------
    // 准备数据：尾插 20、30、40、50
    // --------------------------------------------------------------------
    printf("\n[准备] 尾插 20、30、40、50\n");

    // 尾插 20。必须写 head = ...，接住返回的新头指针（第一次插入时，头指针从 NULL 变成了新节点）。
    // 下面依次尾插 30、40、50，链表变成：20 -> 30 -> 40 -> 50 -> NULL
    head = insert_tail(head, 20);
    head = insert_tail(head, 30);
    head = insert_tail(head, 40);
    head = insert_tail(head, 50);

    print_list(head);

    // 期望的内容数组，用来和链表对比。
    int list_a[] = {20, 30, 40, 50};

    // 把链表和期望数组 list_a（4 个元素）逐个对比，完全一致才算通过。
    check(list_equals(head, list_a, 4), "尾插后链表应为 20 -> 30 -> 40 -> 50");

    check(memory_ok(head), "尾插后：内存计数平衡");

    // --------------------------------------------------------------------
    // 测试 2：删除头节点 20
    // --------------------------------------------------------------------
    printf("\n[测试 2] 删除头节点 20\n");

    // 记下删除之前的释放次数。
    freed_before = freed_count;

    // 删除头节点 20（对应"情况 1"）：头指针会变成指向 30 的节点，所以必须接住返回值。
    head = delete_by_value(head, 20, &is_deleted);

    print_list(head);

    // 期望：删除之后链表是 30 -> 40 -> 50。
    int list_b[] = {30, 40, 50};

    check(is_deleted == 1, "删除头节点 20 应成功");   // 期望：删除成功，is_deleted 应该是 1

    check(list_equals(head, list_b, 3), "删除后链表应为 30 -> 40 -> 50");   // 期望：链表内容是 30 -> 40 -> 50

    check(freed_count == freed_before + 1, "删除头节点：恰好释放 1 个节点");   // 期望：这次恰好释放了 1 个节点（不多也不少）

    check(memory_ok(head), "删除头节点后：内存计数平衡");   // 期望：内存计数依然平衡

    // --------------------------------------------------------------------
    // 测试 3：删除尾节点 50
    // --------------------------------------------------------------------
    printf("\n[测试 3] 删除尾节点 50\n");

    freed_before = freed_count;

    // 删除尾节点 50（对应"情况 2"：prev 是 40，current 是 50）：40 的 next 变成 NULL，头指针不变。
    head = delete_by_value(head, 50, &is_deleted);

    print_list(head);

    // 期望：删除之后链表是 30 -> 40。
    int list_c[] = {30, 40};

    check(is_deleted == 1, "删除尾节点 50 应成功");

    check(list_equals(head, list_c, 2), "删除后链表应为 30 -> 40");

    check(freed_count == freed_before + 1, "删除尾节点：恰好释放 1 个节点");

    check(memory_ok(head), "删除尾节点后：内存计数平衡");

    // --------------------------------------------------------------------
    // 测试 4：删除不存在的值 99
    // --------------------------------------------------------------------
    printf("\n[测试 4] 删除不存在的值 99\n");

    freed_before = freed_count;

    // 整条链表里找不到 99：不删除任何节点，is_deleted 保持 0，链表和释放次数都不变。
    head = delete_by_value(head, 99, &is_deleted);

    print_list(head);

    check(is_deleted == 0, "找不到 99：删除应失败");   // 期望：删除失败，is_deleted 是 0

    check(list_equals(head, list_c, 2), "找不到 99：链表保持 30 -> 40 不变");   // 期望：链表保持 30 -> 40 不变

    check(freed_count == freed_before, "找不到 99：释放次数不变");

    check(memory_ok(head), "删除不存在的值后：内存计数平衡");

    // --------------------------------------------------------------------
    // 测试 5：把剩余节点删光，最后删除唯一的节点 40
    // --------------------------------------------------------------------
    printf("\n[测试 5] 删除剩余节点，最终删除唯一节点 40\n");

    // 先删除 30（此时它是头节点），链表剩下 40 一个节点。
    head = delete_by_value(head, 30, &is_deleted);

    check(is_deleted == 1, "删除节点 30 应成功");

    freed_before = freed_count;

    // 再删除 40：此时它既是头节点，也是最后一个节点。删除后 head 变成它的 next，也就是 NULL，链表变空。
    head = delete_by_value(head, 40, &is_deleted);

    print_list(head);

    check(is_deleted == 1, "删除唯一节点 40 应成功");
    check(head == NULL, "删除唯一节点后，链表为空");   // 期望：删光之后链表为空，头指针是 NULL
    check(freed_count == freed_before + 1, "删除唯一节点：恰好释放 1 个节点");
    check(memory_ok(head), "空链表：内存计数平衡");

    // --------------------------------------------------------------------
    // 测试 6：重复的值 —— 链表 5、7、5，删除 5 时只删第一个
    // --------------------------------------------------------------------
    printf("\n[测试 6] 重复值：5、7、5 中只删除第一个 5\n");

    // 重新往空链表里尾插 5、7、5，得到 5 -> 7 -> 5 -> NULL。
    head = insert_tail(head, 5);
    head = insert_tail(head, 7);
    head = insert_tail(head, 5);

    print_list(head);

    freed_before = freed_count;

    // 删除 5：只会删除"第一个"值为 5 的节点（头节点），第二个 5 保留。
    head = delete_by_value(head, 5, &is_deleted);

    print_list(head);

    // 期望：删除之后链表是 7 -> 5。
    int list_d[] = {7, 5};

    check(is_deleted == 1, "删除第一个值为 5 的节点应成功");

    check(list_equals(head, list_d, 2), "删除后链表应为 7 -> 5，第二个 5 仍然保留");   // 期望：7 -> 5，第二个 5 还在

    check(freed_count == freed_before + 1, "重复值测试：恰好释放 1 个节点");

    check(memory_ok(head), "重复值测试后：内存计数平衡");

    // --------------------------------------------------------------------
    // 收尾：释放全部节点，并做最后的内存检查
    // --------------------------------------------------------------------
    printf("\n[收尾] 释放所有剩余节点\n");

    // 释放整条链表，并用返回的 NULL 更新 head，避免留下"悬空指针"。
    head = free_list(head);

    print_list(head);

    check(head == NULL, "释放整条链表后，head 为 NULL");   // 期望：释放之后 head 是 NULL

    // 最终检查：创建的节点数必须等于释放的节点数；相等就说明没有内存泄漏。
    check(created_count == freed_count, "创建和释放次数相等，没有遗留未释放节点");

    // 输出内存报告：创建了多少、释放了多少、还有多少没释放（应为 0）。
    report_memory();

    // total_checks 是检查总数，failed_checks 是失败的项数。
    printf("\n测试合计：%d 项，失败：%d 项\n", total_checks, failed_checks);

    // 只要有失败项，就以"出错"状态（返回 1）结束程序；这样别的程序或脚本可以通过退出码知道测试有没有通过。
    if (failed_checks > 0) {
        return 1;
    }

    printf("\n===== 所有测试通过，测试结束 =====\n");

    // return 0：结束 main，把 0 交给系统，表示"程序正常结束"。
    return 0;
}

// ====================================================================
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   ===== 开始测试 =====
//
//   [测试 1] 空链表删除 10
//   head -> NULL
//   [通过] 空链表：没有删除任何节点
//   [通过] 空链表：头指针仍为 NULL
//   [通过] 空链表：没有释放节点
//   [通过] 空链表：内存计数平衡
//
//   [准备] 尾插 20、30、40、50
//   head -> 20 -> 30 -> 40 -> 50 -> NULL
//   [通过] 尾插后链表应为 20 -> 30 -> 40 -> 50
//   [通过] 尾插后：内存计数平衡
//
//   [测试 2] 删除头节点 20
//   head -> 30 -> 40 -> 50 -> NULL
//   [通过] 删除头节点 20 应成功
//   [通过] 删除后链表应为 30 -> 40 -> 50
//   [通过] 删除头节点：恰好释放 1 个节点
//   [通过] 删除头节点后：内存计数平衡
//
//   [测试 3] 删除尾节点 50
//   head -> 30 -> 40 -> NULL
//   [通过] 删除尾节点 50 应成功
//   [通过] 删除后链表应为 30 -> 40
//   [通过] 删除尾节点：恰好释放 1 个节点
//   [通过] 删除尾节点后：内存计数平衡
//
//   [测试 4] 删除不存在的值 99
//   head -> 30 -> 40 -> NULL
//   [通过] 找不到 99：删除应失败
//   [通过] 找不到 99：链表保持 30 -> 40 不变
//   [通过] 找不到 99：释放次数不变
//   [通过] 删除不存在的值后：内存计数平衡
//
//   [测试 5] 删除剩余节点，最终删除唯一节点 40
//   [通过] 删除节点 30 应成功
//   head -> NULL
//   [通过] 删除唯一节点 40 应成功
//   [通过] 删除唯一节点后，链表为空
//   [通过] 删除唯一节点：恰好释放 1 个节点
//   [通过] 空链表：内存计数平衡
//
//   [测试 6] 重复值：5、7、5 中只删除第一个 5
//   head -> 5 -> 7 -> 5 -> NULL
//   head -> 7 -> 5 -> NULL
//   [通过] 删除第一个值为 5 的节点应成功
//   [通过] 删除后链表应为 7 -> 5，第二个 5 仍然保留
//   [通过] 重复值测试：恰好释放 1 个节点
//   [通过] 重复值测试后：内存计数平衡
//
//   [收尾] 释放所有剩余节点
//   head -> NULL
//   [通过] 释放整条链表后，head 为 NULL
//   [通过] 创建和释放次数相等，没有遗留未释放节点
//   [内存报告] 创建 7 个节点，释放 7 个节点，尚未释放 0 个节点
//
//   测试合计：29 项，失败：0 项
//
//   ===== 所有测试通过，测试结束 =====
// ====================================================================
*/
