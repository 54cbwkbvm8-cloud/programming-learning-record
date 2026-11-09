import java.util.Arrays;

public class PassByValue {
    public static void changeInt(int x) {
        x = 100;
        System.out.println("x in changeInt: " + x);
    }

    public static void changeArray(int[] arr) {
        arr[0] = 100;
    }

    public static void reassignArray(int[] arr) {
        arr = new int[] { 9, 9, 9 };
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public static void main(String[] args) {
        int num = 5;
        changeInt(num);
        System.out.println("调用后 num = " + num);
        int[] data = { 1, 2, 3 };
        changeArray(data);
        System.out.println("changeArray 后 data = " + Arrays.toString(data));
        reassignArray(data);
        System.out.println("reassignArray 后 data = " + Arrays.toString(data));
        int x = 1, y = 2;
        swap(x, y);
        System.out.println("swap 后 x = " + x + ", y = " + y);
    }
}
// // 导入 Arrays 工具类：下面要用 Arrays.toString(数组) 把数组的内容变成方便阅读的文字，如 [1, 2, 3]。
// // （如果直接打印数组本身，看到的只是一串"类型+地址"的编码，看不懂。）
// import java.util.Arrays;

// /**
// * PassByValue —— 演示 Java 的"值传递"：调用方法时，传进去的参数到底是不是原来的那个变量？
// *
// * 【一句话结论】
// * Java 里传参【永远是"值传递"】：调用方法时，会把"值"复制一份交给方法里的参数。
// * 方法里改的是这份【复制品】，原来的变量不受影响。
// * 区别只在于"被复制的这个值"是什么：
// * · 基本类型（int、double 等）：复制的是"数字本身" → 方法里怎么改，外面都不会变
// * · 数组（引用类型） ：复制的是"数组的地址（门牌号）" → 通过地址能找到同一个数组，
// * 所以能改到数组里的内容；但如果让参数指向别的新数组，外面不受影响
// *
// * 【检查结果】
// * 这份代码没有错误。程序运行后会发现："有的修改生效了，有的没有生效"，这正是要演示的现象，
// * 并不是 bug。所以【逻辑一行都没有改】，只补充了详细注释，并在注释里标出了每一步的预期结果。
// *
// * 【术语】
// * 形参：方法定义里括号中的变量（例如 changeInt(int x) 里的 x）
// * 实参：调用方法时实际传进去的值（例如 changeInt(num) 里的 num）
// * 传参时，实参的值被复制给形参。
// */
// public class PassByValue {

// // ============================================================
// // 演示 1：基本类型（int）作为参数
// // ============================================================
// public static void changeInt(int x) {
// // 这里的 x 是调用者传进来的数字的【一份复制品】，是一个独立的新变量。
// // 把它改成 100，只是改了这份复制品，和外面原来的变量没有任何关系。
// x = 100;
// System.out.println("x in changeInt: " + x); // 方法内部看到的 x 确实是 100
// }

// // ============================================================
// // 演示 2：数组作为参数，并且【修改数组里的元素】
// // ============================================================
// public static void changeArray(int[] arr) {
// // 传进来的 arr 复制的是"数组的地址"，它和外面的 data 指向【同一个数组】
// // （好比：你把家里的门牌号抄给朋友，朋友按地址来，进的是同一间房）。
// // 所以通过 arr[0] 修改元素，改的就是那个唯一的数组，外面也能看到变化。
// arr[0] = 100;
// }

// // ============================================================
// // 演示 3：数组作为参数，但是让参数【指向一个新数组】
// // ============================================================
// public static void reassignArray(int[] arr) {
// // new int[] { 9, 9, 9 } 创建了一个全新的数组，然后让 arr 这个"形参"指向新数组。
// // 注意：这只是让方法里的这份"地址复制品"改指向了新地方，
// // 外面的 data 里存的地址完全没变，仍然指向原来的数组。
// // 好比：朋友把手里抄的门牌号涂掉，换成了另一个地址，并不会改变你家的门牌号。
// // 所以这个方法对外面没有任何影响。
// arr = new int[] { 9, 9, 9 };
// }

// // ============================================================
// // 演示 4：想用方法交换两个数——这样写是【做不到】的
// // ============================================================
// public static void swap(int a, int b) {
// // 这里的 a、b 是两个数字的复制品。交换的只是复制品，外面的原变量纹丝不动。
// // 交换需要借助一个临时变量 temp：先把 a 的值存起来，防止被覆盖丢失。
// int temp = a;
// a = b;
// b = temp;
// // 方法结束，a、b、temp 这几个局部变量全部消失，什么都没留下。
// }

// // main 是程序入口，从这里开始一行一行往下执行
// public static void main(String[] args) {

// // ---------- 演示 1 ----------
// int num = 5;
// changeInt(num); // 把 num 的值 5 复制给 changeInt 的参数 x
// // 预期输出：num = 5。 虽然 changeInt 里把 x 改成了 100，但 num 没有被影响。
// System.out.println("调用后 num = " + num);

// // ---------- 演示 2 ----------
// int[] data = { 1, 2, 3 };
// changeArray(data); // 传进去的是 data 的地址，方法里通过地址改了数组的第 0 个元素
// // 预期输出：[100, 2, 3]。数组内容被改了！
// System.out.println("changeArray 后 data = " + Arrays.toString(data));

// // ---------- 演示 3 ----------
// reassignArray(data); // 方法里让形参指向了新数组，但 data 本身没变
// // 预期输出：还是 [100, 2, 3]，并不是 [9, 9, 9]。
// System.out.println("reassignArray 后 data = " + Arrays.toString(data));

// // ---------- 演示 4 ----------
// // int x = 1, y = 2; 是在一行里同时定义两个 int 变量，等价于分成两行写：
// // int x = 1;
// // int y = 2;
// // （这里的 x 和上面 changeInt 里的参数 x 虽然同名，但它们在不同的方法里，互不干扰。）
// int x = 1, y = 2;
// swap(x, y); // 传进去的是 1 和 2 的复制品
// // 预期输出：x = 1, y = 2，没有被交换！
// System.out.println("swap 后 x = " + x + ", y = " + y);

// // 小结：想真正做到"交换"，不能靠这样的方法去改基本类型的参数。
// // 常见做法有：直接在原地写三行交换代码；或者把两个数放进数组里，对数组元素进行交换。
// }
// }
