/**
 * Q43. 字符串相乘
 * <p>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为
 * 字符串形式。
 */
public class Q43_Multiply {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0
                || num1.equals("0") || num2.equals("0")
        ) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();

        // m * n 最多有 m + n 位
        int[] res = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int num = ((num1.charAt(i) - '0') * (num2.charAt(j) - '0'));
                // 乘积再 res 对应的索引位置
                int p1 = i + j;
                int p2 = i + j + 1;
                // 叠加到 res 上
                int sum = num + res[p2];
                res[p2] = sum % 10;
                // 处理进位，如 19 * 19
                res[p1] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < res.length; i++) {
            // 首位出现0，跳过
            if (res[i] == 0 && i == 0) {
                continue;
            }
            result.append(res[i]);
        }

        return result.toString();
    }


    public static void main(String[] args) {
        Q43_Multiply obj = new Q43_Multiply();
        System.out.println(obj.multiply("9", "99"));
    }
}
