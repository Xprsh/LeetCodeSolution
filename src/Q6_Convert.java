/**
 * Q6. Z 字形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 */
public class Q6_Convert {
    public String convert(String s, int numRows) {
        if (s == null || numRows <= 1 || s.length() <= numRows) {
            return s;
        }

        String[] rows = new String[numRows];

        // 当前 rows 所在行下标
        int index = 0;


        // 所在行是否需要向下（index++）
        boolean down = false;

        // 初始化
        for (int i = 0; i < numRows; i++) {
            rows[i] = "";
        }

        for (int i = 0; i < s.length(); i++) {
            rows[index] += s.substring(i,i+1);

            // 到达两端需要改变方向
            if(index == 0 || index == numRows - 1){
                down = !down;
            }

            // 换行
            index += down ? 1 : -1;
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            ans.append(rows[i]);
        }

        return String.valueOf(ans);

    }

    public static void main(String[] args) {
        Q6_Convert obj = new Q6_Convert();
        System.out.println(obj.convert("LEETCODEISHIRING",3));
    }
}
