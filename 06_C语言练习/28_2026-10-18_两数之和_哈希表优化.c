#include <stddef.h> // 提供 size_t，用于表示数组容量和下标。
#include <stdint.h> // 提供 SIZE_MAX，用于检查容量计算是否可能溢出。
#include <stdio.h>  // 提供 printf，让程序能够输出运行结果。
#include <stdlib.h> // 提供 calloc 和 free，用于申请、释放哈希表所需的内存。

// 一个哈希表槽位：保存数字 key、它在原数组中的下标 index，以及槽位是否已经使用 used。
typedef struct {
    long long key; // 保存已经遇到的数字；使用 long long 也能安全保存补数计算结果。
    int index;     // 保存这个数字第一次出现时在 nums 数组中的下标。
    int used;      // 0 表示槽位空闲，1 表示槽位已经存有数据。
} HashEntry;       // 将这个结构类型命名为 HashEntry，表示哈希表中的一个条目。

// 根据数字 key 计算哈希表的起始位置；capacity 是哈希表的槽位总数。
static size_t hash_index(long long key, size_t capacity) {
    unsigned long long nonnegative_key =
        (unsigned long long)key; // 将整数转换为无符号整数，避免对负数直接求余带来的负下标。
    return (size_t)(nonnegative_key %
                    capacity); // 把数字映射到 0 到 capacity - 1 范围内的一个槽位。
} // 结束 hash_index 函数。

// 从计算出的起始位置查找 key；如果未找到，就返回第一个空闲槽位的位置。
static size_t find_slot(const HashEntry table[], size_t capacity, long long key) {
    size_t position = hash_index(key, capacity); // 先计算 key 对应的哈希表起始位置。
    while (table[position].used &&
           table[position].key !=
               key) { // 如果槽位已被占用但存的不是目标数字，就说明发生了哈希冲突。
        position = (position + 1) % capacity; // 线性探测：移动到下一个槽位，到达末尾后回到槽位 0。
    } // 找到相同数字或空槽位后，结束 while 循环。
    return position; // 返回匹配数字所在槽位，或者可用于插入的空槽位。
} // 结束 find_slot 函数。

// 寻找两个元素，使它们的和等于 target；返回 1 表示找到，0 表示没有，-1 表示参数或内存错误。
int two_sum_hash(const int nums[], int n, int target, int result[2]) {
    if (result == NULL) { // 先确认结果数组存在，否则无法安全写入答案。
        return -1;        // result 为空指针，无法保存下标，因此报告错误。
    } // 结束 result 空指针检查。

    result[0] = -1; // 将第一个结果下标初始化为 -1，表示暂时没有答案。
    result[1] = -1; // 将第二个结果下标初始化为 -1，表示暂时没有答案。

    if (n < 0 ||
        (n > 0 && nums == NULL)) { // 数组长度不能为负数；长度大于 0 时，输入数组也必须存在。
        return -1;                 // 输入参数不合法，返回错误状态。
    } // 结束输入参数检查。

    if ((size_t)n > (SIZE_MAX - 1) / 2) { // 检查 2 * n + 1 是否可能超过 size_t 能表示的最大值。
        return -1;                        // 容量计算会溢出，不能继续申请哈希表。
    } // 结束容量计算溢出检查。

    size_t capacity =
        (size_t)n * 2 + 1; // 申请超过元素数量两倍的槽位，降低冲突概率并确保表中有空槽。
    if (capacity >
        SIZE_MAX / sizeof(HashEntry)) { // 检查申请的槽位数量乘以每个槽位大小是否可能溢出。
        return -1;                      // 所需内存大小无法安全表示，返回错误状态。
    } // 结束内存大小溢出检查。

    HashEntry *table =
        calloc(capacity, sizeof(*table)); // 申请并清零哈希表内存，让所有槽位的 used 初始为 0。
    if (table == NULL) {                  // 检查操作系统是否成功分配了所需内存。
        return -1;                        // 内存申请失败，不能继续使用哈希表。
    } // 结束内存申请检查。

    for (int i = 0; i < n; i++) { // 从左到右遍历 nums，每轮处理一个当前元素。
        long long need =
            (long long)target -
            (long long)
                nums[i]; // 计算当前数字需要搭配的补数；转换为 long long 可避免 int 减法溢出。
        size_t need_slot =
            find_slot(table, capacity, need); // 在哈希表中查找补数，避免再使用一层循环遍历数组。

        if (table[need_slot].used &&
            table[need_slot].key == need) {     // 槽位已使用且键值匹配，说明以前遇到过补数。
            result[0] = table[need_slot].index; // 把以前出现的补数的下标保存为第一个答案。
            result[1] = i;                      // 把当前元素的下标保存为第二个答案。
            free(table); // 找到答案后先释放动态申请的哈希表内存，避免内存泄漏。
            return 1;    // 返回 1 表示找到答案，并立即结束函数。
        } // 结束补数查找成功的分支。

        long long current = (long long)nums[i]; // 把当前数字转成 long long，作为哈希表中保存的键。
        size_t current_slot =
            find_slot(table, capacity, current); // 找到当前数字已有的位置，或找到可供插入的空槽位。
        if (!table[current_slot].used) {         // 只有数字此前未被记录时，才写入新的键值对。
            table[current_slot].key = current;   // 把当前数字保存到哈希表的键字段。
            table[current_slot].index = i;       // 同时记录该数字在原数组中的下标。
            table[current_slot].used = 1;        // 标记这个槽位已经占用，后续查找时可以识别它。
        } // 如果数字已存在，则保留它第一次出现的下标，不覆盖旧记录。
    } // 所有元素都遍历完后，退出 for 循环。

    free(table); // 没有找到答案时同样释放申请的哈希表内存。
    return 0;    // 返回 0 表示检查完所有元素后仍未找到符合条件的数对。
} // 结束 two_sum_hash 函数。

// 主函数是程序入口；这里演示如何调用两数之和哈希表函数。
int main(void) {
    int nums[] = {2, 7, 11, 15}; // 创建测试数组，元素下标依次为 0、1、2、3。
    int target = 9;              // 设置目标和为 9。
    int n =
        (int)(sizeof(nums) / sizeof(nums[0])); // 用数组总字节数除以单个元素字节数，计算元素个数。
    int result[2] = {-1, -1};                  // 创建两个整数的结果数组，用来接收函数返回的下标。
    int status = two_sum_hash(nums, n, target,
                              result); // 调用函数，并用 status 接收找到、未找到或错误的状态。

    if (status == 1) { // 返回 1 代表已经找到一组符合要求的下标。
        printf("找到的下标：[%d, %d]\n", result[0], result[1]); // 输出两个下标；\n 表示换行。
    } else if (status == 0) {                   // 返回 0 代表所有组合检查结束后仍未找到答案。
        printf("没有找到符合条件的两个数。\n"); // 输出未找到的提示。
    } else {                                    // 返回 -1 代表输入参数不合法或申请内存失败。
        printf(
            "程序出错：输入参数不合法或内存申请失败。\n"); // 输出错误提示，避免把错误误认为没找到答案。
    } // 结束状态判断。

    return 0; // 返回 0，表示主函数正常结束。
} // 结束 main 函数。
