import java.util.LinkedList;

/**
 * Q402. 移掉 K 位数字
 * <p>
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 */
public class Q402_RemoveKdigits {
    public String removeKdigits(String num, int k) {
        if (num == null || k >= num.length()) {
            return "0";
        }

        LinkedList<Character> list = new LinkedList<>();

        for (Character c : num.toCharArray()) {

            // 第一个数字，直接存入
            if (list.isEmpty()) {
                list.addLast(c);
            } else {
                while (list.size() > 0 && c < list.peekLast() && k > 0) {
                    list.removeLast();
                    k--;
                }
                list.addLast(c);
            }
        }
        // 遍历完一轮后仍然没有移除足够位数，需要删除最后k位
        for (int i = 0; i < k; i++) {
            list.removeLast();
        }

        // 移除前面的 0
        while (!list.isEmpty()) {
            if (list.peekFirst() == '0') {
                list.removeFirst();
            } else {
                break;
            }
        }

        StringBuilder newStr = new StringBuilder();
        for (Character c : list) {
            newStr.append(c);
        }

        return list.isEmpty() ? "0" : newStr.toString();
    }

    public static void main(String[] args) {
        Q402_RemoveKdigits obj = new Q402_RemoveKdigits();
        System.out.println(obj.removeKdigits("1234567890", 9));
    }
}
