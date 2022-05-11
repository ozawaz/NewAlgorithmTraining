package code.fortyToEighty;

import utils.CommonUtil;
import utils.RandomUtil;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 * @since JDK1.8
 */
public class Code41_货币组成金额 {

    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        // 超出了组装的金额
        if (rest < 0) {
            return 0;
        }
        // base case
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
    }

    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        // 长度
        int n = arr.length;
        // dp数组
        int[][] dp = new int[n + 1][aim + 1];
        // 根据递归可知初始条件
        dp[n][0] = 1;
        // 遍历
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }

        return dp[0][aim];
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = RandomUtil.generateRandomPositiveArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
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
