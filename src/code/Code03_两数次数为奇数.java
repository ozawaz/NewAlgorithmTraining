package code;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 *
 * 数组中有两个数出现的次数为奇数，其余为偶数，找出它们
 */
public class Code03_两数次数为奇数 {

    /**
     * 思路就是，首先将所有数字都异或一边，就会得到奇数次数的异或结果
     * 然后提取出最右边的1，再与数组中值&，假如有值为0，证明找到一个数
     * 其原因就是异或为1，则证明两数在此位上一个为1，一个为0
     */
    public static void printOddTimesNum2(int[] arr) {
        // 异或结果
        int eor = 0;
        for (int a : arr) {
            eor ^= a;
        }

        // 取出最右边的1
        int rightOne = eor & ((~eor) + 1);

        int result1 = 0;
        // 找到一个数
        for (int a : arr) {
            if ((a & rightOne) == 0) {
                result1 = eor ^ a;
                break;
            }
        }
        System.out.println(result1 + "   " + (eor ^ result1));
    }
}
