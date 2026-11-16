public class NullPointerDemo {
    public static void main(String[] args) {
        Student[] students = new Student[2];
        students[0] = new Student("Tom", 90);
        System.out.println("students[0] = " + students[0]);
        System.out.println("students[1] = " + students[1]);
        if (students[1] != null) {
            System.out.println("students[1].getName() = " + students[1].getName());
        } else {
            System.out.println("students[1] is null，已经跳过访问，所以没有出错");
        }
        System.out.println("下面故意不判断 null，直接访问 students[1]");
        System.out.println("students[1].getName() = " + students[1].getName());
        System.out.println("这一行不会被输出");
    }
}
        // /**
        // * NullPointerDemo —— 演示"空指针异常"（NullPointerException，简称 NPE）
        // *
        // * 【什么是 null】
        // * null 是一个特殊的值，意思是"什么都没有，不指向任何对象"。
        // * 对象类型的变量（以及对象数组里的格子）在没有放入对象之前，默认值就是 null。
        // *
        // * 【什么是空指针异常】
        // * 如果一个变量的值是 null，你却对它用点号去调用方法或访问属性（比如 students[1].getName()），
        // * 就好比"拿着一张写着'此处无房'的门牌号去敲门"，Java 找不到对象，就会抛出
        // * NullPointerException，程序当场停止。这是 Java 程序里最常见的错误之一。
        // *
        // * 【避免的办法】
        // * 在对可能为 null 的变量调用方法之前，先用 if (变量 != null) 判断一下。
        // *
        // * 【这个程序会故意崩溃】
        // * 程序的后半段是故意不判断 null、直接访问，目的就是让你亲眼看到这个错误。
        // * 运行结束时屏幕上出现 "Exception in thread "main" java.lang.NullPointerException ..."
        // 是预期的结果，
        // * 最后那句"这一行不会被输出"也确实不会输出，因为程序已经在上一行崩溃停止了。
        // * 注意：这种崩溃是演示用的，正常的程序里应该避免出现。
        // *
        // * 【运行前提】
        // * 这个程序用到 Student 类，所以要和 Student.java 放在同一个文件夹里一起编译：
        // * javac NullPointerDemo.java Student.java
        // *
        // * 【本次修复的问题】
        // * 只有一处文字错误：提示里的 "studens[1]" 少了一个 t，应为 "students[1]"。
        // * （其余逻辑是正确的，没有改动。）
        // */
        // public class NullPointerDemo {

        // // main 是程序的入口，运行程序时从这里开始一行一行往下执行
        // public static void main(String[] args) {

        // // 创建一个能存放 2 个 Student 的数组。
        // // 此时只是准备好了 2 个空格子，两个格子里的值都是 null，还没有创建任何 Student 对象。
        // Student[] students = new Student[2];

        // // 只创建一个学生，放进下标 0 的格子。下标 1 的格子仍然是 null。
        // // students[0] -------> [name=Tom, score=90]
        // // students[1] -------> null（什么都没有）
        // students[0] = new Student("Tom", 90);

        // // 打印整个格子里的值。因为 Student 重写了 toString，所以第一行会显示对象的内容；
        // // 第二行的格子里是 null，直接打印 null 本身是安全的，只会显示文字 null，不会崩溃。
        // // （崩溃发生在"对 null 调用方法"的时候，而不是"打印 null"的时候。）
        // System.out.println("students[0] = " + students[0]); // 预期：students[0] =
        // Student{name='Tom', score=90}
        // System.out.println("students[1] = " + students[1]); // 预期：students[1] = null

        // // ---------- 正确做法：先判断是不是 null ----------
        // // != 是"不等于"。students[1] != null 读作"students[1] 里有对象吗？"
        // if (students[1] != null) {
        // // 确认有对象，才能放心调用它的方法
        // System.out.println("students[1].getName() = " + students[1].getName());
        // } else {
        // // 是 null，就跳过，不去访问，因此不会出错
        // System.out.println("students[1] is null，已经跳过访问，所以没有出错");
        // }

        // // ---------- 错误做法（故意演示）：不判断，直接访问 ----------
        // // 【修复点】原来的文字写的是 "studens[1]"，已改成 "students[1]"，并整理了标点的空格。
        // System.out.println("下面故意不判断 null，直接访问 students[1]");

        // // 执行这一行时，Java 要先计算 students[1].getName()：
        // // students[1] 是 null，对 null 调用 getName() 方法，找不到对象，抛出 NullPointerException。
        // // 异常一旦抛出，而且没有用 try-catch 接住，程序就会立刻停止，后面的代码都不会再执行。
        // // 报错信息大致是：
        // // Exception in thread "main" java.lang.NullPointerException:
        // // Cannot invoke "Student.getName()" because "students[1]" is null
        // // 翻译：无法调用 Student.getName()，因为 students[1] 是 null。
        // // 报错信息的最后一行会告诉你出错的位置（哪个文件的第几行），看到它就能快速找到问题。
        // System.out.println("students[1].getName() = " + students[1].getName());

        // // 因为上一行已经崩溃，程序在这里之前就停止了，这一行永远不会被执行
        // System.out.println("这一行不会被输出");
        // }
        // } 
