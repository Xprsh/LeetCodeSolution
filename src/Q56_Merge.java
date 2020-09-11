import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Q56. 合并区间
 * <p>
 * 给出一个区间的集合，请合并所有重叠的区间。
 */
public class Q56_Merge {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }

        // 按照区间起始位置排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 遍历区间
        List<int[]> res = new ArrayList<>();
        // 合并末端下标
        int index = 0;

        while (index < intervals.length) {
            int left = intervals[index][0];
            int right = intervals[index][1];

            // 判断重叠
            while (index < intervals.length - 1 &&
                    intervals[index + 1][0] <= right) {
                index++;
                right = Math.max(right, intervals[index][1]);
            }

            res.add(new int[]{left, right});
            index++;
        }
        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Q56_Merge obj = new Q56_Merge();
        System.out.println(Arrays.deepToString(obj.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
    }
}
