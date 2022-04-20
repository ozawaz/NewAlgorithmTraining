package code;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 给定一个字符串数组，找出所有拼接后的字串串中，字典序最小的一个
 * @since JDK1.8
 */
public class Code12_最小字典序 {

    /**
     * 暴力解法
     * @param strs 字符串数组
     * @return 返回拼接结果
     */
    private static String lowestStringViolence(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }

        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }

        // 循环全排列
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    /**
     * 删除特定位置上的字符串
     * @param strs 字符串数组
     * @param index 位置
     * @return 返回删除的位置
     */
    public static String[] removeIndexString(String[] strs, int index) {
        int n = strs.length;
        String[] ans = new String[n - 1];
        int ansIndex = 0;
        for (int i = 0; i < n; i++) {
            if (i != index) {
                ans[ansIndex++] = strs[i];
            }
        }
        return ans;
    }


    /**
     * 构造比较器
     */
    private static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    private static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }

        // 根据比较器进行排序
        Arrays.sort(strs, new MyComparator());
        // 从头到尾拼接
        StringBuilder res = new StringBuilder("");
        // 循环拼接
        for (String str : strs) {
            res.append(str);
        }

        return res.toString();
    }

    // 构造随机字符串
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // 构造随机字符串数组
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // 复制
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString(arr1).equals(lowestStringViolence(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
