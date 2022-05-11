package code.fortyToEighty;

import utils.CommonUtil;
import utils.RandomUtil;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code.fortyToEighty
 * @since JDK1.8
 */
public class Code42_无限面值组成金额 {

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        // 方法数记录
        int ways = 0;
        // 依次提高本面值纸币的使用次数
        for (int i = 0; arr[index] * i <= rest; i++) {
            ways += process(arr, index + 1, rest - arr[index] * i);
        }
        return ways;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 长度
        int n = arr.length;
        // dp数组
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        // 遍历
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 方法数记录
                int ways = 0;
                // 依次提高本面值纸币的使用次数
                for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
                    ways += dp[index + 1][rest - arr[index] * zhang];
                }
                dp[index][rest] = ways;
            }
        }

        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 长度
        int n = arr.length;
        // dp数组
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        // 遍历
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 此时格子的值依赖于下层格子的值
                dp[index][rest] = dp[index + 1][rest];
                // 判断是否能继续
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = RandomUtil.generateRandomPositiveArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                CommonUtil.printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
