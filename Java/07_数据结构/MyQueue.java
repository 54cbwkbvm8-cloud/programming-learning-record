import java.util.NoSuchElementException;
public class MyQueue {
    private final int[] data;
    private int head;
    private int tail;
    private int size;
    /** 创建一个容量为 capacity 的循环队列 */
    public MyQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.data = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    /** 入队：成功返回 true，队满返回 false */
    public boolean enqueue(int value) {
        if (size == data.length) {
            return false;
        }
        data[tail] = value;
        tail = (tail + 1) % data.length;
        size++;
        return true;
    }
    /** 出队：队空抛出 NoSuchElementException */
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        int value = data[head];
        head = (head + 1) % data.length;
        size--;
        return value;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    /** 按队头到队尾的顺序返回队列内容 */
    public String snapshot() {
        StringBuilder sb = new StringBuilder("[");
        int index = head;
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(data[index]);
            index = (index + 1) % data.length;
        }
        return sb.append(']').toString();
    }
    public static void main(String[] args) {
        MyQueue q = new MyQueue(5);
        try {
            q.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("empty protected");
        }
        for (int i = 0; i < 5; i++) {
            q.enqueue(i);
        }
        System.out.println(q.snapshot());
        System.out.println("full rejected=" + !q.enqueue(6));
        System.out.println("out=" + q.dequeue());
        q.enqueue(6);
        System.out.println("second full rejected=" + !q.enqueue(7));
        System.out.println(q.snapshot());
        while (!q.isEmpty()) {
            System.out.println("out=" + q.dequeue());
        }
        System.out.println(q.snapshot());
    }
}

