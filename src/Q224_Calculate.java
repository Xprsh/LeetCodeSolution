import java.util.LinkedList;

/**
 * Q224. 基本计算器
 * <p>
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 * <p>
 * 你可以假设所给定的表达式都是有效的。
 */
public class Q224_Calculate {
    public int calculate(String s) {
        LinkedList<Boolean> stack = new LinkedList<>();
        //result: 当前的结果值; opr: 当前的被加/被减数
        int result = 0, opr = 0;
        // 当前要执行的运算符
        Character op = null;

        for (char ch : s.toCharArray()) {
            if (ch == '+' || ch == '-') {
                if (op == null) {
                    //遇到第一个运算符时，将result置为opr（即第一个运算符左边的数字）
                    result = opr;
                } else {
                    // result = result +/- opr;
                    result = cal(op, result, opr);
                }
                //根据栈顶元素决定是否反转运算符
                op = swap(stack.peek() != null && stack.peek(), ch);
                // 清零当前的操作数
                opr = 0;
            } else if (ch == '(') {
                stack.push(op != null && op == '-');
            } else if (ch == ')') {
                stack.pop();
            } else if (ch != ' ') {
                // 数字
                opr = opr * 10 + ch - '0';
            }
        }
        if (op == null) {
            //算式中没有运算符时，opr就是最终结果
            return opr;
        } else {
            //否则将result与opr（即算式中最右边的数字）执行一次运算
            return cal(op, result, opr);
        }
    }


    // 反转运算符
    private char swap(boolean swap, char ch) {
        if (swap) {
            return ch == '+' ? '-' : '+';
        } else {
            return ch;
        }
    }

    private int cal(char op, int opr1, int opr2) {
        switch (op) {
            case '+':
                return opr1 + opr2;
            case '-':
                return opr1 - opr2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Q224_Calculate obj = new Q224_Calculate();
        System.out.println(obj.calculate("-123-123+123"));
    }
}
