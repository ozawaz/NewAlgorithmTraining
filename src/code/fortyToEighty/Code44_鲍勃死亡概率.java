package code.fortyToEighty;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 * @since JDK1.8
 */
public class Code44_鲍勃死亡概率 {

    public static double livePossibility(int row, int col, int k, int n, int m) {
        return (double) process(row, col, k, n, m) / Math.pow(4, k);
    }

    public static long process(int row, int col, int rest, int n, int m) {
        // 超出边界，即死亡
        if (row < 0 || row == n || col < 0 || col == m) {
            return 0;
        }
        // 还在棋盘中！
        if (rest == 0) {
            return 1;
        }

        // 四个方向的存活方案
        long up = process(row - 1, col, rest - 1, n, m);
        long down = process(row + 1, col, rest - 1, n, m);
        long left = process(row, col - 1, rest - 1, n, m);
        long right = process(row, col + 1, rest - 1, n, m);
        return up + down + left + right;
    }

    public static double livePossibilityDp(int row, int col, int k, int n, int m) {
        // 三维dp数组
        int[][][] dp = new int[n][m][k + 1];
        // 所有还在区域内，剩余步数为0的初始值为1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0]=  1;
            }
        }
        // 循环
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    dp[r][c][rest] = pick(dp, n, m, r - 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, n, m, r + 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, n, m, r, c - 1, rest - 1);
                    dp[r][c][rest] += pick(dp, n, m, r, c + 1, rest - 1);
                }
            }
        }

        return dp[row][col][k] / Math.pow(4, k);
    }

    private static int pick(int[][][] dp, int n, int m, int row, int col, int rest) {
        if (row < 0 || row == n || col < 0 || col == m) {
            return 0;
        }
        return dp[row][col][rest];
    }

    public static void main(String[] args) {
        System.out.println(livePossibility(6, 6, 10, 50, 50));
        System.out.println(livePossibilityDp(6, 6, 10, 50, 50));
    }
}
