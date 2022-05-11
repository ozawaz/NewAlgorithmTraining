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
     * 返回1 ~ range 的数字
     */
    public static int randomPositiveNumber(int range) {
        return (int) (Math.random() * range) + 1;
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

    /**
     * 生成随机正整数数组
     * @param maxSize 长度
     * @param maxValue 最大值
     * @return 返回数组
     */
    public static int[] generateRandomPositiveArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomPositiveNumber(maxValue);
        }
        return arr;
    }

    /**
     * 生成随机正整数二维数组
     * @param rowSize 行长度
     * @param colSize 列长度
     * @param maxValue 最大值
     * @return 返回数组
     */
    public static int[][] generateRandomMatrix(int rowSize, int colSize, int maxValue) {
        int[][] matrix = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                matrix[i][j] = randomPositiveNumber(maxValue);
            }
        }
        return matrix;
    }

    /**
     * 构造随机字符串
     * @param strLen 长度
     * @return 返回字符擦混
     */
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    /**
     * 构造随机字符串数组
     * @param arrLen 数组长度
     * @param strLen 字符串长度
     * @return 返回数组
     */
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    /**
     * 构造随机纯数字字符串
     * @param strLen 长度
     * @return 返回字符擦混
     */
    public static String generateRandomNumberString(int strLen) {
        char[] str = new char[strLen];
        for (int i = 0; i < strLen; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }
}
