package utils;

/**
 * @author distantstar
 * @version 1.0
 * @date 2021
 * @description basic.sort.utils
 * @since JDK1.8
 */
public class SwapUtil {

    public static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static <T> void swap(T a, T b) {
        T temp = a;
        a = b;
        b = temp;
    }
}
