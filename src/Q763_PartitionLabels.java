import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q763. 划分字母区间
 * <p>
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。
 * 返回一个表示每个字符串片段的长度的列表。
 */
public class Q763_PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        ArrayList<Integer> list = new ArrayList<>();

        if (S == null || S.length() == 0) {
            list.add(0);
            return list;
        }
        if (S.length() == 1) {
            list.add(1);
            return list;
        }

        // 存放字符最后出现位置
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), i);
        }

        int start = 0, end = 0;

        // 遍历字符串并判断是否达到边界
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, map.get(S.charAt(i)));

            // 遍历位置与边界重合
            if (i == end) {
                list.add(end - start + 1);
                start = end + 1;
            }
        }

        return list;
    }

    public static void main(String[] args) {
        Q763_PartitionLabels obj = new Q763_PartitionLabels();
        System.out.println(obj.partitionLabels("abcaasbabsdwhkfwief"));
    }
}
