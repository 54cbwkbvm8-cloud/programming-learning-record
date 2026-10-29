
#include <stdio.h>  // 提供 printf()、scanf() 等输入输出函数
#include <stdlib.h> // 提供 malloc()、free()、exit() 等内存管理函数

/*
 * 第一部分：定义链表节点
 *
 * 可以把链表想象成一列通过绳子连接起来的盒子。
 *
 * 每个盒子（节点）里面有两样东西：
 * 1. data：存放实际的数据，例如 10、20、30。
 * 2. next：存放下一个盒子的地址，用来把节点连接起来。
 *
 * struct Node 表示这个结构体的类型名称。
 * typedef struct Node { ... } Node;
 * 则让我们以后可以直接写 Node，而不用每次写 struct Node。
 */
typedef struct Node {
    int data;          // 保存当前节点的数据，是一个整数
    struct Node *next; // 保存下一个节点的地址
                       // 如果没有下一个节点，就保存 NULL
} Node;

/*
 * 第二部分：全局统计变量
 *
 * 全局变量在函数外面定义，因此本文件中的函数都可以访问它们。
 * 它们在程序启动时会自动初始化为 0。
 *
 * 注意：这里统计的是通过本程序封装的函数进行的创建和释放，
 * 并不是操作系统对所有内存操作的全面监控。
 */
int created_count = 0; // 累计创建了多少个节点
int freed_count = 0;   // 累计释放了多少个节点
int total_checks = 0;  // 累计执行了多少项测试
int failed_checks = 0; // 累计有多少项测试失败

/*
 * 第三部分：创建节点
 *
 * 参数 value：调用者希望新节点保存的整数。
 *
 * 返回值 Node *：
 * 返回新节点的地址，让调用者能够找到这个节点。
 */
Node *create_node(int value) {

    /*
     * malloc(sizeof(Node)) 的意思是：
     * 向系统申请一块足够存放一个 Node 节点的内存。
     *
     * sizeof(Node) 会自动计算 Node 需要多少字节，
     * 不需要我们手动计算结构体大小。
     *
     * malloc() 返回的是这块内存的地址。
     * (Node *) 是类型转换，将返回的地址转换为 Node * 类型。
     */
    Node *new_node = (Node *)malloc(sizeof(Node));

    /*
     * 内存申请可能失败。
     * 失败时 malloc() 返回 NULL。
     *
     * 如果没有检查就使用这个地址，
     * 例如 new_node->data = value，
     * 就可能导致程序崩溃。
     */
    if (new_node == NULL) {
        printf("内存申请失败，程序结束。\n");
        exit(EXIT_FAILURE); // 结束程序，并报告异常退出
    }

    // 将调用者提供的数据写入新节点
    new_node->data = value;

    /*
     * 新节点刚创建时，还没有连接其他节点。
     * 所以先将 next 设置为 NULL。
     *
     * -> 是通过结构体指针访问成员的运算符。
     * new_node->data 等价于 (*new_node).data。
     */
    new_node->next = NULL;

    // 成功创建一个节点，因此创建计数加 1
    created_count++;

    // 返回新节点的地址
    return new_node;
}

/*
 * 第四部分：释放单个节点
 *
 * 参数 node：准备释放的节点地址。
 *
 * free() 用来归还之前通过 malloc() 申请的内存。
 * 释放后，不能再通过原地址读取这个节点的数据。
 */
void destroy_node(Node *node) {

    /*
     * 防御性检查：
     * 如果 node 是 NULL，就不进行释放。
     *
     * 这样可以避免对空地址进行不必要的操作。
     */
    if (node == NULL) {
        return;
    }

    // 释放 node 指向的内存
    free(node);

    // 记录成功执行了一次节点释放
    freed_count++;
}

/*
 * 第五部分：头插法
 *
 * 例如原链表：
 * head -> 20 -> 30 -> NULL
 *
 * 执行 insert_head(head, 10) 后：
 * head -> 10 -> 20 -> 30 -> NULL
 *
 * 为什么要返回 Node *？
 * 因为头插可能改变链表的第一个节点，
 * 调用者必须接收新的头指针。
 */
Node *insert_head(Node *head, int value) {

    // 创建一个保存 value 的新节点
    Node *new_node = create_node(value);

    /*
     * 先让新节点指向原来的第一个节点。
     *
     * 假设原来的 head 指向节点 20，
     * 执行后 new_node->next 就指向节点 20。
     */
    new_node->next = head;

    /*
     * 返回新节点的地址。
     * 调用者应写：
     * head = insert_head(head, 10);
     *
     * 这样 head 才会更新为新的第一个节点。
     */
    return new_node;
}

