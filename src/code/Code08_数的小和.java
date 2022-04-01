package code;

import utils.CommonUtil;
import utils.RandomUtil;

/**
 * @author : zheng.xiong
 * @version : 1.0
 * @date : Created at 2022/03/29
 * @description : code
 *
 * 一个数左边比它所有小的数的和叫数的小和，加起来叫数组的小和
 */
public class Code08_数的小和 {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return merge(arr, 0, arr.length - 1);
    }

    public static int merge(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }

        int mid = l + ((r - l) >> 1);
        return merge(arr, l, mid) +
               merge(arr, mid + 1, r) +
               mergeSort(arr, l, mid, r);
    }

    public static int mergeSort(int[] arr, int l, int mid, int r) {
        int res = 0;
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l, p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            res += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l++] = help[i];
        }

        return res;
    }

    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int maxTimes = 10000;
        int maxSize = 100;
        int maxValue = 1000;
        boolean success = true;
        for (int i = 0; i < maxTimes; i++) {
            int[] arr1 = RandomUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = CommonUtil.copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                success = false;
                CommonUtil.printArray(arr1);
                CommonUtil.printArray(arr2);
                break;
            }
        }
        System.out.println(success ? "成功" : "失败");
    }
}
