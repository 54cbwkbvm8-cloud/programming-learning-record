import java.util.Arrays;

public class ArrayAndBubbleSort {
    static void demoBasics() {
        int[] scores = new int[5];
        double[] prices = new double[3];
        boolean[] flags = new boolean[3];
        String[] names = new String[3];
        System.out.println("int 默认值 ： " + Arrays.toString(scores));
        System.out.println("double 默认值 ：" + Arrays.toString(prices));
        System.out.println("boolean 默认值 ： " + Arrays.toString(flags));
        System.out.println("String 默认值 ： " + Arrays.toString(names));
        int[] nums = { 5, 3, 8, 1 };
        System.out.println("nums = " + Arrays.toString(nums) + ", length = " + nums.length);
        System.out.println("第一个元素 nums[0] = " + nums[0]);
        System.out.println("最后一个元素 nums[nums.length - 1] = " + nums[nums.length - 1]);
        nums[1] = 30;
        System.out.println("修改后 nums = " + Arrays.toString(nums));
        nums[1] = 3;
        int sum = 0;
        for (int value : nums) {
            sum += value;
        }
        System.out.println("增强 for 求和 = " + sum);
        for (int value : nums) {
            value = value * 2;
        }
        System.out.println("增强 for 里改 value 后 nums = " + Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * 2;
        }
        System.out.println("普通 for 翻倍后 nums = " + Arrays.toString(nums));
    }

    static void demoOutOfBounds() {
        int[] arr = { 10, 20, 30 };
        try {
            System.out.println(arr[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到越界异常：" + e.getMessage());
        }
        System.out.println("异常已被接住，程序没有崩溃，继续往下运行。");
    }

    static void demoCopy() {
        int[] original = { 1, 2, 3 };
        int[] alias = original;
        int[] bigger = Arrays.copyOf(original, 5);
        int[] smaller = Arrays.copyOf(original, 2);
        alias[0] = 99;
        System.out.println("original = " + Arrays.toString(original));
        System.out.println("alias = " + Arrays.toString(alias));
        System.out.println("bigger = " + Arrays.toString(bigger));
        System.out.println("smaller = " + Arrays.toString(smaller));
        System.out.println("直接打印数组名字（错误示范）：" + original);
    }

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    static void bubbleSortTrace(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            System.out.println(" --- 第 " + (i + 1) + " 轮 --- ");
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    System.out.println(" -> 交换，数组变成 " + Arrays.toString(arr));
                } else {
                    System.out.println(" -> 不交换，数组变成 " + Arrays.toString(arr));
                }
            }
            System.out.println("第 " + (i + 1) + " 趟结束：" + Arrays.toString(arr));
            if (!swapped) {
                System.out.println("这一趟没有发生交换，数组已经有序，提前结束");
                break;
            }
        }
    }

    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    static int binarySearchTrace(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int round = 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println(
                    "第 " + round + " 轮： low=" + low + ", high=" + high + ", mid=" + mid + ", arr[mid]=" + arr[mid]);
            if (arr[mid] == target) {
                System.out.println(" " + arr[mid] + " 等于目标 " + target + "，找到了，返回下标 " + mid);
                return mid;
            } else if (arr[mid] < target) {
                System.out.println(" " + arr[mid] + " < " + target + "，去掉左边，low = " + (mid + 1));
                low = mid + 1;
            } else {
                System.out.println(" " + arr[mid] + " > " + target + "，去掉右边，high = " + (mid - 1));
                high = mid - 1;
            }
            round++;
        }
        System.out.println("low=" + low + " 大于 high=" + high + "，区间为空，没有找到，返回-1");
        return -1;
    }

    static void check(String name, boolean passed) {
        if (passed) {
            System.out.println("[通过] " + name);
        } else {
            System.out.println("[失败] " + name);
        }
    }

    public static void main(String[] args) {
        System.out.println("========== 一.数组基础 ==========");
        demoBasics();
        System.out.println("========== 二.数组越界 ==========");
        demoOutOfBounds();
        System.out.println("========== 三.Arrays.toString 与 copyOf ==========");
        demoCopy();
        System.out.println("========== 四.冒泡排序：核对手工推演[5, 3, 8, 1] ==========");
        int[] handDemo = { 5, 3, 8, 1 };
        bubbleSortTrace(handDemo);
        System.out.println("最终结果：" + Arrays.toString(handDemo));
        System.out.println("---------- 已有序数组[1,2,3,4]：应该只跑一趟就提前结束 ----------");
        int[] alreadySorted = { 1, 2, 3, 4 };
        bubbleSortTrace(alreadySorted);
        System.out.println("========== 五.冒泡排序测试 ==========");
        int[] a1 = { 5, 3, 8, 1 };
        bubbleSort(a1);
        check("普通数组 [5,3,8,1] -> [1,3,5,8]", Arrays.equals(a1, new int[] { 1, 3, 5, 8 }));
        int[] a2 = { 4, 3, 2, 1 };
        bubbleSort(a2);
        check("逆序数组 [4,3,2,1] -> [1,2,3,4]", Arrays.equals(a2, new int[] { 1, 2, 3, 4 }));
        int[] a3 = { 1, 2, 3, 4 };
        bubbleSort(a3);
        check("已经有序数组 [1,2,3,4] 保持不变", Arrays.equals(a3, new int[] { 1, 2, 3, 4 }));
        int[] a4 = { 3, 1, 3, 2, 1 };
        bubbleSort(a4);
        check("重复元素数组 [3,1,3,2,1] -> [1,1,2,3,3]", Arrays.equals(a4, new int[] { 1, 1, 2, 3, 3 }));
        int[] a5 = new int[0];
        bubbleSort(a5);
        check("空数组 [] 保持不变", Arrays.equals(a5, new int[] {}));
        int[] a6 = { 7 };
        bubbleSort(a6);
        check("单元素数组 [7] 保持不变", Arrays.equals(a6, new int[] { 7 }));
        int[] sortedArr = { 2, 5, 8, 12, 16, 23, 38, 56 };
        System.out.println("========== 六.二分查找：跟踪查找16 / 2 / 20 ==========");
        System.out.println("查找 16:");
        binarySearchTrace(sortedArr, 16);
        System.out.println("查找 2:");
        binarySearchTrace(sortedArr, 2);
        System.out.println("查找 20:");
        binarySearchTrace(sortedArr, 20);
        System.out.println("========== 七.查找测试 ==========");
        int[] dup = { 1, 2, 2, 2, 3 };
        check("线性查找 16 -> 下标 4", linearSearch(sortedArr, 16) == 4);
        check("线性查找 20 -> -1 (没找到) ", linearSearch(sortedArr, 20) == -1);
        check("线性查找 重复值 2 -> 第一个，下标 1", linearSearch(dup, 2) == 1);
        check("二分查找 16 -> 下标 4", binarySearch(sortedArr, 16) == 4);
        check("二分查找 2 -> 下标 0 (第一个元素) ", binarySearch(sortedArr, 2) == 0);
        check("二分查找 56 -> 下标 7 (最后一个元素) ", binarySearch(sortedArr, 56) == 7);
        check("二分查找 空数组 -> -1", binarySearch(new int[0], 5) == -1);
        check("二分查找 单元素 [7] 找 7 -> 0", binarySearch(new int[] { 7 }, 7) == 0);
        check("二分查找 单元素 [7] 找 5 -> -1", binarySearch(new int[] { 7 }, 5) == -1);
        int idx = binarySearch(dup, 2);
        check("二分查找 重复值 2 -> 下标在 1 到 3 之间", idx >= 1 && idx <= 3);
        int[] unsorted = { 23, 5, 56, 2, 38, 12, 8, 16 };
        bubbleSort(unsorted);
        check("先冒泡排序 结果等于sortedArr", Arrays.equals(unsorted, sortedArr));
        check("排序后再二分查找 16 -> 下标 4", binarySearch(unsorted, 16) == 4);
    }
}
// // import 的作用：告诉 Java "我要使用别人写好的工具"。
// // Arrays 是 Java 自带的"数组工具箱"，里面有很多现成的功能，比如：
// // Arrays.toString(数组) ：把数组变成方便阅读的文字，如 [1, 2, 3]
// // Arrays.copyOf(数组, 长度) ：复制一个数组
// // Arrays.equals(数组1, 数组2)：比较两个数组的内容是否完全相同
// // 它不在 Java 默认范围内，所以必须先 import，否则 Java 不认识 Arrays 这个名字。
// import java.util.Arrays;

