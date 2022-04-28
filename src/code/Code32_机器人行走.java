package code;

/**
 * @author : zheng.xiong
 * @version : 1.0
 * @date : Created at 2022/04/28
 * @description : 题目描述：
 * 给定一个值n，定义线上有几个点
 * 一个值start，表示机器人的起始位置
 * 一个值aim，表示最终的目标位置
 * 一个值num，必须行走的次数
 * 行走的规则如下：
 * 1. 走到1位置后，必须向右走到2位置
 * 2. 走到n位置后，必须向左走到n-1位置
 * 3. 其他位置可以选择向右还是向左走
 * 4. 机器人不可以停留，必须一直行走
 * 统计所有走法中，最终走到目标位置的次数总和
 */
public class Code32_机器人行走 {

    /**
     * 暴力递归
     *  定义递归的参数，第一个是剩余的步数rest，第二个是当前的其实位置start，第三个是目标位置aim，第四个是长度n。
     * 具体的思路就是，baseCase为当此时的rest=0时，判断此时的start是否等于aim，等于返回1，不等返回0。
     * 然后就是根据题目要求，1位置向右走，n位置向左走，其他位置调用两侧的函数。
     */
    public static int walk1(int n, int start, int aim, int num) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return start == aim ? 1 : 0;
        }
        return process1(num, start, aim, n);
    }

    private static int process1(int rest, int start, int aim, int n) {
        if (rest == 0) {
            return start == aim ? 1 : 0;
        }
        // 1位置向右走
        if (start == 1) {
            return process1(rest - 1, start + 1, aim, n);
        }
        // n位置向左走
        if (start == n) {
            return process1(rest - 1, start - 1, aim, n);
        }
        // 其余位置随便走
        return process1(rest - 1, start - 1, aim, n) + process1(rest - 1, start + 1, aim, n);
    }

    /**
     * 缓存
     * 使用一个二维数组dp[][]，其中行是n+1，列为剩余步数num+1。
     * 然后再每次递归开始之前，判断当前是否有值，有则返回，没有则进行递归。
     * 然后递归的过程需要定义一个int类型的ans，记录递归的返回值，然后递归结束时，使用数组记录这个值，然后返回改值。
     */
    public static int walk2(int n, int start, int aim, int num) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return start == aim ? 1 : 0;
        }
        int[][] dp = new int[n + 1][num + 1];
        // 初始化
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= num; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(num, start, aim, n, dp);
    }

    private static int process2(int rest, int start, int aim, int n, int[][] dp) {
        if (dp[start][rest] != -1) {
            return dp[start][rest];
        }
        int ans;
        if (rest == 0) {
            return start == aim ? 1 : 0;
        }
        // 1位置向右走
        if (start == 1) {
            return process2(rest - 1, start + 1, aim, n, dp);
        }
        // n位置向左走
        if (start == n) {
            return process2(rest - 1, start - 1, aim, n, dp);
        }
        // 其余位置随便走
        ans = process2(rest - 1, start - 1, aim, n, dp) +
                process2(rest - 1, start + 1, aim, n, dp);
        // 记录缓存
        dp[start][rest] = ans;

        return ans;
    }


    public static void main(String[] args) {
        System.out.println("暴力递归：" + walk1(6, 4, 3, 7));
        System.out.println("递归加缓存：" + walk2(6, 4, 3, 7));
    }
}
