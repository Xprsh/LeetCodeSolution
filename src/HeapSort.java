import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {

    /**
     * 堆排序
     *
     * @param arr 待排序数组
     */
    public void sort(int[] arr) {

        // 构建大顶堆
        for (int i = (arr.length / 2) - 1; i >= 0; i--) {
            // 从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        // 调整堆结构、交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整大顶堆
     *
     * @param arr    需排序数组
     * @param i
     * @param length
     */
    private void adjustHeap(int[] arr, int i, int length) {
        // 先取出当前元素
        int temp = arr[i];
        // 从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }

            // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    /**
     * 交换元素
     *
     * @param arr 堆数组
     * @param a   交换元素下标 q
     * @param b   交换元素下标 b
     */
    public void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        HeapSort obj = new HeapSort();
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        obj.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
