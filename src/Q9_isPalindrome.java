/**
 * Q9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class Q9_isPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 9) {
            return true;
        }

        return x == reverse(x);

    }


    /**
     * 反转数 不会溢出
     * @param x
     * @return
     */
    public int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        Q9_isPalindrome obj = new Q9_isPalindrome();
        System.out.println(obj.isPalindrome(-121));
    }
}
