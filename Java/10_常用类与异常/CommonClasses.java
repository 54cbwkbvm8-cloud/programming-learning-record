import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;
public class CommonClasses {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("max=" + Math.max(10, 20));
        System.out.println("pow=" + Math.pow(2, 3));
        System.out.println("sqrt=" + Math.sqrt(9));
        System.out.println("ceil=" + Math.ceil(3.14));
        System.out.println("floor=" + Math.floor(3.14));
        System.out.println("round=" + Math.round(3.14));
        System.out.println("mathRandom=" + Math.random());
        String[] names = { "小明", "小红", "小刚" };
        Random random = new Random();
        System.out.println("name=" + names[random.nextInt(names.length)]);
        int data[] = { 8, 3, 5, 1 };
        Arrays.sort(data);
        System.out.println("data=" + Arrays.toString(data));
        System.out.println("found=" + Arrays.binarySearch(data, 5));
        System.out.println(Arrays.binarySearch(data, 4));
        System.out.println("manual=" + manualSearch(data, 5));
        int[] copy = Arrays.copyOf(data, data.length);
        System.out.println("equal=" + Arrays.equals(data, copy));
        Arrays.fill(copy, 0);
        System.out.println("filled=" + Arrays.toString(copy));
        LocalDate today = LocalDate.now();
        LocalDate target = LocalDate.of(2020, 12, 15);
        long days = ChronoUnit.DAYS.between(today, target);
        System.out.println("days=" + days);
        System.out.println("millis=" + System.currentTimeMillis());
    }
    // 手动查找：返回目标值下标，找不到返回 -1
    public static int manualSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}

