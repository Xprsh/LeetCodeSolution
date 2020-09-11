/**
 * Q5. 最长回文子串
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 */
public class Q5_LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }


        // 初始化 dp 二维数组
        boolean[][] dp = new boolean[s.length()][s.length()];

        int start = 0;
        int end = 0;
        int maxLength = 1;
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }


        for (int r = 1; r < s.length(); r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLength) {
                        maxLength = r - l + 1;
                        start = l;
                        end = r;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        Q5_LongestPalindrome obj = new Q5_LongestPalindrome();
        System.out.println(obj.longestPalindrome("babab"));
    }
}
