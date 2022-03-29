package code;

import utils.RandomUtil;
import utils.SwapUtil;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 *
 * 数组中只有一个数出现K次，其他出现M次，找出该数字
 */
public class Code04_一数为K次其他M {

    /**
     * 测试
     */
    public static int test(Integer[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 就算出数组中数组在每位为1的数量，然后将每位1的数字取余m，计算出数字
     */
    public static int findK(Integer[] arr, int k, int m) {
        int[] t = new int[32];
        for (int a : arr) {
            for (int i = 0; i < t.length; i++) {
                // 将此数字在每位为1的个数累加
                t[i] += (a >> i) & 1;
            }
        }

        // 拼出数字
        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                if (t[i] % m == k) {
                    ans |= (1 << i);
                } else {
                    return -1;
                }
            }
        }

        // 假如ans还是为0，判断数组中是否0的个数正好为K
        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }

        return ans;
    }

    public static Integer[] randomArray(int maxKinds, int range, int k, int m) {
        // 生成K数
        int K = RandomUtil.randomNumber(range);
        // set保证每个数不相等
        HashSet<Integer> set = new HashSet<>();
        set.add(K);
        // 结果数组
        Integer[] array = new Integer[k + (maxKinds - 1) * m];
        int index = 0;
        for (; index < k; index++) {
            array[index] = K;
        }
        // 总的数字不同个数 - 1
        int numKinds = (int) (Math.random() * maxKinds) + 1;
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = RandomUtil.randomNumber(range);
            } while (set.contains(curNum));
            // 找到不同数字就加入set，并且种类减一
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                array[index++] = curNum;
            }
        }

        // 将数字的位置打乱
        for (int i = 0; i < array.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * array.length); // 0 ~ N-1
            SwapUtil.swap(array, i, j);
        }

        return array;
    }

    public static void main(String[] args) {
        int kinds = 5;
        int range = 30;
        int testTime = 100000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }
            Integer[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = findK(arr, k, m);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
