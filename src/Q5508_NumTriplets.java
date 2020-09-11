public class Q5508_NumTriplets {
    public int numTriplets(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return 0;
        }

        int count = 0;

        int len1 = nums1.length;
        int len2 = nums2.length;

        long dp1[][] = new long[len1][len1];
        long dp2[][] = new long[len2][len2];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len1; j++) {
                Long a = (long) nums1[i];
                Long b = (long) nums1[j];
                dp1[i][j] = a * b;
            }
        }

        for (int i = 0; i < len2; i++) {
            for (int j = 0; j < len2; j++) {
                Long a = (long) nums2[i];
                Long b = (long) nums2[j];
                dp2[i][j] = a * b;
            }
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                for (int k = j + 1; k < len2; k++) {
                    if (dp1[i][i] == dp2[j][k]){
                        count++;
                    }
                }
            }
        }

        for (int i = 0; i < len2; i++) {
            for (int j = 0; j < len1; j++) {
                for (int k = j + 1; k < len1; k++) {
                    if (dp2[i][i] == dp1[j][k]){
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Q5508_NumTriplets obj = new Q5508_NumTriplets();
        System.out.println(obj.numTriplets(
                new int[]{43024, 99908}, new int[]{1864}
        ));
    }
}
