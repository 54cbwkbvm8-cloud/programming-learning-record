#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct {
    long long key;
    int index;
    int used;
} HashEntry;

static size_t hash_index(long long key, size_t capacity) {
    unsigned long long nonnegative_key =
        (unsigned long long)key;
    return (size_t)(nonnegative_key %
                    capacity);
}

static size_t find_slot(const HashEntry table[], size_t capacity, long long key) {
    size_t position = hash_index(key, capacity);
    while (table[position].used &&
           table[position].key !=
               key) {
        position = (position + 1) % capacity;
    }
    return position;
}

int two_sum_hash(const int nums[], int n, int target, int result[2]) {
    if (result == NULL) {
        return -1;
    }

    result[0] = -1;
    result[1] = -1;

    if (n < 0 ||
        (n > 0 && nums == NULL)) {
        return -1;
    }

    if ((size_t)n > (SIZE_MAX - 1) / 2) {
        return -1;
    }

    size_t capacity =
        (size_t)n * 2 + 1;
    if (capacity >
        SIZE_MAX / sizeof(HashEntry)) {
        return -1;
    }

    HashEntry *table =
        calloc(capacity, sizeof(*table));
    if (table == NULL) {
        return -1;
    }

    for (int i = 0; i < n; i++) {
        long long need =
            (long long)target -
            (long long)
                nums[i];
        size_t need_slot =
            find_slot(table, capacity, need);

        if (table[need_slot].used &&
            table[need_slot].key == need) {
            result[0] = table[need_slot].index;
            result[1] = i;
            free(table);
            return 1;
        }

        long long current = (long long)nums[i];
        size_t current_slot =
            find_slot(table, capacity, current);
        if (!table[current_slot].used) {
            table[current_slot].key = current;
            table[current_slot].index = i;
            table[current_slot].used = 1;
        }
    }

    free(table);
    return 0;
}

