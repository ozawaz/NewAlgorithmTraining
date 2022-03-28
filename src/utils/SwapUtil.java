package utils;

/**
 * @author distantstar
 * @version 1.0
 * @date 2021
 * @description basic.sort.utils
 * @since JDK1.8
 */
public class SwapUtil {

    public static <E> void swap(E[] arr, int a, int b) {
        E temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
