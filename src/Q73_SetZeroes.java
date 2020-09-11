import java.util.Arrays;

/**
 * Q73. 矩阵置零
 *
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 */
public class Q73_SetZeroes {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length ==  0){
            return;
        }
        // 标记行
        int[] row = new int[matrix.length];
        // 标记列
        int[] line = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0){
                    row[i] = 1;
                    line[j] = 1;
                }
            }
        }

        // 行置 0
        for (int i = 0; i < row.length; i++) {
            if(row[i] == 1){
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 列置 0
        for (int i = 0; i < line.length; i++) {
            if(line[i] == 1){
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    static int[][] m = new int[][]{
        {0,1,2,0},
        {3,4,5,2},
        {1,3,1,5}
    };

    public static void main(String[] args) {
        Q73_SetZeroes obj = new Q73_SetZeroes();
        obj.setZeroes(m);
        System.out.println(Arrays.deepToString(m));
    }
}
