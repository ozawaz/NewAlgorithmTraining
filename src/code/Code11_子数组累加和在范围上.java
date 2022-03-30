package code;

import utils.CommonUtil;
import utils.RandomUtil;

/**
 * @author : zheng.xiong
 * @version : 1.0
 * @date : Created at 2022/03/30
 * @description : code
 *
 * https://leetcode.com/problems/count-of-range-sum/
 * 给定一个数组arr，两个整数lower和upper，回arr中有多少个子数组的累加和在[lower,upper]范围上
 */
public class Code11_子数组累加和在范围上 {
    /**
     * 思路就是：
     *  做出一个前缀和数组，然后用这个数组（和数组）进行判断
     *  现在就以每个位置的结尾作为每个子数组的右边界，然后依次从左往右判断
     *  假设 0-6位置的前缀和为100，目标范围是[10, 40]，那么假如0位置为70，则可知1-6位置总和为30，符合条件
     *  那么现在问题就变成了，0-i位置的前缀和是否在[(0-j)-40, (0-j)-10]这个区间上，在的话，证明i-j这个子数组符合条件
     *  那么问题就变成了右边的数减去左边是否在一个范围里面的问题了，那么就可以使用归并排序了
     */
    public static int countRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 计算出前缀和
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i-1] + arr[i];
        }

        return merge(sum, 0, sum.length - 1, lower, upper);
    }

    public static int merge(long[] sum, int l, int r, int lower, int upper) {
        if (l == r) {
            return sum[l] >= lower && sum[r] <= upper ? 1 : 0;
        }

        int mid = l + ((r - l) >> 1);
        return merge(sum, l, mid, lower, upper) +
                merge(sum, mid + 1, r, lower, upper) +
                mergeSort(sum, l, mid, r, lower, upper);
    }

    public static int mergeSort(long[] sum, int l, int mid, int r, int lower, int upper) {
        int res = 0;
        int windowL = l, windowR = l;
        for (int i = mid + 1; i <= r; i++) {
            // 计算出最大值和最小值
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            // 计算窗口的区间
            while (windowR <= mid && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= mid && sum[windowL] < min) {
                windowL++;
            }
            res += windowR - windowL;
        }

        long[] help = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = sum[p1] < sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= mid) {
            help[i++] = sum[p1++];
        }
        while (p2 <= r) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[l++] = help[i];
        }

        return res;
    }

    public static int comparator(int[] arr, int lower, int upper) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            long sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (lower <= sum && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int maxTimes = 100000;
        int maxSize = 100;
        int maxValue = 1000;
        boolean success = true;
        for (int i = 0; i < maxTimes; i++) {
            int[] arr1 = RandomUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = CommonUtil.copyArray(arr1);
            int lower = RandomUtil.randomNumber(maxValue / 10);
            int upper;
            do {
                upper = RandomUtil.randomNumber(maxValue);
            } while (upper <= lower);
            if (countRangeSum(arr1, lower, upper) != comparator(arr2, lower, upper)) {
                success = false;
                CommonUtil.printArray(arr1);
                CommonUtil.printArray(arr2);
                break;
            }
        }
        System.out.println(success ? "成功" : "失败");
    }
}
