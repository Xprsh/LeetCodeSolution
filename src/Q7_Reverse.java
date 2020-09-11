/**
 * Q7. 整数反转
 * <p>
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 注意：
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class Q7_Reverse {

    /**
     * 方法一：直接翻转，效率低
     *
     * @param x
     * @return
     */
    public int reverse_1(int x) {
        if (x == 0) {
            return 0;
        }

        // 翻转之后的整数字符串
        String s;
        if (x > 0) {
            s = new StringBuffer(String.valueOf(x)).reverse().toString();
        } else {
            s = "-" + new StringBuffer(String.valueOf(x)).reverse().toString()
                    .substring(0, String.valueOf(x).length() - 1);
        }

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 方法二：数学计算
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10) && pop > 7) {
                return 0;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Q7_Reverse().reverse(-12345));
    }
}
