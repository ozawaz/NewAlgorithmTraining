package code.oneToForty;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1=“a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * @since JDK1.8
 */
public class Code36_最长公共子序列 {

    /**
     * 这个问题可以变成的是str1[0...j]与str2[0...i]的最长公共子序列，
     * 确定好尝试思路，就可以直接开始编写代码，错误了再更改。
     * 有了具体的尝试思路之后，那么就可以想一下递归应该怎么写，
     * 假如i与j都为0，那么此时就判断此时的字符是否相等，相等返回长度1，反之返回0。
     * 然后假如i为0，那么就变成一个字符与一个字符串进行比较了，
     * 判断此时的字符是否相等，不等，则遍历另一个字符串，j为0同理。
     * 都不为0，那么就有三种情况：
     * ● 判断i，不判断j；
     * ● 不判断i，判断j；
     * ● 都保留或者都不判断；
     * 将这三种结果返回最大值。
     */
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 尝试
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    private static int process1(char[] str1, char[] str2, int i, int j) {
        // 假如i与j都为0，那么此时就判断此时的字符是否相等，相等返回长度1，反之返回0。
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ?  1 : 0;
        } else if (i == 0) {
            // 然后假如i为0，那么就变成一个字符与一个字符串进行比较了，
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            // 然后假如j为0，那么就变成一个字符与一个字符串进行比较了，
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i - 1, j);
            }
        } else {
            // 都不为0，那么就有三种情况：
            //     * ● 判断i，不判断j；
            //     * ● 不判断i，判断j；
            //     * ● 都保留或者都不判断；
            //     * 将这三种结果返回最大值。
            int p1 = process1(str1, str2, i - 1, j);
            int p2 = process1(str1, str2, i, j - 1);
            // 这里这里做的是一个都判断和都不判断的融合操作
            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n][m];
        // 递归条件1
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        // 递归条件2
        for (int j = 1; j < m; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        // 递归条件3
        for (int i = 1; i < n; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        // 递归条件4
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] =  Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[n - 1][m - 1];
    }
}
