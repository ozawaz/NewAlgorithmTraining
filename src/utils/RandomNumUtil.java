package utils;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description utils
 * @since JDK1.8
 */
public class RandomNumUtil {

    /**
     * 返回-range ~ range 的数字
     */
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }
}