/*
 * 第六部分：尾插法
 *
 * 尾插就是将新节点添加到链表的最后面。
 *
 * 例如：
 * 20 -> 30 -> NULL
 *
 * 尾插 40 后：
 * 20 -> 30 -> 40 -> NULL
 */
Node *insert_tail(Node *head, int value) {

    // 先创建新节点。创建函数已经将 new_node->next 设置为 NULL。
    Node *new_node = create_node(value);

    /*
     * 如果 head == NULL，说明原链表为空。
     *
     * 空链表没有第一个节点，
     * 因此新节点自己就是第一个节点。
     */
    if (head == NULL) {
        return new_node;
    }

    // 从链表第一个节点开始寻找最后一个节点
    Node *current = head;

    /*
     * current->next != NULL 表示当前节点后面还有节点。
     *
     * 每次执行 current = current->next，
     * 就向后移动一个节点。
     *
     * 当 current->next == NULL 时，
     * current 就是原链表最后一个节点。
     */
    while (current->next != NULL) {
        current = current->next;
    }

    // 将原来的最后一个节点连接到新节点
    current->next = new_node;

    /*
     * 尾插通常不会改变第一个节点，
     * 所以返回原来的 head。
     */
    return head;
}

/*
 * 第七部分：按值删除第一个匹配的节点
 *
 * 参数说明：
 * head：链表的头指针。
 * target：要删除的数据。
 * is_deleted：输出参数，用来告诉调用者删除是否成功。
 *
 * is_deleted 是 int *，表示它是一个整数变量的地址。
 * 通过 *is_deleted = 1，可以修改调用者的变量。
 *
 * 返回值：
 * 返回删除操作完成后的链表头指针。
 */
Node *delete_by_value(Node *head, int target, int *is_deleted) {

    /*
     * 每次删除之前，先默认删除失败。
     * 只有真正找到目标节点并释放它时，才设置为 1。
     */
    *is_deleted = 0;

    // 情况一：链表为空，没有任何节点可以删除
    if (head == NULL) {
        return NULL;
    }

    /*
     * 情况二：要删除的就是第一个节点。
     *
     * 原链表：
     * head -> 10 -> 20 -> NULL
     *
     * 删除 10 后：
     * head -> 20 -> NULL
     *
     * 这里必须更新 head，
     * 否则 head 仍然会指向已释放的节点。
     */
    if (head->data == target) {

        // 暂时保存原来的第一个节点地址
        Node *to_delete = head;

        // 将头指针移动到第二个节点
        head = head->next;

        /*
         * 释放原来的第一个节点。
         * 此时 head 已经指向后续节点，
         * 所以不会丢失后续链表。
         */
        destroy_node(to_delete);

        // 告诉调用者删除成功
        *is_deleted = 1;

        // 返回更新后的头指针
        return head;
    }

    /*
     * 情况三：目标节点不在第一个位置。
     *
     * prev：当前节点的前一个节点。
     * current：当前正在检查的节点。
     *
     * 例如：
     * prev -> current -> 下一个节点
     *
     * 如果 current 是要删除的节点，
     * 就让 prev 直接连接 current 后面的节点。
     */
    Node *prev = head;
    Node *current = head->next;

    // 从第二个节点开始，依次检查每个节点
    while (current != NULL) {

        // 如果找到了第一个数据等于 target 的节点
        if (current->data == target) {

            /*
             * 跳过 current。
             *
             * 原来：
             * prev -> current -> next_node
             *
             * 修改后：
             * prev ------------> next_node
             */
            prev->next = current->next;

            // 释放被删除节点占用的内存
            destroy_node(current);

            // 标记删除成功
            *is_deleted = 1;

            /*
             * 只删除第一个匹配的节点。
             * 删除后立即返回，不再继续查找。
             */
            return head;
        }

        /*
         * 没找到目标值时，两个指针都向后移动。
         *
         * prev 追上当前节点。
         * current 再移动到下一个节点。
         */
        prev = current;
        current = current->next;
    }

    /*
     * 如果循环结束仍然没有找到 target，
     * 链表不会发生变化，删除标志仍然为 0。
     */
    return head;
}

/*
 * 第八部分：释放整条链表
 *
 * 逐个释放链表中的节点。
 *
 * 注意：
 * 每次释放当前节点之前，必须先保存下一个节点的地址。
 * 否则释放当前节点后，就不能再安全地读取它的 next 成员。
 */
Node *free_list(Node *head) {

    // 只要 head 不是 NULL，就说明还有节点需要释放
    while (head != NULL) {

        // 先记住下一个节点的地址
        Node *next_node = head->next;

        // 释放当前节点
        destroy_node(head);

        // 继续处理刚才保存的下一个节点
        head = next_node;
    }

    /*
     * 所有节点都已经释放。
     * 返回 NULL，方便调用者写：
     * head = free_list(head);
     */
    return NULL;
}

