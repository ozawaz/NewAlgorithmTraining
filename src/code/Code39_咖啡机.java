package code;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 * @since JDK1.8
 */
public class Code39_咖啡机 {

    private static class Machine {
        // 当前时间节点
        public int timePoint;
        // 咖啡机工作完成之后的时间
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }

    private static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    private static PriorityQueue<Machine> initPriorityQueue(int[] arr) {
        // 咖啡工作每次完成时间队列
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        // 将所有咖啡机按照初始时间节点与其工作完成之后的时间添加到堆中
        for (int j : arr) {
            heap.add(new Machine(0, j));
        }
        return heap;
    }

    private static int[] initDrinks(PriorityQueue<Machine> heap, int n) {
        // 所有人喝完之后的杯子
        int[] drinks = new int[n];
        // 根据每台机器工作时间与工作完成之后的时间，计算出最佳的喝咖啡队伍
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            // 将此时的时间节点加上此时咖啡机的工作时间
            assert cur != null;
            cur.timePoint += cur.workTime;
            // 添加此时的时间节点
            drinks[i] = cur.timePoint;
            // 重新计算
            heap.add(cur);
        }
        return drinks;
    }

    public static int minTime1(int[] arr, int n, int a, int b) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 咖啡工作每次完成时间队列
        PriorityQueue<Machine> heap = initPriorityQueue(arr);
        // 所有人喝完之后的杯子
        int[] drinks = initDrinks(heap, n);
        return bestTime(drinks, a, b, 0, 0);
    }

    /**
     * 计算drinks[index.....]都变干净，最早的结束时间
     * @param drinks 所有杯子可以开始洗的时间
     * @param wash 单杯洗干净的时间（串行）
     * @param air 挥发干净的时间(并行)
     * @param index 当前洗的杯子
     * @param free 洗的机器什么时候可用
     * @return drinks[index.....]都变干净，最早的结束时间（返回）
     */
    public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
        // 因为是要与上层取最大值，因此这里的base case取0就行
        if (index == drinks.length) {
            return 0;
        }

        // index号杯子 决定挥发
        // 喝完时间加上挥发时间
        int selfClean1 = drinks[index] + air;
        // 剩余杯子的洗干净时间
        int restClean1 = bestTime(drinks, wash, air, index + 1, free);
        // 两者取最大值
        int p1 = Math.max(selfClean1, restClean1);

        // index号杯子 决定洗
        // 比较喝完咖啡和此时机洗的空闲时间，取最大值加上清洗时间
        int selfClean2 = Math.max(drinks[index], free) + wash;
        // 剩余杯子的洗干净时间，注意将此时的咖啡空闲时间扔进递归中
        int restClean2 = bestTime(drinks, wash, air, index + 1, selfClean2);
        // 两者取最大值
        int p2 = Math.max(selfClean2, restClean2);

        return Math.min(p1, p2);
    }
}
