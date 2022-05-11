package code.oneToForty;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 * @since JDK1.8
 */
public class Code17_项目最大利润 {

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }

    private static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 构建小根堆，按照最小花费排序
        PriorityQueue<Program> minCostQueue = new PriorityQueue<>(new MinCostComparator());
        // 构建大根堆，按照最大利润排序
        PriorityQueue<Program> maxProfitQueue = new PriorityQueue<>(new MaxProfitComparator());

        // 初始小根堆
        for (int i = 0; i < profits.length; i++) {
            minCostQueue.add(new Program(profits[i], capital[i]));
        }
        // 循环做项目
        for (int i = 0; i < k; i++) {
            // 将此时所有能做的项目添加到大根堆
            while (!minCostQueue.isEmpty() && minCostQueue.peek().c <= w) {
                maxProfitQueue.add(minCostQueue.poll());
            }
            // 假如大根堆没有，则证明资金不足以做任何项目
            if (maxProfitQueue.isEmpty()) {
                return w;
            }
            w += maxProfitQueue.poll().p;
        }

        return w;
    }
}