/*
 * 第九部分：打印链表
 *
 * 例如：
 * head -> 10 -> 20 -> 30 -> NULL
 *
 * 这里用 current 从头到尾遍历链表。
 * 打印当前节点后，必须将 current 移到下一个节点。
 */
void print_list(Node *head) {

    Node *current = head;

    printf("head");

    while (current != NULL) {

        // 打印当前节点的数据
        printf(" -> %d", current->data);

        /*
         * 关键语句：
         * 移动到下一个节点。
         *
         * 如果忘记这一句，
         * current 永远指向同一个节点，
         * while 循环就会一直执行，形成死循环。
         */
        current = current->next;
    }

    // NULL 表示已经到达链表末尾
    printf(" -> NULL\n");
}

/*
 * 第十部分：统计链表节点数量
 *
 * 空链表有 0 个节点。
 * 只有一个节点的链表有 1 个节点。
 *
 * 遍历时，每访问一个节点，就将 count 加 1。
 */
int count_nodes(Node *head) {

    int count = 0;
    Node *current = head;

    while (current != NULL) {

        // 当前确实存在一个节点，因此数量加 1
        count++;

        /*
         * 不能写 current = current->data。
         *
         * data 是整数，例如 20；
         * next 才是下一个节点的地址。
         */
        current = current->next;
    }

    return count;
}

/*
 * 第十一部分：检查内存计数是否平衡
 *
 * 理论上：
 * 已创建但尚未释放的节点数
 * = created_count - freed_count
 *
 * 如果链表是本程序唯一管理的节点集合，
 * 并且没有丢失节点，那么这个数应等于链表节点数。
 *
 * 注意：这是一种程序内部的计数检查，
 * 不是完整的内存泄漏检测工具。
 */
int memory_ok(Node *head) { return created_count - freed_count == count_nodes(head); }

/*
 * 第十二部分：检查两条数据序列是否相同
 *
 * expected 是数组，保存期望的数据。
 * expected_count 是数组中需要比较的元素数量。
 *
 * 例如：
 * expected = {20, 30, 50}
 *
 * 如果链表也是 20 -> 30 -> 50 -> NULL，
 * 那么比较结果为 1（相同）。
 */
int list_equals(Node *head, const int expected[], int expected_count) {

    Node *current = head;

    // 依次比较链表节点和数组中的对应元素
    for (int i = 0; i < expected_count; i++) {

        /*
         * 如果链表提前结束，
         * 或当前节点数据与期望值不同，
         * 就说明两者不相同。
         */
        if (current == NULL || current->data != expected[i]) {
            return 0;
        }

        // 移动到链表的下一个节点
        current = current->next;
    }

    /*
     * 数组比较完后，链表也必须恰好结束。
     *
     * 如果 current 仍然不是 NULL，
     * 说明链表还有多余节点。
     */
    return current == NULL;
}

/*
 * 第十三部分：通用测试函数
 *
 * condition 是需要检查的条件：
 * 1 表示条件成立。
 * 0 表示条件不成立。
 *
 * description 是本项测试的文字说明。
 */
void check(int condition, const char *description) {

    // 每执行一次检查，测试总数就增加 1
    total_checks++;

    if (condition) {

        // 条件成立，打印通过信息
        printf("[通过] %s\n", description);

    } else {

        // 条件不成立，打印失败信息
        printf("[失败] %s\n", description);

        // 失败次数加 1
        failed_checks++;
    }
}

/*
 * 第十四部分：输出内存统计报告
 */
void report_memory(void) {

    printf("[内存报告] 创建 %d 个节点，释放 %d 个节点，"
           "尚未释放 %d 个节点\n",
           created_count, freed_count, created_count - freed_count);
}

/*
 * 第十五部分：主函数
 *
 * C 程序通常从 main() 函数开始运行。
 * 下面依次测试：
 * 1. 空链表删除。
 * 2. 尾插节点。
 * 3. 删除头节点。
 * 4. 删除尾节点。
 * 5. 删除不存在的值。
 * 6. 删除唯一节点。
 * 7. 重复值只删除第一个。
 * 8. 释放所有剩余节点。
 */
