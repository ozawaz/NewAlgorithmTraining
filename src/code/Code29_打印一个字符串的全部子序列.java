package code;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code29_打印一个字符串的全部子序列 {

    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        // 每次选择的结果
        String path = "";
        List<String> ans = new ArrayList<>();
        // i就是选择的次数
        process1(str, 0, ans, path);
        return ans;
    }

    private static void process1(char[] str, int i, List<String> ans, String path) {
        // 判断选择的次数是否已经和原字符串的长度一样
        if (i + 1 == str.length) {
            ans.add(path);
            return;
        }
        // 本位置的字符选择还是不选择
        process1(str, i + 1, ans, path);
        process1(str, i + 1, ans, path + str[i]);
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = subs(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");

    }
}
