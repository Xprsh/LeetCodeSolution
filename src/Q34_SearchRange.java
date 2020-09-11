import java.util.Arrays;

/**
 * Q34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class Q34_SearchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int begin = leftSort(nums, 0, nums.length - 1, target, -1);
        int end = rightSort(nums, 0, nums.length - 1, target, -1);

        return new int[]{begin, end};
    }

    private int leftSort(int[] nums, int begin, int end, int target, int index) {

        if (begin >= end) {
            if (nums[begin] == target) {
                return begin;
            } else {
                return index;
            }
        }

        int mid = begin + (end - begin) / 2;
        if (nums[mid] == target) {
            return leftSort(nums, begin, mid - 1, target, mid);
        }

        if (nums[mid] < target) {
            return leftSort(nums, mid + 1, end, target, index);
        }

        if (nums[mid] > target) {
            return leftSort(nums, begin, mid - 1, target, index);
        }
        return -1;
    }

    private int rightSort(int[] nums, int begin, int end, int target, int index) {
        if (begin >= end) {
            if (nums[begin] == target) {
                return begin;
            } else {
                return index;
            }
        }

        int mid = begin + (end - begin) / 2;
        if (nums[mid] == target) {
            return rightSort(nums, mid+1, end, target, mid);
        }

        if (nums[mid] < target) {
            return rightSort(nums, mid + 1, end, target, index);
        }

        if (nums[mid] > target) {
            return rightSort(nums, begin, mid - 1, target, index);
        }
        return -1;
    }


    public static void main(String[] args) {
        Q34_SearchRange obj = new Q34_SearchRange();
        System.out.println(Arrays.toString(obj.searchRange(new int[]{2,2}, 9)));
    }
}
