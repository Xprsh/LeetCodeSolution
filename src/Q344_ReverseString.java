/**
 * Q344. 反转字符串
 *
 * @author Xprsh
 * @Description 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须 原地 修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * @createTime 2020/10/08/ 09:18:41
 */
public class Q344_ReverseString {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }

        int len = s.length;
        int mid = 0;
        if (len % 2 == 0) {
            mid = len / 2 - 1;
        } else {
            mid = len / 2;
        }

        for (int i = 0; i <= mid; i++) {
            int last = len - 1 - i;
            if (i != last) {
                swap(s, i, last);
            }
        }
    }

    void swap(char[] s, int a, int b) {
        char temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }
}
