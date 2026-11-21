public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println(" [Person 构造方法] 正在创建 Person, name = " + name);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void introduce() {
        System.out.println("Hello, my name is " + name + ", I am " + age + " years old.");
    }

    public void sayHello() {
        System.out.println("Hello, my name is " + name);
    }

    public void sayHello(String friendName) {
        System.out.println(name + " says hello to " + friendName + "!");
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}
// /**
// * Student —— "学生"类（子类）：继承 Person，增加学号和成绩
// *
// * 【什么是继承】
// * class Student extends Person 表示 Student "继承"了 Person：
// * Person 里有的 getName()、getAge()、sayHello()、introduce() 等，Student
// 里不用重新写，就能直接使用。
// * Student 还可以增加自己特有的属性（学号 studentId、成绩 score），
// * 或者把父类的方法"重写"成自己的版本（比如 introduce）。
// *
// * 【本次修复的问题】
// * 你上传的文件名是 Student_fc4328.java，但里面写的是 public class Student。
// * Java 有一条硬性规定：被 public 修饰的类，文件名必须和类名完全一致（区分大小写）。
// * 所以直接编译会报错：
// * class Student is public, should be declared in a file named Student.java
// * 修复：文件名必须是 Student.java（已按这个名字保存）。
// * 另外把构造方法里打印的文字 "正在创建Student" 补上了一个空格，和 Person 里的 "正在创建 Person" 写法一致。
// * （其余代码没有问题，没有改动。）
// *
// * 【运行前提】
// * 这个类继承了 Person，所以必须和 Person.java 放在同一个文件夹里一起编译：
// * javac Person.java Student.java
// * 它没有 main 方法，不能单独运行。
// */
// public class Student extends Person {

// // 子类自己新增的属性（父类里没有的）
// private String studentId; // 学号
// private double score; // 成绩（double 可以带小数点，比如 92.5）

// // ============================================================
// // 子类的构造方法
// //
// // 【规则】子类构造方法的第一行，必须先调用父类的构造方法，写法是 super(...)。
// // 原因：子类对象里包含了一个"父类的部分"（name、age），必须先把它准备好，才能在它的基础上准备子类自己的部分。
// // 这里 super(name, age) 把 name 和 age 交给父类 Person 的构造方法去处理。
// //
// // 所以 new Student(...) 的执行顺序是：
// // ① 进入 Student 的构造方法
// // ② 第一行 super(name, age) 去执行 Person 的构造方法（打印 [Person 构造方法]）
// // ③ Person 的构造方法执行完，回来继续执行下面的几行（赋值 studentId 和 score，打印 [Student 构造方法]）
// //
// // 如果没有写 super(...)，Java 会自动在第一行补上 super()，也就是调用父类的"无参构造方法"。
// // 而 Person 只有一个带两个参数的构造方法，没有无参的，
// // 这时自动补上的 super() 就会找不到可用的构造方法，编译报错。所以这里必须手动写出 super(name, age)。
// // ============================================================
// public Student(String name, int age, String studentId, double score) {
// super(name, age); // 调用父类 Person 的构造方法（必须是第一行）
// this.studentId = studentId;
// this.score = score;
// // 【修复点】原来是 "正在创建Student"，"创建"和"Student"之间少了一个空格，已补上。
// System.out.println(" [Student 构造方法] 正在创建 Student，studentId = " + studentId);
// }

// // ============================================================
// // 重写父类的 introduce()
// //
// // 重写：子类写一个和父类【方法名、参数列表完全相同】的方法，覆盖掉父类的版本。
// // @Override 是一个标记，意思是"我是在重写父类的方法"，写错了编译器会提醒。
// //
// // super.introduce()：super.方法名() 表示调用【父类】的版本。
// // 这里先执行父类的自我介绍（打印姓名和年龄），再补充打印学号和成绩，
// // 这样不用把父类已经写过的内容重复写一遍。
// // ============================================================
// @Override
// public void introduce() {
// super.introduce();
// System.out.println("我的学号是：" + studentId + "，我的成绩是：" + score);
// }

// // ============================================================
// // 重写 toString
// //
// // name 和 age 是父类的 private 成员，子类不能直接使用，所以要通过父类公开的 getName()、getAge() 去取。
// // 下面那一长串是用 + 把很多段文字和变量拼接起来，为了好读拆成了两行
// // （Java 不介意换行，以分号 ; 结尾才算一句话）。
// // 单引号 ' 写在双引号包起来的文字里，只是一个普通字符，可以直接写。
// // 最终显示成类似：Student{name='张三', age=18, studentId='20260125', score=92.5}
// // ============================================================
// @Override
// public String toString() {
// return "Student{name='" + getName() + "', age=" + getAge() + ", studentId='"
// + studentId + "', score=" + score
// + "}";
// }
// }
