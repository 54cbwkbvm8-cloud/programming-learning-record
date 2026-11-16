public class ToStringPredictDemo {
    public static void main(String[] args) {
        RawStudent raw = new RawStudent("Tom", 90);
        System.out.println(raw);
        Student stu = new Student("Tom", 90);
        System.out.println(stu);
        System.out.println("字符串拼接也会自动调用 toString(): " + stu);
        System.out.println("手动调用 raw.toString(): " + raw.toString());
    }
}
        // /**
        // * ToStringPredictDemo —— "预测输出"练习：对象直接打印时，到底会显示什么？
        // *
        // * 【建议的用法】
        // * 先不要运行程序，看着代码，在纸上写下你预测的 4 行输出，然后再运行，对照预期。
        // * 这样比直接看答案记得牢。
        // *
        // * 【核心知识：println(对象) 和字符串拼接，会自动调用 toString()】
        // * · 直接 System.out.println(对象) 时，Java 会先调用这个对象的 toString() 方法，
        // * 得到一段文字后再打印出来。
        // * · 把对象用 + 和文字拼接时（"文字" + 对象），Java 同样会自动调用它的 toString()。
        // * · 如果类里没有重写 toString，用的就是 Object 提供的默认版本，
        // * 返回"类名@十六进制数字"，看不出对象里存了什么；
        // * 重写之后，就可以显示成我们想要的样子。
        // *
        // * 【运行前提】
        // * 这个程序用到 RawStudent 和 Student 两个类，三个文件要放在同一个文件夹里一起编译：
        // * javac ToStringPredictDemo.java RawStudent.java Student.java
        // *
        // * 【检查结果】
        // * 这份代码没有错误，所以【逻辑一行都没有改】，只补充了详细注释和每一行的预期输出。
        // */
        // public class ToStringPredictDemo {

        // // main 是程序的入口，运行程序时从这里开始一行一行往下执行
        // public static void main(String[] args) {

        // // ---------- 第 1 组：没有重写 toString 的 RawStudent ----------
        // // new RawStudent("Tom", 90)：按图纸创建一个对象，把 "Tom" 和 90 交给构造方法。
        // RawStudent raw = new RawStudent("Tom", 90);

        // // 预期输出：RawStudent@15db9742 （@ 后面的十六进制数字，每台电脑、每次运行都可能不一样）
        // // 组成：类名 + "@" + 对象哈希码（可以理解成对象的"数字指纹"）的十六进制形式。
        // // 看不出里面存的是 Tom 和 90，这就是默认 toString 的不足。
        // System.out.println(raw);

        // // ---------- 第 2 组：重写了 toString 的 Student ----------
        // Student stu = new Student("Tom", 90);

        // // 预期输出：Student{name='Tom', score=90}
        // // println(stu) 自动调用了 Student 里重写的 toString()，所以内容一目了然。
        // System.out.println(stu);

        // // ---------- 第 3 组：字符串拼接也会自动调用 toString ----------
        // // "文字" + stu：Java 看到 + 的一边是文字，另一边是对象，
        // // 就会自动调用 stu.toString() 把对象变成文字，再拼接起来。
        // // 预期输出：字符串拼接也会自动调用 toString(): Student{name='Tom', score=90}
        // System.out.println("字符串拼接也会自动调用 toString(): " + stu);

        // // ---------- 第 4 组：手动调用 toString ----------
        // // 自动调用只是帮我们省了不写 .toString()，手动调用得到的结果完全一样。
        // // raw 没有重写 toString，所以得到的还是默认的格式，并且和第 1 组的输出是同一个值
        // // （因为是同一个对象，哈希码不变）。
        // // 预期输出：手动调用 raw.toString(): RawStudent@15db9742 （数字和第 1 组完全相同）
        // System.out.println("手动调用 raw.toString(): " + raw.toString());
        // }
        // } 
        // 
