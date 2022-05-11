package code;

import utils.RandomUtil;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 * @since JDK1.8
 */
public class Code40_最短路径和 {

    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }

        // 长度
        int row = m.length;
        int col = m[0].length;

        // dp数组
        int[][] dp = new int[row][col];
        // 初始化左上角
        dp[0][0] = m[0][0];
        // 计算第一行的和
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        // 计算第一列的和
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        // 计算剩余的格子的值
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // 比较左，上两格子，取小值，再加上本格子记得值
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }

    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }

        // 长度
        int row = m.length;
        int col = m[0].length;

        // dp数组
        int[] dp = new int[col];
        // 初始化第一个格子的值
        dp[0] = m[0][0];
        // 计算第一行的值
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j];
        }
        // 遍历
        for (int i = 1; i < row; i++) {
            // 最左列先初始化
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                // 将已经计算好的左值与本格子的值比较，取小值加上m格子的值
                dp[j] = Math.min(dp[j], dp[j - 1]) + m[i][j];
            }
        }
        return dp[col - 1];
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int maxValue = 100;
        int[][] m = RandomUtil.generateRandomMatrix(rowSize, colSize, maxValue);
        System.out.println("动态规划：" + minPathSum1(m));
        System.out.println("数组压缩：" + minPathSum2(m));
    }
}
