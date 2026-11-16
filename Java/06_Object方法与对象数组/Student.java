
import java.util.Objects;

public class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return score == student.score &&
                Objects.equals(this.name, student.name);
    }
                                                                                   // 

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }
}

                                                                                   //
// // 导入 Objects 工具类：它提供了两个方便又安全的方法
// //   Objects.equals(a, b) ：比较两个对象内容是否相同，即使其中某个是 null 也不会报错
// //   Objects.hash(...)    ：根据若干个值，计算出一个"哈希码"（一个整数）
// // 它不在 Java 默认范围内，所以必须先 import。
// import java.util.Objects;

// /**
//  * Student —— "学生"类，并且重写了 toString、equals、hashCode 三个方法
//  *
//  * 【本次修复的问题】
//  *   你上传的文件名是 Student_1.java，但里面写的是 public class Student。
//  *   Java 有一条硬性规定：被 public 修饰的类，【文件名必须和类名完全一致】（区分大小写）。
//  *   所以 Student_1.java 里不能放 public class Student，直接编译会报错：
//  *     class Student is public, should be declared in a file named Student.java
//  *   修复：这个文件的名字必须是 Student.java。我把它改名放在了 object_demos 文件夹里。
//  *   （你原来大概是为了避开另一个叫 Student.java 的练习文件才改了名字。
//  *    那个文件里的 Student 类有 id、name、score 三个属性，和这个类不是同一个版本。
//  *    同一个文件夹里不能同时存在两个 Student 类，所以这一组文件要单独放在一个文件夹里编译运行。）
//  *
//  * 【检查结果】
//  *   除了文件名之外，类里面的代码没有错误：
//  *   toString、equals、hashCode 的写法都是标准的、正确的写法，所以代码逻辑一行都没有改，只补充了详细注释。
//  *
//  * 【什么是"重写"（@Override）】
//  *   所有的类都自动继承了 Object 这个"祖先类"，Object 里已经写好了 toString、equals、hashCode，
//  *   但它的默认实现不太好用。我们可以在自己的类里，写一个同名、同参数的方法，
//  *   覆盖掉默认的版本，这就叫"重写"。
//  *   @Override 是一个"标记"，意思是"我这个方法是在重写父类的方法"。
//  *   它的好处：如果你不小心写错了方法名或参数，编译器会立刻报错提醒你，而不是悄悄变成一个新方法。
//  */
// public class Student {

//     // private：私有，类的外面不能直接访问，只能通过下面的 getter 方法读取（封装）
//     private String name;   // 姓名
//     private int score;     // 成绩

//     // 构造方法：new Student("Tom", 90) 时自动执行，给新对象设置初始值。
//     // 名字和类名相同，没有返回值类型。
//     // this.name 表示"这个对象自己的成员变量 name"，用来区分同名的参数 name。
//     public Student(String name, int score) {
//         this.name = name;
//         this.score = score;
//     }

//     // getter 方法：对外提供"读取"私有成员变量的入口
//     public String getName() {
//         return name;
//     }

//     public int getScore() {
//         return score;
//     }

//     // ============================================================
//     // 重写 toString：让对象"打印出来好看"
//     //
//     // 【默认的 toString 不好用】
//     //   System.out.println(对象) 实际上会自动调用对象的 toString() 方法，把返回的文字打印出来。
//     //   Object 默认的 toString 返回"类名@一串十六进制数字"（例如 RawStudent@15db9742），
//     //   看不出对象里存了什么。重写后，就可以返回我们想要的、一目了然的文字。
//     //
//     // 这个方法的返回结果形如：Student{name='Tom', score=90}
//     // ============================================================
//     @Override
//     public String toString() {
//         // 这是把很多段内容用 + 拼接成一个长文字，为了好读拆成了几行（以分号 ; 结尾才算一句话）。
//         // 拆开看每一段：
//         //   "Student{"       ：普通文字
//         //   "name='"         ：普通文字（里面的单引号只是一个普通字符）
//         //   name             ：姓名变量的值
//         //   '\''             ：一个"字符"，表示单引号。因为单引号本身是字符的边界符号，
//         //                      要表示单引号这个字符，必须在前面加反斜杠 \ 来"转义"：写成 '\''
//         //   ", score="       ：普通文字
//         //   score            ：成绩变量的值（数字会自动变成文字）
//         //   '}'              ：一个字符，右花括号
//         return "Student{" +
//                 "name='" + name + '\'' +
//                 ", score=" + score +
//                 '}';
//     }

