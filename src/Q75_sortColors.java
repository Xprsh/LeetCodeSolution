/**
 * Q75. 颜色分类
 *
 * @author Xprsh
 * @Description 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * @createTime 2020/10/07/ 10:13:02
 */
public class Q75_sortColors {
    /**
     * 方法一：两趟扫描
     */
    public void sortColors1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int[] counts = new int[3];

        for (int num : nums) {
            counts[num]++;
        }

        int i = 0;
        int flag = 0;
        for (int count : counts) {
            for (int j = 0; j < count; j++) {
                nums[i++] = flag;
            }
            flag++;
        }
    }

    /**
     * 方法二：双指针
     */
    public void sortColors(int[] nums) {

        if (nums == null || nums.length < 2) {
            return;
        }

        int len = nums.length;

        // 头指针
        int first = 0;
        // 尾指针
        int last = len - 1;

        for (int i = 0; i < len; i++) {
            // 越界，停止交换
            if (i > last) {
                return;
            }

            if (nums[i] == 0) {
                if (i != first) {
                    swap(nums, i, first);
                }
                first++;
            } else if (nums[i] == 2) {
                if (i != last) {
                    swap(nums, i, last);
                    // 下一轮重新判断
                    i--;
                }
                last--;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
