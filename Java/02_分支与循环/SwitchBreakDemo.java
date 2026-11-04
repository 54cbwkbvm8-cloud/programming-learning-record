public class SwitchBreakDemo {
    public static void main(String[] args) {
        int day = 2;
        System.out.println("【反例1】day = " + day + "，（缺少break语句）期望只输出：星期二");
        switch (day) {
            case 1:
                System.out.println("星期一");
            case 2:
                System.out.println("星期二");
            case 3:
                System.out.println("星期三");
            case 4:
                System.out.println("星期四");
            case 5:
                System.out.println("星期五");
            case 6:
                System.out.println("星期六");
            case 7:
                System.out.println("星期日");
            default:
                System.out.println("无效的日期");
        }
        char level = 'B';
        System.out.println("【反例2】level = " + level + "，（break语句缺少）期望只输出：良好");
        switch (level) {
            case 'A':
                System.out.println("优秀");
                break;
            case 'B':
                System.out.println("良好");
            case 'C':
                System.out.println("及格");
                break;
            case 'D':
                System.out.println("不及格");
            default:
                System.out.println("无效的等级");
                break;
        }
        int x = 9;
        System.out.println("【反例3】x = " + x + "，（break语句缺少）期望只输出：default");
        switch (x) {
            default:
                System.out.println("default：x 不是1，也不是2");
            case 1:
                System.out.println("case 1");
                break;
            case 2:
                System.out.println("case 2");
            case 3:
                System.out.println("case 3");
                break;
        }
        System.out.println("【正确写法】day = " + day + "，每个case末尾都加上break");
        switch (day) {
            case 1:
                System.out.println("星期一");
                break;
            case 2:
                System.out.println("星期二");
                break;
            case 3:
                System.out.println("星期三");
                break;
            case 4:
                System.out.println("星期四");
                break;
            case 5:
                System.out.println("星期五");
                break;
            case 6:
                System.out.println("星期六");
                break;
            case 7:
                System.out.println("星期日");
                break;
            default:
                System.out.println("输入错误");
        }
    }
}