// /**
// * ArrayAndBubbleSort —— 数组基础、冒泡排序、查找（线性查找 + 二分查找）综合练习
// *
// * 这个程序分成两大部分：
// * 【上半部分】写了一堆"方法"（可以理解为"封装好的小功能"）：
// * demoBasics / demoOutOfBounds / demoCopy ：演示数组的基本用法
// * bubbleSort / bubbleSortTrace ：冒泡排序（后者会把每一步都打印出来）
// * linearSearch / binarySearch / binarySearchTrace ：两种查找方法
// * check ：一个小"裁判"，用来判断测试通过没有
// * 【下半部分】main 方法：程序的入口，按顺序把上面的方法一个个调用起来，并做测试
// *
// * 【本次修复的问题】
// * 1. bubbleSortTrace：当两个数不需要交换时，原代码只打印了"不交换，数组变成"，
// * 后面没有把数组打印出来（漏了 + Arrays.toString(arr)），话说到一半就断了。
// * 2. bubbleSortTrace：原代码检测到"这一趟没有发生交换"后，只是打印了"提前结束"这句话，
// * 但并没有真的结束循环（漏了 break）。结果是 [1,2,3,4] 这种本来就有序的数组，
// * 明明说"应该只跑一趟"，实际却白白跑了 3 趟。现在补上 break。
// * 3. main 第六部分"二分查找跟踪"：原代码调用的是 binarySearch（不会打印任何过程），
// * 所以这一部分运行后什么都看不到。应该调用会打印过程的 binarySearchTrace。
// * 4. main 第五部分的冒泡排序测试：原代码的"已经有序数组"这条测试，
// * 检查的其实是上一条测试里刚刚排好序的 a2，并没有真的拿一个"本来就有序"的数组去排序，
// * 所以这条测试永远通过、什么也没测到（变量名也从 a2 直接跳到了 a4，缺了 a3）。
// * 现在补上 a3 = [1,2,3,4]，真正测一次"已有序数组"。
// * 5. demoBasics：打印"修改后 nums[1] = ..."，后面却打印的是整个数组，文字和内容对不上。
// * 现在改成"修改后 nums = ..."。
// * 6. 变量名 hight 拼错了，正确的英文单词是 high（"高"）。已全部改成 high，
// * 打印出来的文字里的 hight 也一起改了。
// * 7. 两处小错别字：标题里的"已有序列组"应为"已有序数组"，
// * "[5，3，8，1]"里用了中文逗号，改成了英文逗号。
// * （其余的逻辑，比如 bubbleSort、linearSearch、binarySearch 本身都是正确的，没有改动。）
// */
// public class ArrayAndBubbleSort {

