package code;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 * @since JDK1.8
 */
public class Code_18点灯 {

    private static int minLight(String road) {
        // 字符数组
        char[] chars = road.toCharArray();
        // 灯的数量
        int light = 0;
        // 位置
        int i = 0;
        // 长度
        int length = chars.length;
        // ● 假如是'X'，前往i+1，灯数量不变；
        // ● 假如是'.'：
        //  ○ i+1是'X'，灯数量加1，前往i+2；
        //  ○ i+1是'.'：
        //    ■ i+2是'X'，灯数量加1，前往i+3；
        //    ■ i+2是'.'，灯数量加1，前往i+3；
        while (i < length) {
            if (chars[i] == 'X') {
                i++;
            } else {
                light++;
                if (i + 1 == length) {
                    break;
                } else {
                    if (chars[i + 1] == 'X') {
                        i += 2;
                    } else {
                        i += 3;
                    }
                }
            }
        }
        return light;
    }
}
