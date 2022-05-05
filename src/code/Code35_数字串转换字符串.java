package code;

import utils.RandomUtil;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * @since JDK1.8
 */
public class Code35_数字串转换字符串 {

    public static int number(String str) {
        if (str == null || str.length() == 0 || str.charAt(0) == '0') {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    public static int process(char[] str, int i) {
        // 假如此时的索引位置已经走完了，证明之前并没有出现错误
        if (i == str.length) {
            return 1;
        }
        // 假如此时的字符是'0'，可知没有字母与0相对，证明此路不通
        if (str[i] == '0') {
            return 0;
        }
        // 当前字符直接转换的结果
        int ways = process(str, i + 1);
        // 判断与下一个字符拼接的条件
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
            ways += process(str, i + 2);
        }
        return ways;
    }

    public static int dp1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 其实就是将上面的递归改成了迭代的方式，逻辑基本一样
        char[] str = s.toCharArray();
        // 这里就是做一个判断
        if (str[0] == '0') {
            return 0;
        }
        int n = str.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (str[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = RandomUtil.generateRandomNumberString(len);
            int ans0 = number(s);
            int ans1 = dp1(s);
            if (ans0 != ans1) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