// // import 导入 java.time.LocalDate，即LocalDate 不含时间的日期类。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.time.LocalDate;
// // import 导入 java.time.temporal.ChronoUnit，即ChronoUnit 日期时间单位枚举，可计算间隔。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.time.temporal.ChronoUnit;
// // import 导入 java.util.Arrays，即Arrays 数组工具类，提供排序、查找等静态方法。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.Arrays;
// // import 导入 java.util.Random，即Random 伪随机数生成器类。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.Random;
// // public class 定义公开类 CommonClasses；类是组织字段与方法的容器。
// // 公开类名必须与 .java 文件名一致；左大括号开启类体。
// public class CommonClasses {
// // main 是 Java 程序的启动入口；public 允许 JVM 调用，static 表示无需创建类实例，void 表示不返回值。
// // String[] args 是命令行参数字符串数组，左大括号开始方法体。
// public static void main(String[] args) {
// // 无参数 println 只输出一个换行，用来在演示开头留空行。
// System.out.println();
// // Math.max 是静态方法，比较两个整数，返回较大的 20；不需要 new Math。
// System.out.println("max=" + Math.max(10, 20));
// // Math.pow(2,3) 计算 2 的三次方，返回 double 8.0；逗号分隔底数和指数。
// System.out.println("pow=" + Math.pow(2, 3));
// // Math.sqrt(9) 计算非负数平方根，返回 double 3.0；sqrt 是 square root 的缩写。
// System.out.println("sqrt=" + Math.sqrt(9));
// // ceil 向正无穷取整，3.14 得到 4.0；不是一般四舍五入。
// System.out.println("ceil=" + Math.ceil(3.14));
// // floor 向负无穷取整，3.14 得到 3.0；对于负数与直接截断不同。
// System.out.println("floor=" + Math.floor(3.14));
// // round 对 double 进行最接近整数的舍入，平局向正无穷方向，返回 long 3。
// System.out.println("round=" + Math.round(3.14));
// // Math.random 产生大于等于 0、小于 1 的伪随机 double；每次执行结果可能不同。
// System.out.println("mathRandom=" + Math.random());
// // String[] 表示字符串数组；花括号列出三个初始元素，下标 0、1、2；names.length 为 3。
// String[] names = { "小明", "小红", "小刚" };
// // Random 是伪随机数生成器类；new 创建对象，random 保存引用，后面调用 nextInt。
// Random random = new Random();
// // nextInt(names.length) 产生 0 到 2 的随机整数，将其作为数组下标；不会取到 3。
// // 数组下标越界会抛异常；这里数组固定非空。
// System.out.println("name=" + names[random.nextInt(names.length)]);
// // int data[] 与 int[] data 等价；花括号初始化四个整数，数组初始顺序为 8、3、5、1。
// int data[] = { 8, 3, 5, 1 };
// // Arrays.sort 对原数组进行升序排序，变为 1、3、5、8。
// // 二分查找要求数组预先排序。
// Arrays.sort(data);
// // Arrays.toString 将数组内容格式化为 [1, 3, 5, 8]；直接打印数组引用不会得到这种列表。
// System.out.println("data=" + Arrays.toString(data));
// // binarySearch 在有序数组中查找 5，返回其下标 2；下标不是人从 1 开始数的位置。
// System.out.println("found=" + Arrays.binarySearch(data, 5));
// // 查找不存在的 4；应插在下标 2，返回 -(插入点)-1 即 -3，并非统一返回 -1。
// System.out.println(Arrays.binarySearch(data, 4));
// // 调用自己写的 manualSearch 线性遍历，找到 5 返回 2；与二分查找结果进行比较。
// System.out.println("manual=" + manualSearch(data, 5));
// // copyOf 创建长度为 data.length 的新数组并复制元素；copy 与 data 不共享数组本体。
// int[] copy = Arrays.copyOf(data, data.length);
// // Arrays.equals 按顺序逐个比较两个数组的元素；目前内容相同，因此 true。
// System.out.println("equal=" + Arrays.equals(data, copy));
// // fill 把 copy 的全部元素改成 0；原始 data 不变，展示独立复制。
// Arrays.fill(copy, 0);
// // 输出 copy 的内容 [0, 0, 0, 0]，验证 fill。
// System.out.println("filled=" + Arrays.toString(copy));
// // LocalDate.now 按系统默认时区获取今天日期，不含时分秒；值会随运行日期变化。
// LocalDate today = LocalDate.now();
// // LocalDate.of 按年、月、日创建 2020-12-15；若日期非法会抛异常。
// LocalDate target = LocalDate.of(2020, 12, 15);
// // ChronoUnit.DAYS.between 计算从 today 到 target 的有符号天数，返回 long。
// // 若目标日期在过去则结果为负，不是错误；顺序反过来符号也反过来。
// long days = ChronoUnit.DAYS.between(today, target);
// // 输出两个日期之间的有符号天数，不固定为某个数字。
// System.out.println("days=" + days);
// // currentTimeMillis 返回自 Unix 纪元以来的毫秒数（long）；用于演示时间戳，不是今天的日期。
// System.out.println("millis=" + System.currentTimeMillis());
// // 右大括号结束「public static void main(String[] args)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 手动查找：返回目标值下标，找不到返回 -1
// // public static 声明可从外部直接调用的静态方法；int 是返回值类型，arr 是整数数组参数，target 是目标整数。
// // 本方法按顺序查找，不要求排序；调用者应提供非 null 数组。
// public static int manualSearch(int[] arr, int target) {
// // for 初始化 i=0，每轮检查 i 是否小于数组长度，再在轮末 i++；逐个访问有效下标。
// for (int i = 0; i < arr.length; i++) {
// // == 比较 arr[i] 和 target 两个整数的值；只有相等时才进入代码块。
// if (arr[i] == target) {
// // return i 立刻结束方法并把第一个匹配的下标交给调用者，不再继续查找。
// return i;
// // 右大括号结束「if (arr[i] == target)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「for (int i = 0; i < arr.length; i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 循环结束还没找到就返回 -1；合法下标从 0 开始，负数可当作未找到的标记。
// return -1;
// // 右大括号结束「public static int manualSearch(int[] arr, int target)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public class CommonClasses」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
