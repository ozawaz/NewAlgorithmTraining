package code;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 *
 * 题目的定义：
 *  无序数组中的相邻的值不相同，假如一个值比其相邻的值都小或等于，则称它为局部最小值
 * 目的：找出一个局部最小值的位置
 *
 * 本题目是为了描述二分的意义：
 *  即只要有一个规则可以抛弃一半的数据，就可以二分，即使不是有序的
 */
public class Code01_局部最小值 {

    /**
     * 其解题思路就是，首先判断0位置是否小于1位置，小于则直接给出，否则判断N-1位置
     * 两个位置都判断比其相邻都大时，则有一个两边向中间下降的趋势
     * 然后判断其中心位置，假如比右边大，则可以直接抛弃左边，因为可以知道的是右边有向中间（左边）下降的趋势
     * 现在有向右边下降的趋势，则可以直接抛弃左边
     * 假如相等或小于，直接返回即可
     */
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int left = 1;
        int right = arr.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) >> 1;
            if (arr[mid] > arr[mid - 1]) {
                left = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                right = mid + 1;
            } else {
                return mid;
            }
        }

        return left;
    }
}