int main(void) {

    // 一开始没有节点，所以头指针为 NULL
    Node *head = NULL;

    // 保存最近一次删除操作是否成功
    int is_deleted = 0;

    // 记录某次操作之前的释放次数，便于比较
    int freed_before = 0;

    printf("===== 开始测试 =====\n");

    /*
     * 测试 1：空链表删除 10。
     *
     * 空链表中没有任何节点，
     * 因此删除应该失败，并且不能释放任何节点。
     */
    printf("\n[测试 1] 空链表删除 10\n");

    freed_before = freed_count;

    head = delete_by_value(head, 10, &is_deleted);

    print_list(head);

    check(is_deleted == 0, "空链表：没有删除任何节点");
    check(head == NULL, "空链表：头指针仍为 NULL");
    check(freed_count == freed_before, "空链表：没有释放节点");
    check(memory_ok(head), "空链表：内存计数平衡");

    /*
     * 准备测试数据：
     * 插入 20、30、40、50。
     *
     * 预期链表：
     * head -> 20 -> 30 -> 40 -> 50 -> NULL
     */
    printf("\n[准备] 尾插 20、30、40、50\n");

    head = insert_tail(head, 20);
    head = insert_tail(head, 30);
    head = insert_tail(head, 40);
    head = insert_tail(head, 50);

    print_list(head);

    int list_a[] = {20, 30, 40, 50};

    check(list_equals(head, list_a, 4), "尾插后链表应为 20 -> 30 -> 40 -> 50");

    check(memory_ok(head), "尾插后：内存计数平衡");

    /*
     * 测试 2：删除头节点 20。
     *
     * 原链表：
     * 20 -> 30 -> 40 -> 50
     *
     * 预期结果：
     * 30 -> 40 -> 50
     */
    printf("\n[测试 2] 删除头节点 20\n");

    freed_before = freed_count;

    head = delete_by_value(head, 20, &is_deleted);

    print_list(head);

    int list_b[] = {30, 40, 50};

    check(is_deleted == 1, "删除头节点 20 应成功");

    check(list_equals(head, list_b, 3), "删除后链表应为 30 -> 40 -> 50");

    check(freed_count == freed_before + 1, "删除头节点：恰好释放 1 个节点");

    check(memory_ok(head), "删除头节点后：内存计数平衡");

    /*
     * 测试 3：删除尾节点 50。
     *
     * 原链表：
     * 30 -> 40 -> 50
     *
     * 预期结果：
     * 30 -> 40 -> NULL
     */
    printf("\n[测试 3] 删除尾节点 50\n");

    freed_before = freed_count;

    head = delete_by_value(head, 50, &is_deleted);

    print_list(head);

    int list_c[] = {30, 40};

    check(is_deleted == 1, "删除尾节点 50 应成功");

    check(list_equals(head, list_c, 2), "删除后链表应为 30 -> 40");

    check(freed_count == freed_before + 1, "删除尾节点：恰好释放 1 个节点");

    check(memory_ok(head), "删除尾节点后：内存计数平衡");

    /*
     * 测试 4：删除不存在的值 99。
     *
     * 预期：
     * 删除标志为 0；
     * 链表内容不变；
     * 释放次数不变。
     */
    printf("\n[测试 4] 删除不存在的值 99\n");

    freed_before = freed_count;

    head = delete_by_value(head, 99, &is_deleted);

    print_list(head);

    check(is_deleted == 0, "找不到 99：删除应失败");

    check(list_equals(head, list_c, 2), "找不到 99：链表保持 30 -> 40 不变");

    check(freed_count == freed_before, "找不到 99：释放次数不变");

    check(memory_ok(head), "删除不存在的值后：内存计数平衡");

    /*
     * 测试 5：删除唯一节点。
     *
     * 先删除 30，再删除剩下的 40。
     * 删除 40 后，链表变成空链表。
     */
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

    /*
     * 测试 6：重复值。
     *
     * 创建：
     * 5 -> 7 -> 5 -> NULL
     *
     * 删除值为 5 的第一个节点后：
     * 7 -> 5 -> NULL
     *
     * 这里要验证的是：只删除第一个匹配节点。
     */
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

    /*
     * 收尾：释放链表中的所有剩余节点。
     *
     * free_list() 返回 NULL，
     * 因此必须将返回值重新赋给 head。
     */
    printf("\n[收尾] 释放所有剩余节点\n");

    head = free_list(head);

    print_list(head);

    check(head == NULL, "释放整条链表后，head 为 NULL");

    check(created_count == freed_count, "创建和释放次数相等，没有遗留未释放节点");

    report_memory();

    printf("\n测试合计：%d 项，失败：%d 项\n", total_checks, failed_checks);

    /*
     * 按照常见约定：
     * 返回 0 表示程序正常完成；
     * 返回非 0 表示测试发现失败。
     */
    if (failed_checks > 0) {
        return 1;
    }

    printf("\n===== 所有测试通过，测试结束 =====\n");

    return 0;
}
