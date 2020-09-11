import java.util.ArrayList;
import java.util.List;

/**
 * Q118. 杨辉三角
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 */
public class Q118_Generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < numRows ; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i ; j++) {
                if (j == 0 || j == i){
                    list.add(1);
                }else {
                    list.add(ans.get(i-1).get(j) + ans.get(i-1).get(j-1));
                }
            }
            ans.add(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        Q118_Generate obj = new Q118_Generate();
        System.out.println(obj.generate(1));
    }
}
