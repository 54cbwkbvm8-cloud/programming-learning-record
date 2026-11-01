#include <stdio.h>
#define TABLE_SIZE 10
typedef struct {
    int used;
    int id;
    int score;
} Entry;
int hash(int id) { return id % TABLE_SIZE; }
void initTable(Entry table[]) {
    for (int i = 0; i < TABLE_SIZE; i++) {
        table[i].used = 0;
        table[i].id = 0;
        table[i].score = 0;
    }
}
int insert(Entry table[], int id, int score) {
    int start = hash(id);
    for (int step = 0; step < TABLE_SIZE; step++) {
        int index = (start + step) % TABLE_SIZE;
        if (table[index].used == 0 || table[index].id == id) {
            table[index].used = 1;
            table[index].id = id;
            table[index].score = score;
            return 1;
        }
    }
    return 0;
}
int search(Entry table[], int id) {
    int start = hash(id);
    for (int step = 0; step < TABLE_SIZE; step++) {
        int index = (start + step) % TABLE_SIZE;
        if (table[index].used == 0) {
            return -1;
        }
        if (table[index].id == id) {
            return index;
        }
    }
    return -1;
}
int main(void) {
    Entry table[TABLE_SIZE] = {0};
    initTable(table);
    if (!insert(table, 101, 85)) {
        printf("哈希表已满，插入失败。\n");
        return 1;
    }
    if (!insert(table, 111, 90)) {
        printf("哈希表已满，插入失败。\n");
        return 1;
    }
    if (!insert(table, 121, 95)) {
        printf("哈希表已满，插入失败。\n");
        return 1;
    }
    if (!insert(table, 202, 100)) {
        printf("哈希表已满，插入失败。\n");
        return 1;
    }
    int index = search(table, 121);
    if (index != -1) {
        printf("找到学号%d，成绩是%d，数组下标是%d\n", table[index].id, table[index].score, index);
    } else {
        printf("没有找到该学生。\n");
    }
    for (int i = 0; i < TABLE_SIZE; i++) {
        if (table[i].used) {
            printf("下标是%d，学号是%d，成绩是%d\n", i, table[i].id, table[i].score);
        }
    }
    return 0;
}
/*// ====================================================================
// 文件名：40_2026-10-24_学生哈希表插入与查找.c
// 作用：用"哈希表"保存"学号 → 成绩"：
//       学号 % 10 决定放在数组的哪个位置；位置被占了，就往后找下一个空位（线性探测）。
//       演示：插入 4 名学生（其中有冲突）、按学号查找、输出表里所有记录。
//
// 怎么运行（在 Mac 终端里，先 cd 到这个文件所在的文件夹）：
//   编译：clang -Wall -Wextra -o demo "40_2026-10-24_学生哈希表插入与查找.c"
//   运行：./demo
//   说明：编译 = 把人能看懂的 C 代码翻译成电脑能执行的程序。
//         -Wall -Wextra 的意思是"把所有可疑的地方都警告我"，
//         编译时出现警告，一定要弄明白原因，不要忽略。
// ====================================================================


// --------------------------------------------------------------------
// 第一部分：引入"工具箱"和宏定义
// --------------------------------------------------------------------
// <stdio.h> 是"标准输入输出"工具箱，printf 在里面。
#include <stdio.h>

// #define 叫"宏定义"：编译之前，程序里所有的 TABLE_SIZE 都会被替换成 10（行末不写分号）。
// 这样哈希表的格子数要修改时，只改这一处。
#define TABLE_SIZE 10


// --------------------------------------------------------------------
// 第二部分：定义哈希表里的"一个格子"
// --------------------------------------------------------------------
// struct { ... } 把几个相关的数据打包；typedef ... Entry; 给它起类型名 Entry，以后直接写 Entry。
// used：0 表示这个格子是空的，1 表示已经存了数据。
// 为什么要单独用一个 used？不能用 id == 0 表示"空"，因为学号 0 也可能是真实数据；用明确的标记最清楚。
// id：学号；score：成绩。
typedef struct {
    int used;
    int id;
    int score;
} Entry;


// --------------------------------------------------------------------
// 第三部分：哈希函数
// --------------------------------------------------------------------
// 哈希表的核心思想：不是从头到尾一个一个找，而是用一个公式把"学号"直接算成"数组下标"，
// 查找时再算一次，就能直接定位。
// 这里的哈希函数是：学号 % 10，结果在 0 到 9 之间，正好是数组下标的范围。
// 例：101 % 10 = 1，所以学号 101 本来应该放在下标 1。
// 不同学号可能算出同一个下标（比如 101 和 111 都是 1），这叫"冲突"，下面用"线性探测"来解决。
int hash(int id) { return id % TABLE_SIZE; }


// --------------------------------------------------------------------
// 第四部分：初始化哈希表
// --------------------------------------------------------------------
// 把每个格子清空：used、id、score 都设成 0。
// 参数 Entry table[] 是数组参数，传进来的是数组的首地址，所以函数里修改的就是 main 里的那张表。
void initTable(Entry table[]) {

    // 遍历表里所有 TABLE_SIZE 个格子。
    for (int i = 0; i < TABLE_SIZE; i++) {
        table[i].used = 0;
        table[i].id = 0;
        table[i].score = 0;
    }
}


// --------------------------------------------------------------------
// 第五部分：插入
// --------------------------------------------------------------------
// 返回值：插入成功返回 1，哈希表已满返回 0。
int insert(Entry table[], int id, int score) {

    // start 是这个学号"本来应该放的位置"，也是探测的起点。
    int start = hash(id);

    // 线性探测：从起点出发，每次多走 1 步（step 是 0、1、2……）找位置，最多走 TABLE_SIZE 步（把整张表找一遍）。
    for (int step = 0; step < TABLE_SIZE; step++) {
        // 实际检查的下标 = 起点 + 步数。% TABLE_SIZE 让下标走到末尾后"绕回"开头，把数组当成一个圈来用。
        // 例：起点是 8，step 是 3：(8 + 3) % 10 = 1。
        int index = (start + step) % TABLE_SIZE;

        // 这个位置可以使用的两种情况：
        //   1) 位置是空的（used == 0）；
        //   2) 位置上已经是同一个学号（id 相同）——这时是"更新成绩"，不是新增。
        // || 读作"或者"，满足任意一种就可以。
        if (table[index].used == 0 || table[index].id == id) {
            // 占用这个位置并写入数据：标记已使用，记下学号和成绩。
            table[index].used = 1;
            table[index].id = id;
            table[index].score = score;

            // 插入成功，立刻结束函数，返回 1。
            return 1;
        }
    }

    // 整张表都走了一遍还没有可用的位置，说明表满了，返回 0。
    return 0;
}


// --------------------------------------------------------------------
// 第六部分：查找
// --------------------------------------------------------------------
// 返回值：找到时返回数组下标；没找到返回 -1（-1 不是合法下标，所以可以表示"没有"）。
int search(Entry table[], int id) {

    // 查找时要用和插入时同一个哈希函数，从同一个起点开始找。
    int start = hash(id);
    for (int step = 0; step < TABLE_SIZE; step++) {
        int index = (start + step) % TABLE_SIZE;

        // 遇到空位就可以停止了：如果这个学号插入过，它一定在探测路线上、第一个空位之前；
        // 走到空位还没找到，说明根本没有这个学号。
        // （这个结论的前提是程序没有"删除"操作，本程序没有删除。）
        if (table[index].used == 0) {
            return -1;
        }

        // 学号相同，找到了，返回这个位置的下标。
        if (table[index].id == id) {
            return index;
        }
    }

    // 整张表都找遍了也没找到。
    return -1;
}


// --------------------------------------------------------------------
// 第七部分：main 函数 —— 程序从这里开始
// --------------------------------------------------------------------
int main(void) {

    // 创建一张有 10 个格子的表。= {0} 会把所有成员都初始化成 0。
    Entry table[TABLE_SIZE] = {0};

    // 再调用 initTable 清空一遍，效果和上一行重复，不过写成函数更清楚，以后需要"重置"时也方便。
    initTable(table);

    // ! 读作"非"：insert 返回 0（失败）时，!0 为真，进入 if，提示后结束程序。
    // 插入学号 101、成绩 85：hash(101) = 1，下标 1 是空的，所以放在下标 1。
    if (!insert(table, 101, 85)) {
        printf("哈希表已满，插入失败。\n");

        // return 1：以"出错"状态结束程序（0 表示正常，非 0 表示出错）。
        return 1;
    }

    // 111 % 10 = 1，起点也是下标 1，但下标 1 已经被 101 占用了（冲突）。
    // 线性探测：step 是 1，试下标 2，是空的，放在下标 2。
    if (!insert(table, 111, 90)) {
        printf("哈希表已满，插入失败。\n");
        return 1;
    }

    // 121 % 10 = 1，下标 1、2 都被占用，继续探测到下标 3，放在下标 3。
    if (!insert(table, 121, 95)) {
        printf("哈希表已满，插入失败。\n");
        return 1;
    }

    // 202 % 10 = 2，起点下标 2 被占用（111），下标 3 也被占用（121），探测到下标 4，放在下标 4。
    if (!insert(table, 202, 100)) {
        printf("哈希表已满，插入失败。\n");
        return 1;
    }

    // 查找学号 121：起点 hash(121) = 1，依次检查：下标 1（101，不是）、下标 2（111，不是）、下标 3（121，是！），返回 3。
    int index = search(table, 121);

    // != -1 表示找到了。
    if (index != -1) {
        // table[index] 取出找到的那一项：它的学号、成绩，再加上它在数组里的下标 index。
        printf("找到学号%d，成绩是%d，数组下标是%d\n", table[index].id, table[index].score, index);
    } else {
        printf("没有找到该学生。\n");
    }

    // 遍历整张表，输出所有已使用的格子。
    for (int i = 0; i < TABLE_SIZE; i++) {
        // used 是 1（C 里非 0 就是"真"）才输出，空格子跳过。
        if (table[i].used) {
            printf("下标是%d，学号是%d，成绩是%d\n", i, table[i].id, table[i].score);
        }
    }

    // return 0：结束 main，把 0 交给系统，表示"程序正常结束"。
    return 0;
}

// ====================================================================
// 完整的预期输出（我在电脑上实际运行过，应该与下面完全一致）：
//   找到学号121，成绩是95，数组下标是3
//   下标是1，学号是101，成绩是85
//   下标是2，学号是111，成绩是90
//   下标是3，学号是121，成绩是95
//   下标是4，学号是202，成绩是100
// ====================================================================
*/