int main(void) {
    int nums[] = {2, 7, 11, 15};
    int target = 9;
    int n =
        (int)(sizeof(nums) / sizeof(nums[0]));
    int result[2] = {-1, -1};
    int status = two_sum_hash(nums, n, target,
                              result);

    if (status == 1) {
        printf("找到的下标：[%d, %d]\n", result[0], result[1]);
    } else if (status == 0) {
        printf("没有找到符合条件的两个数。\n");
    } else {
        printf(
            "程序出错：输入参数不合法或内存申请失败。\n");
    }

    return 0;
}
/*// ====================================================================
// 文件名：28_2026-10-18_两数之和_哈希表优化.c
// 作用：用哈希表解决"两数之和"：在数组里找出两个数，使它们的和等于目标值 target，返回这两个数的下标。
//       做法：从左到右扫描数组，对每个数 x，去哈希表里看有没有 target - x；
//             没有，就把 x 存进表里，留给后面的数去找；有，就找到了答案。
//       比"两层循环暴力枚举"快得多：数组只需要扫一遍。
//       示例：数组 {2, 7, 11, 15}，目标 9，答案是下标 [0, 1]（因为 2 + 7 = 9）。
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "28_2026-10-18_两数之和_哈希表优化.c"
//   运行：./demo
//   说明：编译 = 把人能看懂的 C 代码翻译成电脑能执行的程序。
//         -Wall -Wextra 的意思是"把所有可疑的地方都警告我"，
//         编译时出现警告，一定要弄明白原因，不要忽略。
// ====================================================================


// --------------------------------------------------------------------
// 第一部分：引入"工具箱"
// --------------------------------------------------------------------
// <stddef.h> 提供 size_t（表示"大小、下标"的无符号整数类型）和 NULL（空指针）。
#include <stddef.h>

// <stdint.h> 提供 SIZE_MAX（size_t 能表示的最大值），下面用它检查数字会不会溢出。
#include <stdint.h>

// <stdio.h>：printf 在里面。
#include <stdio.h>

// <stdlib.h>：calloc（申请内存）和 free（释放内存）在里面。
#include <stdlib.h>


// --------------------------------------------------------------------
// 第二部分：哈希表里的"一个格子"
// --------------------------------------------------------------------
// struct { ... } 把几个数据打包；typedef ... HashEntry; 给它起类型名 HashEntry。
//   key  ：键，也就是数组里的某个数值；
//   index：这个数在原数组里的下标；
//   used ：0 表示格子是空的，1 表示已经存了数据。
// key 用 long long（范围更大的整数），是为了保存 target - nums[i] 时不会溢出。
typedef struct {
    long long key;
    int index;
    int used;
} HashEntry;


// --------------------------------------------------------------------
// 第三部分：哈希函数
// --------------------------------------------------------------------
// 哈希函数的作用：把一个键 key 变成表里的位置（下标）。
// static 表示这个函数只在本文件内部使用。
// key 可能是负数，负数取余会得到负的余数，不能当下标；所以先转成"无符号"数（变成非负数），
// 再对 capacity 取余，结果一定在 0 到 capacity - 1 之间。
static size_t hash_index(long long key, size_t capacity) {

    // (unsigned long long)key 是强制类型转换：把可能为负的 key 转成无符号数，得到一个非负数。
    // 哈希只要求"同一个 key 永远得到同一个结果"，这一点转换后依然成立。
    // （这条语句被拆成了两行写。）
    unsigned long long nonnegative_key =
        (unsigned long long)key;

    // % 是取余：把一个很大的数压缩到 0 到 capacity - 1 的范围内，作为下标。
    // 例：capacity 是 9，key 是 7，7 % 9 = 7，所以位置是 7。
    return (size_t)(nonnegative_key %
                    capacity);
}


// --------------------------------------------------------------------
// 第四部分：在表里找位置（线性探测）
// --------------------------------------------------------------------
// 作用：找 key 应该在的位置——要么是已经存着这个 key 的格子，要么是第一个空格子。
// 参数：table 是哈希表（const 表示这个函数只读不改），capacity 是格子总数，key 是要找的键。
static size_t find_slot(const HashEntry table[], size_t capacity, long long key) {

    // 先从哈希函数算出来的位置开始找。
    size_t position = hash_index(key, capacity);

    // 循环条件：这个格子里有数据（used 为真），并且存的不是我要找的 key（!=）。&& 读作"并且"。
    // 满足时说明发生了"冲突"：这个位置已经被别的 key 占了。（这个条件被拆成了几行写。）
    while (table[position].used &&
           table[position].key !=
               key) {
        // 线性探测：去检查下一个格子。% capacity 让位置走到末尾后"绕回"开头。
        // 例：capacity 是 9，位置 8 的下一个是 (8 + 1) % 9 = 0。
        // 为什么循环一定会停？后面会让 capacity 比元素个数大，表里一定有空格子。
        position = (position + 1) % capacity;
    }

    // 循环结束时：要么这个格子是空的，要么里面存的正是 key，把位置返回给调用者。
    return position;
}


// --------------------------------------------------------------------
// 第五部分：两数之和（哈希表版）
// --------------------------------------------------------------------
// 参数：nums 是原数组；n 是元素个数；target 是目标和；result 用来"带出"两个下标。
// 返回值：1 表示找到了；0 表示没找到；-1 表示出错（参数不合法或内存不足）。
// 为什么要用 result 数组？函数的返回值只有一个，但答案是两个下标。
// 数组传参传的是地址，函数里改 result，调用者的数组就跟着变。
int two_sum_hash(const int nums[], int n, int target, int result[2]) {

    // 先检查参数是否合法：result 是 NULL（空指针）就没法写入结果，直接返回出错状态 -1。
    if (result == NULL) {
        return -1;
    }

    // 先把结果设成 -1（表示"还没找到"），这样即使函数中途出错返回，调用者拿到的也是明确的"无效"值。
    result[0] = -1;
    result[1] = -1;

    // 继续检查参数：元素个数不能是负数；有元素（n > 0）时，数组指针 nums 不能是 NULL。
    // || 读作"或者"，&& 读作"并且"。（这个条件被拆成了两行写。）
    if (n < 0 ||
        (n > 0 && nums == NULL)) {
        return -1;
    }

    // 防止溢出：下面要计算 n * 2 + 1，如果 n 太大，乘 2 再加 1 会超过 size_t 能表示的最大值，结果就错了。
    // 所以先检查 n 不能超过 (SIZE_MAX - 1) / 2。对一般的小数组，这个条件永远不会成立，是一道"安全检查"。
    if ((size_t)n > (SIZE_MAX - 1) / 2) {
        return -1;
    }

    // 哈希表的格子数 = 元素个数 × 2 + 1。
    // 为什么这么多？表越空，冲突越少，查找越快；而且格子数一定比元素个数多，表里永远有空格子，
    // 这样 find_slot 里的循环不会死循环。
    size_t capacity =
        (size_t)n * 2 + 1;

    // 再检查一次溢出：申请内存的总字节数 = capacity × sizeof(HashEntry)，不能超过 size_t 的上限。
    // sizeof(HashEntry) 是一个格子占多少字节。
    if (capacity >
        SIZE_MAX / sizeof(HashEntry)) {
        return -1;
    }

    // 申请一块内存当作哈希表。HashEntry *table 表示 table 是"指向 HashEntry 的指针"，
    // 指向申请到的内存的起点，之后就可以像数组一样写 table[i]。
    HashEntry *table =

        // calloc(个数, 每个的大小)：向系统申请一块内存，并把里面全部清零。
        // 清零很重要：used 全是 0，表示所有格子一开始都是空的。
        // sizeof(*table)：*table 是一个 HashEntry，所以这是"一个格子"的大小。
        // 对比 malloc：malloc 不清零，里面是随机数据。内存是向系统"借"的，用完必须用 free 归还。
        calloc(capacity, sizeof(*table));

    // 申请内存可能失败（内存不足），失败时 calloc 返回 NULL。必须检查，不检查就使用，程序会崩溃。
    if (table == NULL) {
        return -1;
    }

    // 从左到右扫描数组里的每个数 nums[i]。
    // 思路：对每个数，先问"我需要的搭档，前面已经出现过吗？"出现过就找到了答案；
    // 没出现，就把自己登记进表，留给后面的数去找。
    // 例（nums = {2, 7, 11, 15}，target = 9，capacity = 4 × 2 + 1 = 9）：
    //   i 是 0：需要的搭档是 9 - 2 = 7，表里没有；把 2 登记到位置 2 % 9 = 2。
    //   i 是 1：需要的搭档是 9 - 7 = 2，到表里一找，位置 2 存的正是 2，找到了！答案是 [0, 1]。
    for (int i = 0; i < n; i++) {
        // need 是"我需要的搭档" = target - nums[i]。
        // 为什么都转成 long long 再相减？两个 int 相减，结果可能超出 int 的范围（溢出）；用 long long 就安全了。
        // （这条语句被拆成了好几行写。）
        long long need =
            (long long)target -
            (long long)
                nums[i];

        // 到表里找 need 应该在的位置。
        size_t need_slot =
            find_slot(table, capacity, need);

        // 这个位置里有数据（used），而且存的 key 正好等于 need，说明搭档在前面出现过！
        if (table[need_slot].used &&
            table[need_slot].key == need) {
            // 答案：第一个下标是搭档在原数组里的下标（表里存的 index），第二个下标是当前的 i。
            result[0] = table[need_slot].index;
            result[1] = i;

            // 返回之前必须释放申请的内存，否则就是"内存泄漏"（借了不还）。free 之后不要再使用 table。
            free(table);

            // 返回 1，表示找到了。
            return 1;
        }

        // 没找到搭档，就把当前这个数登记进表。先转成 long long（表里 key 的类型）。
        long long current = (long long)nums[i];

        // 找到它登记的位置。
        size_t current_slot =
            find_slot(table, capacity, current);

        // ! 读作"非"：只有这个位置还是空的（没登记过）才登记。
        // 如果数组里有两个相同的数，只保留第一次出现的下标。
        if (!table[current_slot].used) {
            // 登记：key 存数值，index 存它在原数组里的下标，used 设成 1 表示这个格子有数据了。
            table[current_slot].key = current;
            table[current_slot].index = i;
            table[current_slot].used = 1;
        }
    }

    // 扫描完所有数都没找到，也要释放内存。
    free(table);

    // 返回 0，表示没有找到符合条件的两个数。
    return 0;
}


// --------------------------------------------------------------------
// 第六部分：main 函数 —— 程序从这里开始
// --------------------------------------------------------------------
int main(void) {

    // 测试数组。方括号里不写大小，由大括号里的数据个数决定（4 个）。
    int nums[] = {2, 7, 11, 15};

    // 目标和。
    int target = 9;

    // 元素个数：整个数组的字节数 ÷ 一个元素的字节数 = 4。外面的 (int) 把结果（size_t 类型）转成 int。
    // （这条语句被拆成了两行写。）
    int n =
        (int)(sizeof(nums) / sizeof(nums[0]));

    // 用来接收两个下标，初始都是 -1（表示"无效"）。
    int result[2] = {-1, -1};

    // 调用函数。数组名作为参数时传过去的是地址，函数把答案写进 result，状态码存进 status。
    int status = two_sum_hash(nums, n, target,
                              result);

    // 分三种情况处理返回值：1 表示找到；0 表示没找到；其他（-1）表示出错。
    if (status == 1) {
        // 输出两个下标。例：2 + 7 = 9，所以输出 [0, 1]。
        printf("找到的下标：[%d, %d]\n", result[0], result[1]);
    } else if (status == 0) {
        printf("没有找到符合条件的两个数。\n");
    } else {
        // （这条 printf 的参数被拆成了两行写，意思不变。）
        printf(
            "程序出错：输入参数不合法或内存申请失败。\n");
    }

    // return 0：结束 main，把 0 交给系统，表示"程序正常结束"。
    return 0;
}

// ====================================================================
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   找到的下标：[0, 1]
// ====================================================================
*/
