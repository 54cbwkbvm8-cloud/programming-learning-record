/* 有点难呀，还是先歇歇吧😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭😭*/
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
