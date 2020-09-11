import java.util.HashMap;
import java.util.Map;

class SumOfTwoNumbers {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if(map.containsKey(num)){
                return new int[] {map.get(num),i};
            } else {
                map.put(nums[i],i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SumOfTwoNumbers sum = new SumOfTwoNumbers();
        int[] twoSum = sum.twoSum(new int[]{1,3,4,5}, 10);
        System.out.println(twoSum);
    }
}