// // ============================================================
// // 说明：下面每个"static void 名字()"都是一个【方法】。
// // · 方法 = 一段打包好、起了名字的代码，需要的时候"喊一下名字"（调用）就会执行
// // · static ：现在先记住"这里的方法前面都要写 static"，因为它们要被 main 直接调用
// // · void ：表示这个方法执行完"不返回任何结果"，只负责做事（比如打印）
// // · 括号 () ：里面可以写"参数"，参数就是调用时传给方法的材料。空括号表示不需要材料
// // ============================================================

// // ============================================================
// // 方法 1：演示数组的基础用法
// // ============================================================
// static void demoBasics() {

// // ---------- 创建数组，并观察"默认值" ----------
// // 数组 = 一排连续的、同类型的"格子"，每个格子能放一个数据。
// // 写法：类型[] 名字 = new 类型[格子数量];
// // 下面这句：创建一个能放 5 个 int（整数）的数组，名字叫 scores。
// // new 的意思是"向电脑申请这块空间"。刚创建出来时，格子里不是空的，而是自动放了"默认值"。
// int[] scores = new int[5]; // 5 个整数格子，默认值全是 0
// double[] prices = new double[3]; // 3 个小数格子，默认值全是 0.0
// boolean[] flags = new boolean[3]; // 3 个"真/假"格子，默认值全是 false
// String[] names = new String[3]; // 3 个文字格子，默认值全是 null（意思是"什么都没有"）

// // Arrays.toString(数组)：把数组里的内容变成像 [0, 0, 0] 这样的文字，方便打印查看。
// // 如果直接打印数组本身，看到的是一串没法读的东西（下面的 demoCopy 里会演示）。
// System.out.println("int 默认值 ： " + Arrays.toString(scores));
// System.out.println("double 默认值 ：" + Arrays.toString(prices));
// System.out.println("boolean 默认值 ： " + Arrays.toString(flags));
// System.out.println("String 默认值 ： " + Arrays.toString(names));

// // ---------- 创建数组并同时放入内容 ----------
// // 另一种写法：直接用花括号 { } 把内容列出来，Java 会自动数出有几个，帮你创建好。
// int[] nums = { 5, 3, 8, 1 };

// // nums.length 是数组的"长度"（一共有几个格子）。注意：length 后面【没有】括号。
// System.out.println("nums = " + Arrays.toString(nums) + ", length = " +
// nums.length);

// // ---------- 通过"下标"访问元素 ----------
// // 下标 = 格子的编号，【从 0 开始数，不是从 1】！
// // nums 的内容： 5 3 8 1
// // 对应的下标： 0 1 2 3
// // 用 数组名[下标] 就能取出对应格子里的值。
// System.out.println("第一个元素 nums[0] = " + nums[0]); // 5

// // 最后一个元素的下标 = 长度 - 1。因为下标从 0 开始，4 个元素最大下标是 3，而不是 4。
// System.out.println("最后一个元素 nums[nums.length - 1] = " + nums[nums.length -
// 1]); // 1

// // ---------- 修改元素 ----------
// // 用 数组名[下标] = 新值; 就能改写那个格子里的内容。
// nums[1] = 30; // 把下标 1 的格子（原来是 3）改成 30
// // 【修复点】原代码这里的文字写的是"nums[1] ="，但后面打印的是整个数组，文字对不上，
// // 所以改成"nums ="。
// System.out.println("修改后 nums = " + Arrays.toString(nums)); // [5, 30, 8, 1]
// nums[1] = 3; // 改回去，让数组恢复成 [5, 3, 8, 1]，方便后面继续演示

// // ---------- 增强 for 循环（也叫 for-each 循环）：用来"依次取出每一个元素" ----------
// // 读法："对于 nums 里的每一个 int 类型的元素 value，做花括号里的事"。
// // 好处：不用管下标，写起来简单。
// int sum = 0; // 累加器：用来保存总和，一开始是 0
// for (int value : nums) {
// sum += value; // sum += value 等价于 sum = sum + value，把当前元素加进总和
// }
// System.out.println("增强 for 求和 = " + sum); // 5+3+8+1 = 17

