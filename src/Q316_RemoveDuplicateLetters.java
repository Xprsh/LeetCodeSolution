import java.util.Stack;

/**
 * Q316. 去除重复字母
 * 给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 */
public class Q316_RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);

            // 若栈中已经有当前元素，则直接去除当前元素
            if (stack.contains(c)) {
                continue;
            }

            // 若当前的栈顶元素比当前的元素字典序大，且当前元素的位置后面还有栈顶元素,
            // 将栈顶元素出栈, 将当前元素入栈, 这样来找到最优的排列
            while (!stack.empty() && stack.peek() > c &&
                    s.indexOf(stack.peek(), i) != -1) {
                stack.pop();
            }
            stack.push(c);
        }

        char[] res = new char[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            res[i] = stack.get(i);
        }
        return new String(res);
    }

    public static void main(String[] args) {
        Q316_RemoveDuplicateLetters obj = new Q316_RemoveDuplicateLetters();
        System.out.println(obj.removeDuplicateLetters("cbacdcbc"));
    }
}