//     // ============================================================
//     // 重写 equals：定义"两个学生对象在什么情况下算相等"
//     //
//     // 【默认的 equals 不好用】
//     //   Object 默认的 equals 效果和 == 一样：只有"同一个对象"才算相等。
//     //   这会导致：两个姓名、成绩完全一样的学生，因为是分别 new 出来的两个对象，也被认为"不相等"。
//     //   重写之后，我们规定：【姓名和成绩都相同】就算相等。
//     //
//     // 参数是 Object 类型：因为 equals 是从 Object 继承来的，参数类型必须保持一致（Object），
//     // 这样才能接收任意类型的对象来比较。
//     // ============================================================
//     @Override
//     public boolean equals(Object obj) {

//         // 第 1 步：先看是不是"自己和自己比"。this 代表当前对象自己。
//         // 如果 this == obj，说明两边是同一个对象，肯定相等，可以直接返回 true，不用再往下比较了。
//         if (this == obj) {
//             return true;
//         }

//         // 第 2 步：排除两种明显不相等的情况：
//         //   obj == null                   ：对方是 null（什么都没有），肯定不相等。
//         //                                   必须先检查它，否则下一行的 obj.getClass() 会因为对 null 调用方法而崩溃。
//         //   getClass() != obj.getClass()  ：getClass() 能得到对象所属的"类"。
//         //                                   如果两边不是同一个类（比如一边是 Student，一边是 RawStudent 或文字），
//         //                                   肯定不相等。
//         // || 表示"或者"：只要有一个条件成立，就返回 false。
//         // 而且 || 是"短路"的：左边已经是 true 时，右边就不再执行，所以 obj 为 null 时不会执行到 obj.getClass()。
//         if (obj == null || getClass() != obj.getClass()) {
//             return false;
//         }

//         // 第 3 步：走到这里，说明 obj 一定是一个 Student 对象，但它的类型仍然是 Object。
//         // 想访问它的 score、name，需要先"强制类型转换"：(Student) obj，意思是"我确定它是 Student，请当作 Student 用"。
//         // 因为上一步已经确认了类型相同，所以这个转换是安全的。
//         Student student = (Student) obj;

//         // 第 4 步：比较内容。
//         //   score == student.score ：成绩相同。成绩是 int（基本类型），用 == 比较的就是数值本身。
//         //   Objects.equals(this.name, student.name) ：姓名相同。
//         //       姓名是 String（对象类型），比较内容要用 equals，不能用 ==（== 比较的是地址）。
//         //       用 Objects.equals 而不是 this.name.equals(...)，
//         //       是因为如果 this.name 是 null，直接调用 .equals 会崩溃；Objects.equals 会安全地处理 null。
//         //   && 表示"并且"：两个条件同时成立，才算相等。
//         return score == student.score &&
//                 Objects.equals(this.name, student.name);
//     }

//     // ============================================================
//     // 重写 hashCode：返回对象的"哈希码"（一个整数，可以理解成对象的"数字指纹"）
//     //
//     // 【为什么重写 equals 就必须同时重写 hashCode？】
//     //   Java 有一条规定：【如果两个对象 equals 为 true，那么它们的 hashCode 必须相同】。
//     //   像 HashMap、HashSet 这样的集合，是先用 hashCode 找到"大概位置"，再用 equals 精确比较的。
//     //   如果只重写了 equals 没有重写 hashCode，两个"内容相同"的对象会得到不同的哈希码，
//     //   放进 HashSet 时会被当作两个不同的元素，造成各种难以发现的错误。
//     //
//     // Objects.hash(name, score)：根据 name 和 score 这两个值，计算出一个整数。
//     // 这样一来：内容相同的两个 Student（equals 为 true），算出的哈希码也一定相同，满足规定。
//     // 规则要保持一致：equals 里比较了哪些成员变量，hashCode 里就用哪些。
//     // ============================================================
//     @Override
//     public int hashCode() {
//         return Objects.hash(name, score);
//     }
// }
