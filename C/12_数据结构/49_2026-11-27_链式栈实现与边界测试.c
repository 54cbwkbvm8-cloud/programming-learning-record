#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
typedef struct Node {
    int data;
    struct Node *next;
} Node;
typedef struct {
    Node *top;
    size_t size;
} Stack;
static void stack_init(Stack *s) {
    s->top = NULL;
    s->size = 0;
}
static bool stack_is_empty(const Stack *s) { return s->size == 0; }
static void stack_push(Stack *s, int value) {
    Node *node = (Node *)malloc(sizeof(Node));
    if (node == NULL) {
        fprintf(stderr, "内存分配失败\n");
        exit(EXIT_FAILURE);
    }
    node->data = value;
    node->next = s->top;
    s->top = node;
    s->size++;
}
static bool stack_pop(Stack *s, int *value) {
    if (s->top == NULL) {
        return false;
    }
    Node *node = s->top;
    *value = node->data;
    s->top = node->next;
    free(node);
    s->size--;
    return true;
}
static bool stack_peek(const Stack *s, int *value) {
    if (s->top == NULL) {
        return false;
    }
    *value = s->top->data;
    return true;
}
static void stack_print(const Stack *s) {
    printf("栈内容（栈顶 -> 栈底）: ");
    for (Node *cur = s->top; cur != NULL; cur = cur->next) {
        printf("%d ", cur->data);
    }
    printf("(size = %zu)\n", s->size);
}
static void stack_free(Stack *s) {
    while (s->top != NULL) {
        Node *node = s->top;
        s->top = node->next;
        free(node);
    }
    s->size = 0;
}
static int check(const char *name, bool ok) {
    printf("[%s] %s\n", ok ? "PASS" : "FAIL", name);
    return ok ? 1 : 0;
}
int main(void) {
    int passed = 0;
    int total = 0;
    int value = 0;
    Stack s;
    stack_init(&s);
    printf("=== 测试 1：空栈 pop / peek（边界）===\n");
    total++;
    passed += check("新建的栈为空", stack_is_empty(&s));
    total++;
    passed += check("空栈 pop 返回 false，不崩溃", !stack_pop(&s, &value));
    total++;
    passed += check("空栈 peek 返回 false，不崩溃", !stack_peek(&s, &value));
    total++;
    passed += check("空栈 pop / peek 后 size 仍为 0", s.size == 0);
    printf("\n=== 测试 2：单元素 push / peek / pop（边界）===\n");
    stack_push(&s, 42);
    stack_print(&s);
    total++;
    passed += check("push 后栈不为空", !stack_is_empty(&s));
    total++;
    passed += check("peek 得到 42 且不删除", stack_peek(&s, &value) && value == 42 && s.size == 1);
    total++;
    passed += check("pop 得到 42", stack_pop(&s, &value) && value == 42);
    total++;
    passed += check("弹出唯一元素后栈为空（top 回到 NULL）", stack_is_empty(&s) && s.size == 0 && s.top == NULL);
    printf("\n=== 测试 3：多元素后进先出（关键逻辑）===\n");
    stack_push(&s, 10);
    stack_push(&s, 20);
    stack_push(&s, 30);
    stack_print(&s);
    total++;
    passed += check("size 为 3", s.size == 3);
    total++;
    passed += check("peek 得到 30", stack_peek(&s, &value) && value == 30);
    total++;
    passed += check("第 1 次 pop 得到 30", stack_pop(&s, &value) && value == 30);
    total++;
    passed += check("第 2 次 pop 得到 20", stack_pop(&s, &value) && value == 20);
    total++;
    passed += check("第 3 次 pop 得到 10", stack_pop(&s, &value) && value == 10);
    total++;
    passed += check("全部弹出后栈为空", stack_is_empty(&s));
    printf("\n=== 测试 4：释放整个栈 ===\n");
    for (int i = 1; i <= 5; i++) {
        stack_push(&s, i);
    }
    stack_print(&s);
    stack_free(&s);
    total++;
    passed += check("free 后栈为空且 size 为 0", stack_is_empty(&s) && s.size == 0);
    stack_push(&s, 99);
    total++;
    passed += check("free 后重新 push 仍可用", stack_peek(&s, &value) && value == 99);
    stack_free(&s);
    printf("\n结果：%d / %d 项检查通过\n", passed, total);
    return passed == total ? 0 : 1;
}