// // ---------- 一个容易踩的坑：增强 for 里没法修改数组本身 ----------
// // 增强 for 取出来的 value 只是【元素的一份拷贝】，不是数组里的那个格子本身。
// // 就像你把试卷复印了一份，在复印件上涂改，原试卷不会变。
// for (int value : nums) {
// value = value * 2; // 只是把"拷贝"翻了倍，数组里的内容完全没变
// }
// System.out.println("增强 for 里改 value 后 nums = " + Arrays.toString(nums)); //
// 还是 [5, 3, 8, 1]

// // ---------- 想真正修改数组，要用普通 for 循环 + 下标 ----------
// // i 从 0 开始，到 nums.length - 1 结束（所以条件是 i < nums.length，不能写 <=）。
// // 每次通过 nums[i] 直接操作数组里真正的那个格子。
// for (int i = 0; i < nums.length; i++) {
// nums[i] = nums[i] * 2; // 取出第 i 个格子的值，乘 2，再放回同一个格子
// }
// System.out.println("普通 for 翻倍后 nums = " + Arrays.toString(nums)); // [10, 6,
// 16, 2]
// }

// // ============================================================
// // 方法 2：演示"数组下标越界"，以及如何用 try-catch 接住错误
// // ============================================================
// static void demoOutOfBounds() {
// int[] arr = { 10, 20, 30 }; // 3 个元素，合法下标只有 0、1、2

// // try-catch：用来"预防错误导致程序崩溃"。
// // try ：把"可能出错的代码"放在这里面试着运行
// // catch ：如果 try 里真的出了指定类型的错误，就跳到这里处理，程序不会崩溃
// try {
// // arr[3] 访问的是下标 3，但数组只有下标 0~2，根本没有这个格子！
// // 这叫"数组下标越界"，Java 会在这一行抛出一个错误（叫"异常"）。
// System.out.println(arr[3]);
// } catch (ArrayIndexOutOfBoundsException e) {
// // ArrayIndexOutOfBoundsException 就是"数组下标越界异常"的名字。
// // e 是这个错误的"详情对象"，e.getMessage() 能取出错误的文字说明，
// // 内容类似：Index 3 out of bounds for length 3（下标 3 超出了长度 3）
// System.out.println("捕获到越界异常：" + e.getMessage());
// }

// // 因为错误被 catch 接住了，程序没有崩溃，会继续执行 try-catch 后面的代码。
// // 如果没有 try-catch，程序会在 arr[3] 那行直接崩溃，这句话就永远打印不出来了。
// System.out.println("异常已被接住，程序没有崩溃，继续往下运行。");
// }

// // ============================================================
// // 方法 3：演示数组的"复制"，以及"引用"的概念
// // ============================================================
// static void demoCopy() {
// int[] original = { 1, 2, 3 };

// // 【重点】这一句【不是复制数组】！
// // 数组本身存放在内存的一块空间里，original 这个变量里存的只是这块空间的"地址"（像门牌号）。
// // int[] alias = original; 只是把"门牌号"抄了一份给 alias。
// // 结果：original 和 alias 是同一个数组的两个名字，指向同一间"房子"。
// int[] alias = original;

// // Arrays.copyOf(原数组, 新长度)：真正复制出一个全新的、独立的数组。
// // 新长度比原来大：多出来的位置补默认值（int 就是 0）
// // 新长度比原来小：只复制前面的部分，后面的被截掉
// int[] bigger = Arrays.copyOf(original, 5); // 复制并扩容成 5 格：[1, 2, 3, 0, 0]
// int[] smaller = Arrays.copyOf(original, 2); // 复制并截短成 2 格：[1, 2]

// // 通过 alias 修改下标 0。因为 alias 和 original 指向同一个数组，
// // 所以 original 里的内容也跟着变了！
// // 而 bigger 和 smaller 是上面【提前复制好的独立数组】，不受影响，里面还是原来的 1。
// alias[0] = 99;

// System.out.println("original = " + Arrays.toString(original)); // [99, 2, 3]
// 被改了
// System.out.println("alias = " + Arrays.toString(alias)); // [99, 2, 3] 同一个数组
// System.out.println("bigger = " + Arrays.toString(bigger)); // [1, 2, 3, 0, 0]
// 独立的，没被改
// System.out.println("smaller = " + Arrays.toString(smaller)); // [1, 2]
// 独立的，没被改

// // 错误示范：直接把数组名字拿来打印，打印出来的是数组的"类型+地址"，
// // 样子类似 [I@1b6d3586（[I 表示 int 数组，@ 后面是地址编码），人根本看不懂。
// // 而且每次运行，后面的编码都可能不一样。
// // 想看数组内容，一定要用 Arrays.toString(数组)。
// System.out.println("直接打印数组名字（错误示范）：" + original);
// }

// // ============================================================
// // 方法 4：冒泡排序（把数组从小到大排好）
// //
// // 【基本思想】
// // 重复地比较"相邻的两个数"，如果左边的比右边的大，就交换它们的位置。
// // 这样每走完一趟，当前没排好的数里最大的那个，就像水里的气泡一样，"浮"到了最右边。
// //
// // 例子：[5, 3, 8, 1]
// // 第 1 趟：5>3 交换 → [3,5,8,1]；5<8 不换；8>1 交换 → [3,5,1,8] （最大的 8 到了最后）
// // 第 2 趟：3<5 不换；5>1 交换 → [3,1,5,8] （第二大的 5 就位）
// // 第 3 趟：3>1 交换 → [1,3,5,8] （全部排好）
// //
// // 【参数说明】int[] arr：要排序的数组。
// // 注意：数组传给方法时，传的是"地址"（见上面 demoCopy 的讲解），
// // 所以方法里对 arr 的修改，会直接改到调用者那边的原数组。这就是为什么 bubbleSort 不需要返回值（void）。
// // ============================================================
// static void bubbleSort(int[] arr) {
// int n = arr.length; // 数组长度，存进变量 n，后面要反复用，写起来更短

