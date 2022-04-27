package code;

import utils.CollectionConvertUtil;
import utils.SwapUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code31_打印一个字符的不重复全排列 {
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        Character[] chars = CollectionConvertUtil.characterConvert(s.toCharArray());
        g1(chars, 0, ans);
        return ans;
    }

    public static void g1(Character[] str, int start, List<String> ans) {
        if (start == str.length) {
            ans.add(Arrays.toString(str));
        }
        // 校验数组，判断该字符是否当前已经判断过
        byte[] visited = new byte[256];
        // 依次交换字符
        for (int i = start; i < str.length; i++) {
            if (visited[str[i]] == 0) {
                visited[str[i]] = 1;
                SwapUtil.swap(str, start, i);
                g1(str, start + 1, ans);
                SwapUtil.swap(str, start, i);
            }
        }
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
    }
}
