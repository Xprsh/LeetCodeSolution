import java.util.Stack;

/**
 * Q20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 注意空字符串可被认为是有效字符串。
 */
public class Q20_IsValid {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        // 字符串长度为奇数，一定不匹配
        if (s.length() == 1 || s.length() % 2 == 1) {
            return false;
        }

        // 辅助栈，用于存储左括号进行匹配
        Stack<Character> stack = new Stack<>();


        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '(' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                switch (s.charAt(i)) {
                    case '}':
                        if (stack.size() > 0 && stack.peek() == '{') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    case ']':
                        if (stack.size() > 0 && stack.peek() == '[') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    case ')':
                        if (stack.size() > 0 && stack.peek() == '(') {
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Q20_IsValid obj = new Q20_IsValid();
        System.out.println(obj.isValid("{"));
    }
}
