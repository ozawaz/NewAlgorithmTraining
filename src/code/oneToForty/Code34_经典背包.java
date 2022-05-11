package code.oneToForty;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 * @since JDK1.8
 */
public class Code34_经典背包 {

    /**
     * 从左到右依次尝试，判断当前位置的物品装填还是不装填，返回一个最大的价值。
     */
    public static int knapsack1(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process1(w, v, 0, bag);
    }

    private static int process1(int[] w, int[] v, int index, int rest) {
        // 假如背包已经超重了，证明上一个装的物品不行
        if (rest < 0) {
            return -1;
        }
        // 已经没有物品装了
        if (index == w.length) {
            return 0;
        }
        int p1 = process1(w, v, index + 1, rest);
        int p2 = 0;
        int next = process1(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    public static int knapsackDp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }

        // 物品数组长度
        int length = w.length;
        int[][] dp = new int[length + 1][bag + 1];
        // 由递归定义可知，最后一行即dp[length][0..bag]全为0，因此直接从dp[length-1]开始遍历
        for (int index = length - 1; index >= 0; index--) {
            // 背包的剩余容量依次遍历
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        // 根据递归的初始调用，可以知道这里返回的是这个
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println("暴力递归：" + knapsack1(weights, values, bag));
        System.out.println("动态规划：" + knapsackDp(weights, values, bag));
    }
}
