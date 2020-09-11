import java.util.Stack;

/**
 * Q32. 最长有效括号
 * <p>
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 */
public class Q32_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int ans = 0;
        // 存入栈的下标
        Stack<Integer> stack = new Stack<>();
        // 向栈中预置一个-1，将计算长度的方式转化成“）”的下标减去出栈后栈顶元素的下标
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                stack.push(i);
            }else {
                stack.pop();
                // 如栈空，则注入信息 i 做为预置下标
                if (stack.isEmpty()){
                    stack.push(i);
                }
                ans = Math.max(ans, i-stack.peek());
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Q32_LongestValidParentheses obj = new Q32_LongestValidParentheses();
        System.out.println(obj.longestValidParentheses("()(()"));
    }
}