// // 外层循环：控制"一共要走几趟"。
// // n 个数，最多只需要走 n-1 趟：每趟能确定一个最大值的位置，
// // 确定了 n-1 个之后，剩下的最后一个自动就位。
// // i 表示"已经走完了几趟"（从 0 开始数）。
// for (int i = 0; i < n - 1; i++) {

// // swapped 的意思是"这一趟有没有发生过交换"，一开始当作"没有"（false）。
// // 它是一个"优化开关"：如果某一趟下来一次交换都没发生，说明所有相邻的数都已经是
// // 左小右大的顺序了，数组已经排好，不用再继续走了。
// boolean swapped = false;

// // 内层循环：控制"这一趟里，从左到右依次比较相邻的两个数"。
// // j 是当前比较的左边那个数的下标，右边那个数的下标是 j + 1。
// //
// // 为什么条件是 j < n - 1 - i ？分两步想：
// // · 每次要访问 arr[j + 1]，所以 j 最大只能是 n - 2，否则 j + 1 会越界。
// // （只考虑这个的话，条件应该是 j < n - 1）
// // · 每走完 1 趟，最右边就多一个"已经排好的最大值"，这些不需要再比较了。
// // 已经走完 i 趟，右边就有 i 个排好的数，所以要再少比较 i 个。
// // 合起来就是 j < n - 1 - i。
// for (int j = 0; j < n - 1 - i; j++) {

// // 如果左边的数比右边的数大，说明顺序反了，需要交换
// if (arr[j] > arr[j + 1]) {

// // 交换两个数需要借助一个"临时变量" temp。
// // 为什么不能直接 arr[j] = arr[j+1]; arr[j+1] = arr[j]; ？
// // 因为第一句执行后，arr[j] 原来的值就被覆盖丢失了。
// // 类比：想交换两杯水（可乐和雪碧），必须再拿一个空杯子当中转：
// int temp = arr[j]; // ① 先把左边的值倒进空杯子 temp 保存起来
// arr[j] = arr[j + 1]; // ② 把右边的值放到左边（此时左边的旧值已安全保存在 temp 里）
// arr[j + 1] = temp; // ③ 再把 temp 里保存的旧左值放到右边，交换完成

// swapped = true; // 记录："这一趟发生过交换"
// }
// }

// // 一趟结束后检查：如果 swapped 仍然是 false，说明这一趟一次交换都没发生，
// // 也就是数组已经有序了，直接用 break 跳出外层循环，不再浪费时间继续比较。
// // （!swapped 读作"没有发生交换"，! 表示"取反"）
// if (!swapped) {
// break;
// }
// }
// }

// // ============================================================
// // 方法 5：冒泡排序（带"过程演示"版）
// // 逻辑和 bubbleSort 完全一样，区别是：每走一步都会把过程打印出来，
// // 让你能亲眼看到数组是怎么一步步变有序的。适合用来对照"手工推演"。
// // ============================================================
// static void bubbleSortTrace(int[] arr) {
// int n = arr.length;

// for (int i = 0; i < n - 1; i++) {
// boolean swapped = false;

// // i 是从 0 开始数的，但人习惯说"第 1 轮"，所以打印时用 i + 1。
// // 注意 (i + 1) 要加括号：这样 Java 才会先算加法，再把结果拼进文字里。
// // 如果不加括号写成 "第 " + i + 1，会被拼成"第 01"之类的文字，而不是做加法。
// System.out.println(" --- 第 " + (i + 1) + " 轮 --- ");

// for (int j = 0; j < n - 1 - i; j++) {
// if (arr[j] > arr[j + 1]) {
// int temp = arr[j];
// arr[j] = arr[j + 1];
// arr[j + 1] = temp;
// swapped = true;
// System.out.println(" -> 交换，数组变成 " + Arrays.toString(arr));
// } else {
// // 【修复点 1】原代码这里只写了 " -> 不交换，数组变成 "，后面没有内容，话说到一半。
// // 现在补上 + Arrays.toString(arr)，把此时的数组（没有变化）也打印出来。
// System.out.println(" -> 不交换，数组变成 " + Arrays.toString(arr));
// }
// }

// System.out.println("第 " + (i + 1) + " 趟结束：" + Arrays.toString(arr));

// if (!swapped) {
// System.out.println("这一趟没有发生交换，数组已经有序，提前结束");
// // 【修复点 2】原代码只打印了上面这句话，却没有真的结束循环，
// // 导致已经有序的数组仍然把剩下的几趟全部白跑一遍，和"提前结束"的说法自相矛盾。
// // 加上 break 之后，才是真正地提前结束。
// break;
// }
// }
// }

