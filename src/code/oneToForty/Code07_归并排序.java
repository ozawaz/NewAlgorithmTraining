package code.oneToForty;

import utils.CommonUtil;
import utils.RandomUtil;

/**
 * @author : zheng.xiong
 * @version : 1.0
 * @date : Created at 2022/03/29
 * @description : code
 */
public class Code07_归并排序 {

    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        merge(arr, 0, arr.length - 1);
    }

    public static void merge(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }

        int mid = l + ((r - l) >> 1);
        merge(arr, l, mid);
        merge(arr, mid + 1, r);
        mergeSort(arr, l, mid, r);
    }

    public static void mergeSort(int[] arr, int l, int mid, int r) {
        // 临时数组
        int[] help = new int[r-  l + 1];
        // 临时数组索引
        int i = 0;
        // 指针
        int p1 = l, p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 越界问题
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        // 结果倒回数组
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    /**
     * 非递归版本，设计一个步长，每次merge时就merger左组和右组（长度等于步长），假如数组剩余长度不够，就不处理
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        // 步长
        int mergeSize = 1;
        while (mergeSize < N) {
            int l = 0;
            while (l < N) {
                if (mergeSize >= N - l) {
                    break;
                }
                // 中点位置
                int mid = l + mergeSize - 1;
                // 剩余长度不够
                int r = Math.min(mid + mergeSize, N - 1);
                mergeSort(arr, l, mid, r);
                // 下一组的起始位置
                l  = r + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = RandomUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = CommonUtil.copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!CommonUtil.isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                CommonUtil.printArray(arr1);
                CommonUtil.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
