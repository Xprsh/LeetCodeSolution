/**
 * Q258. 各位相加
 * <p>
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 */
public class Q258_addDigits {
    public int addDigits(int num) {
        if (num < 0) {
            return 0;
        }

        if (num < 10) {
            return num;
        }

        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return addDigits(sum);
    }

    public static void main(String[] args) {
        Q258_addDigits obj = new Q258_addDigits();
        System.out.println(obj.addDigits(38));
    }
}
