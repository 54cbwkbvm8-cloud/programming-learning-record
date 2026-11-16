public class RawStudent {
    private String name;
    private int score;

    public RawStudent(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
    

  // /**
  // * RawStudent —— "原始版"的学生类：只有属性和构造方法，什么额外的功能都没有
  // *
  // * 这个类是专门用来和 Student 类做对比的"对照组"：
  // * RawStudent：没有重写 toString / equals / hashCode
  // * Student ：重写了这三个方法
  // * 在 ToStringPredictDemo.java 和 EqualsDemo.java 里，会让它们并排运行，
  // * 对比"没有重写"和"重写之后"，输出结果有什么不同。
  // *
  // * 【没有重写意味着什么】
  // * Java 里所有的类都【自动继承】一个叫 Object 的"祖先类"，
  // * 所以即使 RawStudent 里一行都没写，它也自带了 Object 提供的 toString()、equals()、hashCode() 等方法：
  // * · 默认的 toString()：返回"类名@一串十六进制数字"，例如 RawStudent@15db9742，看不出对象里存的数据
  // * · 默认的 equals() ：效果和 == 完全一样，比较的是"是不是同一个对象"（地址是否相同），而不是内容是否相同
  // *
  // * 【检查结果】
  // * 这份代码没有错误，所以【逻辑一行都没有改】，只补充了详细注释。
  // * 说明：这两个成员变量是 private 的，而且这个类里没有提供 getter 方法，
  // * 所以创建出对象之后，从类的外面没有办法读取里面的 name 和 score，
  // * 有的编程工具会提示"变量从未被使用"。作为"对照组"，这是正常的，不需要处理。
  // */
  // public class RawStudent {

// // private：私有的，只有 RawStudent 这个类自己里面的代码能访问，类的外面不能直接访问。
// // 这是"封装"的做法：把数据藏起来，不让外界随便读写。
// private String name; // 姓名

// private int score; // 成绩

// // ============================================================
// // 构造方法：用 new RawStudent(...) 创建对象时自动执行，负责给新对象的成员变量设置初始值。
// // · 名字必须和类名完全相同
// // · 没有返回值类型（连 void 都不写）
// //
// // 【this 的作用】
// // 参数名（name、score）和成员变量的名字完全一样。在方法里只写 name，
// // Java 认为指的是离得最近的"参数 name"。要表示"这个对象自己的成员变量 name"，必须写 this.name。
// // this.name = name; → 把参数 name 的值，存进这个对象自己的成员变量 name 里
// // ============================================================
// public RawStudent(String name, int score) {
// this.name = name;
// this.score = score;
// }
// }
