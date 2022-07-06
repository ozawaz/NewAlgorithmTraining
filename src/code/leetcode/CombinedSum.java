package code.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code.leetcode
 * 给你一个无重复元素的整数数组candidates和一个目标整数target，
 * 找出candidates中可以使数字和为目标数target的所有不同组合，并以列表形式返回。你可以按任意顺序返回这些组合。
 * candidates中的同一个数字可以无限制重复被选取。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since JDK1.8
 */
public class CombinedSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new LinkedList<>();
        combine(lists, candidates, new LinkedList<>(), target, 0);
        return lists;
    }

    /**
     *
     * @param lists 结果集合
     * @param candidates 排列好的数组
     * @param list 本次组合
     * @param rest 剩余的和
     * @param start 数组开始的索引
     */
    private void combine(List<List<Integer>> lists, int[] candidates, List<Integer> list, int rest, int start) {
        if (rest < 0) {
            return;
        }
        if (rest == 0) {
            // 保证每次存入的都是新对象
            lists.add(new LinkedList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 回溯递归
            list.add(candidates[i]);
            combine(lists, candidates, list, rest - candidates[i], i);
            list.remove((Integer)candidates[i]);
        }
    }
}
