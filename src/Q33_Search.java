/**
 * Q33. 搜索旋转排序数组
 *
 */
public class Q33_Search {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        return binarySort(nums, 0, nums.length - 1, target);

    }

    private int binarySort(int[] nums, int start, int end, int target) {
        if (start == end) {
            if (nums[start] == target) {
                return start;
            } else {
                return -1;
            }
        }

        int mid = start + (end - start) / 2;

        // 左半部分有序
        if (nums[start] <= nums[mid]) {
            if (nums[start] <= target && target <= nums[mid]) {
                return binarySort(nums, start, mid, target);
            } else {
                return binarySort(nums, mid + 1, end, target);
            }
        } else {
            // 右半部分有序
            if (nums[mid + 1] <= target && target <= nums[end]) {
                return binarySort(nums, mid + 1, end, target);
            } else {
                return binarySort(nums, start, mid, target);
            }
        }
    }

    public static void main(String[] args) {
        Q33_Search obj = new Q33_Search();
        System.out.println(obj.search(new int[]{3,4,5,6,1,2},2));
    }
}
