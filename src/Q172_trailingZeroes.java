/**
 * Q172. 阶乘后的零
 * <p>
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 */
public class Q172_trailingZeroes {
    public int trailingZeroes(int n) {
        if (n < 5) {
            return 0;
        }

        // 实际上只要考虑统计 5 的个数
        // 如 10! = 【2*（2*2）*5*（2*3）*（2*2*2）*（2*5）】
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }

    public static void main(String[] args) {
        Q172_trailingZeroes obj = new Q172_trailingZeroes();
        System.out.println(obj.trailingZeroes(10));
    }
}
