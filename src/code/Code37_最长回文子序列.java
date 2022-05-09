package code;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code37_最长回文子序列 {

    public int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str1 = s.toCharArray();
        StringBuilder buf = new StringBuilder(s);
        char[] str2 = buf.reverse().toString().toCharArray();

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

    /***
     * 回归到本题的递归思路。本题可以变成求str[i...j]的最长回文子序列，那么就开始规定base case。
     * 当i==j，此时就只剩下一个字符，直接返回1，
     * 剩下两个字符，即i==j-1或者i+1==j，两者没啥区别，合并成一个即可，
     * 判断此时str[i]==str[j-1]，相同返回2，u否则1。
     * 后面的就是四种情况了。
     */
    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    private static int f(char[] str, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return str[l] == str[r] ? 2 : 1;
        }

        int p1 = f(str, l + 1, r);
        int p2 = f(str, l, r - 1);
        int p3 = f(str, l + 1, r - 1);
        // 这里肯定不能是直接的l，r，所以做了一个判断
        int p4 = str[l] == str[r] ? (2 + f(str, l + 1, r - 1)) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        // 这里将递归的条件1和条件2做一个合并操作
        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1;
            // 这里的i+1，其实就是l==r-1的条件
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        // 这里根据返回的结果，可以知道是需要由下到上
        // 而这里的起始条件，根据画图就可知
        for (int l = n - 3; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                // 可以知道，此位置不会比其他三个位置小，而左下位置会被下位置的左和左位置的下所比较
                // 所以不需要判断左下位置
                dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                if (str[l] == str[r]) {
                    dp[l][r] = Math.max(dp[l][r], 2 + dp[l + 1][r - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
