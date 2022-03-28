package code;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code02_简单的异或题目 {

    /**
     * 不用临时值交换两个数字
     */
    public static void swapNoTemp(int a, int b) {
        a = a ^ b;
        b = a ^ b; // a ^ b ^ b = a
        a = a ^ b; // a ^ b ^ a = b
    }

    /**
     * 统计位为1的个数
     */
    public static int countBitOne(int a) {
        int count = 0;
        while (a != 0) {
            // 将最右边的1找出
            int rightOne = a & ((~a) + 1);
            count++;
            // 抹去最右边的1
            a ^= rightOne;
        }
        return count;
    }
}