// // ============================================================
// // 方法 6：线性查找（也叫顺序查找）
// //
// // 思路：从第一个元素开始，一个一个往后看，找到了就报告位置；全看完都没有就说没找到。
// // 优点：简单，数组不需要事先排好序。 缺点：数据很多时比较慢，最坏要看完所有元素。
// //
// // 【方法的写法】
// // static int linearSearch(int[] arr, int target)
// // int ：这个方法执行完会"返回一个整数"（返回值类型）。这里返回找到的下标。
// // int[] arr ：参数 1，要在哪个数组里找
// // int target ：参数 2，要找的目标值（target 是"目标"的意思）
// // return 值; ：把结果交给调用者，并且【立刻结束这个方法】
// //
// // 约定：找不到时返回 -1。因为合法下标最小是 0，不可能是 -1，所以 -1 可以明确表示"没找到"。
// // ============================================================
// static int linearSearch(int[] arr, int target) {
// for (int i = 0; i < arr.length; i++) {
// if (arr[i] == target) { // 注意：判断"相等"用两个等号 ==，一个等号 = 是"赋值"
// return i; // 找到了：立刻返回当前下标 i，方法到此结束（后面的元素不用看了）
// }
// }
// // 能走到这里，说明整个 for 循环都跑完了也没有 return，也就是没找到
// return -1;
// }

// // ============================================================
// // 方法 7：二分查找
// //
// // 【前提条件（非常重要）】数组必须【已经从小到大排好序】！没排序的数组不能用二分查找。
// //
// // 【思路】像猜数字游戏："我想了一个 1~100 的数"，你每次猜中间的 50，
// // 对方说"大了"，你就知道答案在 1~49，一下子排除了一半。每次排除一半，所以特别快。
// //
// // 【具体做法】用 low 和 high 两个下标，圈出"目前还可能有答案的范围"：
// // low ：范围的左边界（最左边那个格子的下标）
// // high ：范围的右边界（最右边那个格子的下标）
// // mid ：范围正中间那个格子的下标（mid 是 middle"中间"的缩写）
// // 每次看 arr[mid]：
// // · 等于目标 → 找到了，返回 mid
// // · 比目标小 → 目标一定在 mid 的右边，把左边界移到 mid + 1（mid 自己和左边全部排除）
// // · 比目标大 → 目标一定在 mid 的左边，把右边界移到 mid - 1（mid 自己和右边全部排除）
// // 当 low > high，说明范围里一个格子都不剩了，就是没找到。
// //
// // 【修复点】原代码把变量名 high 拼成了 hight，这是个拼写错误，已全部改正。
// // ============================================================
// static int binarySearch(int[] arr, int target) {
// int low = 0; // 一开始，范围是整个数组，左边界是第一个格子（下标 0）
// int high = arr.length - 1; // 右边界是最后一个格子（下标 长度-1）

// // 只要范围里还有格子（low <= high），就继续找。
// // 注意是 <= 而不是 <：当 low 和 high 相等时，范围里还剩 1 个格子，这个格子也要检查。
// while (low <= high) {

// // 计算中间位置。
// // 为什么不直接写 (low + high) / 2 ？数学上是一样的，
// // 但当数字非常大时，low + high 可能超过 int 能表示的最大值而"溢出"变成错误的负数。
// // 写成 low + (high - low) / 2 就不会溢出，这是专业人员的标准写法。
// // 另外：两个整数相除，结果只保留整数部分（小数直接丢掉）。比如 7 / 2 = 3。
// int mid = low + (high - low) / 2;

// if (arr[mid] == target) {
// return mid; // 正好是目标，找到了，返回下标
// } else if (arr[mid] < target) {
// low = mid + 1; // 中间的数太小了，目标在右半边，左边界右移到 mid 的下一格
// } else {
// high = mid - 1; // 中间的数太大了，目标在左半边，右边界左移到 mid 的前一格
// }
// }

// // 循环结束了还没 return，说明范围缩没了，目标不存在
// return -1;
// }

// // ============================================================
// // 方法 8：二分查找（带"过程演示"版）
// // 逻辑和 binarySearch 完全一样，区别是每一轮都会把 low、high、mid 的值打印出来，
// // 让你看清范围是怎么一步步缩小的。
// //
// // 以 sortedArr = {2, 5, 8, 12, 16, 23, 38, 56}（下标 0~7）查找 16 为例：
// // 第 1 轮：low=0, high=7, mid=3, arr[3]=12。12 < 16，目标在右边 → low = 4
// // 第 2 轮：low=4, high=7, mid=5, arr[5]=23。23 > 16，目标在左边 → high = 4
// // 第 3 轮：low=4, high=4, mid=4, arr[4]=16。等于目标，找到了，返回 4
// // ============================================================
// static int binarySearchTrace(int[] arr, int target) {
// int low = 0;
// int high = arr.length - 1;
// int round = 1; // 记录"现在是第几轮"，只用来打印，从 1 开始（符合人的说法）

// while (low <= high) {
// int mid = low + (high - low) / 2;

// // 一条很长的 println 拆成了几行写，是为了方便阅读，Java 并不介意换行（以分号 ; 结尾才算一句话）。
// System.out.println(
// "第 " + round + " 轮： low=" + low + ", high=" + high + ", mid=" + mid + ",
// arr[mid]=" + arr[mid]);

