import java.util.Stack;

public class Q1143_LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) {
            return 0;
        }

        int len1 = text1.length();
        int len2 = text2.length();
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

        if (len1 == 0 || len2 == 0) {
            return 0;
        }

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (t1[i - 1] == t2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        Q1143_LongestCommonSubsequence obj = new Q1143_LongestCommonSubsequence();
        System.out.println(obj.longestCommonSubsequence("abcde","ace"));
    }
}
