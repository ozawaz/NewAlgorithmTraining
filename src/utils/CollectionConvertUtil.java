package utils;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description utils
 * @since JDK1.8
 */
public class CollectionConvertUtil {

    public static Character[] characterConvert(char[] chars) {
        Character[] characters = new Character[chars.length];
        int i = 0;
        for (char c : chars) {
            characters[i++] = c;
        }
        return characters;
    }
}
