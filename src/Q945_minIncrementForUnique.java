import java.util.Arrays;

/**
 * Q945. 使数组唯一的最小增量
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * <p>
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 */
public class Q945_minIncrementForUnique {
    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }

        Arrays.sort(A);

        int ans = 0;
        int max = A[0];

        for (int i = 1; i < A.length; i++) {
            if (A[i] <= max) {
                ans += max - A[i] + 1;
                max++;
            } else {
                max = A[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q945_minIncrementForUnique obj = new Q945_minIncrementForUnique();
        System.out.println(obj.minIncrementForUnique(
                new int[]{0}
        ));
    }
}
