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
/*// ====================================================================
// 文件名：43_2026-10-28_单链表节点连接与遍历.c
// 作用：手工创建 3 个节点，用 next 把它们连成一条单链表：10 -> 20 -> 30 -> NULL，然后：
//       1. 输出每个节点的地址、data 和 next，看清"连接关系"
//       2. 通过 node1->next->next->data 一步步访问第三个节点
//       3. 从头出发，沿着 next 遍历整条链表
//       4. 最后逐个释放节点
//       生活类比：链表像一列火车，每节车厢（节点）装着货物（data），
//       还有一个挂钩（next）指向下一节车厢；最后一节车厢的挂钩不连任何东西，就是 NULL。
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "43_2026-10-28_单链表节点连接与遍历.c"
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
// struct Node { ... } 定义一个结构体：把节点需要的两样东西打包在一起。
//   data ：节点里存的数据（这里是一个整数）；
//   next ：指向"下一个节点"的指针（地址）。
// 为什么写成 struct Node *next？结构体里不能直接包含它自己（那样会无限大），但可以包含"指向同类型的指针"。
// 这时候 typedef 起的名字 Node 还没生效，所以里面要写完整的 struct Node。
// typedef ... Node; 给整个结构体起了个短名字 Node，以后直接写 Node 就行。
typedef struct Node {
    int data;
    struct Node *next;
} Node;


// --------------------------------------------------------------------
// 第三部分：创建一个新节点
// --------------------------------------------------------------------
// 返回类型 Node *：返回"指向 Node 的指针"，也就是新节点的地址。参数 value 是要存进节点的数据。
Node *create_node(int value) {

    // malloc(字节数)：在"堆"上向系统申请一块内存，返回起始地址；失败返回 NULL。
    // sizeof(Node)：一个 Node 占多少字节。(Node *) 是强制类型转换，把地址转成"指向 Node 的指针"
    // （在 C 里这个转换可以省略，写上更清楚）。
    // 为什么用 malloc？节点是用的时候才创建的，用 malloc 申请的内存，函数结束后不会消失，可以一直保留。
    Node *new_node = (Node *)malloc(sizeof(Node));

    // 申请可能失败（内存不足），失败时 malloc 返回 NULL，必须检查，否则后面访问 NULL 会崩溃。
    if (new_node == NULL) {
        printf("错误：内存申请失败，无法创建节点。\n");

        // 创建失败，返回 NULL 告诉调用者"没成功"。
        return NULL;
    }

    // -> 叫"通过指针访问成员"：new_node->data 的意思是"new_node 指向的那个节点里的 data"。
    // 把传进来的 value 存进去。
    new_node->data = value;

    // 新节点还没有"下一个"，所以 next 设为 NULL。
    // 为什么必须设置？malloc 申请到的内存是随机的旧数据，next 里可能是乱地址，
    // 不设成 NULL，后面遍历就会跑到不存在的地方，程序崩溃。
    new_node->next = NULL;

    // 把新节点的地址交给调用者。这块内存在堆上，函数结束后依然存在，所以可以放心返回它的地址。
    return new_node;
}


// --------------------------------------------------------------------
// 第四部分：main 函数 —— 程序从这里开始
// --------------------------------------------------------------------
int main(void) {

    // 创建三个节点，数据分别是 10、20、30。node1、node2、node3 是指针，分别保存三个节点的地址。
    // 此时它们还是三个互不相连的节点，next 都是 NULL。
    Node *node1 = create_node(10);
    Node *node2 = create_node(20);
    Node *node3 = create_node(30);

    // || 读作"或者"：只要有一个节点创建失败（是 NULL），就不能继续了。
    if (node1 == NULL || node2 == NULL || node3 == NULL) {
        printf("错误：至少有一个节点创建失败，程序结束。\n");

        // 已经创建成功的节点要释放，免得内存泄漏。
        // free(NULL) 是安全的，什么也不做，所以这里不需要逐个判断哪个成功了。
        free(node1);
        free(node2);
        free(node3);

        // return 1：以"出错"状态结束程序（0 表示正常，非 0 表示出错）。
        return 1;
    }

    // --------------------------------------------------------------------
    // 第五部分：把三个节点连起来
    // --------------------------------------------------------------------
    // node1->next = node2：让 node1 的 next 保存 node2 的地址，也就是"node1 的下一个是 node2"。
    node1->next = node2;

    // 同理，node2 的下一个是 node3。node3 的 next 一直是 NULL，所以链表在 node3 结束：
    // 10 -> 20 -> 30 -> NULL
    node2->next = node3;

    // --------------------------------------------------------------------
    // 第六部分：查看节点内容和连接关系
    // --------------------------------------------------------------------
    printf("=====三个节点的内容与连接关系=====\n");

    // %p 是输出"内存地址"的占位符，(void *) 是 printf 要求的标准写法（转成通用指针类型）。
    // node1->data 是 node1 里存的数据 10；node1->next 是它保存的"下一个节点的地址"。
    // 观察结果：node1 的 next 的地址，正好等于 node2 本身的地址——这就是"连接"。
    // （这条 printf 被拆成了两行写，意思不变。）
    printf("node1：地址%p，data = %d，next = %p\n", (void *)node1, node1->data,
           (void *)node1->next);
    printf("node2：地址%p，data = %d，next = %p\n", (void *)node2, node2->data,
           (void *)node2->next);
    printf("node3：地址%p，data = %d，next = %p\n", (void *)node3, node3->data,
           (void *)node3->next);

    // node3 的 next 是 NULL，表示后面没有节点了，链表在这里结束。== 判断相等。
    if (node3->next == NULL) {
        printf("node3->next = NULL，表示链表在node3这里结束。\n");
    }

    // 从 node1 出发，一节一节往后走：
    //   node1->next       是 node2；
    //   node1->next->next 是 node3；
    //   node1->next->next->data 就是 node3 的数据 30。
    printf("通过node1->next->next->data访问第三节点：%d\n", node1->next->next->data);

    // --------------------------------------------------------------------
    // 第七部分：遍历链表
    // --------------------------------------------------------------------
    printf("\n=====从节点头出发，沿着next依次遍历=====\n");

    // 遍历：用一个"游标"current，从第一个节点出发，一个一个往后走。
    // 为什么不直接移动 node1？node1 要保留着，它是链表的"入口"，移动了就再也找不回开头。
    Node *current = node1;

    // position 记录现在是第几个节点，从 1 开始数。
    int position = 1;

    // 只要游标还指向一个真实的节点（不是 NULL），就继续循环。走出最后一个节点时 current 变成 NULL，循环结束。
    while (current != NULL) {
        // 输出当前是第几个节点，以及这个节点里的数据。
        printf("第%d个节点：data = %d\n", position, current->data);

        // 游标移到下一个节点：这是遍历链表的核心一步。
        // 例：current 指向 10 的节点，current->next 是 20 的节点的地址，执行后 current 就指向 20 了。
        current = current->next;

        // position 加 1，下一个节点的序号。
        position++;
    }

    // --------------------------------------------------------------------
    // 第八部分：释放所有节点
    // --------------------------------------------------------------------
    // 每个节点都是 malloc 申请来的，用完必须逐个 free，否则就是"内存泄漏"。游标回到第一个节点，重新出发。
    current = node1;
    while (current != NULL) {
        // 先把"下一个节点的地址"保存起来！
        // 因为 free(current) 之后，current 指向的内存就不能再访问了（包括 current->next），
        // 不先保存，就找不到后面的节点了。
        Node *next_node = current->next;

        // 释放当前节点。
        free(current);

        // 游标移到刚才保存的下一个节点，继续释放。
        current = next_node;
    }

    // return 0：结束 main，把 0 交给系统，表示"程序正常结束"。
    return 0;
}

// ====================================================================
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
// （%p 输出的是内存地址，每次运行、每台电脑都可能不同，下面的 0x... 只是某一次运行的例子）
//   =====三个节点的内容与连接关系=====
//   node1：地址0x55dea86ee2a0，data = 10，next = 0x55dea86ee2c0
//   node2：地址0x55dea86ee2c0，data = 20，next = 0x55dea86ee2e0
//   node3：地址0x55dea86ee2e0，data = 30，next = (nil)
//   node3->next = NULL，表示链表在node3这里结束。
//   通过node1->next->next->data访问第三节点：30
//
//   =====从节点头出发，沿着next依次遍历=====
//   第1个节点：data = 10
//   第2个节点：data = 20
//   第3个节点：data = 30
// ====================================================================
*/
