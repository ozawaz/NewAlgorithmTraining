package utils;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description utils
 * @since JDK1.8
 */
public class RandomUtil {

    /**
     * 返回-range ~ range 的数字
     */
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    /**
     * 生成随机数组
     * @param maxSize 长度
     * @param maxValue 最大值
     * @return 返回数组
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomNumber(maxValue);
        }
        return arr;
    }
}
