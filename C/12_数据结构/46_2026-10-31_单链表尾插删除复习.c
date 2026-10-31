#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node *next;
} Node;

Node *creat_node(int value) {
    Node *node = (Node *)malloc(sizeof(Node));
    if (node == NULL) {
        printf("内存分配失败！！\n");
        return NULL;
    }
    node->data = value;
    node->next = NULL;
    return node;
}

Node *append_node(Node *head, int value) {
    Node *new_node = creat_node(value);
    if (new_node == NULL) {
        return head;
    }
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

void print_list(const Node *head) {
    const Node *cur = head;
    while (cur != NULL) {
        printf("%d -> ", cur->data);
        cur = cur->next;
    }
    printf("NULL\n");
}

Node *delete_value(Node *head, int value) {
    if (head == NULL) {
        printf("链表为空，无法删除%d\n", value);
        return NULL;
    }
    if (head->data == value) {
        Node *old_head = head;
        head = head->next;
        free(old_head);
        return head;
    }
    Node *prev = head;
    while (prev->next != NULL && prev->next->data != value) {
        prev = prev->next;
    }
    if (prev->next == NULL) {
        printf("没找到 %d\n", value);
        return head;
    }
    Node *target = prev->next;
    prev->next = target->next;
    free(target);
    return head;
}

void free_list(Node *head) {
    while (head != NULL) {
        Node *next = head->next;
        free(head);
        head = next;
    }
}

int main(void) {
    Node *head = NULL;
    printf("空链表：");
    print_list(head);
    head = append_node(head, 10);
    head = append_node(head, 20);
    head = append_node(head, 30);
    printf("追加 10 20 30：");
    print_list(head);
    head = delete_value(head, 20);
    printf("删除 20 节点后：");
    print_list(head);
    head = delete_value(head, 30);
    printf("删除 30 节点后：");
    print_list(head);
    head = delete_value(head, 99);
    printf("删除 99 节点后：");
    print_list(head);
    head = delete_value(head, 10);
    printf("删除 10 节点后：");
    print_list(head);
    head = append_node(head, 1);
    head = append_node(head, 2);
    printf("再次追加：");
    print_list(head);
    free_list(head);
    head = NULL;
    return 0;
}
/*// ====================================================================
// 文件名：linked_list_fixed.c
// 作用：用 C 语言实现"单链表"，包含 5 个功能：
//       1. creat_node  ：造一个新节点
//       2. append_node ：把新节点接到链表的最后面
//       3. print_list  ：把链表从头到尾打印出来
//       4. delete_value：删除链表里值等于某个数的节点
//       5. free_list   ：把整条链表占用的内存还给系统
//
// 【本次修复了 3 处，代码里用"【修复】"标出】
//   修复 1：print_list 少了 cur = cur->next; ，导致死循环
//   修复 2：delete_value 没写完，缺少"没找到 / 摘除 / 释放 / 返回"
//   修复 3：main 里删除 99 的提示文字写成了"删除 10"
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o linked_list_fixed linked_list_fixed.c
//   运行：./linked_list_fixed
//   说明：编译 = 把人能看懂的 C 代码翻译成电脑能执行的程序。
//         -Wall -Wextra 的意思是"把所有可疑的地方都警告我"，
//         养成习惯：编译时出现警告，一定要弄明白原因，不要忽略。
// ====================================================================


// --------------------------------------------------------------------
// 第一部分：引入"工具箱"
// --------------------------------------------------------------------

// #include 的意思是"把另一个文件的内容借过来用"。
// <stdio.h> 是"标准输入输出"工具箱（standard input output）。
// 我们用的 printf（把文字显示在屏幕上）就放在这个工具箱里。
// 不写这一行，编译器就不认识 printf。
#include <stdio.h>

// <stdlib.h> 是"标准工具库"（standard library）。
// 我们用的 malloc（向系统申请内存）和 free（把内存还给系统）都在里面。
#include <stdlib.h>


// --------------------------------------------------------------------
// 第二部分：定义"节点"长什么样
// --------------------------------------------------------------------
// 先理解什么是"节点"：
//   链表就像一串"接龙"，每一个人叫做一个节点。
//   每个节点手里有两样东西：
//     (1) 自己的数据（比如数字 10）
//     (2) 一张写着"下一个人在哪"的纸条（下一个节点的地址）
//   最后一个人没有"下一个人"，他的纸条上写"空"，这个"空"在 C 语言里叫 NULL。
//
// struct（结构体）：让我们把几样东西打包成一个"自定义类型"，
// 就像设计一张表格，表格里有几栏，每个节点就是按这张表格填出来的一张卡片。

// typedef 的意思是"给类型起一个短别名"。
// 下面整段的写法是：先定义 struct Node，最后在 } 后面写 Node，
// 表示"以后可以直接用 Node 代替 struct Node"，少写几个字。
typedef struct Node {
    // data 是"数据栏"，int 表示这里存一个整数（比如 10、20、30）。
    int data;

    // next 是"下一个节点在哪"的纸条。
    // 类型 struct Node * 读作"指向 struct Node 的指针"。
    //   指针 = 专门用来保存"地址"的变量。
    //   地址 = 数据在电脑内存里的"门牌号"。
    //   星号 * 就表示"这是一个指针"。
    // 为什么这里必须写 struct Node 而不能写 Node？
    //   因为别名 Node 要等到整句 typedef 结束后才生效，
    //   在结构体内部，名字还只能叫 struct Node。
    // 为什么 next 是指针，而不是直接放一个 Node？
    //   如果节点里再装一个完整的节点，它里面又要装节点，一直套下去，
    //   大小就无穷大了，所以只存"地址"（门牌号），不存整个节点。
    struct Node *next;
} Node;   // 这个 Node 就是别名，末尾的分号 ; 不能漏


// --------------------------------------------------------------------
// 第三部分：功能 1 —— 创建一个新节点
// --------------------------------------------------------------------
// 函数 = 一段可以反复使用的"小程序"，有名字、有输入、有输出。
// 函数头 Node *creat_node(int value) 的读法：
//   Node *     ：这个函数执行完会"交还一个 Node 的地址"（返回值的类型）
//   creat_node ：函数的名字
//   int value  ：调用时要传入一个整数，在函数里叫 value（参数）
// 作用：造一个新节点，把 value 放进去，把节点的地址交给调用者。
Node *creat_node(int value) {

    // malloc 的意思是"向系统申请一块内存"（memory allocate）。
    // sizeof(Node) 表示"一个 Node 需要多少字节"，系统就给我们这么大一块。
    // 这块内存位于"堆"里，除非我们自己用 free 归还，否则一直存在。
    //   对比：如果直接写 Node node; 这种普通变量，函数一结束就被系统收回，
    //         节点就没了，链表就断了。所以链表的节点必须用 malloc 来造。
    // malloc 返回的是"一块内存的地址"，类型是 void *（不知道里面放什么）。
    // 前面的 (Node *) 叫"类型转换"，意思是"我告诉你，这块内存我当 Node 用"。
    // 左边的 Node *node 是新建一个指针变量 node，用来保存这块内存的地址。
    Node *node = (Node *)malloc(sizeof(Node));

    // 内存是有限的，系统可能申请失败（比如内存用光了）。
    // 失败时 malloc 会返回 NULL（表示"空，没有"）。
    // == 是"判断是否相等"。注意：一个等号 = 是"赋值"，两个 == 才是"比较"。
    if (node == NULL) {
        // printf 把双引号里的文字显示到屏幕上。
        // \n 表示"换行"，相当于按一下回车。
        printf("内存分配失败！！\n");
        // 返回 NULL，告诉调用者"这次没造成功"。
        // return 的意思：结束这个函数，并把后面的值交给调用者。
        return NULL;
    }

    // -> 读作"箭头"，意思是"通过指针找到它指向的节点，再访问里面的某一栏"。
    // node->data = value; 的意思是：
    //   顺着 node 这个地址找到新节点，把它的"数据栏"填成 value。
    // 这里的 = 是赋值：把右边的值放进左边。
    node->data = value;

    // 新节点此刻还没接到任何节点后面，所以"下一个"先写成 NULL。
    // 这一步非常重要：如果忘了设置，next 里是一个随机的垃圾地址，
    // 以后顺着它走就会崩溃。
    node->next = NULL;

    // 把新节点的地址交给调用者。
    return node;
}


// --------------------------------------------------------------------
// 第四部分：功能 2 —— 在链表末尾追加一个节点
// --------------------------------------------------------------------
// 参数 Node *head ：链表的"头指针"，保存着第一个节点的地址。
//                   链表是空的时候，head 就是 NULL。
// 参数 int value  ：要追加的数据。
// 返回值 Node *   ：追加完成后的"头指针"。
// 为什么要返回 head？
//   C 语言传参数时只传"复印件"。函数里改 head，外面的 head 不会变。
//   所以空链表追加第一个节点时，函数必须把新的头"返回"，
//   外面要用 head = append_node(head, 10); 接住它。
Node *append_node(Node *head, int value) {

    // 先调用上面的 creat_node 造一个新节点，把它的地址存进 new_node。
    Node *new_node = creat_node(value);

    // 如果造节点失败（得到 NULL），就什么也不做，原样返回 head。
    if (new_node == NULL) {
        return head;
    }

    // 如果 head 是 NULL，说明链表是空的，根本没有"最后一个节点"可找。
    // 这时新节点自己就是第一个节点，直接把它当作新的头返回。
    // 注意：这一步同时也避免了空指针错误。
    // 因为如果 head 是 NULL 还往下走，后面的 cur->next 会崩溃。
    if (head == NULL) {
        return new_node;
    }

    // 走到这里说明链表至少有一个节点。
    // cur 是"游标"（cursor），像一根手指，指着当前看到哪个节点。
    // 一开始让手指指向第一个节点，也就是 head 保存的地址。
    // 注意：这里只是复制地址给 cur，head 本身没有动，所以不会丢掉链表的头。
    Node *cur = head;

    // 循环（while）的意思是"条件成立就一直重复"。
    // cur->next != NULL 读作"cur 所指节点的 next 不是 NULL"，
    // 也就是"cur 后面还有节点吗？"
    //   还有（不是 NULL）→ 条件成立 → 进入循环，继续往后走。
    //   没有了（是 NULL）→ 条件不成立 → 退出循环。
    // != 的意思是"不等于"。
    // 为什么写 cur->next 而不是 cur？
    //   我们要停在"最后一个节点"上，而不是走到 NULL 上。
    //   如果写成 cur != NULL，手指会走到最后一个节点的后面（NULL），
    //   下面的 cur->next = new_node 就会对 NULL 操作，程序崩溃。
    while (cur->next != NULL) {
        // 手指往后移动一个节点：
        // cur->next 是"下一个节点的地址"，把它赋给 cur，手指就移过去了。
        cur = cur->next;
    }
    // 循环结束时：cur->next 是 NULL，也就是 cur 正好指着最后一个节点。

    // 把最后一个节点的 next 改成新节点的地址，新节点就接到链尾了。
    // 新节点的 next 本来就是 NULL（creat_node 里设置的），
    // 所以它自然成为新的链尾。
    cur->next = new_node;

    // 头没有变化，原样返回。
    return head;
}


// --------------------------------------------------------------------
// 第五部分：功能 3 —— 打印整条链表
// --------------------------------------------------------------------
// void 的意思是"这个函数不返回任何值"，它只负责做事（打印）。
// const 的意思是"承诺只读不改"：加上它，函数里一旦想修改节点内容，
// 编译器就会报错提醒，可以防止误操作。
void print_list(const Node *head) {

    // 游标 cur 从第一个节点出发。
    // 这里也要写 const，因为它指向的是"不允许修改"的节点。
    const Node *cur = head;

    // 这里要走遍每一个节点，所以条件是 cur != NULL（当前这个节点存在吗）。
    // 对比上面的 append_node：那里要停在最后一个节点，所以判断 cur->next。
    // 记忆方法：
    //   想"走完所有节点"→ 判断 cur
    //   想"停在最后一个节点"→ 判断 cur->next
    while (cur != NULL) {
        // %d 是占位符，意思是"这里放一个整数"，后面的 cur->data 就是那个整数。
        // 例如 cur->data 是 10，屏幕上显示 "10 -> "。
        printf("%d -> ", cur->data);

        // 【修复 1】原来的代码缺少这一行。
        // 作用：让游标移到下一个节点。
        // 原来没有这一行，cur 永远指着第一个节点，
        // cur != NULL 永远成立，循环永远停不下来，屏幕上不停地刷 "10 -> 10 -> ..."。
        // 检查循环的小技巧：问自己"每一轮，有什么东西在变？"
        // 如果什么都没变，就是死循环。
        cur = cur->next;
    }

    // 循环结束说明走到了链尾之后，打印 NULL 表示"到头了"。
    // 【修复 1 附带】加上 \n 换行，不然下一次输出会接在同一行后面。
    printf("NULL\n");
}


// --------------------------------------------------------------------
// 第六部分：功能 4 —— 删除第一个值等于 value 的节点
// --------------------------------------------------------------------
// 返回值是删除后的"头指针"，原因和 append_node 一样：
// 如果删掉的是头节点，头就变了，必须把新头返回给外面。
//
// 删除要分四种情况：
//   情况 1：链表是空的
//   情况 2：要删的是第一个节点（头节点）
//   情况 3：要删的节点在中间或末尾
//   情况 4：整条链表里都没有这个值
// 删除的核心思想：让"前一个节点"的 next 绕过要删的节点，
// 再把这个节点的内存释放掉。就像接龙时一个人离开，
// 他前面的人直接拉住他后面的人。
Node *delete_value(Node *head, int value) {

    // 情况 1：空链表，没有东西可删。
    if (head == NULL) {
        printf("链表为空，无法删除%d\n", value);
        // 返回 NULL，因为链表本来就是空的。
        return NULL;
    }

    // 情况 2：头节点就是要删的。
    // head->data 是第一个节点里的数据。
    if (head->data == value) {
        // 先用 old_head 记住旧的头节点地址。
        // 如果不记住，下一行 head 一改，旧头的地址就丢了，
        // 就再也没法 free 它，内存就泄漏了（一直占着不还）。
        Node *old_head = head;

        // 头指针后移一个，让第二个节点成为新的头。
        // 如果只有一个节点，head->next 是 NULL，删完后链表就变成空链表。
        head = head->next;

        // free 的意思是"把这块内存还给系统"。
        // 和 malloc 是一对：malloc 借，free 还。
        free(old_head);

        // 返回新的头。
        return head;
    }

    // 走到这里：链表非空，而且头节点不是要删的那个。
    // 接下来用 prev 表示"待删节点的前一个节点"。
    // 为什么要找"前一个"而不是"待删节点本身"？
    // 因为单链表只能往后走，不能往回走。
    // 要绕过待删节点，必须让它的前一个节点修改自己的 next。
    // 一开始 prev 指向头节点，（头节点我们已经检查过，它不是要删的）。
    Node *prev = head;

    // 循环：不断往后走，直到"prev 的下一个节点是要删的值"，或"走到链尾"。
    // 条件有两个，用 && 连接，&& 读作"并且"，两边都成立整个条件才成立。
    //   左边 prev->next != NULL ：prev 后面还有节点（没走到链尾）
    //   右边 prev->next->data != value ：prev 后面那个节点的数据不是要删的值
    //     prev->next->data 读作"prev 的下一个节点里的数据"。
    // 两个条件的顺序很重要！
    // && 有"短路"的规则：左边是假（不成立），右边就不再计算了。
    // 所以左边负责"保护"右边：
    //   当 prev->next 是 NULL 时，左边为假，右边不会执行，
    //   就避免了对 NULL 使用 ->（那样会崩溃）。
    // 如果把两个条件对调，走到链尾时就会先计算 prev->next->data，程序崩溃。
    while (prev->next != NULL && prev->next->data != value) {
        // 两个条件都成立，说明下一个节点不是要删的，也还没走到链尾。
        // 手指往后移一格，继续检查。
        prev = prev->next;
    }
    // 循环结束有两种可能：
    //   A. prev->next 是 NULL：走到链尾都没找到。
    //   B. prev->next->data 等于 value：找到了，prev 的下一个节点就是要删的。

    // 【修复 2】下面是原来缺少的后半部分。
    // 原来循环结束后函数就直接结束了，没有 return，
    // 编译器警告 control reaches end of non-void function（函数结束时没返回值），
    // 返回值是不确定的，head 可能被赋成无效的值，之后的操作就不可靠了。

    // 情况 4：对应上面的可能 A，没找到。
    if (prev->next == NULL) {
        printf("没找到 %d\n", value);
        // 链表没有变化，原样返回 head。
        return head;
    }

    // 情况 3：对应上面的可能 B，找到了。
    // 先用 target 记住"要删的节点"的地址。
    Node *target = prev->next;

    // 让前一个节点绕过 target，直接连到 target 后面的节点。
    // 如果 target 是最后一个节点，target->next 是 NULL，
    // 那 prev->next 也变成 NULL，prev 就成了新的链尾，同样正确。
    prev->next = target->next;

    // 顺序很重要：必须先把前后接好（上一行要读 target->next），再释放。
    // free 之后这块内存已经"还给系统"了，
    // 再去读 target->next 就是使用已释放的内存，结果不可靠，可能崩溃。
    free(target);

    // 头没有变化，返回 head。
    return head;
}


// --------------------------------------------------------------------
// 第七部分：功能 5 —— 释放整条链表
// --------------------------------------------------------------------
// 每个节点都是用 malloc 借来的，程序结束前应该全部 free 归还，
// 否则就是"内存泄漏"。
// 这个函数从头到尾，逐个节点释放。
void free_list(Node *head) {

    // 只要还有节点（head 不是 NULL）就继续。
    // 这里直接拿参数 head 当游标用。head 是外面那个头指针的"复印件"，
    // 在函数里改它，不会影响外面。
    while (head != NULL) {

        // 先把"下一个节点的地址"存进 next。
        // 为什么要先存？因为下一行 free(head) 之后，
        // 这个节点的内存就不能再读了，如果不提前存好，
        // 下一个节点的地址就永远丢了。
        Node *next = head->next;

        // 释放当前节点。
        free(head);

        // 移到下一个节点，继续循环。
        head = next;
    }
}


// --------------------------------------------------------------------
// 第八部分：main 函数 —— 程序从这里开始执行
// --------------------------------------------------------------------
// 每个 C 程序都从 main 函数开始。
// int 表示 main 返回一个整数给系统（0 表示"一切正常"）。
// (void) 表示 main 不需要接收任何参数。
int main(void) {

    // 创建头指针，初始为 NULL，表示"现在是一条空链表，还没有任何节点"。
    Node *head = NULL;

    // 先打印一条提示，再打印空链表。
    printf("空链表：");
    // 空链表打印出来只有 NULL。
    // 预期输出：NULL
    print_list(head);

    // 依次追加三个节点。
    // 为什么每次都要写 head = ？因为函数返回的是（可能变化的）头指针，
    // 第一次追加时，head 从 NULL 变成了第一个节点的地址，
    // 不接住返回值，节点就丢了。
    head = append_node(head, 10);   // 链表变成：10
    head = append_node(head, 20);   // 链表变成：10 -> 20
    head = append_node(head, 30);   // 链表变成：10 -> 20 -> 30

    printf("追加 10 20 30：");
    // 预期输出：10 -> 20 -> 30 -> NULL
    print_list(head);

    // 测试：删除中间的节点。
    head = delete_value(head, 20);
    printf("删除 20 节点后：");
    // 预期输出：10 -> 30 -> NULL
    print_list(head);

    // 测试：删除最后一个节点。
    head = delete_value(head, 30);
    printf("删除 30 节点后：");
    // 预期输出：10 -> NULL
    print_list(head);

    // 测试：删除一个不存在的值，链表应该保持不变，并提示"没找到 99"。
    head = delete_value(head, 99);
    // 【修复 3】原来这里写成了"删除 10 节点后"，与上一行的 99 对不上，现在改正。
    printf("删除 99 节点后：");
    // 预期输出：10 -> NULL
    print_list(head);

    // 测试：删除唯一的节点（它同时是头），删完应该变成空链表。
    head = delete_value(head, 10);
    printf("删除 10 节点后：");
    // 预期输出：NULL
    print_list(head);

    // 空链表上继续追加，检查"从空开始"这种情况是否正常。
    head = append_node(head, 1);
    head = append_node(head, 2);
    printf("再次追加：");
    // 预期输出：1 -> 2 -> NULL
    print_list(head);

    // 程序结束前，把所有节点还给系统。
    free_list(head);

    // free_list 里改的是 head 的复印件，main 里的 head 还保存着旧地址，
    // 但那块内存已经归还了，这种指针叫"悬空指针"，不能再用。
    // 把它设成 NULL，是个好习惯，以后别人看到 NULL 就知道"这里没有链表"。
    head = NULL;

    // 告诉系统：程序正常结束。
    return 0;
}

// ====================================================================
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   空链表：NULL
//   追加 10 20 30：10 -> 20 -> 30 -> NULL
//   删除 20 节点后：10 -> 30 -> NULL
//   删除 30 节点后：10 -> NULL
//   没找到 99
//   删除 99 节点后：10 -> NULL
//   删除 10 节点后：NULL
//   再次追加：1 -> 2 -> NULL
// ====================================================================
*/
