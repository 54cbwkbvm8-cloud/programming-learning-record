public class EqualsDemo {
    public static void main(String[] args) {
        int x = 5;
        int y = 5;
        System.out.println("x == y : " + (x == y));
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        System.out.println("s1 == s2 : " + (s1 == s2));
        System.out.println("s1 == s3 : " + (s1 == s3));
        System.out.println("s1.equals(s3) : " + (s1.equals(s3)));
        Student a = new Student("Tome", 90);
        Student b = new Student("Tome", 90);
        Student c = a;
        System.out.println("a == b : " + (a == b));
        System.out.println("a.equals(b) : " + (a.equals(b)));
        System.out.println("a == c : " + (a == c));
        RawStudent r1 = new RawStudent("Tome", 90);
        RawStudent r2 = new RawStudent("Tome", 90);
        System.out.println("r1 == r2 : " + (r1 == r2));
        System.out.println("r1.equals(r2) : " + (r1.equals(r2)));
    }
}

// /**
//  * EqualsDemo —— "==" 和 "equals" 的区别
//  *
//  * 【最重要的结论，先记住】
//  * · 基本类型（int、double、char、boolean 等）：用 == 比较，比的是【值】是否相等。
//  * · 对象类型（String、Student 等）：用 == 比较，比的是【地址】是否相同，也就是"是不是同一个对象"；
//  * 想比较【内容】是否相同，要用 equals 方法。
//  * · equals 到底怎么比，取决于这个类有没有【重写】equals：
//  * - 没有重写（如 RawStudent）：用的是 Object 默认的 equals，效果等同于 ==，比的还是地址
//  * - 重写了（如 String、Student）：按重写的规则比较内容
//  *
//  * 【运行前提】
//  * 这个程序用到 Student 和 RawStudent，三个文件要放在同一个文件夹里一起编译：
//  * javac EqualsDemo.java Student.java RawStudent.java
//  *
//  * 【检查结果】
//  * 这份代码没有错误，所以【逻辑一行都没有改】，只补充了详细注释和每一行的预期输出与原因。
//  */
// public class EqualsDemo {

//     // main 是程序的入口，运行程序时从这里开始一行一行往下执行
//     public static void main(String[] args) {

//         // ==================== 第 1 组：基本类型 ====================
//         int x = 5;
//         int y = 5;
//         // 基本类型的变量里直接存放的就是数值，== 比的就是数值。
//         System.out.println("x == y : " + (x == y)); // true（5 等于 5）
//         // 加括号 (x == y) 是为了让 Java 先算出比较结果（true 或 false），再拼接到文字后面。

//         // ==================== 第 2 组：String 字符串 ====================
//         // 下面三个变量内容都是 "abc"，但创建方式不同：
//         String s1 = "abc"; // 直接写文字常量
//         String s2 = "abc"; // 同样直接写文字常量
//         String s3 = new String("abc"); // 用 new 明确要求"新建一个对象"

//         // 【字符串常量池】Java 为了节省内存，对于直接写出来的文字常量（"abc"），
//         // 会放在一个叫"常量池"的地方，相同内容只保存一份，需要时共用。
//         // 所以 s1 和 s2 指向的是【同一个对象】。
//         // 而 new String("abc") 强制新建了一个全新的对象，s3 指向的是另一块内存。
//         //
//         // s1 ----+
//         // +---> [ "abc" ] （常量池里的那一个）
//         // s2 ----+
//         // s3 -------> [ "abc" ] （new 出来的另一个，内容相同，但不是同一个对象）
//         System.out.println("s1 == s2 : " + (s1 == s2)); // true：同一个对象（地址相同）
//         System.out.println("s1 == s3 : " + (s1 == s3)); // false：不是同一个对象（地址不同）
//         // String 重写了 equals，比较的是内容：内容都是 abc，所以相等。
//         System.out.println("s1.equals(s3) : " + (s1.equals(s3))); // true
//         // 实际开发中，比较字符串内容【一律用 equals，不要用 ==】，
//         // 因为 == 的结果取决于字符串是怎么创建的，很容易出现难以发现的错误。

//         // ==================== 第 3 组：重写了 equals 的 Student ====================
//         Student a = new Student("Tome", 90); // 对象 1
//         Student b = new Student("Tome", 90); // 对象 2：内容和对象 1 完全相同，但是另一个独立创建的对象
//         Student c = a; // c 只是 a 的"另一个名字"：把 a 里的地址复制给了 c，没有创建新对象

//         // a ----+
//         // +---> [name=Tome, score=90] （对象 1）
//         // c ----+
//         // b -------> [name=Tome, score=90] （对象 2）
//         System.out.println("a == b : " + (a == b)); // false：是两个不同的对象
//         // Student 重写了 equals：姓名和成绩都相同就算相等，所以结果是 true。
//         System.out.println("a.equals(b) : " + (a.equals(b))); // true
//         System.out.println("a == c : " + (a == c)); // true：a 和 c 指向同一个对象

//         // ==================== 第 4 组：没有重写 equals 的 RawStudent ====================
//         RawStudent r1 = new RawStudent("Tome", 90);
//         RawStudent r2 = new RawStudent("Tome", 90); // 内容和 r1 一样，但是另一个对象
//         System.out.println("r1 == r2 : " + (r1 == r2)); // false：不是同一个对象
//         // RawStudent 没有重写 equals，用的是 Object 默认的 equals，效果和 == 一样（比较地址），
//         // 所以即使内容完全相同，结果也是 false。
//         // 这正好和上面第 3 组形成对比：同样的数据，Student 的 equals 是 true，RawStudent 的是 false。
//         System.out.println("r1.equals(r2) : " + (r1.equals(r2))); // false
//     }
// }