// // #include 是预处理指令；stdbool.h 提供 bool、true、false，使函数能用真假表达操作状态。
// #include <stdbool.h>
// // stdio.h 声明 printf、fprintf 等标准输入输出函数；没有正确声明不能规范调用这些函数。
// #include <stdio.h>
// // stdlib.h 声明 malloc、free、exit，并提供 size_t、EXIT_FAILURE 等类型或宏；用于动态内存管理。
// #include <stdlib.h>
// // typedef struct Node 定义一个名为 Node 的结构体类型；struct 把不同字段组合成一个数据对象。
// // 此处带结构体标签 Node，供下一行自引用。
// typedef struct Node {
// // int data 是结点保存的整数数据；每个结点有自己的 data，不是共享变量。
// int data;
// // struct Node *next 是指向下一个同类型结点的指针，保存地址而非另一个完整结构体。
// // 末尾结点的 next 为 NULL，链表才能结束。
// struct Node *next;
// // } Node; 结束结构体定义并通过 typedef 创建别名 Node，后续可写 Node * 而不必写 struct Node *。
// } Node;
// // typedef struct 开始定义另一个结构体类型，组合栈顶指针与元素数。
// typedef struct {
// // Node *top 指向当前栈顶结点；NULL 代表没有任何结点。
// Node *top;
// // size_t 是适合表示对象数量/大小的无符号整数类型；size 保存当前结点数量。
// // 使用 size_t 避免用 int 计数时较早达到有符号上限。
// size_t size;
// // } Stack; 结束栈结构定义并建立类型别名 Stack；每个 Stack 都有独立的 top 和 size。
// } Stack;
// // static 限制函数仅在当前源文件可见；void 表示不返回数据；Stack *s 是指向调用者栈对象的指针。
// // 通过地址修改原栈，而不是修改结构体副本。
// static void stack_init(Stack *s) {
// // s->top 是 (*s).top 的简写，通过指针访问字段；NULL 表示空指针，初始化后没有结点。
// s->top = NULL;
// // size 初始化为 0，必须与空 top 同步，不能使用未初始化的计数。
// s->size = 0;
// // 右大括号结束「static void stack_init(Stack *s)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // static bool 表示文件内部函数返回真假；const Stack *s 表示通过此指针不能修改栈。
// // s->size == 0 检查元素数量，return 返回比较结果；同一行花括号包住完整函数体。
// static bool stack_is_empty(const Stack *s) { return s->size == 0; }
// // push 接收栈地址 s 及待入栈整数 value；void 表示成功时不返回值，原设计分配失败时终止程序。
// static void stack_push(Stack *s, int value) {
// // malloc(sizeof(Node)) 在堆上申请一个 Node 所需字节数；sizeof 自动计算实际大小。
// // (Node *) 是指针类型转换，在 C 中可省略，但保留原写法；node 保存申请地址，失败时为 NULL。
// Node *node = (Node *)malloc(sizeof(Node));
// // 先检查 malloc 返回值是否为 NULL；不能在分配失败时用 -> 访问无效地址。
// if (node == NULL) {
// // fprintf(stderr,...) 将错误写入标准错误流；\\n 表示换行，与普通 stdout 输出区分。
// fprintf(stderr, "内存分配失败\n");
// // exit(EXIT_FAILURE) 立即结束进程并返回失败状态，避免空指针解引用；本示例保留原分配失败策略。
// exit(EXIT_FAILURE);
// // 右大括号结束「if (node == NULL)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // node->data = value 把待入栈值写入新结点的数据字段。
// node->data = value;
// // 让新结点 next 指向旧栈顶，保留原来整条链；原栈为空时赋值 NULL。
// node->next = s->top;
// // s->top 更新为新结点地址；必须先保存旧 top，否则旧链可能丢失。
// s->top = node;
// // s->size++ 将栈的元素数量加一，保持计数与链表一致。
// s->size++;
// // 右大括号结束「static void stack_push(Stack *s, int value)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // pop 接收栈地址和 int *value 输出参数；bool 表示是否成功，实际出栈值通过指针写给调用者。
// // 调用约定要求两个指针有效且输出参数可写。
// static bool stack_pop(Stack *s, int *value) {
// // 如果 top==NULL，说明没有可取结点，必须先保护空栈。
// if (s->top == NULL) {
// // 返回 false 表示出栈失败，不修改输出变量或栈状态。
// return false;
// // 右大括号结束「if (s->top == NULL)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // node 临时保存原栈顶地址，后面移动 top 后仍可释放它。
// Node *node = s->top;
// // *value 解引用输出指针，把原栈顶的整数写入调用者提供的变量；不要把地址和数据混淆。
// *value = node->data;
// // 将 top 移到原结点的 next，逻辑上把原栈顶从链中移除；最后一个结点出栈时变成 NULL。
// s->top = node->next;
// // free(node) 归还 malloc 申请的内存；之后不能再读取 node->data，否则会使用已释放内存。
// free(node);
// // size-- 使元素数量减少一；之前已确认非空，所以不会出现无符号计数下溢。
// s->size--;
// // 返回 true 表明成功移除栈顶，并已把数值写给调用者。
// return true;
// // 右大括号结束「static bool stack_pop(Stack *s, int *value)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // peek 是只查看、不删除的函数；const 限制通过 s 修改栈，int *value 用来输出读到的值。
// static bool stack_peek(const Stack *s, int *value) {
// // 先判断 top 是否 NULL，防止空指针解引用。
// if (s->top == NULL) {
// // 空栈返回 false，不修改调用者的输出变量。
// return false;
// // 右大括号结束「if (s->top == NULL)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 读取 s->top->data，即栈顶结点数据，并通过 *value 写回；不改变 top、size 或 next。
// *value = s->top->data;
// // 返回 true 说明读取成功；不释放任何结点。
// return true;
// // 右大括号结束「static bool stack_peek(const Stack *s, int *value)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // stack_print 接收只读栈指针；void 表示只产生输出，没有返回值。
// static void stack_print(const Stack *s) {
// // printf 打印遍历方向的标题；此处不换行，后续元素接在同一行。
// printf("栈内容（栈顶 -> 栈底）: ");
// // for 初始化 cur 为 top，条件 cur!=NULL 保证当前结点有效，轮末 cur=cur->next 移到下一结点。
// // cur 是局部遍历指针，不修改真实栈顶；从栈顶到栈底逐结点访问。
// for (Node *cur = s->top; cur != NULL; cur = cur->next) {
// // %d 是 int 的十进制格式说明符，输出 cur->data；末尾空格用于分隔元素。
// printf("%d ", cur->data);
// // 右大括号结束「for (Node *cur = s->top; cur != NULL; cur = cur->next)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // %zu 是 size_t 的正确输出格式；\\n 换行，输出栈当前元素数量。
// printf("(size = %zu)\n", s->size);
// // 右大括号结束「static void stack_print(const Stack *s)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // stack_free 接收可修改栈对象的指针，负责释放全部动态结点；不释放栈对象本身。
// static void stack_free(Stack *s) {
// // while 在 top 不为 NULL 时继续，确保每个剩余结点都会被释放。
// while (s->top != NULL) {
// // 暂存当前栈顶地址，以便移动 top 后释放旧结点。
// Node *node = s->top;
// // 先读取旧结点 next 并更新 top，避免先 free 后访问已释放内存。
// s->top = node->next;
// // free 释放刚摘下的结点；下一轮处理新的 top，不会重复释放同一结点。
// free(node);
// // 右大括号结束「while (s->top != NULL)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 所有结点释放后将 size 设为 0；top 已经为 NULL，可重新使用该栈。
// s->size = 0;
// // 右大括号结束「static void stack_free(Stack *s)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // check 是内部测试辅助函数；const char *name 指向只读测试名称字符串，bool ok 表示是否符合预期；返回 int 便于累计通过数。
// static int check(const char *name, bool ok) {
// // ?: 三元运算符按 ok 选择 PASS 或 FAIL；两个 %s 分别输出状态文字与测试名称。
// printf("[%s] %s\n", ok ? "PASS" : "FAIL", name);
// // ok 为真返回 1，否则返回 0；便于 main 通过 += 累计成功次数。
// return ok ? 1 : 0;
// // 右大括号结束「static int check(const char *name, bool ok)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // int main(void) 是常规 C 程序入口；不接收参数，返回整数退出状态给操作系统。
// int main(void) {
// // passed 从 0 开始，累计已通过的检查数量。
// int passed = 0;
// // total 从 0 开始，记录总共执行了多少项检查。
// int total = 0;
// // value 初始化为 0，用于接收 peek 和 pop 写出的整数。
// int value = 0;
// // Stack s 声明一个栈结构体局部变量；字段此刻尚未初始化，不能立即访问。
// Stack s;
// // &s 取出栈变量地址，传给 stack_init 以初始化原变量。
// stack_init(&s);
// // 输出第一组测试标题，检查空栈边界。
// printf("=== 测试 1：空栈 pop / peek（边界）===\n");
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // &s 传栈的地址，stack_is_empty 返回真假；check 输出状态并返回 0/1，passed += 累加该结果。
// passed += check("新建的栈为空", stack_is_empty(&s));
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // ! 对 pop 返回的真假取反；空栈应返回 false，取反为 true 表示测试通过；&value 提供输出地址。
// passed += check("空栈 pop 返回 false，不崩溃", !stack_pop(&s, &value));
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 对空栈执行 peek，期望 false，! 取反用于 check；名称必须准确标识 peek 而非 pop。
// passed += check("空栈 peek 返回 false，不崩溃", !stack_peek(&s, &value));
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 使用点号访问结构体变量 s 的 size，检查空栈操作没有改变元素计数。
// passed += check("空栈 pop / peek 后 size 仍为 0", s.size == 0);
// // 输出第二组单元素边界测试标题；开头的换行分隔测试组。
// printf("\n=== 测试 2：单元素 push / peek / pop（边界）===\n");
// // stack_push 接收 &s 和 42，将一个结点加入空栈。
// stack_push(&s, 42);
// // 打印当前栈内容和计数，预期只有 42 且 size=1。
// stack_print(&s);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // push 后 is_empty 应为 false，! 取反为真表示入栈后的状态正确。
// passed += check("push 后栈不为空", !stack_is_empty(&s));
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // && 从左到右短路检查 peek 成功、value 等于 42、size 仍为 1；只有三项全真才通过。
// // 证明查看不会删除元素。
// passed += check("peek 得到 42 且不删除", stack_peek(&s, &value) && value == 42 && s.size == 1);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 检查 pop 是否成功且输出值为 42；&& 防止前一条件失败时误读旧 value。
// passed += check("pop 得到 42", stack_pop(&s, &value) && value == 42);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 同时验证空栈状态、size 为 0 和 top==NULL；测试名提到的栈顶指针也必须实际检查。
// passed += check("弹出唯一元素后栈为空（top 回到 NULL）", stack_is_empty(&s) && s.size == 0 && s.top == NULL);
// // 输出第三组多元素测试标题，验证栈的后进先出规则。
// printf("\n=== 测试 3：多元素后进先出（关键逻辑）===\n");
// // 将 10 入栈，成为最早进入的结点。
// stack_push(&s, 10);
// // 将 20 入栈，它的 next 指向保存 10 的旧栈顶。
// stack_push(&s, 20);
// // 将 30 入栈，成为新的栈顶，链的顺序为 30、20、10。
// stack_push(&s, 30);
// // 打印完整链表，验证栈顶到栈底顺序。
// stack_print(&s);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 检查三次入栈后 size 是否为 3。
// passed += check("size 为 3", s.size == 3);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // peek 必须看到最近入栈的 30，且操作不改变栈。
// passed += check("peek 得到 30", stack_peek(&s, &value) && value == 30);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 第一次 pop 必须返回 30，说明最近进入的元素最先出栈。
// passed += check("第 1 次 pop 得到 30", stack_pop(&s, &value) && value == 30);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 第二次 pop 必须返回 20，旧栈顶已释放。
// passed += check("第 2 次 pop 得到 20", stack_pop(&s, &value) && value == 20);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 第三次 pop 必须返回 10，完成三元素逆序取出。
// passed += check("第 3 次 pop 得到 10", stack_pop(&s, &value) && value == 10);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 检查全部出栈后的空栈状态。
// passed += check("全部弹出后栈为空", stack_is_empty(&s));
// // 输出第四组测试标题，验证批量内存释放及重用。
// printf("\n=== 测试 4：释放整个栈 ===\n");
// // for 让 i 从 1 开始，每次加一，只要 i<=5 就继续，共执行五轮。
// for (int i = 1; i <= 5; i++) {
// // 将当前 i 入栈，最终逻辑顺序为 5、4、3、2、1。
// stack_push(&s, i);
// // 右大括号结束「for (int i = 1; i <= 5; i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 输出释放前的栈内容，核对五个结点均已创建。
// stack_print(&s);
// // stack_free 释放五个动态结点，并重置栈状态。
// stack_free(&s);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 检查批量释放后 is_empty 为真且 size 等于 0。
// passed += check("free 后栈为空且 size 为 0", stack_is_empty(&s) && s.size == 0);
// // 在已清空的栈中再次压入 99，验证栈对象仍可使用。
// stack_push(&s, 99);
// // total++ 将测试总数增加 1，等价于 total = total + 1；下一行执行一项检查，保证分母与实际检查数一致。
// total++;
// // 查看 99 并检查返回值，证明批量释放未破坏栈对象。
// passed += check("free 后重新 push 仍可用", stack_peek(&s, &value) && value == 99);
// // 再次释放剩下的结点，避免程序正常退出时遗留动态内存。
// stack_free(&s);
// // printf 使用两个 %d 输出通过数与总数；passed 和 total 都是 int，与格式匹配。
// printf("\n结果：%d / %d 项检查通过\n", passed, total);
// // 三元运算符在 passed==total 时选择 0（成功），否则选择 1（失败）；操作系统可通过退出码判断测试结果。
// return passed == total ? 0 : 1;
// // 右大括号结束「int main(void)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
