/**
 * Q14. 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class Q14_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        // 第一个字符串做为比对标本
        String str = strs[0];

        for (int i = 1; i < strs.length; i++) {
            String temp = "";
            for (int j = 1; j <= Math.min(str.length(), strs[i].length()); j++) {
                if (str.substring(0, j).equals(strs[i].substring(0, j))) {
                    temp = str.substring(0, j);
                }
            }
            if (temp.equals("")) {
                return "";
            } else {
                str = temp;
            }

        }

        return str;
    }

    public static void main(String[] args) {
        Q14_LongestCommonPrefix obj = new Q14_LongestCommonPrefix();
        System.out.println(obj.longestCommonPrefix(
                new String[]{"flower", "flow", "flight"}
        ));
        System.out.println(obj.longestCommonPrefix(
                new String[]{"dog", "racecar", "car"}
        ));

        System.out.println(obj.longestCommonPrefix(
                new String[]{"dog"}
        ));
        System.out.println(obj.longestCommonPrefix(
                new String[]{""}
        ));
        System.out.println(obj.longestCommonPrefix(
                null
        ));
    }
}
