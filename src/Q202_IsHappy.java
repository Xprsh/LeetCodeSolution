import java.util.HashSet;
import java.util.Set;

/**
 * Q202. 快乐数
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，
 * 那么这个数就是快乐数。
 *
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 */
public class Q202_IsHappy {
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }

        if (n == 1) {
            return true;
        }

        Set<Integer> set = new HashSet<>();

        while (true) {
            // 重复出现该整数，意味着这不是快乐数
            if (set.contains(n)) {
                return false;
            }

            int happy = happy(n, 0);
            if (happy == 1) {
                return true;
            } else {
                set.add(n);
                n = happy;
            }
        }
    }

    int happy(int n, int sum) {
        if (n <= 9) {
            sum += n * n;
        } else {
            sum += (n % 10) * (n % 10);
            sum = happy(n / 10, sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        Q202_IsHappy obj = new Q202_IsHappy();
        System.out.println(obj.isHappy(19));
    }
}
