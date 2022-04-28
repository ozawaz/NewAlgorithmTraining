package code;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 * @since JDK1.8
 */
public class Code33_分数纸牌 {

    /**
     * 暴力递归
     * 从题目中找线索，可以知道的是，有先手和后手的区别，那么就可以定义两个函数f()和g()，那么参数应该是什么？
     * 可以知道的是，拿牌这个动作，其实就是数组的左右指针向中间移动，
     * 那么参数其实已经出来了，就是左右指针l，r和数组arr，那么basecase是啥？
     * 假如数组的长度是奇数，那么先手的情况下肯定会拿到最后一张牌，即l==r的时候，返回arr[r]，
     * 后手就拿不到最后一张牌，返回0；数组的长度是偶数，
     * 假如剩下最后两张牌，A先手拿走了一张，A就变成后手了，B此时是先手，
     * 只剩下最后一张牌。那么显而易见的是，无论数组的长度是奇数还是偶数，
     * f()和g()的basecase都是一样的。那么就剩下最后一个问题，函数内部的具体处理逻辑是啥？
     * 从题目可知，先手的情况下，可以选择拿左边或者右边的牌，然后变成后手，那么就是：
     * arr[l] + g(l+1, r, arr)和arr[r] + g(l, r-1, arr)中的更大值，这就是f()的逻辑，那么g()的呢？
     * 既然已经是后手了，那么肯定就是只能等对面拿牌，然后自己拿剩下的，那么就是：
     * f(l+1, r, arr)和f(l, r-1, arr)中的更小值，为什么是最小值呢，
     * 因为先手的人一定会将更小的选择留给后手的人。到这里，递归函数已经完全定义完毕，就剩下主函数的定义了。
     * 主函数其实就是分别调用f()和g()，然后取更大值返回就行了。
     */
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        return Math.max(f1(0, arr.length - 1, arr), g1(0, arr.length - 1, arr));
    }

    private static int g1(int l, int r, int[] arr) {
        if (l == r) {
            return 0;
        }
        int p1 = f1(l + 1, r, arr);
        int p2 = f1(l, r - 1, arr);
        return Math.min(p1, p2);
    }

    private static int f1(int l, int r, int[] arr) {
        if (l == r) {
            return arr[r];
        }
        int p1 = arr[l] + g1(l + 1, r, arr);
        int p2 = arr[r] + g1(l, r - 1, arr);
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println("暴力递归：" + win1(arr));
    }
}
