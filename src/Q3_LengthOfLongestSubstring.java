import java.util.HashMap;
import java.util.HashSet;

/**
 * Q3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Q3_LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return 1;
        }


        int ans = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for (int start = 0, end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                start = Math.max(map.get(ch), start);
            }
            map.put(ch, end + 1);
            ans = Math.max(end - start + 1, ans);
        }

        return ans;
    }

    public static void main(String[] args) {
        Q3_LengthOfLongestSubstring solution = new Q3_LengthOfLongestSubstring();
        System.out.println(solution.lengthOfLongestSubstring("tmmzuxt"));
    }
}
