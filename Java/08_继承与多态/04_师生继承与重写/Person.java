class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void introduce() {
        System.out.println("我是 " + name + "，一个普通人。");
    }
}
// /**
// * Person —— "人"类（父类）：只有姓名，会做一个普通的自我介绍
// *
// * 【这是什么文件】
// * 这个文件和同文件夹里的 Student.java、Teacher.java 是一组：
// * Person 是父类，Student（学生）和 Teacher（老师）都继承它。
// * 它们都只是"类的定义"，没有 main 方法，不能单独运行，需要另一个带 main 方法的文件来创建对象并使用它们。
// *
// * 【关于文件名】
// * 你上传时这个文件叫 Person_2.java（文件名后面的 _2 是上传时为了区分同名文件自动加上的）。
// * 这里的类没有写 public，所以 Java 不要求文件名和类名一致，叫什么都能编译；
// * 但规范的做法是文件名和类名保持一致，所以保存为 Person.java。
// *
// * 【本次修复的问题】
// * getter 方法的名字写成了 getname()（n 是小写），但同组的 Student 和 Teacher 里调用的是 getName()（N
// 大写）。
// * Java 严格区分大小写，getname 和 getName 是两个完全不同的名字，所以那两个类找不到 getName()，编译报错：
// * cannot find symbol: method getName()
// * 修复：把 getname() 改成了 getName()。
// * Java 里 getter 的标准命名是 get 加上属性名，并且属性名的首字母大写（驼峰式命名）：name → getName。
// * （其余代码没有问题，没有改动。）
// */
// class Person {

// // private：私有，只有 Person 这个类自己里面的代码能直接访问。
// // 子类 Student、Teacher 虽然继承了 Person，也不能直接访问 name，只能通过下面的 getName() 读取。
// // 这叫"封装"：把数据藏起来，外界只能通过公开的方法来读写。
// private String name;

// // 构造方法：new Person(...) 时自动执行，负责给新对象设置初始值。
// // · 名字必须和类名完全相同 · 没有返回值类型（连 void 都不写）
// // 注意：这个类只写了这一个构造方法（需要传入 name），没有写"无参数的构造方法"。
// // 这意味着：创建 Person（以及它的子类）的时候，必须提供姓名。
// // 这一点会影响子类：子类的构造方法必须用 super(name) 把姓名交给它（见 Student.java 和 Teacher.java）。
// //
// // 参数 name 和成员变量同名，在方法里只写 name 指的是参数；
// // 要表示"这个对象自己的成员变量 name"，必须写 this.name（this 代表"当前这个对象自己"）。
// public Person(String name) {
// this.name = name;
// }

// // getter 方法：对外提供"读取"私有成员变量的入口。
// // 【修复点】方法名原来是 getname()，已改成标准的 getName()。
// public String getName() {
// return name;
// }

// // 自我介绍。子类 Student 和 Teacher 都重写了这个方法，写出各自的版本。
// public void introduce() {
// System.out.println("我是 " + name + "，一个普通人。");
// }
// }
