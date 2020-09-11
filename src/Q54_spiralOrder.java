import java.util.ArrayList;
import java.util.List;

/**
 * Q54. 螺旋矩阵
 * <p>
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 */
public class Q54_spiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();

        // 定义上下左右指针（扫描边界）
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;

        // 扫描方向 right -> down -> left -> up -> ...
        int direction = 0;

        while (up <= down && left <= right) {
            direction = direction % 4;
            switch (direction) {
                // 向右
                case 0:
                    for (int i = left; i <= right; i++) {
                        ans.add(matrix[up][i]);
                    }
                    up++;
                    direction++;
                    break;
                // 向下
                case 1:
                    for (int i = up; i <= down; i++) {
                        ans.add(matrix[i][right]);
                    }
                    right--;
                    direction++;
                    break;
                // 向左
                case 2:
                    for (int i = right; i >= left; i--) {
                        ans.add(matrix[down][i]);
                    }
                    down--;
                    direction++;
                    break;
                // 向上
                case 3:
                    for (int i = down; i >= up; i--) {
                        ans.add(matrix[i][left]);
                    }
                    left++;
                    direction++;
                    break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Q54_spiralOrder obj = new Q54_spiralOrder();
        System.out.println(obj.spiralOrder(
                new int[][]{
                        {1, 2, 3, 4}
                }
        ).toString());
    }
}
