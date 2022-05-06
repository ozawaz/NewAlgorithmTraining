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
}