// if (arr[mid] == target) {
// System.out.println(" " + arr[mid] + " 等于目标 " + target + "，找到了，返回下标 " + mid);
// return mid;
// } else if (arr[mid] < target) {
// System.out.println(" " + arr[mid] + " < " + target + "，去掉左边，low = " + (mid +
// 1));
// low = mid + 1;
// } else {
// System.out.println(" " + arr[mid] + " > " + target + "，去掉右边，high = " + (mid -
// 1));
// high = mid - 1;
// }
// round++; // 这一轮结束，轮数加 1
// }

// // 走到这里 low > high：范围里一个格子都不剩了
// System.out.println("low=" + low + " 大于 high=" + high + "，区间为空，没有找到，返回-1");
// return -1;
// }

// // ============================================================
// // 方法 9：check —— 一个简单的"测试裁判"
// //
// // 参数 name ：这条测试的名字（文字），用来打印出来说明在测什么
// // 参数 passed ：测试是否通过（boolean 类型，只有 true 和 false 两种值）
// //
// // 用法：check("测试名", 某个应该成立的判断);
// // 判断成立（true）就打印 [通过]，不成立（false）就打印 [失败]。
// // 这样测试结果一眼就能看出来，不用自己对照每一行输出。
// // ============================================================
// static void check(String name, boolean passed) {
// if (passed) {
// System.out.println("[通过] " + name);
// } else {
// System.out.println("[失败] " + name);
// }
// }

// // ============================================================
// // main 方法：整个程序的入口。
// // 运行程序时，Java 从这里的第一行开始，一行一行往下执行；上面的那些方法，
// // 只有在这里（或别的方法里）被"调用"了，才会真正运行。
// //
// // public ：公开的，Java 虚拟机要从外面调用它，所以必须是公开的
// // static ：不需要先创建对象就能调用
// // void ：没有返回值
// // String[] args ：运行程序时可以从命令行传进来的参数，本程序用不到
// // ============================================================
// public static void main(String[] args) {

// // ==================== 一. 数组基础 ====================
// System.out.println("========== 一.数组基础 ==========");
// demoBasics(); // 调用方法：写方法名加一对括号，就会执行那个方法里的全部代码

// // ==================== 二. 数组越界 ====================
// System.out.println("========== 二.数组越界 ==========");
// demoOutOfBounds();

// // ==================== 三. Arrays.toString 与 copyOf ====================
// System.out.println("========== 三.Arrays.toString 与 copyOf ==========");
// demoCopy();

// // ==================== 四. 冒泡排序：跟踪过程，核对手工推演 ====================
// // 修复了标题里的中文逗号，改成英文逗号
// System.out.println("========== 四.冒泡排序：核对手工推演[5, 3, 8, 1] ==========");
// int[] handDemo = { 5, 3, 8, 1 };
// bubbleSortTrace(handDemo); // 数组传进去，方法里把它排好序并打印每一步
// // 因为方法修改的是同一个数组（传的是地址），所以这里 handDemo 已经是排好序的了
// System.out.println("最终结果：" + Arrays.toString(handDemo));

// // 修复了错别字："已有序列组"改成"已有序数组"
// System.out.println("---------- 已有序数组[1,2,3,4]：应该只跑一趟就提前结束 ----------");
// int[] alreadySorted = { 1, 2, 3, 4 };
// bubbleSortTrace(alreadySorted); // 修复 break 之后，这里只会出现"第 1 轮"，然后提前结束

// // ==================== 五. 冒泡排序测试 ====================
// // 测试的思路：准备各种不同情况的数组（普通、逆序、已有序、有重复、空、只有一个），
// // 排序之后，用 Arrays.equals 检查结果是不是我们预期的样子。
// //
// // 为什么用 Arrays.equals(数组1, 数组2) 而不是 ==？
// // 对数组用 == 比较的是"地址是否相同"（是不是同一个数组），而不是内容。
// // 两个内容一样的数组，地址不同，== 会得到 false。
// // Arrays.equals 才是逐个比较"内容"是否相同。
// //
// // new int[] { 1, 3, 5, 8 } 是"临时创建一个数组"的写法：
// // 不需要先起名字存起来，创建完直接拿来用（这里直接当作"预期结果"）。
// System.out.println("========== 五.冒泡排序测试 ==========");

// // 情况 1：普通的乱序数组
// int[] a1 = { 5, 3, 8, 1 };
// bubbleSort(a1);
// check("普通数组 [5,3,8,1] -> [1,3,5,8]", Arrays.equals(a1, new int[] { 1, 3, 5, 8
// }));

// // 情况 2：完全逆序的数组（冒泡排序最费力的情况）
// int[] a2 = { 4, 3, 2, 1 };
// bubbleSort(a2);
// check("逆序数组 [4,3,2,1] -> [1,2,3,4]", Arrays.equals(a2, new int[] { 1, 2, 3, 4
// }));

