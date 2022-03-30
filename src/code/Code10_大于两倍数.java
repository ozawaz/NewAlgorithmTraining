package code;

import utils.CommonUtil;
import utils.RandomUtil;

/**
 * @author : zheng.xiong
 * @version : 1.0
 * @date : Created at 2022/03/30
 * @description : code
 *
 * 求数组中一个数比它后面的数*2还要大的个数
 */
public class Code10_大于两倍数 {

    public static int biggerThanDouble(int[] arr) {
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

        int widow = r;
        for (int i = mid; i >= l; i--) {
            // 假如此时左组的数不比右边大，右指针向左移动
            while (widow >= mid + 1 && arr[i] <= arr[widow] * 2) {
                widow--;
            }
            res += widow - mid;
        }

        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
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
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxTimes = 100000;
        int maxSize = 100;
        int maxValue = 1000;
        boolean success = true;
        for (int i = 0; i < maxTimes; i++) {
            int[] arr1 = RandomUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = CommonUtil.copyArray(arr1);
            if (biggerThanDouble(arr1) != comparator(arr2)) {
                success = false;
                CommonUtil.printArray(arr1);
                CommonUtil.printArray(arr2);
                break;
            }
        }
        System.out.println(success ? "成功" : "失败");
    }
}
