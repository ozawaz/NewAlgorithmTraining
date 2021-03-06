package code.oneToForty;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。返回最多的宣讲场次。
 * 即{{1,2}, {3,4},{3,6}}，选出最长可连续的数组
 * @since JDK1.8
 */
public class Code15_最多会议 {

    private static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrangeViolence(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    /**
     * 还剩下的会议都放在programs里
     * done之前已经安排了多少会议的数量
     * timeLine目前来到的时间点是什么
     * 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
     * 返回能安排的最多会议数量
     */
    public static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        // 还剩下会议
        int max = done;
        // 当前安排的会议是什么会，每一个都枚举
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] next = copyButExcept(programs, i);
                max = Math.max(max, process(next, done + 1, programs[i].end));
            }
        }
        return max;
    }

    public static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
    }

    /**
     * 构造比较器
     */
    private static class MyComparator implements Comparator<Program> {
        @Override
        public int compare(Program a, Program b) {
            return a.end - b.end;
        }
    }

    public static int bestArrangeGreedy(Program[] programs) {
        // 排序
        Arrays.sort(programs, new MyComparator());
        // 结束时间
        int timeLine = 0;
        // 会议次数
        int res = 0;
        // 循环
        for (Program program : programs) {
            if (timeLine <= program.start) {
                timeLine = program.end;
                res++;
            }
        }
        return res;
    }

    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrangeViolence(programs) != bestArrangeGreedy(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
