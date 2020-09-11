/**
 * Q8. 字符串转换整数
 * <p>
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 */
public class Q8_MyAtoi {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        String num = "";

        boolean isStart = false;

        for (int i = 0; i < str.length(); i++) {
            boolean isNumber = '0' <= str.charAt(i) && str.charAt(i) <= '9';
            if (!isStart) {
                if (str.charAt(i) == ' ') {
                    continue;
                }
                if (isNumber || str.charAt(i) == '-') {
                    num += str.charAt(i);
                    isStart = true;
                } else if (str.charAt(i) == '+') {
                    isStart = true;
                } else {
                    break;
                }
            } else {
                if (isNumber) {
                    num += str.charAt(i);
                } else {
                    break;
                }
            }
        }

        // 字符串转换整数
        if (num.equals("-") || num.equals("")) {
            return 0;
        }

        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            if (num.charAt(0) == '-') {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Q8_MyAtoi().myAtoi("+21"));
    }
}
