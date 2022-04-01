package code;

/**
 * @author : zheng.xiong
 * @version : 1.0
 * @date : Created at 2022/04/01
 * @description : code
 */
public class Code13_桶排序 {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, arr.length - 1, maxBits(arr));
    }

    /**
     * 找到最大位数
     */
    public static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            max = Math.max(max, j);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    /**
     * 获取当前位上的数
     * @param x 判断的数字
     * @param d 位
     * @return 返回数字
     */
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    private static void radixSort(int[] arr, int r, int digit) {

        /**
         * 思路：
         *  将当前需要判断的位置的不同数的数量累加
         *  然后累加数量做一个累加和
         *  然后根据累加和的位置填充每个一位置
         */

        final int radix = 10;
        // 桶
        int[] help = new int[r + 1];
        int i, j;
        // 根据最高的位数，需要循环几次
        for (int d = 1; d <= digit; d++) {
            // 数量累加数组
            int[] count = new int[radix];
            for (i = 0; i <= r; i++) {
                // 对应数字数量++
                count[getDigit(arr[i], d)]++;
            }
            // 累加和
            for (i = 1; i <= radix; i++) {
                count[i] += count[i - 1];
            }
            // 倒入桶中(从右往左)
            for (i = r; i >= 0; i--) {
                // 然后根据累加和的位置填充每个一位置
                help[--count[getDigit(arr[i], d)]] = arr[i];
            }
            // 倒回原数组
            for (i = 0, j = 0; i <= r; i++, j++) {
                arr[i] = help[j];
            }
        }
    }
}