// // import 导入 java.util.NoSuchElementException，即NoSuchElementException 无可用元素时抛出的异常类。
// // 导入后可直接使用类的简短名称；分号结束导入语句。
// import java.util.NoSuchElementException;
// // public class 定义公开类 MyQueue；类是组织字段与方法的容器。
// // 公开类名必须与 .java 文件名一致；左大括号开启类体。
// public class MyQueue {
// // private 限制字段只能在类内部直接访问；final 保证 data 引用不能重新赋值，但元素仍可修改。
// // int[] 是存储队列元素的固定长度整数数组。
// private final int[] data;
// // head 是队头下标，指向下一次要取出的元素；不是元素的值。
// private int head;
// // tail 是下一次成功入队时的写入位置；循环队列允许它回到数组开头。
// private int tail;
// // size 保存当前有效元素数量，用于区分 head==tail 时的空队和满队。
// private int size;
// /** 创建一个容量为 capacity 的循环队列 */
// // 构造方法与类同名、没有返回类型；new MyQueue(capacity) 时执行，用容量参数初始化对象。
// public MyQueue(int capacity) {
// // <= 表示小于或等于；容量为 0 或负数不能形成有效循环数组。
// if (capacity <= 0) {
// // throw 主动抛出 IllegalArgumentException，说明调用者传了非法容量；构造过程立刻终止。
// throw new IllegalArgumentException("capacity must be positive");
// // 右大括号结束「if (capacity <= 0)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // new int[capacity] 分配指定长度的整数数组，元素自动初始化为 0；this.data 表示当前对象的字段。
// this.data = new int[capacity];
// // 把当前对象的队头下标设为 0；初始没有有效元素。
// this.head = 0;
// // 把下一次写入的下标设为 0；第一次入队写入数组开头。
// this.tail = 0;
// // size=0 表示初始为空；不能通过数组元素是否为 0 来判断队列空满，因为 0 也可入队。
// this.size = 0;
// // 右大括号结束「public MyQueue(int capacity)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** 入队：成功返回 true，队满返回 false */
// // public 方法允许外部调用；boolean 返回是否入队成功，value 是待存入的整数。
// public boolean enqueue(int value) {
// // 如果 size 与数组长度相等，表示所有槽位都被占用，必须拒绝写入避免覆盖旧数据。
// if (size == data.length) {
// // 返回 false 表示入队失败；执行到这里不改变任何队列状态。
// return false;
// // 右大括号结束「if (size == data.length)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // data[tail] 将 value 存到当前队尾写入位置，方括号表示数组下标。
// data[tail] = value;
// // % 取余；tail 加 1 后对容量取余，到末尾自动回到 0，保持有效下标。
// tail = (tail + 1) % data.length;
// // size++ 增加有效元素数量，与成功写入对应。
// size++;
// // 返回 true 表明本次成功入队。
// return true;
// // 右大括号结束「public boolean enqueue(int value)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** 出队：队空抛出 NoSuchElementException */
// // int 表示出队返回一个整数；dequeue 没有参数，因为总是移除最早进入的有效元素。
// public int dequeue() {
// // 调用 isEmpty 判断队列是否没有元素；空队列不能读取旧槽位。
// if (isEmpty()) {
// // 抛出 NoSuchElementException 而不是返回 0 等可能是合法数据的值。
// throw new NoSuchElementException("queue is empty");
// // 右大括号结束「if (isEmpty())」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 先把队头元素复制到局部变量 value，后面移动 head 时仍能返回原数值。
// int value = data[head];
// // head 加 1 再对数组长度取余，得到下一次出队位置；到末尾循环到 0。
// head = (head + 1) % data.length;
// // size-- 减少有效元素数；不必擦除旧槽位，后续只根据 head 和 size 访问有效数据。
// size--;
// // return value 把原队头整数返回调用者，结束出队方法。
// return value;
// // 右大括号结束「public int dequeue()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // isEmpty 是无参数查询方法，返回布尔值，不修改队列。
// public boolean isEmpty() {
// // size == 0 比较有效元素数量是否为 0，直接返回比较结果。
// return size == 0;
// // 右大括号结束「public boolean isEmpty()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // size() 是公开查询方法；同名字段 size 不带括号，是内部保存的整数。
// public int size() {
// // 返回当前有效元素数量而非数组总容量，不改变队列状态。
// return size;
// // 右大括号结束「public int size()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// /** 按队头到队尾的顺序返回队列内容 */
// // snapshot 返回按队头到队尾排列的文本，不改变任何队列字段。
// public String snapshot() {
// // 用 StringBuilder 创建包含左方括号的可修改文本，方便循环拼接。
// StringBuilder sb = new StringBuilder("[");
// // int index = head 建立独立的遍历下标，保存当前队头位置，不修改队列的 head。
// int index = head;
// // for 从 i=0 开始，i<size 时继续，每轮 i++；只访问 size 个有效元素，避免遍历无效槽位。
// for (int i = 0; i < size; i++) {
// // i>0 说明不是首个元素；只有第二个及以后的元素前才加分隔符。
// if (i > 0) {
// // append 在文本末尾追加逗号和空格，避免开头出现多余逗号。
// sb.append(", ");
// // 右大括号结束「if (i > 0)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // data[index] 访问当前有效槽位；append 将该整数的文字表示追加到 builder。
// sb.append(data[index]);
// // index 加一再对数组长度取余，到末尾回到 0；避免 head+i 在极大容量下发生 int 溢出，不改变真实队头。
// index = (index + 1) % data.length;
// // 右大括号结束「for (int i = 0; i < size; i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // append(']') 添加右方括号，toString 返回普通字符串；空队得到 []。
// return sb.append(']').toString();
// // 右大括号结束「public String snapshot()」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // main 是 Java 程序的启动入口；public 允许 JVM 调用，static 表示无需创建类实例，void 表示不返回值。
// // String[] args 是命令行参数字符串数组，左大括号开始方法体。
// public static void main(String[] args) {
// // 创建容量为 5 的队列对象，q 保存引用；所有队列字段属于这个对象。
// MyQueue q = new MyQueue(5);
// // try 开启受保护的代码块；其中出现匹配异常时会跳到后续 catch，而不是继续执行剩余语句。
// try {
// // 在空队列上调用 dequeue，故意触发空队异常以验证防护。
// q.dequeue();
// // 右大括号结束 try 块；catch 捕获 NoSuchElementException 类型的异常，e 是异常对象引用。
// // 只有匹配异常时进入此处理块；正常路径跳过 catch。
// } catch (NoSuchElementException e) {
// // 捕获空队异常后输出 empty protected，说明程序没有直接崩溃。
// System.out.println("empty protected");
// // 右大括号结束「catch (NoSuchElementException e)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // i 从 0 到 4 循环五次，恰好填满容量为 5 的队列。
// for (int i = 0; i < 5; i++) {
// // 每轮将当前 i 入队；这五次都成功，依次加入 0、1、2、3、4。
// q.enqueue(i);
// // 右大括号结束「for (int i = 0; i < 5; i++)」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 输出 snapshot，队头到队尾应是 [0, 1, 2, 3, 4]。
// System.out.println(q.snapshot());
// // 尝试向满队加入 6；! 对 false 取反得到 true，证明拒绝了多余元素。
// System.out.println("full rejected=" + !q.enqueue(6));
// // 调用 dequeue 取出最早的 0，并拼接到输出中；此时腾出一个槽位。
// System.out.println("out=" + q.dequeue());
// // 把 6 加入刚释放的槽位，tail 发生环绕；逻辑顺序变成 1、2、3、4、6。
// q.enqueue(6);
// // 再尝试加入 7，此时已满；输出取反后的布尔值验证拒绝，而不是忽略返回值。
// System.out.println("second full rejected=" + !q.enqueue(7));
// // 输出当前逻辑顺序，注意数组物理顺序与队列逻辑顺序不同。
// System.out.println(q.snapshot());
// // ! 对 isEmpty 的结果取反；只要队列仍有元素就继续循环。
// while (!q.isEmpty()) {
// // 每轮取出最早的有效元素并输出，size 会逐次减 1。
// System.out.println("out=" + q.dequeue());
// // 右大括号结束「while (!q.isEmpty())」的代码块；循环体结束后返回循环条件（for 先执行更新表达式），或在条件失败时继续后面的代码。
// }
// // 所有元素出队后打印 []，验证空队状态恢复。
// System.out.println(q.snapshot());
// // 右大括号结束「public static void main(String[] args)」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
// // 右大括号结束「public class MyQueue」的代码块；此处离开该块，局部变量不再在块外可见（若有）；后续按外层控制流继续。
// }
