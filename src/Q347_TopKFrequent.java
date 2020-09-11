import java.util.*;

/**
 * Q347. 前 K 个高频元素
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 */
public class Q347_TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 遍历数组获取每个元素的出现次数
        for (Integer num: nums) {
            if (!map.containsKey(num)){
                map.put(num,1);
            }else {
                map.put(num,map.get(num)+1);
            }
        }

        // 遍历map，用最小堆保存频率最大的 k 个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for (Integer num:map.keySet()){
            if (pq.size() < k){
                pq.add(num);
            }else if (map.get(num) > map.get(pq.peek())){
                pq.remove();
                pq.add(num);
            }
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = pq.remove();
        }

        return ans;
    }

    public static void main(String[] args) {
        Q347_TopKFrequent obj = new Q347_TopKFrequent();
        System.out.println(Arrays.toString(obj.topKFrequent(
                new int[]{1, 1, 1, 2, 2, 3}, 2
        )));
    }
}
