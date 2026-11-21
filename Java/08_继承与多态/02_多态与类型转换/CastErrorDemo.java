public class CastErrorDemo {
    public static void main(String[] args) {
        Person p = new Teacher("Tom", "java");

        System.out.println("p 指向的真实对象类型：" + p.getClass().getName());

        Student s = (Student) p;

        System.out.println("这行不会被执行到：" + s.getName());
    }

    static class Person {
        private final String name;

        Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Teacher extends Person {
        private final String subject;

        Teacher(String name, String subject) {
            super(name);
            this.subject = subject;
        }

        public String getSubject() {
            return subject;
        }
    }

    static class Student extends Person {
        Student(String name) {
            super(name);
        }
    }
}
// /**
// * CastErrorDemo —— 演示"向下转型"出错时的 ClassCastException（类型转换异常）
// *
// * 【场景】
// * 有三个类：Person（人）是父类，Teacher（老师）和 Student（学生）都继承 Person。
// * 老师和学生是"兄弟"关系：它们都是 Person，但老师不是学生，学生也不是老师。
// * 程序先创建了一个老师对象，用 Person 变量保存（向上转型，没问题）；
// * 然后强行把它转换成 Student（向下转型），Java 运行时发现它真身是 Teacher，不是 Student，
// * 于是抛出 ClassCastException，程序当场停止。
// *
// * 【这个程序会故意崩溃】
// * 运行后你会先看到一行正常的输出，然后出现红色的错误信息：
// * Exception in thread "main" java.lang.ClassCastException: class
// CastErrorDemo$Teacher cannot be cast to class CastErrorDemo$Student
// * 最后一行 "这行不会被执行到" 也确实不会被执行，因为程序已经在上一行崩溃停止了。
// * 这是演示用的预期结果，不是程序有 bug。正常的程序里应该避免出现这种崩溃。
// *
// * 【为什么编译能通过，运行才报错】
// * 编译器只看变量的"声明类型"：p 是 Person 类型，而 Student 是 Person 的子类，
// * 所以"把 Person 转成 Student"在语法上是可能成立的，编译器会放行。
// * 但 p 实际指向的对象是什么，只有运行起来才知道。运行到这一行时，Java 发现真身是 Teacher，
// * 不是 Student，就报错了。
// *
// * 【怎么避免】
// * 向下转型之前，先用 instanceof 检查真身，是目标类型才转换，例如：
// * if (p instanceof Student) {
// * Student s = (Student) p;
// * }
// * （完整的写法可以看 PolymorphismDemo.java 里第 3 部分。）
// *
// * 【检查结果】
// * 这份代码没有错误，所以【逻辑一行都没有改】，只补充了详细注释。
// * 这个文件把 Person、Teacher、Student 都写成了 CastErrorDemo 里面的"静态内部类"（见下面），
// * 所以它不依赖别的文件，也不会和别的练习里同名的类冲突，单独就能编译运行。
// */
// public class CastErrorDemo {

// // main 是程序的入口：运行程序时，Java 从这里的第一行开始，一行一行往下执行。
// public static void main(String[] args) {

// // 向上转型：创建一个老师对象，交给 Person 类型的变量保管。这一步是自动的，不会出错。
// // 老师"是一个"人，所以可以这样放。
// Person p = new Teacher("Tom", "java");

// // getClass().getName() 取出对象"真正的类型"的名字。
// // 输出里出现的 $ 符号表示：这是 CastErrorDemo 里面的"内部类"，
// // 完整名字写成 外部类名$内部类名，所以显示为 CastErrorDemo$Teacher。
// // 这行证明了：p 的真身是 Teacher。
// System.out.println("p 指向的真实对象类型：" + p.getClass().getName());

// // 向下转型：(Student) p 的意思是"请把 p 当作 Student 来用"。
// // 但 p 的真身是 Teacher，不是 Student，所以运行到这一行时会抛出 ClassCastException，程序在这里停止。
// // 异常发生后，下面这一行不会执行，变量 s 也不会被创建出来。
// Student s = (Student) p;

// System.out.println("这行不会被执行到：" + s.getName());
// }

// // ============================================================
// // 下面三个类都写在 CastErrorDemo 的里面，叫"内部类"。
// // 前面加了 static，叫"静态内部类"，意思是它们不依赖 CastErrorDemo 的某个对象就能使用，
// // 可以直接 new Person(...)。
// //
// // 为什么要这样写？注释里原话是：补齐演示所需的类型，避免依赖外部文件。
// // 这样这个文件单独就能运行，不用再放进一堆别的文件里。
// // ============================================================

// // 父类：人
// static class Person {

// // final：最终的，赋值之后不能再修改。name 在构造方法里赋值一次后就固定了。
// private final String name;

// // 构造方法：创建 Person 时自动执行，名字和类名相同，没有返回值类型。
// // 这里没有写 public，表示只有同一个包里的代码才能调用，对于这个演示来说足够了。
// Person(String name) {
// this.name = name; // this.name 表示"这个对象自己的成员变量 name"，用来区分同名的参数
// }

// public String getName() {
// return name;
// }
// }

// // 子类：老师。extends Person 表示继承 Person。
// static class Teacher extends Person {

// private final String subject; // 所教科目（Teacher 新增的属性）

// Teacher(String name, String subject) {
// // 子类构造方法的第一行，必须先调用父类的构造方法：super(...)。
// // 因为要先把"父类的部分"（name）初始化好。
// super(name);
// this.subject = subject;
// }

// public String getSubject() {
// return subject;
// }
// }

// // 子类：学生。和 Teacher 一样继承 Person，但两者互相没有关系。
// static class Student extends Person {

// Student(String name) {
// super(name);
// }
// }
// }