// // 情况 3：本来就已经有序的数组
// // 【修复点】原代码这里没有新建数组，检查的还是上面排好序的 a2，等于什么都没测到。
// // 现在新建 a3，让它在排序前就是有序的，才真正测试"有序数组传进去不会被弄乱"。
// int[] a3 = { 1, 2, 3, 4 };
// bubbleSort(a3);
// check("已经有序数组 [1,2,3,4] 保持不变", Arrays.equals(a3, new int[] { 1, 2, 3, 4 }));

// // 情况 4：有重复元素的数组
// int[] a4 = { 3, 1, 3, 2, 1 };
// bubbleSort(a4);
// check("重复元素数组 [3,1,3,2,1] -> [1,1,2,3,3]", Arrays.equals(a4, new int[] { 1,
// 1, 2, 3, 3 }));

// // 情况 5：空数组（一个元素都没有，长度为 0）。
// // 这是"边界情况"：程序遇到极端情况也不应该出错。
// // 此时 n = 0，n - 1 = -1，外层循环条件 i < -1 一开始就不成立，循环一次都不执行，不会出错。
// int[] a5 = new int[0];
// bubbleSort(a5);
// check("空数组 [] 保持不变", Arrays.equals(a5, new int[] {}));

// // 情况 6：只有一个元素的数组（另一个边界情况）。
// // n = 1，n - 1 = 0，外层循环条件 i < 0 也不成立，同样不会执行，直接保持原样。
// int[] a6 = { 7 };
// bubbleSort(a6);
// check("单元素数组 [7] 保持不变", Arrays.equals(a6, new int[] { 7 }));

// // ==================== 六. 二分查找：跟踪过程 ====================
// // sortedArr 是一个已经从小到大排好序的数组，满足二分查找的前提条件
// int[] sortedArr = { 2, 5, 8, 12, 16, 23, 38, 56 };
// System.out.println("========== 六.二分查找：跟踪查找16 / 2 / 20 ==========");

// // 【修复点】原代码这里调用的是 binarySearch（不会打印过程），所以什么都看不到。
// // 改成调用 binarySearchTrace，才能看到每一轮 low / high / mid 的变化。
// // （方法有返回值，但这里只关心打印出来的过程，所以不用把返回值存起来，直接调用即可。）
// System.out.println("查找 16:"); // 目标在中间偏右的位置
// binarySearchTrace(sortedArr, 16);
// System.out.println("查找 2:"); // 目标是第一个元素（最左边）
// binarySearchTrace(sortedArr, 2);
// System.out.println("查找 20:"); // 目标不在数组里，演示"找不到"的过程
// binarySearchTrace(sortedArr, 20);

// // ==================== 七. 查找测试 ====================
// System.out.println("========== 七.查找测试 ==========");

// int[] dup = { 1, 2, 2, 2, 3 }; // 有重复值 2 的数组，用来测试"重复元素"的情况

// // ---- 线性查找测试 ----
// // check 的第二个参数是一个判断式，比如 linearSearch(sortedArr, 16) == 4：
// // 先执行 linearSearch 得到返回值，再判断它是否等于 4，结果是 true 或 false。
// check("线性查找 16 -> 下标 4", linearSearch(sortedArr, 16) == 4);
// check("线性查找 20 -> -1 (没找到) ", linearSearch(sortedArr, 20) == -1);
// // 线性查找从左往右看，遇到第一个 2 就返回，所以是下标 1
// check("线性查找 重复值 2 -> 第一个，下标 1", linearSearch(dup, 2) == 1);

// // ---- 二分查找测试 ----
// check("二分查找 16 -> 下标 4", binarySearch(sortedArr, 16) == 4);
// check("二分查找 2 -> 下标 0 (第一个元素) ", binarySearch(sortedArr, 2) == 0); //
// 边界：目标在最左边
// check("二分查找 56 -> 下标 7 (最后一个元素) ", binarySearch(sortedArr, 56) == 7); //
// 边界：目标在最右边
// check("二分查找 空数组 -> -1", binarySearch(new int[0], 5) == -1); // 边界：空数组
// check("二分查找 单元素 [7] 找 7 -> 0", binarySearch(new int[] { 7 }, 7) == 0); //
// 边界：只有一个元素，找得到
// check("二分查找 单元素 [7] 找 5 -> -1", binarySearch(new int[] { 7 }, 5) == -1); //
// 边界：只有一个元素，找不到

// // 有重复值时，二分查找可能返回其中任意一个 2 的下标（取决于 mid 刚好落在哪个上），
// // 所以不能要求它一定等于某个固定的下标，只能检查"落在 1 到 3 之间"（三个 2 所在的位置）。
// // && 表示"并且"：两个条件必须同时成立，结果才是 true。
// int idx = binarySearch(dup, 2);
// check("二分查找 重复值 2 -> 下标在 1 到 3 之间", idx >= 1 && idx <= 3);

// // ---- 综合测试：先排序，再二分查找 ----
// // unsorted 是乱序的，不能直接二分查找，所以先用冒泡排序把它排好
// int[] unsorted = { 23, 5, 56, 2, 38, 12, 8, 16 };
// bubbleSort(unsorted);
// check("先冒泡排序 结果等于sortedArr", Arrays.equals(unsorted, sortedArr));
// check("排序后再二分查找 16 -> 下标 4", binarySearch(unsorted, 16) == 4);
// }
// }
